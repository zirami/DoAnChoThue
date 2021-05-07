
package com.nhom2.controller;

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

import com.nhom2.DAO.CT_PhieuMuonDAO;
import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.DAO.PhieuMuonDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.CT_PHIEUMUON;
import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.PHIEUMUON;
import com.nhom2.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/")
public class PhieuMuonController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("phieumuon_moi")
	public PHIEUMUON phieumuon_moi() {
		return new PHIEUMUON();
	}

	@ModelAttribute("phieumuon_sua")
	public PHIEUMUON phieumuon_sua() {
		return new PHIEUMUON();
	}

	// Load danh sách nhân viên
	@ModelAttribute("listNhanViens")
	public List<String> getListNhanViens() {
		List<String> list = new ArrayList<>();
		list.add("nv1");
		list.add("nv2");
		list.add("nv3");
		return list;
	}

	// Load danh sách người mượn
	@ModelAttribute("listNguoiMuons")
	public List<NGUOIMUON> getListNguoiMuon() {
		List<NGUOIMUON> list = new NguoiMuonDAO().getAll(factory);
		return list;
	}

	// DANH SÁCH LOẠI THIẾT BỊ ĐỂ SELECT
	@ModelAttribute("loaiThietBis")
	public List<THIETBI> getLoaiThietBis() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "phieumuon", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listPhieuMuon", new PhieuMuonDAO().getAll(factory));
		return "phieumuon/ds_phieu_muon";
	}

	@RequestMapping(value = "phieumuon", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("phieumuon_moi") PHIEUMUON phieumuon_moi,
			BindingResult reusult, @RequestParam("thietBi1") String thietBi1,
			@RequestParam("slThietBi1") Integer slThietBi1, @RequestParam("thietBi2") String thietBi2,
			@RequestParam("slThietBi2") Integer slThietBi2, @RequestParam("thietBi3") String thietBi3,
			@RequestParam("slThietBi3") Integer slThietBi3, @RequestParam("thietBi4") String thietBi4,
			@RequestParam("slThietBi4") Integer slThietBi4, @RequestParam("thietBi5") String thietBi5,
			@RequestParam("slThietBi5") Integer slThietBi5) {

		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("phieumuon_moi", phieumuon_moi);

		if (reusult.hasErrors())
			return home(model);

		Boolean result = false;

		if (thietBi1 != "" && slThietBi1 != null || thietBi2 != "" && slThietBi2 != null
				|| thietBi3 != "" && slThietBi3 != null || thietBi4 != "" && slThietBi4 != null
				|| thietBi5 != "" && slThietBi5 != null) {
			// Xử lý thông báo thêm thành công
			new PhieuMuonDAO().save(factory, phieumuon_moi);
		}
		if (thietBi1 != "" && slThietBi1 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi1: " + thietBi1 + " So luong: " + slThietBi1);

			CT_PHIEUMUON tb1 = new CT_PHIEUMUON();
			tb1.setPhieumuon(phieumuon_moi);
			tb1.setSoluong(slThietBi1);
			tb1.setThietbi_muon(new ThietBiDAO().getById(thietBi1, factory));
			System.out.println("TB1 :" + tb1.getId() + "," + tb1.getSoluong() + "," + tb1.getPhieumuon().getMapm() + ","
					+ tb1.getThietbi_muon().getMatb());
			result = new CT_PhieuMuonDAO().save(factory, tb1);
		}
		if (thietBi2 != "" && slThietBi2 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi2: " + thietBi2 + " So luong: " + slThietBi2);

			CT_PHIEUMUON tb2 = new CT_PHIEUMUON();
			tb2.setPhieumuon(phieumuon_moi);
			tb2.setSoluong(slThietBi2);
			tb2.setThietbi_muon(new ThietBiDAO().getById(thietBi2, factory));
			System.out.println("TB2 :" + tb2.getId() + "," + tb2.getSoluong() + "," + tb2.getPhieumuon().getMapm() + ","
					+ tb2.getThietbi_muon().getMatb());

			result = new CT_PhieuMuonDAO().save(factory, tb2);
		}
		if (thietBi3 != "" && slThietBi3 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi3: " + thietBi3 + " So luong: " + slThietBi3);

			CT_PHIEUMUON tb3 = new CT_PHIEUMUON();
			tb3.setPhieumuon(phieumuon_moi);
			tb3.setSoluong(slThietBi3);
			tb3.setThietbi_muon(new ThietBiDAO().getById(thietBi3, factory));
			System.out.println("TB3 :" + tb3.getId() + "," + tb3.getSoluong() + "," + tb3.getPhieumuon().getMapm() + ","
					+ tb3.getThietbi_muon().getMatb());
			result = new CT_PhieuMuonDAO().save(factory, tb3);
		}
		if (thietBi4 != "" && slThietBi4 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi4: " + thietBi4 + " So luong: " + slThietBi4);

			CT_PHIEUMUON tb4 = new CT_PHIEUMUON();
			tb4.setPhieumuon(phieumuon_moi);
			tb4.setSoluong(slThietBi4);
			tb4.setThietbi_muon(new ThietBiDAO().getById(thietBi4, factory));
			System.out.println("TB4 :" + tb4.getId() + "," + tb4.getSoluong() + "," + tb4.getPhieumuon().getMapm() + ","
					+ tb4.getThietbi_muon().getMatb());
			result = new CT_PhieuMuonDAO().save(factory, tb4);
		}
		if (thietBi5 != "" && slThietBi5 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi5: " + thietBi5 + " So luong: " + slThietBi5);

			CT_PHIEUMUON tb5 = new CT_PHIEUMUON();
			tb5.setPhieumuon(phieumuon_moi);
			tb5.setSoluong(slThietBi5);
			tb5.setThietbi_muon(new ThietBiDAO().getById(thietBi5, factory));
			System.out.println("TB5 :" + tb5.getId() + "," + tb5.getSoluong() + "," + tb5.getPhieumuon().getMapm() + ","
					+ tb5.getThietbi_muon().getMatb());
			result = new CT_PhieuMuonDAO().save(factory, tb5);

		}
		model.addAttribute("insert", result);
		return home(model);

	}

	@RequestMapping(value = "phieumuon/edit/{mapm}", method = RequestMethod.GET)
	public String update(ModelMap model, @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua, @PathVariable("mapm") String mapm) {
		model.addAttribute("form_edit", true);
		model.addAttribute("phieumuon_sua", new PhieuMuonDAO().getById(mapm, factory));
		return home(model);
	}

	@RequestMapping(value = "phieumuon/update", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
			BindingResult result) {

		System.out.println("has error: " + result.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", result.hasErrors());
		model.addAttribute("thietbi_sua", phieumuon_sua);
		if (result.hasErrors())
			return home(model);

//		PHIEUMUON phieumuon_cansua = new PhieuMuonDAO().getById(phieumuon_sua.getMapm(), factory);
//		for(CT_PHIEUMUON elem: phieumuon_cansua.getCt_phieumuons()) {
//			new CT_PhieuMuonDAO().update(factory,elem);
//		}
		model.addAttribute("update", new PhieuMuonDAO().update(factory, phieumuon_sua));
		return home(model);
	}

	// Delete
	@RequestMapping(value = "phieumuon/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("mapm") String mapm) {
		PHIEUMUON phieumuon_xoa = new PhieuMuonDAO().getById(mapm, factory);
		if (phieumuon_xoa.getThoigiantra() != null) {
			model.addAttribute("delete", false);
		} else {
			// Nó có thể vướng những cái ct_phieumuon nên không xóa được. kiểm tra.
			for (CT_PHIEUMUON elem : phieumuon_xoa.getCt_phieumuons()) {
				new CT_PhieuMuonDAO().delete(factory, elem);
			}
			PHIEUMUON phieumuon_canxoa = new PHIEUMUON();
			phieumuon_canxoa.setMapm(mapm);
			model.addAttribute("delete", new PhieuMuonDAO().delete(factory, phieumuon_canxoa));
		}
		return home(model);
	}
}
