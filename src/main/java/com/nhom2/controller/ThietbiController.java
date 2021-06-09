package com.nhom2.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.nhom2.DAO.LoaiThietBiDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.LOAITHIETBI;
import com.nhom2.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/")
public class ThietbiController {

	public ThietbiController() {
		super();
	}

	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "thiet-bi", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listThietbi", new ThietBiDAO().getAll(factory));
		return "thiet_bi/ds_thiet_bi";
	}

	// THIẾT BỊ ĐỂ THÊM
	@ModelAttribute("thietbi_moi")
	public THIETBI thietbi_moi() {
		return new THIETBI();
	}

	// THIẾT BỊ ĐỂ SỬA
	@ModelAttribute("thietbi_sua")
	public THIETBI thietbi_sua() {
		return new THIETBI();
	}

	// DANH SÁCH LOẠI THIẾT BỊ ĐỂ SELECT
	@ModelAttribute("loaiThietBis")
	public List<LOAITHIETBI> getLoaiThietBis() {
		List<LOAITHIETBI> list = new LoaiThietBiDAO().getAll(factory);
		return list;
	}

	// DANH SÁCH TÌNH TRẠNG ĐỂ SELECT
	@ModelAttribute("tinhTrangs")
	public List<String> getTinhTrangs() {
		List<String> list = new ArrayList<>();
		list.add("lock");
		list.add("unlocked");
		return list;
	}

	// THÊM
	@RequestMapping(value = "thiet-bi", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("thietbi_moi") THIETBI thietbi_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("thietbi_moi", thietbi_moi);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new ThietBiDAO().save(factory, thietbi_moi)); // Xử lý thông báo thêm thành công

		return home(model);
	}

	// LẤY RA THIẾT BỊ BẰNG ID ĐỂ SHOW FORM EDIT
	@RequestMapping(value = "thiet-bi/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("thietbi_sua") THIETBI thietbi_sua,
			@PathVariable String id) {

		model.addAttribute("form_edit", true);
		model.addAttribute("thietbi_sua", new ThietBiDAO().getById(id, factory));
		return home(model);
	}

	// UPDATE
	@RequestMapping(value = "thiet-bi/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("thietbi_sua") THIETBI thietbi_sua,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", reusult.hasErrors());
		model.addAttribute("thietbi_sua", thietbi_sua);
		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("update", new ThietBiDAO().update(factory, thietbi_sua));
		return home(model);
	}

	// DELETE
	@RequestMapping(value = "thiet-bi/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("id") String id) {
		System.out.println("id = " + id);
		THIETBI thietbi_xoa = new THIETBI();
		thietbi_xoa.setMatb(id);
		model.addAttribute("delete", new ThietBiDAO().del(factory, thietbi_xoa));
		return home(model);
	}

	// IMPORT EXCEL
	@RequestMapping(value = "import-excel", method = RequestMethod.POST)
	public RedirectView importExcell(@RequestParam("file") MultipartFile input, RedirectAttributes model)
			throws IOException {
		if (!input.getContentType().toString()
				.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			model.addFlashAttribute("insert", false);
			return new RedirectView("thiet-bi");
		}
		XSSFWorkbook workbook = new XSSFWorkbook(input.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		boolean kq = true;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			THIETBI thietbi = new THIETBI();
			System.out.println(row.getCell(0));
			System.out.println(row.getCell(1));
			System.out.println(row.getCell(2));

//			product.setProduct_id(getNewId());
//			product.setNhanvien_id(nhanvien.getUsers_id());
//			product.setProduct_name(row.getCell(0).toString());
//			product.setProduct_price((int) row.getCell(1).getNumericCellValue());
//			product.setProduct_discount((int) row.getCell(2).getNumericCellValue());
//			product.setProduct_danhmuc((int) row.getCell(3).getNumericCellValue());
//			product.setProduct_image(row.getCell(4).toString());
//			product.setProduct_soluongtonkho((int) row.getCell(5).getNumericCellValue());
//			product.setProduct_thuonghieu(row.getCell(6).toString());
//			product.setProduct_sanxuat(row.getCell(7).toString());
//			product.setProduct_thanhphan(row.getCell(8).toString());
//			product.setProduct_thetich(row.getCell(9).toString());
//			product.setProduct_baoquan(row.getCell(10).toString());
//			product.setProduct_sudung(row.getCell(11).toString());
//			product.setProduct_nsx(row.getCell(12).getLocalDateTimeCellValue().format(formatter).toString());
//			product.setProduct_hsd(row.getCell(13).getLocalDateTimeCellValue().format(formatter).toString());
//			product.setMaNCC((int) Double.parseDouble(row.getCell(14).toString()));
//			product.setProduct_content(row.getCell(15).toString());
//			kq = _pro.save(product);
//			if (kq)
//				new phieunhapDao().themVaoPhieuNhap(product);
//			else
//				break;
		}
		model.addFlashAttribute("insert", kq);
		return new RedirectView("thiet-bi");
	}

}
