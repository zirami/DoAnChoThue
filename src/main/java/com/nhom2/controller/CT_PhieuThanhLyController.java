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

import com.nhom2.entity.CT_PHIEUTHANHLY;

@Transactional
@Controller
@RequestMapping("/")
public class CT_PhieuThanhLyController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("ct_phieuthanhly")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_PHIEUTHANHLY";
		Query <CT_PHIEUTHANHLY> query = session.createQuery(hql);
		List <CT_PHIEUTHANHLY> list = query.list();
		model.addAttribute("listCT_Phieuthanhly",list);
		model.addAttribute("ct_phieuthanhly_moi",new CT_PHIEUTHANHLY());
		return "ct_phieuthanhly";
	}
	@RequestMapping("ct_phieuthanhly/insert")
	public String insert(ModelMap model, @ModelAttribute("ct_phieuthanhly_moi") CT_PHIEUTHANHLY ct_phieuthanhly_moi) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ct_phieuthanhly_moi);
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
