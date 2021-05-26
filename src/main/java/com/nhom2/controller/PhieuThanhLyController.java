
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


	// DANH SÁCH LOẠI THIẾT BỊ ĐỂ SELECT
	@ModelAttribute("loaiThietBis")
	public List<THIETBI> getLoaiThietBis() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "phieuthanhly", method = RequestMethod.GET)
	public String home(ModelMap model, HttpSession session) {
		model.addAttribute("listPhieuThanhLy", new PhieuThanhLyDAO().getAll(factory));
		model.addAttribute("indexValue", 0);
		return "phieuthanhly/ds_phieu_thanh_ly";
	}

	@RequestMapping(value = "phieuthanhly", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("phieuthanhly_moi") PHIEUTHANHLY phieuthanhly_moi,
			BindingResult reusult, @RequestParam("indexValue") Integer indexValue,
			@RequestParam("demValue") Integer demValue, HttpSession session, HttpServletRequest rq) {

		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("phieuthanhly_moi", phieuthanhly_moi);

		// nếu có lỗi thì quay về chương trình
		if (reusult.hasErrors())
			return home(model, session);

		// Kiểm tra giá trị của index bên view
		if (indexValue == 0) {
			System.out.print("co gia tri");
		}
		// nếu số lượng các phần tử khác không thì tạo 1 Phiếu thanh lý
		if (demValue != 0) {
			new PhieuThanhLyDAO().save(factory, phieuthanhly_moi);
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
			CT_PHIEUTHANHLY ct_tb = new CT_PHIEUTHANHLY();
			ct_tb.setThietbi_thanhly(new ThietBiDAO().getById(ele.getMatb(), factory));
			ct_tb.setSoluong(ele.getSoluong());
			ct_tb.setPhieuthanhly(phieuthanhly_moi);
			result = new CT_PhieuThanhLyDAO().save(factory, ct_tb);
		}

		model.addAttribute("insert", result);
		return home(model, session);

	}

	@RequestMapping(value = "phieuthanhly/edit/{maptl}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("phieuthanhly_sua") PHIEUTHANHLY phieuthanhly_sua,
			@PathVariable("maptl") String maptl, HttpSession session) {
		model.addAttribute("form_edit", true);
		phieuthanhly_sua = new PhieuThanhLyDAO().getById(maptl, factory);
		model.addAttribute("indexValue", 0);
		model.addAttribute("slThietBiSua", phieuthanhly_sua.getCt_phieuthanhlys().size());
		model.addAttribute("phieuthanhly_sua", phieuthanhly_sua);
		return home(model, session);
	}

	@RequestMapping(value = "phieuthanhly/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("phieuthanhly_sua") PHIEUTHANHLY phieuthanhly_sua,
			BindingResult result, @RequestParam("indexValue") Integer indexValue,
			@RequestParam("demValue") Integer demValue, HttpSession session, HttpServletRequest rq) {

		System.out.println("has error: " + result.hasErrors());
		model.addAttribute("sua_saidinhdang", result.hasErrors());
		model.addAttribute("phieuthanhly_sua", phieuthanhly_sua);
		// Nếu có lỗi thì quay lại chương trình
		if (result.hasErrors())
			return home(model, session);

		Boolean result1 = false;
		// lấy phiếu TL bằng mã của phiếu TL sửa.
		PHIEUTHANHLY phieuthanhly_cansua = new PhieuThanhLyDAO().getById(phieuthanhly_sua.getMaptl(), factory);

		/// Nếu thời gian tl tồn tại, thì không có quyền sửa
		if (phieuthanhly_cansua.getThoigian() != null) {
			model.addAttribute("update", false);
		}
		// Có quyền chỉnh sửa
		else {

			// Xóa hết các ct phiếu tl hiện tại
			for (CT_PHIEUTHANHLY elem : phieuthanhly_cansua.getCt_phieuthanhlys()) {
				new CT_PhieuThanhLyDAO().delete(factory, elem);
			}

			// Nếu đếm value == 0 tức là người dùng đã bỏ chọn hết thiết bị, sẽ xóa luôn
			// phiếu mượn.
			if (demValue == 0) {

				// Xử lý thông báo thêm thành công
				model.addAttribute("update", new PhieuThanhLyDAO().delete(factory, phieuthanhly_sua));
				return home(model, session);
			}

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
				CT_PHIEUTHANHLY ct_tb = new CT_PHIEUTHANHLY();
				ct_tb.setThietbi_thanhly(new ThietBiDAO().getById(ele.getMatb(), factory));
				ct_tb.setSoluong(ele.getSoluong());
				ct_tb.setPhieuthanhly(phieuthanhly_cansua);
				result1 = new CT_PhieuThanhLyDAO().save(factory, ct_tb);
			}
			model.addAttribute("update", new PhieuThanhLyDAO().update(factory, phieuthanhly_cansua));

		}
		model.addAttribute("update", result1);
		return home(model, session);
	}

	// Delete
	@RequestMapping(value = "phieuthanhly/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("maptl") String maptl, HttpSession session) {
		PHIEUTHANHLY phieuthanhly_xoa = new PhieuThanhLyDAO().getById(maptl, factory);
		if (phieuthanhly_xoa.getThoigian() != null) {
			model.addAttribute("delete", false);
		} else {
			// Nó có thể vướng những cái ct_phieuthanhly nên không xóa được. kiểm tra. done
			for (CT_PHIEUTHANHLY elem : phieuthanhly_xoa.getCt_phieuthanhlys()) {
				new CT_PhieuThanhLyDAO().delete(factory, elem);
			}
			PHIEUTHANHLY phieuthanhly_canxoa = new PHIEUTHANHLY();
			phieuthanhly_canxoa.setMaptl(maptl);
			model.addAttribute("delete", new PhieuThanhLyDAO().delete(factory, phieuthanhly_canxoa));
		}
		return home(model, session);

	}
}
