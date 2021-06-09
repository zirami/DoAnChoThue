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
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.ACCOUNT;
import com.nhom2.entity.CT_PHIEUNHAP;
import com.nhom2.entity.NHANVIEN;
import com.nhom2.entity.PHIEUNHAP;
import com.nhom2.entity.THIETBI;


@Controller
@RequestMapping("/")
public class PhieuNhapController {
	@Autowired
	SessionFactory factory;

	@ModelAttribute("listThietbi")
	public List<THIETBI> getListTB() {
		return new ThietBiDAO().getAll(factory);
	}

	@ModelAttribute("phieunhap_them")
	public PHIEUNHAP getPhieuNhapThem() {
		return new PHIEUNHAP();
	}

	@ModelAttribute("phieunhap_sua")
	public PHIEUNHAP getPhieuNhapSua() {
		return new PHIEUNHAP();
	}
	
	@ModelAttribute("today")
	public LocalDate getToday() {		
		return LocalDate.now(); 
	}

	@ModelAttribute("nv")
	public NHANVIEN getNv(HttpSession session) {
		ACCOUNT account = (ACCOUNT) session.getAttribute("account_db");
		System.out.println("username: " + account.getUsername());
		//return new NhanVienDAO().getByUserName(account.getUsername(), factory);
		if (account.getNhanviens().isEmpty()) return null;
		return account.getNhanviens().get(0);
	}

	@RequestMapping("phieu-nhap")
	public String home(ModelMap model) {
		model.addAttribute("listPhieuNhap", new PhieuNhapDAO().getAll(factory));

		return "phieu_nhap/ds_phieu_nhap";
	}

	public List<CT_PHIEUNHAP> removeDuplicate(List<String> matbs, List<Integer> soluongnhaps, List<Double> dongias,
			PHIEUNHAP phieunhap) {
		if (matbs.size() < 1 || soluongnhaps.size() < 1 || dongias.size() < 1)
			return null;

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

		List<CT_PHIEUNHAP> listCt_pn = new ArrayList<CT_PHIEUNHAP>();
		for (int i = 0; i < matbs.size(); i++) {
			CT_PHIEUNHAP ct_pn = new CT_PHIEUNHAP();
			ct_pn.setPhieunhap(phieunhap);
			ct_pn.setThietbi(new ThietBiDAO().getById(matbs.get(i), factory));
			ct_pn.setSoluongnhap(soluongnhaps.get(i));
			ct_pn.setDongia(dongias.get(i));
			listCt_pn.add(ct_pn);
		}

		return listCt_pn;
	}

	// THÊM PHIẾU NHẬP
	@RequestMapping(value = "phieu-nhap", method = RequestMethod.POST)
	public RedirectView insert(@ModelAttribute("phieunhap_them") @Valid PHIEUNHAP phieunhap_them, BindingResult result,
			@RequestParam("matb") List<String> matbs, @RequestParam("soluongnhap") List<Integer> soluongnhaps,
			@RequestParam("dongia") List<Double> dongias, final RedirectAttributes model) {

		// Mặc định là thất bại
		Boolean kq = false;

		// Kiểm tra PhieuNhap nhận về có đủ dữ liệu cần không
		if (result.hasErrors()) {
			// Hiển thị thông báo kết quả
			model.addFlashAttribute("notify", kq);
			return new RedirectView("phieu-nhap");
		}

		List<CT_PHIEUNHAP> listCt_pn = removeDuplicate(matbs, soluongnhaps, dongias, phieunhap_them);
		// Có đủ dữ liệu thì mới thêm
		if (listCt_pn != null) {
			phieunhap_them.setCt_phieunhaps(listCt_pn);
			phieunhap_them.setThoigiannhap(Date.valueOf(LocalDate.now()));
			kq = new PhieuNhapDAO().saveOrUpdate(factory, phieunhap_them);
		}

		// Hiển thị thông báo kết quả
		model.addFlashAttribute("notify", kq);
		
		System.out.println(phieunhap_them.getThoigiannhap());

		return new RedirectView("phieu-nhap");
	}

	// GIAO DIỆN SỬA PHIẾU NHẬP
	@RequestMapping("phieu-nhap/edit/{id}")
	public RedirectView edit(@PathVariable String id, RedirectAttributes model) {
		PHIEUNHAP phieunhap_sua = new PhieuNhapDAO().getById(id, factory);
		model.addFlashAttribute("phieunhap_sua", phieunhap_sua);
		
		model.addFlashAttribute("form_edit", true);
		return new RedirectView("../../phieu-nhap");
	}
	// UPDATE PHIẾU NHẬP
	@RequestMapping(value = "phieu-nhap/update", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute("phieunhap_them") @Valid PHIEUNHAP phieunhap_sua, BindingResult result,
			@RequestParam("matb") List<String> matbs, @RequestParam("soluongnhap") List<Integer> soluongnhaps,
			@RequestParam("dongia") List<Double> dongias, final RedirectAttributes model) {
		final String choXacNhan = "choXacNhan";
		// Mặc định là thất bại
		Boolean kq = false;

		// Kiểm tra PhieuNhap nhận về có đủ dữ liệu cần không
		// Kiểm tra xem trạng thái PhieuNhap != chờ xác nhận thì không cho sửa
		// || !phieunhap_sua.getTrangthai().equals(choXacNhan)
		if (result.hasErrors() ) {
			// Hiển thị thông báo kết quả
			model.addFlashAttribute("notify", kq);
			return new RedirectView("../phieu-nhap");
		}

		List<CT_PHIEUNHAP> listCt_pn = removeDuplicate(matbs, soluongnhaps, dongias, phieunhap_sua);
		// Có đủ dữ liệu thì mới thêm
		if (listCt_pn != null) {
			phieunhap_sua.setCt_phieunhaps(listCt_pn);
			kq = new PhieuNhapDAO().update(factory, phieunhap_sua);
		}

		// Hiển thị thông báo kết quả
		model.addFlashAttribute("notify", kq);

		return new RedirectView("../phieu-nhap");
	}
	
	@RequestMapping(value = "phieu-nhap/delete/{mapn}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String delete(@PathVariable String mapn) throws Exception {
		PHIEUNHAP phieunhap = new PhieuNhapDAO().getById2delete(mapn, factory);
		if(phieunhap.getTrangthai().equals("daXacNhan")) return "false";
		String kq = new PhieuNhapDAO().delete(factory, phieunhap).toString();
		return kq;
	}
}
