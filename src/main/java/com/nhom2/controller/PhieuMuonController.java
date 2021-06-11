
package com.nhom2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
	
	public String getRandomMa() {
		List <PHIEUMUON> list = new PhieuMuonDAO().getAll(factory);
		int ma = 1001;
		for (PHIEUMUON elem : list) {
			String temp = "pm"+ma;
			if(elem.getMapm().compareTo(temp)==0) {
				ma=ma+1;
			}
		}
		return "pm" + ma;
	}

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

	@ModelAttribute("listThietBi")
	public List<String> getListThietBi() {
		List<String> list = new ArrayList<>();
		list.add("thietBi0");

		return list;
	}

	@ModelAttribute("listSoLuong")
	public List<String> getListSoLuong() {
		List<String> list = new ArrayList<>();
		list.add("slThietBi0");

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
	public String home(ModelMap model, HttpSession session) {
		model.addAttribute("listPhieuMuon", new PhieuMuonDAO().getAll(factory));
		model.addAttribute("maphieumuon",getRandomMa());
		model.addAttribute("indexValue", 0);
		return "phieumuon/ds_phieu_muon";
	}

	@RequestMapping(value = "phieumuon", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("phieumuon_moi") PHIEUMUON phieumuon_moi,
			BindingResult reusult, @RequestParam("indexValue") Integer indexValue,
			@RequestParam("demValue") Integer demValue, HttpSession session, HttpServletRequest rq) {

		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("phieumuon_moi", phieumuon_moi);

		// nếu có lỗi thì quay về chương trình
		if (reusult.hasErrors())
			return home(model, session);

		// Kiểm tra giá trị của index bên view
		if (indexValue == 0) {
			System.out.print("co gia tri");
		}
		// nếu số lượng các phần tử khác không thì tạo 1 Phiếu mượn
		if (demValue != 0) {
			new PhieuMuonDAO().save(factory, phieumuon_moi);
		}
		// Khởi tạo thằng trả về bằng false
		Boolean result = false;

		// Thêm từng cái CT_ThietBi
		int i = 1;
		List<THIETBI> list = new ArrayList<>();
		for (i = 1; i <= indexValue; i++) {
			// Tạo phần tử để requestParameter
			String tb = "thietBi" + i;
			String slTb = "slThietBi" + i;

			// Phần tử chứ requestParameter

			String slTB = rq.getParameter(slTb);
			String thietBi = rq.getParameter(tb);

			if (thietBi != "" && slTB != null) {
				int slThietBi = Integer.valueOf(slTB);

				THIETBI tb_them = new THIETBI();
				tb_them.setMatb(thietBi);
				tb_them.setSoluong(slThietBi);
				// Tìm kiếm mã thiết bị xem đã tồn tại hay chưa, luôn check trước khi thêm

				boolean check = false;
				for (THIETBI ele : list) {
					if (thietBi.compareTo(ele.getMatb()) == 0) {
						ele.setSoluong((ele.getSoluong() + slThietBi));
						check = true;
						break;
					}
				}
				if (check == false)
					list.add(tb_them);
			}

		}

		for (THIETBI ele : list) {
			CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
			ct_tb.setThietbi_muon(new ThietBiDAO().getById(ele.getMatb(), factory));
			ct_tb.setSoluong(ele.getSoluong());
			ct_tb.setPhieumuon(phieumuon_moi);
			result = new CT_PhieuMuonDAO().save(factory, ct_tb);
		}

		model.addAttribute("insert", result);
		return home(model, session);

	}

	@RequestMapping(value = "phieumuon/edit/{mapm}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
			@PathVariable("mapm") String mapm, HttpSession session) {
		model.addAttribute("form_edit", true);
		phieumuon_sua = new PhieuMuonDAO().getById(mapm, factory);
		model.addAttribute("indexValue", 0);
		model.addAttribute("slThietBiSua", phieumuon_sua.getCt_phieumuons().size());
		model.addAttribute("phieumuon_sua", phieumuon_sua);
		return home(model, session);
	}

	@RequestMapping(value = "phieumuon/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
			BindingResult result, @RequestParam("indexValue") Integer indexValue,
			@RequestParam("demValue") Integer demValue, HttpSession session, HttpServletRequest rq) {

		System.out.println("has error: " + result.hasErrors());
		model.addAttribute("sua_saidinhdang", result.hasErrors());
		model.addAttribute("phieumuon_sua", phieumuon_sua);
		// Nếu có lỗi thì quay lại chương trình
		if (result.hasErrors())
			return home(model, session);

		Boolean result1 = false;
		// lấy phiếu mượn bằng mã của phiếu mượn sửa.
		PHIEUMUON phieumuon_cansua = new PhieuMuonDAO().getById(phieumuon_sua.getMapm(), factory);

		/// Nếu thời gian trả tồn tại, thì không có quyền sửa
		if (phieumuon_cansua.getThoigiantra() != null) {
			model.addAttribute("update", false);
		}
		// Có quyền chỉnh sửa
		else {

			// Xóa hết các ct phiếu mượn hiện tại
			for (CT_PHIEUMUON elem : phieumuon_cansua.getCt_phieumuons()) {
				new CT_PhieuMuonDAO().delete(factory, elem);
			}

			// Nếu đếm value == 0 tức là người dùng đã bỏ chọn hết thiết bị, sẽ xóa luôn
			// phiếu mượn.
			if (demValue == 0) {

				// Xử lý thông báo thêm thành công
				model.addAttribute("update", new PhieuMuonDAO().delete(factory, phieumuon_sua));
				return home(model, session);
			}

			// Các trường hợp chỉnh sửa: thêm, điều chỉnh, bớt các thiết bị.
			// Khởi tạo thằng trả về bằng false

			// Thêm từng cái CT_ThietBi
//			int i = 1;
//			for (i = 1; i <= indexValue; i++) {
//				// Tạo phần tử để requestParameter
//				String tb = "thietBi" + i;
//				String slTb = "slThietBi" + i;
//
//				// Phần tử chứ requestParameter
//				String thietBi = rq.getParameter(tb);
//				String slTB = rq.getParameter(slTb);
//				// chưa request chuỗi
//				if (thietBi != "" && slTB != null) {
//					// Chuyển slThietBi thành số
//					int slThietBi = Integer.valueOf(slTB);
//
//					CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
//					ct_tb.setThietbi_muon(new ThietBiDAO().getById(thietBi, factory));
//					ct_tb.setSoluong(slThietBi);
//					ct_tb.setPhieumuon(phieumuon_cansua);
//
//					result1 = new CT_PhieuMuonDAO().save(factory, ct_tb);
// Thêm từng cái CT_ThietBi
			int i = 1;
			List<THIETBI> list = new ArrayList<>();
			for (i = 1; i <= indexValue; i++) {
				// Tạo phần tử để requestParameter
				String tb = "thietBi" + i;
				String slTb = "slThietBi" + i;

				// Phần tử chứ requestParameter

				String slTB = rq.getParameter(slTb);
				String thietBi = rq.getParameter(tb);

				if (thietBi != "" && slTB != null) {
					int slThietBi = Integer.valueOf(slTB);

					THIETBI tb_them = new THIETBI();
					tb_them.setMatb(thietBi);
					tb_them.setSoluong(slThietBi);
					// Tìm kiếm mã thiết bị xem đã tồn tại hay chưa, luôn check trước khi thêm

					boolean check = false;
					for (THIETBI ele : list) {
						if (thietBi.compareTo(ele.getMatb()) == 0) {
							ele.setSoluong((ele.getSoluong() + slThietBi));
							check = true;
							break;
						}
					}
					if (check == false)
						list.add(tb_them);
				}

			}

			for (THIETBI ele : list) {
				CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
				ct_tb.setThietbi_muon(new ThietBiDAO().getById(ele.getMatb(), factory));
				ct_tb.setSoluong(ele.getSoluong());
				ct_tb.setPhieumuon(phieumuon_cansua);
				result1 = new CT_PhieuMuonDAO().save(factory, ct_tb);
			}
			model.addAttribute("update", new PhieuMuonDAO().update(factory, phieumuon_cansua));

		}
		model.addAttribute("update", result1);
		return home(model, session);
	}

	// Delete
	@RequestMapping(value = "phieumuon/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("mapm") String mapm, HttpSession session) {
		PHIEUMUON phieumuon_xoa = new PhieuMuonDAO().getById(mapm, factory);
		if (phieumuon_xoa.getThoigiantra() != null) {
			model.addAttribute("delete", false);
		} else {
			// Nó có thể vướng những cái ct_phieumuon nên không xóa được. kiểm tra. done
			for (CT_PHIEUMUON elem : phieumuon_xoa.getCt_phieumuons()) {
				new CT_PhieuMuonDAO().delete(factory, elem);
			}
			PHIEUMUON phieumuon_canxoa = new PHIEUMUON();
			phieumuon_canxoa.setMapm(mapm);
			model.addAttribute("delete", new PhieuMuonDAO().delete(factory, phieumuon_canxoa));
		}
		return home(model, session);

	}
}
