package com.nhom2.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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

import com.nhom2.DAO.NhanVienDAO;
import com.nhom2.entity.NHANVIEN;

@Transactional
@Controller
@RequestMapping("/")
public class NhanVienController {

	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "nhan-vien", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listNhanvien", new NhanVienDAO().getAll(factory));
		return "nhanvien/ds_nhanvien";
	}

	@ModelAttribute("nhanvien_moi")
	public NHANVIEN nhanvien_moi() {
		return new NHANVIEN();
	}

	@ModelAttribute("nhanvien_sua")
	public NHANVIEN nhanvien_sua() {
		return new NHANVIEN();
	}
	
	@ModelAttribute("gioiTinhs")
	public List<String> getGioiTinhs() {
		List<String> list = new ArrayList<>();
		list.add("Nam");
		list.add("Nữ");
		return list;
	}
	// THÊM
	@RequestMapping(value = "nhan-vien", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("nhanvien_moi") NHANVIEN nhanvien_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("nhanvien_moi", nhanvien_moi);
		Date ngaysinh = nhanvien_moi.getNgaysinh();
		System.out.println("ngay sinh: " + ngaysinh);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new NhanVienDAO().save(factory, nhanvien_moi)); // Xá»­ lÃ½ thÃ´ng bÃ¡o thÃªm thÃ nh cÃ´ng

		return home(model);
	}

	// 
	@RequestMapping(value = "nhan-vien/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua, @PathVariable String id) {
		
		model.addAttribute("form_edit", true);
		model.addAttribute("nhanvien_sua", new NhanVienDAO().getById(id, factory));
		return home(model);
	}
	

	// UPDATE
	@RequestMapping(value = "nhan-vien/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua, BindingResult result) {
		System.out.println("has error: " + result.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", result.hasErrors());
		model.addAttribute("nhanvien_sua", nhanvien_sua);
		if (result.hasErrors())
			return home(model);

		model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua));
		return home(model);
	}

	// DELETE
	@RequestMapping(value = "nhan-vien/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("id") String id) {
		System.out.println("id = " + id);
		NHANVIEN nhanvien_xoa = new NHANVIEN();
		nhanvien_xoa.setManv(id);
		model.addAttribute("delete", new NhanVienDAO().del(factory, nhanvien_xoa));
		return home(model);
	}

}
