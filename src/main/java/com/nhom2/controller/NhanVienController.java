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

import com.nhom2.DAO.NhanVienDAO;
import com.nhom2.entity.NHANVIEN;

@Transactional
@Controller
@RequestMapping("/")
public class NhanVienController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("nhanvien_moi")
	public NHANVIEN nhanvien_moi() {
		return new NHANVIEN();
	}

	@ModelAttribute("nhanvien_sua")
	public NHANVIEN nhanvien_sua() {
		return new NHANVIEN();
	}
	//DANH SÁCH GIỚI TÍNH ĐỂ SELECT
		@ModelAttribute("gioiTinhs")
		public List<String> getGioiTinhs() {
			List<String> list = new ArrayList<>();
			list.add("Nam");
			list.add("Nữ");
			return list;
		}

	@RequestMapping(value = "qlnhanvien", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listNhanVien", new NhanVienDAO().getAll(factory));
		return "nhanvien/ds_nhanvien";
	}

	// THÊM
	@RequestMapping(value = "qlnhanvien", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("nhanvien_moi") NHANVIEN nhanvien_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("nhanvien_moi", nhanvien_moi);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new NhanVienDAO().save(factory, nhanvien_moi)); // Xử lý thông báo thêm thành
																						// công

		return home(model);
	}

	// Sửa
	// LẤY RA THIẾT BỊ BẰNG ID ĐỂ SHOW FORM EDIT
	@RequestMapping(value = "qlnhanvien/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua,
			@PathVariable String id) {
		System.out.println("id = " + id);
		model.addAttribute("form_edit", true);
		model.addAttribute("nhanvien_sua", new NhanVienDAO().getById(id, factory));
		return home(model);
	}

		// UPDATE
		@RequestMapping(value = "qlnhanvien/update", method = RequestMethod.POST)
		public String update(ModelMap model, @Valid @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua, BindingResult reusult) {
			System.out.println("has error: " + reusult.getFieldErrors().toString());
			model.addAttribute("sua_saidinhdang", reusult.hasErrors());
			model.addAttribute("nhanvien_sua", nhanvien_sua);
			if (reusult.hasErrors())
				return home(model);

			model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua));
			return home(model);
		}

	@RequestMapping(value = "qlnhanvien/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("manv") String manv) {
		System.out.println("manv = " + manv);
		NHANVIEN nhanvien_xoa = new NHANVIEN();
		nhanvien_xoa.setManv(manv);
		model.addAttribute("delete", new NhanVienDAO().del(factory, nhanvien_xoa));
		return home(model);
	}
}
