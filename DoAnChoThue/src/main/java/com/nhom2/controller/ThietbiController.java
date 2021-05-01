package com.nhom2.controller;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/")
public class ThietbiController {

	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "thiet-bi", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listThietbi", new ThietBiDAO().getAll(factory));
		return "thiet_bi/ds_thiet_bi";
	}

	@ModelAttribute("thietbi_moi")
	public THIETBI thietbi_moi() {
		return new THIETBI();
	}

	@ModelAttribute("thietbi_sua")
	public THIETBI thietbi_sua() {
		return new THIETBI();
	}

	// THÊM
	@RequestMapping(value = "thiet-bi", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("thietbi_moi") THIETBI thietbi_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("thietbi_moi", thietbi_moi);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new ThietBiDAO().save(factory, thietbi_moi)); // Xử lý thông báo thêm thành công

		return home(model);
	}

	// LẤY RA THIẾT BỊ BẰNG ID ĐỂ SHOW FORM EDIT
	@RequestMapping(value = "thiet-bi/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("thietbi_sua") THIETBI thietbi_sua, @PathVariable String id) {
		System.out.println("id = " + id);
		model.addAttribute("form_edit", true);
		model.addAttribute("thietbi_sua", new ThietBiDAO().getById(id, factory));
		return home(model);
	}
	

	// UPDATE
	@RequestMapping(value = "thiet-bi/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("thietbi_sua") THIETBI thietbi_sua, BindingResult reusult) {
		System.out.println("has error: " + reusult.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", reusult.hasErrors());
		model.addAttribute("thietbi_sua", thietbi_sua);
		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("update", new ThietBiDAO().update(factory, thietbi_sua));
		return home(model);
	}

	// DELETE
	@RequestMapping(value = "thiet-bi/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("id") String id) {
		System.out.println("id = " + id);
		THIETBI thietbi_xoa = new THIETBI();
		thietbi_xoa.setMatb(id);
		model.addAttribute("delete", new ThietBiDAO().del(factory, thietbi_xoa));
		return home(model);
	}

}
