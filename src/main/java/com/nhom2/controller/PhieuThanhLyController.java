package com.nhom2.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.DAO.PhieuMuonDAO;
import com.nhom2.entity.THIETBI;
import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.PHIEUMUON;
@Transactional
@Controller
@RequestMapping("/")
public class PhieuThanhLyController {
	
	@Autowired
	SessionFactory factory;

	@ModelAttribute("phieuthanhly_moi")
	public PHIEUMUON phieumuon_moi() {
		return new PHIEUMUON();
	}

	@ModelAttribute("chonThietBis")
	public List<String> chonThietBis() {
		List<String> list = new ArrayList<>();
		return list;
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
	

	// DANH SÁCH LOẠI THIẾT BỊ ĐỂ SELECT
	@ModelAttribute("loaiThietBis")
	public List<THIETBI> getLoaiThietBis() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "phieuthanhly", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("listPhieuThanhLy", new PhieuMuonDAO().getAll(factory));
		return "phieuthanhly/ds_phieu_thanh_ly";
	}
	@RequestMapping(value="phieuthanhly",method=RequestMethod.POST)
	public String insert(HttpServletRequest rq,ModelMap model) {
		String maTb1 = rq.getParameter("thietBi1");
		String slTb1 = rq.getParameter("slThietBi1");
		if(maTb1.isEmpty()==false&&slTb1.isEmpty()==false) {
			//Thêm cái Phiếu Thanh Lý để lấy mã
			
		}
		else {
			//Không cần thêm, vì nó sai.
		}
		
		
		return "phieuthanhly/ds_phieu_thanh_ly)";
	}
}
