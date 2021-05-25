package com.nhom2.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.PHIEUMUON;
import com.nhom2.entity.THIETBI;

@Controller
public class TestController {

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
	@ModelAttribute("thietBis")
	public List<THIETBI> getListThietBi() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "multi/multi_input";
	}

	@RequestMapping(value = "test", method = RequestMethod.POST)
	public String getMultiData(ModelMap model, @RequestParam("thietBis") List<String> thietBis,
			@RequestParam("soLuongTbs") List<Integer> soLuongTbs) {

		// Bỏ phần tử đầu tiên: hidden-element
		for (int i = 1; i < thietBis.size(); i++) {
			System.out.println("Truoc khi gop: {maTb: " + thietBis.get(i) + ", so luong: " + soLuongTbs.get(i) + " }");
		}
		
		
		//Gop phan tu bi trung ten
		//Duyet tat ca phan tu
		for (int i = 1; i < thietBis.size() - 1; i++)
			for (int j = i + 1; j < thietBis.size(); j++) {
				//neu trung ten
				if(thietBis.get(i).equals(thietBis.get(j))) {
					//remove thiet bi trung
					thietBis.remove(j);	
					
					//them vao list so luong tb moi
					int soLuong = soLuongTbs.get(i) + soLuongTbs.get(j);
					soLuongTbs.set(i, soLuong);
					
					//remove sl cua thiet bi trung
					soLuongTbs.remove(j);
				}
			}
		
		//Ds sau khi gop
		for (int i = 1; i < thietBis.size(); i++) {
			System.out.println("Sau khi gop: maTb: " + thietBis.get(i)+ ", so luong: " + soLuongTbs.get(i));
		}


		return index(model);
	}

}
