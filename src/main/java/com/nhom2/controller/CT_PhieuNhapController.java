package com.nhom2.controller;

import java.util.List;

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

import com.nhom2.entity.CT_PHIEUNHAP;

@Transactional
@Controller
@RequestMapping("/")
public class CT_PhieuNhapController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("ct_phieunhap")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_PHIEUNHAP";
		Query <CT_PHIEUNHAP> query = session.createQuery(hql);
		List <CT_PHIEUNHAP> list = query.list();
		model.addAttribute("listCT_Phieunhap",list);
		model.addAttribute("ct_phieunhap_moi",new CT_PHIEUNHAP());
		return "ct_phieunhap";
	}
	@RequestMapping("ct_phieunhap/insert")
	public String insert(ModelMap model, @ModelAttribute("ct_phieunhap_moi") CT_PHIEUNHAP ct_phieunhap_moi) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ct_phieunhap_moi);
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
