package com.nhom2.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom2.DAO.PhieuMuonDAO;

@Transactional
@Controller
@RequestMapping("/")
public class PhieuMuonController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("qlphieumuon")
	public String index(ModelMap model) {
		model.addAttribute("listPhieuMuon", new PhieuMuonDAO().getAll(factory));
		return "phieumuon/ds_phieu_muon";
	}

}
