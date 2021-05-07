package com.nhom2.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom2.entity.CT_PHIEUMUON;

@Transactional
@Controller
@RequestMapping("/")
public class CT_PhieuMuonController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("ct_phieumuon")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_PHIEUMUON";
		Query <CT_PHIEUMUON> query = session.createQuery(hql);
		List <CT_PHIEUMUON> list = query.list();
		model.addAttribute("listCT_Phieumuon",list);
		model.addAttribute("ct_phieumuon_moi",new CT_PHIEUMUON());
		return "ct_phieumuon";
	}
	@RequestMapping("ct_phieumuon/insert")
	public String insert(ModelMap model, @ModelAttribute("ct_phieumuon_moi") CT_PHIEUMUON ct_phieumuon_moi) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ct_phieumuon_moi);
			t.commit();
			model.addAttribute("message","Thành công!");
		}
		catch(Exception e) {
			t.rollback();
			model.addAttribute("message","Thất bại!");
		}
		finally {
			session.close();
		}
		
		return index(model);
	}

}
