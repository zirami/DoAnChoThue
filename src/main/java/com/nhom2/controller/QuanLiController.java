package com.nhom2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom2.DAO.QuanLiDAO;
import com.nhom2.entity.QUANLI;

@Transactional
@Controller
@RequestMapping("/")
public class QuanLiController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("quanli_moi")
	public QUANLI quanli_moi() {
		return new QUANLI();
	}

	@ModelAttribute("quanli_sua")
	public QUANLI quanli_sua() {
		return new QUANLI();
	}
	//DANH SÁCH GIỚI TÍNH ĐỂ SELECT
		@ModelAttribute("gioiTinhs")
		public List<String> getGioiTinhs() {
			List<String> list = new ArrayList<>();
			list.add("Nam");
			list.add("Nữ");
			return list;
		}

	@RequestMapping(value = "qlquanli", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listQuanLi", new QuanLiDAO().getAll(factory));
		return "nhanvien/ds_quanli";
	}

	// THÊM
	@RequestMapping(value = "qlquanli", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("quanli_moi") QUANLI quanli_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("quanli_moi", quanli_moi);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new QuanLiDAO().save(factory, quanli_moi)); // Xử lý thông báo thêm thành
																						// công

		return home(model);
	}

	// Sửa
	// LẤY RA THIẾT BỊ BẰNG ID ĐỂ SHOW FORM EDIT
	@RequestMapping(value = "qlquanli/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("quanli_sua") QUANLI quanli_sua,
			@PathVariable String id) {
		System.out.println("id = " + id);
		model.addAttribute("form_edit", true);
		model.addAttribute("quanli_sua", new QuanLiDAO().getById(id, factory));
		return home(model);
	}

		// UPDATE
		@RequestMapping(value = "qlquanli/update", method = RequestMethod.POST)
		public String update(ModelMap model, @Valid @ModelAttribute("quanli_sua") QUANLI quanli_sua, BindingResult reusult) {
			System.out.println("has error: " + reusult.getFieldErrors().toString());
			model.addAttribute("sua_saidinhdang", reusult.hasErrors());
			model.addAttribute("quanli_sua", quanli_sua);
			if (reusult.hasErrors())
				return home(model);
			model.addAttribute("update", new QuanLiDAO().update(factory, quanli_sua));
			return home(model);
		}

	@RequestMapping(value = "qlquanli/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("maql") String maql) {
		System.out.println("maql = " + maql);
		QUANLI quanli_xoa = new QUANLI();
		quanli_xoa.setMaql(maql);
		model.addAttribute("delete", new QuanLiDAO().del(factory, quanli_xoa));
		return home(model);
	}
}
