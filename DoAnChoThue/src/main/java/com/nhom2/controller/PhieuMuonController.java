package com.nhom2.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom2.entity.PHIEUMUON;

@Transactional
@Controller
@RequestMapping("/")
public class PhieuMuonController {

//	@Autowired
//	SessionFactory factory;
//
//	@RequestMapping("phieumuon")
//	public String index(ModelMap model) {
//		Session session;
//
//		/* Nếu chưa có session nào thì tạo session mới */
//		try {
//			session = factory.getCurrentSession();
//		} catch (HibernateException e) {
//			session = factory.openSession();
//		}
//		
//		// From [ten Entity]
//		String hql = "from PHIEUMUON";
//		/* Bắt đầu quá trình truy vấn vào DB */
//
//		Query<PHIEUMUON> query = session.createQuery(hql);
//		List<PHIEUMUON> list = query.list();
//		model.addAttribute("listPhieumuon", list);
//
//		return "phieumuon";
//	}

}
