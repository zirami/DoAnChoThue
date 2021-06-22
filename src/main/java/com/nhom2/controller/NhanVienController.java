package com.nhom2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import com.nhom2.DAO.NguoiMuonDAO;
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
		List<NHANVIEN> list = new NhanVienDAO().getAll(factory);
		int ma = 1001;
		String id = "nv" + ma;
		while (list.contains(new NhanVienDAO().getById(id, factory))) {
			ma++;
			id = "nv" + ma;
		}
		return id;
	}
	
	@ModelAttribute("nhanvien_moi")
	public NHANVIEN nhanvien_moi() {
		return new NHANVIEN();
	}
	
	@ModelAttribute("maQuanLi")
	public String maQuanLi() {
		
		return "ql1";
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
		
		if(nhanvien_moi.getCmnd().length()>12 || nhanvien_moi.getCmnd().length()<9 || sdt.length()>10 || sdt.length()<10) {
			model.addAttribute("nhanvien_moi", nhanvien_moi);
			model.addAttribute("insert", false);
			return home(model);
		}
		
		if (photo.isEmpty()) {
			model.addAttribute("insert", false);
			model.addAttribute("nhanvien_moi", nhanvien_moi);
			return home(model);
		} else {

				String path = rq.getServletContext().getRealPath("");
				path = path.substring(0, path.indexOf(".metadata")) + "\\" + rq.getServletContext().getContextPath()
						+ "\\src\\main\\webapp\\resources\\files\\";
				saveFile(path, photo, rq);
//				String photoPath = rq.getServletContext()
//						.getRealPath("/resources/files/" + photo.getOriginalFilename());

//				photo.transferTo(new File(photoPath));

//				model.addAttribute("photo_name", photo.getOriginalFilename());
				
				nhanvien_moi.setHinh(photo.getOriginalFilename());
				ACCOUNT account_moi = new ACCOUNT();
				account_moi.setGmail(gmail);
				password = new AccountDAO().getMd5(password);
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
				nhanvien_moi.setTen( new NguoiMuonDAO().chuanHoaTen(nhanvien_moi.getTen()));
				nhanvien_moi.setHo( new NguoiMuonDAO().chuanHoaTen(nhanvien_moi.getHo()));
				
				model.addAttribute("insert", new NhanVienDAO().save(factory, nhanvien_moi)); // Xử lý thông báo thêm
																								// thành công
				return home(model);

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
		
		if(nhanvien_sua.getCmnd().length()>12 || nhanvien_sua.getCmnd().length()<9 || nhanvien_sua.getAcc().getSdt().length()>10 || nhanvien_sua.getAcc().getSdt().length()<10) {
			model.addAttribute("nhanvien_sua", nhanvien_sua);
			model.addAttribute("insert", false);
			return home(model);
		}
		
		NHANVIEN nhanvien_db = new NhanVienDAO().getById(nhanvien_sua.getManv(), factory);
		
		if (photo.isEmpty()) {
			ACCOUNT account_sua = nhanvien_sua.getAcc();
			account_sua.setGmail(nhanvien_sua.getAcc().getGmail());
			if(nhanvien_db.getAcc().getPassword().compareTo(nhanvien_sua.getAcc().getPassword())==0){
				account_sua.setPassword(nhanvien_sua.getAcc().getPassword());
			}
			else {
				String password = new AccountDAO().getMd5(nhanvien_sua.getAcc().getPassword());
				account_sua.setPassword(password);
			}
			
			PHANQUYEN qp = new PHANQUYEN();

			if(nhanvien_db.getIsadmin()!=null) {
				qp.setMapq("admin");
				nhanvien_sua.setIsadmin("yes");
			}
			else {
				qp.setMapq("staff");
			}
			account_sua.setPhanquyen(qp);
			account_sua.setSdt(nhanvien_sua.getAcc().getSdt());
			account_sua.setUsername(nhanvien_sua.getAcc().getUsername());

			new AccountDAO().update(factory, account_sua);

			nhanvien_sua.setAcc(account_sua);
			
			nhanvien_sua.setTen( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getTen()));
			nhanvien_sua.setHo( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getHo()));
			
			model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua)); // Xử lý thông báo thêm thành																			// công
			model.addAttribute("nhanvien_sua", new NHANVIEN());
			return home(model);
		} else {

				String path = rq.getServletContext().getRealPath("");

				path = path.substring(0, path.indexOf(".metadata")) + "\\" + rq.getServletContext().getContextPath()
						+ "\\src\\main\\webapp\\resources\\files\\";
				int kqSave = saveFile(path, photo, rq);
				if (kqSave > 0)
					nhanvien_sua.setHinh(photo.getOriginalFilename());
				ACCOUNT account_sua = nhanvien_sua.getAcc();
				account_sua.setGmail(nhanvien_sua.getAcc().getGmail());
				
				if(nhanvien_db.getAcc().getPassword().compareTo(nhanvien_sua.getAcc().getPassword())==0){
					account_sua.setPassword(nhanvien_sua.getAcc().getPassword());
				}
				else {
					String password = new AccountDAO().getMd5(nhanvien_sua.getAcc().getPassword());
					account_sua.setPassword(password);
				}
				
				PHANQUYEN qp = new PHANQUYEN();
				if(nhanvien_db.getIsadmin()!=null) {
					qp.setMapq("admin");
					nhanvien_sua.setIsadmin("yes");
				}
				else {
					qp.setMapq("staff");
				}
				account_sua.setPhanquyen(qp);
				account_sua.setSdt(nhanvien_sua.getAcc().getSdt());
				account_sua.setUsername(nhanvien_sua.getAcc().getUsername());

				new AccountDAO().update(factory, account_sua);

				nhanvien_sua.setAcc(account_sua);
				
				nhanvien_sua.setTen( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getTen()));
				nhanvien_sua.setHo( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getHo()));
				
				model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua));
				model.addAttribute("nhanvien_sua", new NHANVIEN());
				return home(model);

		}
	}

	@RequestMapping(value = "qlnhanvien/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("manv") String manv) {
		System.out.println("manv = " + manv);
		
		NHANVIEN nhanvien_xoa = new NHANVIEN();
		nhanvien_xoa.setManv(manv);
		NHANVIEN nhanvien = new NHANVIEN();
		nhanvien = new NhanVienDAO().getById(manv, factory);
		ACCOUNT account_xoa = new ACCOUNT();
		account_xoa.setUsername(nhanvien.getAcc().getUsername());
		model.addAttribute("delete", new NhanVienDAO().del(factory, nhanvien_xoa));

		model.addAttribute("delete", new AccountDAO().del(factory, account_xoa));
		
		return home(model);
	}
	
	private int saveFile(String path, MultipartFile photo, HttpServletRequest rq) {
		if (!photo.isEmpty()) {
			try {

				String photoPath = rq.getServletContext()
						.getRealPath("/resources/files/" + photo.getOriginalFilename());
				byte[] bytes = photo.getBytes();
				BufferedOutputStream outstream = new BufferedOutputStream(new FileOutputStream(new File(photoPath)));
				outstream.write(bytes);
				outstream.flush();
				outstream.close();
				photo.transferTo(new File(path + photo.getOriginalFilename()));
				return 1; // thành công
			} catch (Exception e) {
				e.printStackTrace();
				return -1; // lỗi
			}
		}
		return 0; // edit không chọn hình thì giữ lại hình gốc
	}
}
