package com.nhom2.controller;

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

import com.nhom2.entity.THIETBI;

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

	@RequestMapping("thong-ke")
	public String home(ModelMap model) {

		return "thong_ke/thong_ke";
	}

	@RequestMapping(value = "thong-ke", method = RequestMethod.POST)
	public String thongke(ModelMap model, @RequestParam("loaiThongke") String loaiThongke,
			@RequestParam("ngaybatdau") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaybatdau,
			@RequestParam("ngayketthuc") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayketthuc) {

		Session session;
		String hql = "";
		//Nếu là Phiếu Mượn
		if(loaiThongke.equals("Phiếu Mượn")){
			hql = "select thoigianmuon, "
					//+ "sum(case when thoigianmuon between :ngaybatdau and :ngayketthuc then 1 else 0 end) "
					+ "count(*) "
					+ "from PHIEUMUON "
					+ "where thoigianmuon between :ngaybatdau and :ngayketthuc "
					+ "group by thoigianmuon";
		}
		//Nếu là Phiếu Nhập		
		if(loaiThongke.equals("Thiết Bị")){
			hql = "select tb.ten, "
					+ "sum(case when tb.matb = ct_pm.thietbi_muon.matb then 1 else 0 end) "
					+ "from THIETBI tb, CT_PHIEUMUON ct_pm "
					+ "where ct_pm.phieumuon.thoigianmuon between :ngaybatdau and :ngayketthuc "
					+ "group by tb.ten";
		}

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}

		/* Bắt đầu quá trình truy vấn vào DB */

		Query<Object[]> query = session.createQuery(hql);
		
		query.setParameter("ngaybatdau", ngaybatdau);
		query.setParameter("ngayketthuc", ngayketthuc);
		List<Object[]> kqThongke = query.list();

		model.addAttribute("loaiThongke", loaiThongke);
		model.addAttribute("kqThongke", kqThongke);
		
		if(loaiThongke.equals("Thiết Bị")) {
			for(Object[] objects: kqThongke) {
				System.out.println("ten: " + objects[0]);
				System.out.println("soluong: " + objects[1]);
				System.out.println("------------------");
			}
			return "redirect: thong-ke";
		}
		return home(model);
	}

}
