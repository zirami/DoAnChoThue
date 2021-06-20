package com.nhom2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
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

	String msg = "Thao tác thất bại";

	public String getRandomMa() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		int ma = 1001;
		String id = "tb" + ma;
		while (list.contains(new ThietBiDAO().getById(id, factory))) {
			ma++;
			id = "tb" + ma;
		}
		return id;
	}

	@RequestMapping(value = "thiet-bi", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listThietbi", new ThietBiDAO().getAll(factory));
		model.addAttribute("maThietBi", getRandomMa());
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
			HttpServletRequest rq, @RequestParam("photo") MultipartFile photo, BindingResult reusult) {
		String UNLOCKED = "unlocked"; // Trạng thái mở khoá của THIẾT BỊ khi không dính PHIẾU MƯỢN, PHIẾU NHẬP
		String LOCKED = "locked"; // Trạng thái mở khoá của THIẾT BỊ khi không dính PHIẾU MƯỢN, PHIẾU NHẬP
		String CONTOT = "Còn tốt"; // Tình trạng mặc định là Tốt

		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("thietbi_moi", thietbi_moi);
		if (reusult.hasErrors())
			return home(model);
		String path = rq.getServletContext().getRealPath("");
		path = path.substring(0, path.indexOf(".metadata")) + "\\" + rq.getServletContext().getContextPath()
				+ "\\src\\main\\webapp\\resources\\files\\";
		int kqSave = saveFile(path, photo, rq);
		if (kqSave > 0)
			thietbi_moi.setHinh(photo.getOriginalFilename());
		else if (kqSave < 0 || photo.isEmpty()) {
			model.addAttribute("insert", false); // Xử lý thông báo thất bại
			return home(model);
		}
		thietbi_moi.setSoluong(0);
		thietbi_moi.setTrangthai(UNLOCKED);
		thietbi_moi.setTinhtrang("Còn Tốt");
		model.addAttribute("insert", new ThietBiDAO().save(factory, thietbi_moi));
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
	public RedirectView update(@Valid @ModelAttribute("thietbi_sua") THIETBI thietbi_sua, HttpServletRequest rq,
			@RequestParam("photo") MultipartFile photo, BindingResult reusult, RedirectAttributes model) {
		System.out.println("has error: " + reusult.getFieldErrors().toString());
		model.addFlashAttribute("sua_saidinhdang", reusult.hasErrors());
		model.addFlashAttribute("thietbi_sua", thietbi_sua);
		if (reusult.hasErrors())
			return new RedirectView("../thiet-bi");
		String path = rq.getServletContext().getRealPath("");

		path = path.substring(0, path.indexOf(".metadata")) + "\\" + rq.getServletContext().getContextPath()
				+ "\\src\\main\\webapp\\resources\\files\\";
		int kqSave = saveFile(path, photo, rq);
		if (kqSave > 0)
			thietbi_sua.setHinh(photo.getOriginalFilename());
		else if (kqSave < 0) {
			model.addFlashAttribute("update", false); // Xử lý thông báo thất bại
			return new RedirectView("../thiet-bi");
		}
		model.addFlashAttribute("update", new ThietBiDAO().update(factory, thietbi_sua)); // Xử lý thông báo thành công

		return new RedirectView("../thiet-bi");
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
			THIETBI thietbi_them = new THIETBI();
			thietbi_them.setMatb(getRandomMa());
			thietbi_them.setTen(row.getCell(1).toString());
			thietbi_them.setLoai(new LoaiThietBiDAO().getById((int) row.getCell(2).getNumericCellValue(), factory));
			thietbi_them.setHinh(row.getCell(3).toString());
			thietbi_them.setGhichu(row.getCell(4).toString());
			thietbi_them.setSoluong(0);
			thietbi_them.setTinhtrang("Còn Tốt");
			thietbi_them.setTrangthai("unlocked");

			if (checkThietbi(thietbi_them)) {
				kq = false;
				break;
			}

			kq = new ThietBiDAO().save(factory, thietbi_them);
			System.out.println(thietbi_them);
			if (!kq)
				break;
		}
		model.addFlashAttribute("insert", kq);
		model.addFlashAttribute("msg", msg);
		return new RedirectView("thiet-bi");
	}

	private boolean checkThietbi(THIETBI thietbi) {
		if (thietbi.getTen().isBlank() || thietbi.getLoai().getId().equals(null) || thietbi.getHinh().isBlank())
			return true;
		return false;
	}

}
