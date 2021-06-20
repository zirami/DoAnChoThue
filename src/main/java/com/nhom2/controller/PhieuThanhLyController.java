package com.nhom2.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.nhom2.DAO.PhieuNhapDAO;
import com.nhom2.DAO.PhieuThanhLyDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.ACCOUNT;
import com.nhom2.entity.CT_PHIEUNHAP;
import com.nhom2.entity.CT_PHIEUTHANHLY;
import com.nhom2.entity.NHANVIEN;
import com.nhom2.entity.PHIEUNHAP;
import com.nhom2.entity.PHIEUTHANHLY;
import com.nhom2.entity.THIETBI;

@Controller
@RequestMapping("/")
public class PhieuThanhLyController {
	@Autowired
	SessionFactory factory;
	
	public String msg = "Thao tác thất bại";
	
	final String daXacNhan = "daXacNhan";
	final String choXacNhan = "choXacNhan";

	@ModelAttribute("listThietbi")
	public List<THIETBI> getListTB() {
		return new ThietBiDAO().getAll(factory);
	}

	@ModelAttribute("phieuthanhly_them")
	public PHIEUTHANHLY getPhieuThanhLyThem() {
		return new PHIEUTHANHLY();
	}

	@ModelAttribute("phieuthanhly_sua")
	public PHIEUTHANHLY getPhieuThanhLySua() {
		return new PHIEUTHANHLY();
	}

	@ModelAttribute("today")
	public LocalDate getToday() {
		return LocalDate.now();
	}

	@ModelAttribute("nv")
	public NHANVIEN getNv(HttpSession session, ModelMap model) {
		ACCOUNT account = (ACCOUNT) session.getAttribute("account_db");
		System.out.println("username: " + account.getUsername());
		// return new NhanVienDAO().getByUserName(account.getUsername(), factory);
		if (account.getNhanviens().isEmpty())
			return null;
		if (account.getUsername().equals("admin")) 
			model.addAttribute("admin",true);
		model.addAttribute("current_user",account.getNhanviens().get(0).getManv());
		return account.getNhanviens().get(0);
	}
	
	@ModelAttribute("newID")
	public String getRandomMa() {
		List<PHIEUTHANHLY> list = new PhieuThanhLyDAO().getAll(factory);
		int ma = 1001;
		String id = "tb" + ma;
		while (list.contains(new PhieuThanhLyDAO().getById(id, factory))) {
			ma++;
			id = "tb" + ma;
		}
		return id;
	}

	@RequestMapping("phieu-thanhly")
	public String home(ModelMap model) {
		model.addAttribute("listPhieuThanhLy", new PhieuThanhLyDAO().getAll(factory));
		System.out.println(new PhieuThanhLyDAO().getAll(factory));
		return "phieu_thanhly/ds_phieu_thanhly";
	}

	public List<CT_PHIEUTHANHLY> removeDuplicate(List<String> matbs, List<Integer> soluongnhaps, List<Double> dongias,
			PHIEUTHANHLY phieuthanhly) {
		if (matbs.size() < 1 || soluongnhaps.size() < 1 || dongias.size() < 1) {
			msg += ", Vui lòng nhập đầy đủ chi tiết thiết bị !!!";
			return null;
		}

		boolean kq = true;

		// Gop phan tu bi trung ten
		// Duyet tat ca phan tu
		for (int i = 0; i < matbs.size() - 1; i++)
			for (int j = i + 1; j < matbs.size(); j++) {
				// neu trung ten
				if (matbs.get(i).equals(matbs.get(j))) {
					// remove thiet bi trung
					matbs.remove(j);

					// cộng dồn số lượng và đơn giá
					int soLuong = soluongnhaps.get(i) + soluongnhaps.get(j);
					soluongnhaps.set(i, soLuong);
					Double donGia = dongias.get(i) + dongias.get(j);
					dongias.set(i, donGia);

					// remove sl và đơn giá cua thiet bi trung
					soluongnhaps.remove(j);
					dongias.remove(j);
				}
			}

		List<CT_PHIEUTHANHLY> listCt_ptl = new ArrayList<CT_PHIEUTHANHLY>();
		for (int i = 0; i < matbs.size(); i++) {
			CT_PHIEUTHANHLY ct_ptl = new CT_PHIEUTHANHLY();
			ct_ptl.setPhieuthanhly(phieuthanhly);
			ct_ptl.setThietbi_thanhly(new ThietBiDAO().getById2thanhly(matbs.get(i), factory));

			if (soluongnhaps.get(i) > ct_ptl.getThietbi_thanhly().getSoluong()) {
				kq = false;
				break;
			}

			ct_ptl.setSoluong(soluongnhaps.get(i));
			ct_ptl.setDongia(dongias.get(i));
			listCt_ptl.add(ct_ptl);
		}

		if (!kq) {
			msg += ", Số lưọng lớn hơn tồn kho !!!";
			return null;
		}
			
		return listCt_ptl;
	}

	// THÊM PHIẾU NHẬP
	@RequestMapping(value = "phieu-thanhly", method = RequestMethod.POST)
	public RedirectView insert(@ModelAttribute("phieuthanhly_them") @Valid PHIEUTHANHLY phieuthanhly_them,
			BindingResult result, @RequestParam("matb") List<String> matbs,
			@RequestParam("soluong") List<Integer> soluongnhaps, @RequestParam("dongia") List<Double> dongias,
			final RedirectAttributes model) {
		final String daXacNhan = "daXacNhan";
		// Mặc định là thất bại
		Boolean kq = false;

		// Kiểm tra PhieuNhap nhận về có đủ dữ liệu cần không
		if (result.hasErrors()) {
			// Hiển thị thông báo kết quả
			model.addFlashAttribute("notify", kq);
			msg += ", "+result.getAllErrors();
			model.addFlashAttribute("msg", msg);
			
			return new RedirectView("phieu-thanhly");
		}

		List<CT_PHIEUTHANHLY> listCt_ptl = removeDuplicate(matbs, soluongnhaps, dongias, phieuthanhly_them);
		// Có đủ dữ liệu thì mới thêm
		if (listCt_ptl != null) {
			phieuthanhly_them.setCt_phieuthanhlys(listCt_ptl);
			phieuthanhly_them.setThoigian(Date.valueOf(LocalDate.now()));
			if (phieuthanhly_them.getTrangthai().equals("duocXacNhan"))
				phieuthanhly_them.setTrangthai("daXacNhan");
			kq = new PhieuThanhLyDAO().saveOrUpdate(factory, phieuthanhly_them);

			// Nếu trạng thái = đã Xác nhận thì số lượng bên Thietbi sẽ giảm
			if (phieuthanhly_them.getTrangthai().equals(daXacNhan)) {
				for (CT_PHIEUTHANHLY ct_ptl : listCt_ptl) {
					THIETBI thietbi_thanhly = ct_ptl.getThietbi_thanhly();
					thietbi_thanhly.setSoluong(thietbi_thanhly.getSoluong() - ct_ptl.getSoluong());
					kq = new ThietBiDAO().update(factory, thietbi_thanhly);
					if (!kq) {
						msg+=", Cập nhật số lượng tồn kho thiết bị thất bại !!!";
						break;
					}
				}

			}
		}

		// Hiển thị thông báo kết quả
		model.addFlashAttribute("notify", kq);
		model.addFlashAttribute("msg", msg);

		return new RedirectView("phieu-thanhly");
	}

	// GIAO DIỆN SỬA PHIẾU NHẬP
	@RequestMapping("phieu-thanhly/edit/{id}")
	public RedirectView edit(@PathVariable String id, RedirectAttributes model) {
		PHIEUTHANHLY phieuthanhly_sua = new PhieuThanhLyDAO().getById(id, factory);
		model.addFlashAttribute("phieuthanhly_sua", phieuthanhly_sua);
		
		if (phieuthanhly_sua.getTrangthai().equals(daXacNhan))
			model.addFlashAttribute("form_info", true);
		else model.addFlashAttribute("form_edit", true);
		return new RedirectView("../../phieu-thanhly");
	}

	// UPDATE PHIẾU NHẬP
	@RequestMapping(value = "phieu-thanhly/update", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute("phieuthanhly_sua") @Valid PHIEUTHANHLY phieuthanhly_sua,
			BindingResult result, @RequestParam("matb") List<String> matbs,
			@RequestParam("soluong") List<Integer> soluongnhaps, @RequestParam("dongia") List<Double> dongias,
			final RedirectAttributes model) {
		
		// Mặc định là thất bại
		Boolean kq = false;

		// Kiểm tra PhieuNhap nhận về có đủ dữ liệu cần không
		// Kiểm tra xem trạng thái PhieuNhap != chờ xác nhận thì không cho sửa
		// || !phieunhap_sua.getTrangthai().equals(choXacNhan)
		if (result.hasErrors()) {
			// Hiển thị thông báo kết quả
			model.addFlashAttribute("notify", kq);
			msg += ", "+result.getAllErrors();
			model.addFlashAttribute("msg", msg);
			return new RedirectView("../phieu-thanhly");
		}

		List<CT_PHIEUTHANHLY> listCt_ptl = removeDuplicate(matbs, soluongnhaps, dongias, phieuthanhly_sua);
		// Có đủ dữ liệu thì mới thêm
		if (listCt_ptl != null) {
			phieuthanhly_sua.setCt_phieuthanhlys(listCt_ptl);
			if (phieuthanhly_sua.getTrangthai().equals("duocXacNhan"))
				phieuthanhly_sua.setTrangthai("daXacNhan");
			kq = new PhieuThanhLyDAO().update(factory, phieuthanhly_sua);

			if (!kq) {
				// Hiển thị thông báo kết quả
				model.addFlashAttribute("notify", kq);
				model.addFlashAttribute("msg", msg);
				return new RedirectView("../phieu-thanhly");
			}

			// Nếu trạng thái = đã Xác nhận thì số lượng bên Thietbi sẽ giảm
			if (phieuthanhly_sua.getTrangthai().equals(daXacNhan)) {
				for (CT_PHIEUTHANHLY ct_ptl : listCt_ptl) {
					THIETBI thietbi_thanhly = ct_ptl.getThietbi_thanhly();
					thietbi_thanhly.setSoluong(thietbi_thanhly.getSoluong() - ct_ptl.getSoluong());
					kq = new ThietBiDAO().update(factory, thietbi_thanhly);
					if (!kq) {
						msg+=", Cập nhật số lượng tồn kho thiết bị thất bại !!!";
						break;
					}
				}

			}
		}

		// Hiển thị thông báo kết quả
		model.addFlashAttribute("notify", kq);
		model.addFlashAttribute("msg", msg);
		return new RedirectView("../phieu-thanhly");
	}

	@RequestMapping(value = "phieu-thanhly/delete/{maptl}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String delete(@PathVariable String maptl) throws Exception {
		PHIEUTHANHLY phieuthanhly = new PhieuThanhLyDAO().getById2delete(maptl, factory);
		if (phieuthanhly == null || phieuthanhly.getTrangthai().equals("daXacNhan"))
			return "false";
		String kq = new PhieuThanhLyDAO().delete(factory, phieuthanhly).toString();
		return kq;
	}
	
	@RequestMapping(value = "phieu-thanhly/getSoluongton/{matb}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSoluongton(@PathVariable String matb) throws Exception {
		THIETBI thietbi = new ThietBiDAO().getById2thanhly(matb, factory);
		return thietbi.getSoluong().toString();
	}
}
