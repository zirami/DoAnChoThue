package com.nhom2.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhom2.DAO.LoaiThietBiDAO;
import com.nhom2.entity.LOAITHIETBI;
import com.nhom2.entity.PHANQUYEN;

@Controller
public class TestController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("phanQuyen")
	public PHANQUYEN getTb() {
		return new PHANQUYEN();
	}

	@ModelAttribute("loaiTb")
	public LOAITHIETBI loaiTb() {
		return new LOAITHIETBI();
	}

	@ModelAttribute("listLoaiTB")
	public List<LOAITHIETBI> getNv() {
		return new LoaiThietBiDAO().getAll(factory);
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "multi/multi_input";
	}

	@RequestMapping(value = "test", method = RequestMethod.POST)
	public String getMultiData(ModelMap model, 
			@Valid @ModelAttribute("loaiTb") LOAITHIETBI loaiTb, BindingResult reusult1,  
			@Valid @ModelAttribute("phanQuyen") PHANQUYEN phanQuyen, BindingResult reusult2) {
		System.out.println(loaiTb.getTen());
		System.out.println(phanQuyen.getTenpq());
		System.out.println("has error1: " + reusult1.hasErrors());
		System.out.println("has error2: " + reusult2.hasErrors());
		return index(model);
	}

}
