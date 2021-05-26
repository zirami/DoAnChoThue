package com.nhom2.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhom2.DAO.PhieuNhapDAO;

@Controller
@RequestMapping("/")
public class PhieuNhapController {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("phieu-nhap")
	public String home(ModelMap model) {
		model.addAttribute("listPhieuNhap", new PhieuNhapDAO().getAll(factory));
		return "phieu_nhap/ds_phieu_nhap";
	}
	
	
	//THÊM PHIẾU NHẬP
	@RequestMapping(value = "phieu-nhap", method = RequestMethod.POST)
	public String insert(ModelMap model) {
		return home(model);
	}
}
