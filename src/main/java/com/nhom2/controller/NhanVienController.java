package com.nhom2.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.nhom2.DAO.AccountDAO;
import com.nhom2.DAO.NhanVienDAO;
import com.nhom2.DAO.PhieuMuonDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.ACCOUNT;
import com.nhom2.entity.NHANVIEN;
import com.nhom2.entity.PHANQUYEN;
import com.nhom2.entity.PHIEUMUON;
import com.nhom2.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/")
public class NhanVienController {

	@Autowired
	SessionFactory factory;

	public String getRandomMa() {
		List <NHANVIEN> list = new NhanVienDAO().getAll(factory);
		int ma = 1001;
		for (NHANVIEN elem : list) {
			String temp = "nv"+ma;
			if(elem.getManv().compareTo(temp)==0) {
				ma=ma+1;
			}
		}
		return "nv" + ma;
	}
	
	@ModelAttribute("nhanvien_moi")
	public NHANVIEN nhanvien_moi() {
		return new NHANVIEN();
	}

	@ModelAttribute("nhanvien_sua")
	public NHANVIEN nhanvien_sua() {
		return new NHANVIEN();
	}

	// DANH SÁCH GIỚI TÍNH ĐỂ SELECT
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
		model.addAttribute("maNhanVien",getRandomMa());
		return "nhanvien/ds_nhanvien";
	}

	// THÊM
	@RequestMapping(value = "qlnhanvien", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("nhanvien_moi") NHANVIEN nhanvien_moi,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("gmail") String gmail, @RequestParam("sdt") String sdt, HttpServletRequest rq,
			@RequestParam("photo") MultipartFile photo, BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("nhanvien_moi", nhanvien_moi);
		if (reusult.hasErrors())
			return home(model);
		if (photo.isEmpty()) {
			model.addAttribute("insert", false);
			model.addAttribute("nhanvien_moi", nhanvien_moi);
			return home(model);
		} else {
			try {

				String photoPath = rq.getServletContext()
						.getRealPath("/resources/files/" + photo.getOriginalFilename());

				photo.transferTo(new File(photoPath));

//				model.addAttribute("photo_name", photo.getOriginalFilename());
				nhanvien_moi.setHinh(photo.getOriginalFilename());
				ACCOUNT account_moi = new ACCOUNT();
				account_moi.setGmail(gmail);
				account_moi.setPassword(password);
				PHANQUYEN qp = new PHANQUYEN();
				//Do chỉ có 1 admin nên chỗ này sẽ set là staff. Sau này thêm nhiều quản lí thì sẽ thêm tính năng setMapq
				qp.setMapq("staff");
				account_moi.setPhanquyen(qp);
				account_moi.setSdt(sdt);
				account_moi.setUsername(username);
				
				ACCOUNT account_temp = new AccountDAO().getById(username, factory);
				if(account_temp!=null) {
					model.addAttribute("accountTonTai", true);
					model.addAttribute("nhanvien_moi", nhanvien_moi);
					return home(model);
				}
				
				new AccountDAO().save(factory, account_moi);

				nhanvien_moi.setAcc(account_moi);
				model.addAttribute("insert", new NhanVienDAO().save(factory, nhanvien_moi)); // Xử lý thông báo thêm
																								// thành công
				return home(model);

			} catch (Exception e) {
				model.addAttribute("insert", false);
				model.addAttribute("nhanvien_moi", nhanvien_moi);
				return home(model);
			}
		}
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
	public String update(ModelMap model, @Valid @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua,
			HttpServletRequest rq, @RequestParam("photo") MultipartFile photo, BindingResult reusult) {
		System.out.println("has error: " + reusult.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", reusult.hasErrors());
		model.addAttribute("nhanvien_sua", nhanvien_sua);
		if (reusult.hasErrors())
			return home(model);
		if (photo.isEmpty()) {
			ACCOUNT account_sua = nhanvien_sua.getAcc();
			account_sua.setGmail(nhanvien_sua.getAcc().getGmail());
			account_sua.setPassword(nhanvien_sua.getAcc().getPassword());
			
			PHANQUYEN qp = new PHANQUYEN();

			if(nhanvien_sua.getIsadmin().compareTo("yes")==0) {
				qp.setMapq("admin");
			}
			else {
				qp.setMapq("staff");
			}
			account_sua.setPhanquyen(qp);
			account_sua.setSdt(nhanvien_sua.getAcc().getSdt());
			account_sua.setUsername(nhanvien_sua.getAcc().getUsername());

			new AccountDAO().update(factory, account_sua);

			nhanvien_sua.setAcc(account_sua);
			model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua)); // Xử lý thông báo thêm thành																			// công
			model.addAttribute("nhanvien_sua", new NHANVIEN());
			return home(model);
		} else {
			try {

				String photoPath = rq.getServletContext().getRealPath("/resources/files/" + photo.getOriginalFilename());
				photo.transferTo(new File(photoPath));

//			model.addAttribute("photo_name", photo.getOriginalFilename());
				nhanvien_sua.setHinh(photo.getOriginalFilename());
				ACCOUNT account_sua = nhanvien_sua.getAcc();
				account_sua.setGmail(nhanvien_sua.getAcc().getGmail());
				account_sua.setPassword(nhanvien_sua.getAcc().getPassword());
				PHANQUYEN qp = new PHANQUYEN();
				qp.setMapq("staff");
				account_sua.setPhanquyen(qp);
				account_sua.setSdt(nhanvien_sua.getAcc().getSdt());
				account_sua.setUsername(nhanvien_sua.getAcc().getUsername());

				new AccountDAO().update(factory, account_sua);

				nhanvien_sua.setAcc(account_sua);
				model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua));
				model.addAttribute("nhanvien_sua", new NHANVIEN());
				return home(model);

			} catch (Exception e) {
				model.addAttribute("update", false);
				model.addAttribute("nhanvien_sua", nhanvien_sua);
				return home(model);
			}
		}
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
