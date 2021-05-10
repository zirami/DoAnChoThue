
package com.nhom2.controller;

import java.util.ArrayList;
import java.util.Collection;
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

import com.nhom2.DAO.CT_PhieuThanhLyDAO;
import com.nhom2.DAO.NhanVienDAO;
import com.nhom2.DAO.PhieuThanhLyDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.CT_PHIEUTHANHLY;
import com.nhom2.entity.NHANVIEN;
import com.nhom2.entity.PHIEUTHANHLY;
import com.nhom2.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/")
public class PhieuThanhLyController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("phieuthanhly_moi")
	public PHIEUTHANHLY phieuthanhly_moi() {
		return new PHIEUTHANHLY();
	}

	@ModelAttribute("phieuthanhly_sua")
	public PHIEUTHANHLY phieuthanhly_sua() {
		return new PHIEUTHANHLY();
	}

	// Load danh sách nhân viên
	@ModelAttribute("listNhanViens")
	public List<NHANVIEN> getListNhanViens() {
		return new NhanVienDAO().getAll(factory);
	}

	// DANH SÁCH LOẠI THIẾT BỊ ĐỂ SELECT
	@ModelAttribute("loaiThietBis")
	public List<THIETBI> getLoaiThietBis() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "phieuthanhly", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listPhieuThanhLy", new PhieuThanhLyDAO().getAll(factory));
		return "phieuthanhly/ds_phieu_thanh_ly";
	}

	@RequestMapping(value = "phieuthanhly", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("phieuthanhly_moi") PHIEUTHANHLY phieuthanhly_moi,
			BindingResult reusult, @RequestParam("thietBi1") String thietBi1,
			@RequestParam("slThietBi1") Integer slThietBi1, @RequestParam("thietBi2") String thietBi2,
			@RequestParam("slThietBi2") Integer slThietBi2, @RequestParam("thietBi3") String thietBi3,
			@RequestParam("slThietBi3") Integer slThietBi3, @RequestParam("thietBi4") String thietBi4,
			@RequestParam("slThietBi4") Integer slThietBi4, @RequestParam("thietBi5") String thietBi5,
			@RequestParam("slThietBi5") Integer slThietBi5, @RequestParam("donGiaTB1") Float donGiaTB1) {

		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("phieuthanhly_moi", phieuthanhly_moi);

		if (reusult.hasErrors())
			return home(model);
		
		

		Boolean result = new PhieuThanhLyDAO().save(factory, phieuthanhly_moi);
		
		if(result == false) {
			model.addAttribute("insert", result);			
			return home(model);
		}

		if (thietBi1 != "" && slThietBi1 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi1: " + thietBi1 + " So luong: " + slThietBi1);

			CT_PHIEUTHANHLY tb1 = new CT_PHIEUTHANHLY();
			tb1.setPhieuthanhly(phieuthanhly_moi);
			tb1.setSoluong(slThietBi1);
			tb1.setDongia(donGiaTB1);
			tb1.setThietbi_thanhly(new ThietBiDAO().getById(thietBi1, factory));
			System.out.println("TB1 :" + tb1.getId() + "," + tb1.getSoluong() + "," + tb1.getPhieuthanhly().getMaptl()
					+ "," + tb1.getThietbi_thanhly().getMatb());
			result = new CT_PhieuThanhLyDAO().save(factory, tb1);
		}
		if (thietBi2 != "" && slThietBi2 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi2: " + thietBi2 + " So luong: " + slThietBi2);

			CT_PHIEUTHANHLY tb2 = new CT_PHIEUTHANHLY();
			tb2.setPhieuthanhly(phieuthanhly_moi);
			tb2.setSoluong(slThietBi2);
			tb2.setThietbi_thanhly(new ThietBiDAO().getById(thietBi2, factory));
			System.out.println("TB2 :" + tb2.getId() + "," + tb2.getSoluong() + "," + tb2.getPhieuthanhly().getMaptl()
					+ "," + tb2.getThietbi_thanhly().getMatb());

			result = new CT_PhieuThanhLyDAO().save(factory, tb2);
		}
		if (thietBi3 != "" && slThietBi3 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi3: " + thietBi3 + " So luong: " + slThietBi3);

			CT_PHIEUTHANHLY tb3 = new CT_PHIEUTHANHLY();
			tb3.setPhieuthanhly(phieuthanhly_moi);
			tb3.setSoluong(slThietBi3);
			tb3.setThietbi_thanhly(new ThietBiDAO().getById(thietBi3, factory));
			System.out.println("TB3 :" + tb3.getId() + "," + tb3.getSoluong() + "," + tb3.getPhieuthanhly().getMaptl()
					+ "," + tb3.getThietbi_thanhly().getMatb());
			result = new CT_PhieuThanhLyDAO().save(factory, tb3);
		}
		if (thietBi4 != "" && slThietBi4 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi4: " + thietBi4 + " So luong: " + slThietBi4);

			CT_PHIEUTHANHLY tb4 = new CT_PHIEUTHANHLY();
			tb4.setPhieuthanhly(phieuthanhly_moi);
			tb4.setSoluong(slThietBi4);
			tb4.setThietbi_thanhly(new ThietBiDAO().getById(thietBi4, factory));
			System.out.println("TB4 :" + tb4.getId() + "," + tb4.getSoluong() + "," + tb4.getPhieuthanhly().getMaptl()
					+ "," + tb4.getThietbi_thanhly().getMatb());
			result = new CT_PhieuThanhLyDAO().save(factory, tb4);
		}
		if (thietBi5 != "" && slThietBi5 != null) {
			// in thiết bị và số lượng nhận được.
			System.out.println("ThietBi5: " + thietBi5 + " So luong: " + slThietBi5);

			CT_PHIEUTHANHLY tb5 = new CT_PHIEUTHANHLY();
			tb5.setPhieuthanhly(phieuthanhly_moi);
			tb5.setSoluong(slThietBi5);
			tb5.setThietbi_thanhly(new ThietBiDAO().getById(thietBi5, factory));
			System.out.println("TB5 :" + tb5.getId() + "," + tb5.getSoluong() + "," + tb5.getPhieuthanhly().getMaptl()
					+ "," + tb5.getThietbi_thanhly().getMatb());
			result = new CT_PhieuThanhLyDAO().save(factory, tb5);

		}
		model.addAttribute("insert", result);
		// Nếu CT_phieuthanhly được thêm thành công thì phieuthanhly mới cho phép thêm
		
		return home(model);

	}

	@RequestMapping(value = "phieuthanhly/edit/{mapm}", method = RequestMethod.GET)
	public String update(ModelMap model, @ModelAttribute("phieuthanhly_sua") PHIEUTHANHLY phieuthanhly_sua,
			@PathVariable("maptl") String maptl) {
		model.addAttribute("form_edit", true);
		phieuthanhly_sua = new PhieuThanhLyDAO().getById(maptl, factory);
		model.addAttribute("phieuthanhly_sua", phieuthanhly_sua);
		return home(model);
	}

	@RequestMapping(value = "phieuthanhly/update", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("phieuthanhly_sua") PHIEUTHANHLY phieuthanhly_sua,
			@RequestParam("thietBi1") String thietBi1, @RequestParam("slThietBi1") Integer slThietBi1,
			@RequestParam("thietBi2") String thietBi2, @RequestParam("slThietBi2") Integer slThietBi2,
			@RequestParam("thietBi3") String thietBi3, @RequestParam("slThietBi3") Integer slThietBi3,
			@RequestParam("thietBi4") String thietBi4, @RequestParam("slThietBi4") Integer slThietBi4,
			@RequestParam("thietBi5") String thietBi5, @RequestParam("slThietBi5") Integer slThietBi5,
			BindingResult result) {

		System.out.println("has error: " + result.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", result.hasErrors());
		model.addAttribute("phieuthanhly_sua", phieuthanhly_sua);
		if (result.hasErrors())
			return home(model);

		PHIEUTHANHLY phieuthanhly_cansua = new PhieuThanhLyDAO().getById(phieuthanhly_sua.getMaptl(), factory);

		int index = 1;

		for (CT_PHIEUTHANHLY elem : phieuthanhly_cansua.getCt_phieuthanhlys()) {
			if (index == 1) {
				elem.setSoluong(slThietBi1);
				elem.getThietbi_thanhly().setMatb(thietBi1);
				new CT_PhieuThanhLyDAO().update(factory, elem);
			} else if (index == 2) {
				elem.setSoluong(slThietBi2);
				elem.getThietbi_thanhly().setMatb(thietBi2);
				new CT_PhieuThanhLyDAO().update(factory, elem);
			} else if (index == 3) {
				elem.setSoluong(slThietBi3);
				elem.getThietbi_thanhly().setMatb(thietBi3);
				new CT_PhieuThanhLyDAO().update(factory, elem);
			} else if (index == 4) {
				elem.setSoluong(slThietBi4);
				elem.getThietbi_thanhly().setMatb(thietBi4);
				new CT_PhieuThanhLyDAO().update(factory, elem);
			} else if (index == 5) {
				elem.setSoluong(slThietBi5);
				elem.getThietbi_thanhly().setMatb(thietBi5);
				new CT_PhieuThanhLyDAO().update(factory, elem);
			}
			index = index + 1;
		}
		model.addAttribute("update", new PhieuThanhLyDAO().update(factory, phieuthanhly_sua));
		return home(model);
	}

	// Delete
	@RequestMapping(value = "phieuthanhly/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("maptl") String maptl) {
		PHIEUTHANHLY phieuthanhly_xoa = new PhieuThanhLyDAO().getById(maptl, factory);

		// Nó có thể vướng những cái ct_phieumuon nên không xóa được. kiểm tra.
		if (phieuthanhly_xoa.getCt_phieuthanhlys().size() > 0) {
			model.addAttribute("delete", false);
			return home(model);
		}
		
		//Ngược lại không vướng thì có thể xoá
		PHIEUTHANHLY phieuthanhly_canxoa = new PHIEUTHANHLY();
		phieuthanhly_canxoa.setMaptl(maptl);
		model.addAttribute("delete", new PhieuThanhLyDAO().delete(factory, phieuthanhly_canxoa));

		return home(model);
	}
}
