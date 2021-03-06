package com.nhom2.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
@RequestMapping("/")
public class ThongKeController {
	@Autowired
	SessionFactory factory;

	@ModelAttribute("loaiThongkes")
	public List<String> getLoaiThongke() {
		List<String> loaiThongkes = Arrays.asList("Phiếu Mượn", "Thiết Bị", "Loại Người Mượn");
		return loaiThongkes;
	}

	@RequestMapping("thong-ke")
	public String home(ModelMap model) {

		return "thong_ke/thong_ke";
	}

	@RequestMapping(value = "thong-ke", method = RequestMethod.POST)
	public String thongke(@RequestParam("loaiThongke") String loaiThongke,
			@RequestParam("ngaybatdau") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaybatdau,
			@RequestParam("ngayketthuc") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayketthuc, ModelMap model) {
		final String case1 = "Phiếu Mượn";
		final String case2 = "Thiết Bị";
		final String case3 = "Loại Người Mượn";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		List<Object[]> kqThongke = null;
		String hql = "";
		switch (loaiThongke) {
		// Nếu là Phiếu Mượn
		case case1: {
			kqThongke = getKqThongkePM(ngaybatdau, ngayketthuc);
			break;
		}

		// Nếu là Thiết Bị
		case case2: {
			kqThongke = getKqThongkeTB(ngaybatdau, ngayketthuc);
			break;
		}

		// Nếu là Người Mượn
		case case3: {
			kqThongke = getKqThongkeNM(ngaybatdau, ngayketthuc);
			break;
		}

		default:
			model.addAttribute("error", true);
			model.addAttribute("msg", "Loại thiết bị không xác định !!!");
			break;

		}

		model.addAttribute("loaiThongke", loaiThongke);
		model.addAttribute("ngaybatdau", formatter.format(ngaybatdau));
		model.addAttribute("ngayketthuc", formatter.format(ngayketthuc));

		model.addAttribute("kqThongke", kqThongke);

		return home(model);
	}

	private List<Object[]> getKqThongkeNM(Date ngaybatdau, Date ngayketthuc) {
		List<Object[]> listKq = new ArrayList<Object[]>();
		Session session;
		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}

		Object[] kq = { null, null };

		String hql = "select nm.loai, count(*) from PHIEUMUON "
				+ "where thoigianmuon between :ngaybatdau and :ngayketthuc " + "group by nm.loai";
		Query<Object[]> q = session.createQuery(hql);
		q.setParameter("ngaybatdau", ngaybatdau);
		q.setParameter("ngayketthuc", ngayketthuc);

		for (Object[] row : q.list()) {
			listKq.add(row);
		}
		return listKq;
	}

	// KẾT QUẢ THỐNG KÊ PHIẾU MƯỢN
	public List<Object[]> getKqThongkePM(Date ngaybatdau, Date ngayketthuc) {
		List<Object[]> listKq = new ArrayList<Object[]>();
		Session session;
		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "select thoigianmuon, thoigiantra, count(*) from PHIEUMUON "
				+ "where thoigianmuon between :ngaybatdau and :ngayketthuc ";

		// Cho thời gian chạy từ ngày bắt đầu - ngày kết thúc, tăng dần 1 ngày
		// 86400000 ms = 1 ngày = 24h * 60p * 60s * 1000ms
		for (long i = ngaybatdau.getTime(); i <= ngayketthuc.getTime(); i += 86400000) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			System.out.println(formatter.format(i));
			Object[] kq = { null, 0, 0 };

			/* Kiểm tra từng ngày thứ [i] xem có phiếu mượn CHƯA TRẢ nào phát sinh không */
			hql = "select thoigianmuon, count(*) from PHIEUMUON " + "where thoigianmuon = :thoigianmuon "
					+ "and thoigiantra is null " + "group by thoigianmuon";
			Query<Object[]> q = session.createQuery(hql);
			q.setParameter("thoigianmuon", new Date(i));

			if (!q.list().isEmpty()) {
				kq[0] = formatter.format(i); // Ngay
				kq[1] = q.list().get(0)[1]; // soluong muon
			}

			/* Kiểm tra từng ngày thứ [i] xem có phiếu mượn ĐÃ TRẢ nào phát sinh không */
			hql = "select thoigiantra, count(*) from PHIEUMUON " + "where thoigiantra = :thoigiantra "
					+ "and thoigiantra is not null " + "group by thoigiantra";
			q = session.createQuery(hql);
			q.setParameter("thoigiantra", new Date(i));

			if (!q.list().isEmpty()) {
				kq[0] = formatter.format(i); // Ngay
				kq[2] = q.list().get(0)[1]; // soluong tra

			}

			/*
			 * Nếu ngày thứ [i] phát sinh phiếu mượn chưa trả hoặc đã trả 
			 * sẽ thêm vào danh sách KQ
			 */
			if (kq[0] != null) {
				listKq.add(kq);
			}
		}

		return listKq;
	}

//	KẾT QUẢ THỐNG KÊ THIẾT BỊ
	public List<Object[]> getKqThongkeTB(Date ngaybatdau, Date ngayketthuc) {
		List<Object[]> listKq = new ArrayList<Object[]>();
		Session session;
		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "";

		for (long i = ngaybatdau.getTime(); i <= ngayketthuc.getTime(); i += 86400000) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			System.out.println(formatter.format(i));
			Object[] kq = { null, 0, 0 };

			/* THIẾT BỊ NHẬP VỀ */
			hql = "select pn.thoigiannhap, sum(ct_pn.soluongnhap) from CT_PHIEUNHAP ct_pn, PHIEUNHAP pn "
					+ "where ct_pn.phieunhap.mapn = pn.mapn " + "and pn.trangthai = 'daXacNhan' "
					+ "and pn.thoigiannhap = :thoigiannhap " + "group by pn.thoigiannhap";
			Query<Object[]> listPn = session.createQuery(hql);
			listPn.setParameter("thoigiannhap", new Date(i));

			if (!listPn.list().isEmpty()) {
				kq[0] = formatter.format(new Date(i)); // Ngày
				kq[1] = listPn.list().get(0)[1]; // soluong nhập về
			}

			/* THIẾT BỊ BỊ THANH LÝ (HỎNG) */
			hql = "select ptl.thoigian, sum(ct_ptl.soluong) from CT_PHIEUTHANHLY ct_ptl, PHIEUTHANHLY ptl "
					+ "where ct_ptl.phieuthanhly.maptl = ptl.maptl " + "and ptl.trangthai = 'daXacNhan' "
					+ "and ptl.thoigian = :thoigian " + "group by ptl.thoigian";
			Query<Object[]> listPtl = session.createQuery(hql);
			listPtl.setParameter("thoigian", new Date(i));

			if (!listPtl.list().isEmpty()) {
				kq[0] = formatter.format(new Date(i));// Ngày
				kq[2] = listPtl.list().get(0)[1]; // soluong thanh lý
			}
			
			/* Ngày thứ [i] phát sinh nhập hoặc thanh lý sẽ thêm vào danh sách */
			if (kq[0] != null) {
				listKq.add(kq);
			}

		}
		return listKq;
	}

}
