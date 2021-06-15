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

import com.nhom2.entity.CT_PHIEUNHAP;
import com.nhom2.entity.CT_PHIEUTHANHLY;
import com.nhom2.entity.PHIEUNHAP;
import com.nhom2.entity.PHIEUTHANHLY;

@Transactional
@Controller
@RequestMapping("/")
public class ThongKeController {
	@Autowired
	SessionFactory factory;

	@ModelAttribute("loaiThongkes")
	public List<String> getLoaiThongke() {
		List<String> loaiThongkes = Arrays.asList("Phiếu Mượn", "Thiết Bị");
		return loaiThongkes;
	}

	@ModelAttribute("optionPM")
	public List<String> getOptionPM() {
		List<String> optionPM = Arrays.asList("Tất cả", "Đã trả", "Chưa trả");
		return optionPM;
	}

	@ModelAttribute("optionTB")
	public List<String> getOptionTB() {
		List<String> optionPM = Arrays.asList("Nhập về", "Hư hỏng");
		return optionPM;
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
		final String case3 = "Phiếu Nhập";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		List<Object[]> kqThongke = null;
		String hql = "";
		List<String> options = getOptionTB();
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

		default:
			model.addAttribute("error", true);
			model.addAttribute("msg", "Loại thiết bị không xác định !!!");
			break;

		}

		model.addAttribute("loaiThongke", loaiThongke);
		model.addAttribute("options", options);
		model.addAttribute("ngaybatdau", formatter.format(ngaybatdau));
		model.addAttribute("ngayketthuc", formatter.format(ngayketthuc));

		model.addAttribute("kqThongke", kqThongke);

		return home(model);
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

		for (long i = ngaybatdau.getTime(); i <= ngayketthuc.getTime(); i += 86400000) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			System.out.println(formatter.format(i));
			Object[] kq = { "", null, null };
			kq[0] = formatter.format(i); // Ngay

			hql = "select thoigianmuon, count(*) from PHIEUMUON " + "where thoigianmuon = :thoigianmuon "
					+ "and thoigiantra is null " + "group by thoigianmuon";
			Query<Object[]> q = session.createQuery(hql);
			q.setParameter("thoigianmuon", new Date(i));

			if (!q.list().isEmpty()) {
				kq[1] = q.list().get(0)[1]; // soluong muon
			}

			hql = "select thoigiantra, count(*) from PHIEUMUON " + "where thoigiantra = :thoigiantra "
					+ "and thoigiantra is not null " + "group by thoigiantra";
			q = session.createQuery(hql);
			q.setParameter("thoigiantra", new Date(i));

			if (!q.list().isEmpty()) {
				kq[2] = q.list().get(0)[1]; // soluong tra

			}

			if (kq[1] != null || kq[2] != null) {
				if (kq[1] == null)
					kq[1] = 0;
				if (kq[2] == null)
					kq[2] = 0;
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
			Object[] kq = { null, null, null };
			
			/* THIẾT BỊ NHẬP VỀ */
			hql = "from PHIEUNHAP where trangthai = 'daXacNhan' "
				+ "and thoigiannhap = :thoigiannhap";
			Query<PHIEUNHAP> listPn = session.createQuery(hql);
			listPn.setParameter("thoigiannhap", new Date(i));

			if (!listPn.list().isEmpty()) {
				int tong = 0;
				for(CT_PHIEUNHAP ct_pn: listPn.list().get(0).getCt_phieunhaps()) {
					tong+=ct_pn.getSoluongnhap();
				}
				kq[0] = listPn.list().get(0).getThoigiannhap(); //Ngày
				kq[1] = tong; // soluong nhập về

			}
			
			/* THIẾT BỊ BỊ THANH LÝ (HỎNG) */
			hql = "from PHIEUTHANHLY " + "where trangthai = 'daXacNhan' "
				+ "and thoigian = :thoigian";
			Query<PHIEUTHANHLY> listPtl = session.createQuery(hql);
			listPtl.setParameter("thoigian", new Date(i));

			if (!listPtl.list().isEmpty()) {
				int tong = 0;
				for(CT_PHIEUTHANHLY ct_ptl: listPtl.list().get(0).getCt_phieuthanhlys()) {
					tong+=ct_ptl.getSoluong();
				}
				kq[0] = listPtl.list().get(0).getThoigian(); //Ngày
				kq[2] = tong; // soluong hỏng

			}

			if (kq[0] != null ) {
				if (kq[1] == null)
					kq[1] = 0;
				if (kq[2] == null)
					kq[2] = 0;
				listKq.add(kq);
			}

		}

		return listKq;
	}

}
