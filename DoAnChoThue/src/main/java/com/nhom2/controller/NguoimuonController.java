package com.nhom2.controller;

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

import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.entity.NGUOIMUON;

@Transactional
@Controller
@RequestMapping("/")
public class NguoimuonController {
	///Test thử!!!
	/// Test Nhiều cái thử!!!
	/// Test lần 3!!!
	///test lần 4
	public NguoimuonController(){
		super();
	}
	@Autowired
	SessionFactory factory;

	// Ngoài view yêu cầu Attribute listThietbi sẽ trả về ds Thiết bị
//	@ModelAttribute("listNguoiMuon")
//	public List<NGUOIMUON> hienThids() {
//		return new NguoiMuonDAO().getAll(factory);
//	}

	@ModelAttribute("nguoimuon_moi")
	public NGUOIMUON nguoimuon_moi() {
		return new NGUOIMUON();
	}

	@ModelAttribute("nguoimuon_sua")
	public NGUOIMUON nguoimuon_sua() {
		return new NGUOIMUON();
	}

	@RequestMapping(value = "qlnguoimuon", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listNguoiMuon", new NguoiMuonDAO().getAll(factory));
		return "nguoimuon/ds_nguoimuon";
	}

//	// THÊM
//	@RequestMapping(value = "qlnguoimuon", method = RequestMethod.POST)
//	public String insert(ModelMap model, @ModelAttribute("nguoimuonmoi") NGUOIMUON nguoimuonmoi) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		try {
//			session.save(nguoimuonmoi);
//			t.commit();
//			model.addAttribute("message", "Thêm mới thành công!");
//
//		} catch (Exception e) {
//			t.rollback();
//			model.addAttribute("message", "Thêm mới thất bại!");
//		} finally {
//			
//			session.close();
//		}
//		return "redirect: qlnguoimuon";
//	}

	// THÊM
	@RequestMapping(value = "qlnguoimuon", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("nguoimuon_moi") NGUOIMUON nguoimuon_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("nguoimuon_moi", nguoimuon_moi);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new NguoiMuonDAO().save(factory, nguoimuon_moi)); // Xử lý thông báo thêm thành
																						// công

		return home(model);
	}

	// Sửa
	// LẤY RA THIẾT BỊ BẰNG ID ĐỂ SHOW FORM EDIT
	@RequestMapping(value = "qlnguoimuon/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("nguoimuon_sua") NGUOIMUON nguoimuon_sua,
			@PathVariable String id) {
		System.out.println("id = " + id);
		model.addAttribute("form_edit", true);
		model.addAttribute("nguoimuon_sua", new NguoiMuonDAO().getById(id, factory));
		model.addAttribute("gioitinh_sua",new NguoiMuonDAO().getById(id, factory).getGioitinh());
		return home(model);
	}

		// UPDATE
		@RequestMapping(value = "qlnguoimuon/update", method = RequestMethod.POST)
		public String update(ModelMap model, @Valid @ModelAttribute("nguoimuon_sua") NGUOIMUON nguoimuon_sua, BindingResult reusult) {
			System.out.println("has error: " + reusult.getFieldErrors().toString());
			model.addAttribute("sua_saidinhdang", reusult.hasErrors());
			model.addAttribute("nguoimuon_sua", nguoimuon_sua);
			if (reusult.hasErrors())
				return home(model);

			model.addAttribute("update", new NguoiMuonDAO().update(factory, nguoimuon_sua));
			return home(model);
		}

	@RequestMapping(value = "qlnguoimuon/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("manm") String manm) {
		System.out.println("manm = " + manm);
		NGUOIMUON nguoimuon_xoa = new NGUOIMUON();
		nguoimuon_xoa.setManm(manm);
		model.addAttribute("delete", new NguoiMuonDAO().del(factory, nguoimuon_xoa));
		return home(model);
	}
}
