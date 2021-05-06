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

import com.nhom2.entity.CT_PHIEUMUON;

@Transactional
@Controller
@RequestMapping("/")
public class CT_PhieuMuonController {

//	@Autowired
//	SessionFactory factory;
//
//	@RequestMapping("ct_phieumuon")
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
//		String hql = "from CT_PHIEUMUON";
//		/* Bắt đầu quá trình truy vấn vào DB */
//
//		Query<CT_PHIEUMUON> query = session.createQuery(hql);
//		List<CT_PHIEUMUON> list = query.list();
//		model.addAttribute("listCT_Phieumuon", list);
//
//		return "ct_phieumuon";
//	}

}
