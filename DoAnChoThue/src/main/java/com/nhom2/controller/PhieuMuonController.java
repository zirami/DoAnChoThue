package com.nhom2.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.DAO.CT_PhieuMuonDAO;
import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.DAO.PhieuMuonDAO;
import com.nhom2.entity.THIETBI;
import com.nhom2.entity.CT_PHIEUMUON;
import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.PHIEUMUON;
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

	
	//Load danh sách nhân viên
	@ModelAttribute("listNhanViens")
	public List<String> getListNhanViens() {
		List<String> list= new ArrayList<> ();
		list.add("nv1");
		list.add("nv2");
		list.add("nv3");
		return list;
	}
	
	//Load danh sách người mượn
	@ModelAttribute("listNguoiMuons")
	public List<NGUOIMUON> getListNguoiMuon(){
		List <NGUOIMUON> list = new NguoiMuonDAO().getAll(factory);
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
	@RequestMapping(value="phieumuon",method=RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("phieumuon_moi") PHIEUMUON phieumuon_moi,
				BindingResult reusult, 
				@RequestParam("thietBi1") String thietBi1, @RequestParam("slThietBi1") Integer slThietBi1) {
			System.out.println("has error: " + reusult.hasErrors());
			model.addAttribute("them_saidinhdang", reusult.hasErrors());
			model.addAttribute("phieumuon_moi", phieumuon_moi);

			if (reusult.hasErrors())
				return home(model);
			if(thietBi1!=""&&slThietBi1!=null) {
				//in thiết bị và số lượng nhận được.
				System.out.println("ThietBi1: " + thietBi1 + " So luong: " + slThietBi1);
			
				new PhieuMuonDAO().save(factory, phieumuon_moi); // Xử lý thông báo thêm thành công
				
				CT_PHIEUMUON tb1 = new CT_PHIEUMUON();
				tb1.setPhieumuon(phieumuon_moi);
				tb1.setSoluong(slThietBi1);
				tb1.setThietbi_muon(new ThietBiDAO().getById(thietBi1, factory));
				System.out.println("TB1 :" + tb1.getId() + "," + tb1.getSoluong() + "," + tb1.getPhieumuon().getMapm() + "," + tb1.getThietbi_muon().getMatb());
				model.addAttribute("insert", new CT_PhieuMuonDAO().save(factory, tb1));
			}
		return home(model);

	}
}
