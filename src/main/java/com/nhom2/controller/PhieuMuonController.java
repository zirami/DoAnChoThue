
package com.nhom2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import com.nhom2.DAO.CT_PhieuMuonDAO;
import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.DAO.NhanVienDAO;
import com.nhom2.DAO.PhieuMuonDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.ACCOUNT;
import com.nhom2.entity.CT_PHIEUMUON;
import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.NHANVIEN;
import com.nhom2.entity.PHIEUMUON;
import com.nhom2.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/")
public class PhieuMuonController {

	@Autowired
	SessionFactory factory;

	@Autowired
	JavaMailSender mailer;

	public String getRandomMa() {
		List<PHIEUMUON> list = new PhieuMuonDAO().getAll(factory);
		int ma = 1001;
		String id = "pm" + ma;
		while (list.contains(new PhieuMuonDAO().getById(id, factory))) {
			ma++;
			id = "pm" + ma;
		}
		return id;
	}

	@ModelAttribute("tieude_mail")
	public String tieude_mail() {
		return "C???nh b??o m?????n thi???t b???!!!";
	}

	@ModelAttribute("noidung_mail")
	public String noidung_mail() {
		return "Ph??ng C?? S??? V???t Ch???t xin th??ng b??o vi???c m?????n thi???t b??? c???a b???n ???? qu?? 3 ng??y. "
				+ "Vui l??ng ho??n t???t th??? t???c tr??? thi???t b??? m?????n.";
	}
	
	@ModelAttribute("today")
	public LocalDate getToday() {
		return LocalDate.now();
	}

	@ModelAttribute("phieumuon_moi")
	public PHIEUMUON phieumuon_moi() {
		return new PHIEUMUON();
	}

	@ModelAttribute("phieumuon_sua")
	public PHIEUMUON phieumuon_sua() {
		return new PHIEUMUON();
	}

	@ModelAttribute("phieumuon_chitiet")
	public PHIEUMUON phieumuon_chitiet() {
		return new PHIEUMUON();
	}

	// Load danh s??ch nh??n vi??n
	@ModelAttribute("listNhanViens")
	public List<NHANVIEN> getListNhanViens() {
		List<NHANVIEN> list = new NhanVienDAO().getAll(factory);
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

	// Load danh s??ch ng?????i m?????n
	@ModelAttribute("listNguoiMuons")
	public List<NGUOIMUON> getListNguoiMuon() {
		List<NGUOIMUON> list = new NguoiMuonDAO().getAll(factory);
		return list;
	}

	// DANH S??CH LO???I THI???T B??? ????? SELECT
	@ModelAttribute("loaiThietBis")
	public List<THIETBI> getLoaiThietBis() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "phieumuon", method = RequestMethod.GET)
	public String home(ModelMap model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		ACCOUNT account = (ACCOUNT) session.getAttribute("account_login");

		model.addAttribute("account_login", account);
		model.addAttribute("listPhieuMuon", new PhieuMuonDAO().getAll(factory));
		model.addAttribute("maphieumuon", getRandomMa());
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

		// n???u c?? l???i th?? quay v??? ch????ng tr??nh
		if (reusult.hasErrors())
			return home(model, session, rq);
//
//		// Ki???m tra gi?? tr??? c???a index b??n view
//		if (indexValue == 0) {
//			System.out.print("co gia tri");
//		}
		if (phieumuon_moi.getThoigiantra() != null) {
			if (phieumuon_moi.tinhKhoangCachHaiNgay_Date(phieumuon_moi.getThoigianmuon(),
					phieumuon_moi.getThoigiantra()) <= 0) {
				model.addAttribute("phieumuon_moi", new PHIEUMUON());
				model.addAttribute("failDate", true);
				return home(model, session, rq);
			}
		}

		// n???u s??? l?????ng c??c ph???n t??? kh??c kh??ng th?? t???o 1 Phi???u m?????n
		if (demValue != 0) {

			new PhieuMuonDAO().save(factory, phieumuon_moi);

		} else {
//			model.addAttribute("phieumuon_moi",new PHIEUMUON());
			model.addAttribute("emptyThietBi", true);
			return home(model, session, rq);
		}
		// Kh???i t???o th???ng tr??? v??? b???ng false
		Boolean result = false;

//		// Th??m t???ng c??i CT_ThietBi

		// Tr?????ng h???p c?? s??? l?????ng nh??ng kh??ng ch???n thi???t b???, th??ng b??o
		boolean isEmptyThietBi = false;

		int i = 1;
		List<THIETBI> list = new ArrayList<>();
		for (i = 1; i <= indexValue; i++) {
			// T???o ph???n t??? ????? requestParameter
			String tb = "thietBi" + i;
			String slTb = "slThietBi" + i;

			// Ph???n t??? ch??? requestParameter

			String slTB = rq.getParameter(slTb);
			String thietBi = rq.getParameter(tb);

			if (thietBi != "" && slTB != null) {
				int slThietBi = Integer.valueOf(slTB);

				THIETBI tb_them = new THIETBI();
				tb_them.setMatb(thietBi);
				tb_them.setSoluong(slThietBi);
				// T??m ki???m m?? thi???t b??? xem ???? t???n t???i hay ch??a, lu??n check tr?????c khi th??m

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
			if (thietBi == "" && slTB != null) {
				isEmptyThietBi = true;
				break;
			}

		}

		// X??? l?? ph???n thi???t b??? r???ng ho???c ko c?? thi???t b???
		if (isEmptyThietBi) {
			new PhieuMuonDAO().delete(factory, phieumuon_moi);
			model.addAttribute("phieumuon_moi", phieumuon_moi);
			model.addAttribute("emptyThietBi", true);
			return home(model, session, rq);
		}
		if (list.isEmpty()) {
			new PhieuMuonDAO().delete(factory, phieumuon_moi);
			model.addAttribute("phieumuon_moi", phieumuon_moi);
			model.addAttribute("emptyThietBi", true);
			return home(model, session, rq);
		}

		/// Ki???m tra c?? thi???t b??? n??o ??m kh??ng
		for (THIETBI ele : list) {
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);

			float checkSoLuong = thietbii.getSoluong() - ele.getSoluong();

			if (checkSoLuong < 0) {
				new PhieuMuonDAO().delete(factory, phieumuon_moi);
				model.addAttribute("phieumuon_moi", new PHIEUMUON());
				model.addAttribute("insert", false);
				return home(model, session, rq);
			}

		}

		String LOCKED = "locked";
		for (THIETBI ele : list) {
			CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);
			thietbii.setTrangthai(LOCKED);
			ct_tb.setThietbi_muon(thietbii);
			ct_tb.setSoluong(ele.getSoluong());
			ct_tb.setPhieumuon(phieumuon_moi);
			result = new CT_PhieuMuonDAO().save(factory, ct_tb);

			thietbii.setSoluong(thietbii.getSoluong() - ele.getSoluong());
			new ThietBiDAO().update(factory, thietbii);

		}

		result = new PhieuMuonDAO().update(factory, phieumuon_moi);
		model.addAttribute("insert", result);
		return home(model, session, rq);

	}

	@RequestMapping(value = "phieumuon/edit/{mapm}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
			@PathVariable("mapm") String mapm, HttpSession session, HttpServletRequest request) {
		model.addAttribute("form_edit", true);
		phieumuon_sua = new PhieuMuonDAO().getById(mapm, factory);
		model.addAttribute("indexValue", 0);
		model.addAttribute("slThietBiSua", phieumuon_sua.getCt_phieumuons().size());
		model.addAttribute("phieumuon_sua", phieumuon_sua);
		return home(model, session, request);
	}

	@RequestMapping(value = "phieumuon/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
			BindingResult result, @RequestParam("indexValue") Integer indexValue,
			@RequestParam("demValue") Integer demValue, HttpSession session, HttpServletRequest rq) {

		System.out.println("has error: " + result.hasErrors());
		model.addAttribute("sua_saidinhdang", result.hasErrors());
		model.addAttribute("phieumuon_sua", phieumuon_sua);
		// N???u c?? l???i th?? quay l???i ch????ng tr??nh
		if (result.hasErrors())
			return home(model, session, rq);

		// ki???m tra ng??y tr??? c?? h???p l??? hay kh??ng.
		if (phieumuon_sua.getThoigiantra() != null) {
			if (phieumuon_sua.tinhKhoangCachHaiNgay_Date(phieumuon_sua.getThoigianmuon(),
					phieumuon_sua.getThoigiantra()) <= 0) {
				model.addAttribute("phieumuon_sua", new PHIEUMUON());
				model.addAttribute("failDate", true);
				return home(model, session, rq);
			}
		}

		Boolean result1 = false;
		// l???y phi???u mua b???ng m?? c???a phi???u mua s???a.
		PHIEUMUON phieumuon_cansua = new PhieuMuonDAO().getById(phieumuon_sua.getMapm(), factory);

		// Ki???m tra ng??y tr??? ???? c?? trong database ch??a, n???u c?? r???i th?? ko cho ch???nh s???a.
		if (phieumuon_cansua.getThoigiantra() != null) {
			model.addAttribute("phieumuon_sua", new PHIEUMUON());
			model.addAttribute("update", false);
			return home(model, session, rq);
		}

		List<CT_PHIEUMUON> backup = new ArrayList<>();
		// X??a h???t c??c ct phi???u mua hi???n t???i
		for (CT_PHIEUMUON elem : phieumuon_cansua.getCt_phieumuons()) {

			backup.add(elem);
			new CT_PhieuMuonDAO().delete(factory, elem);
		}

		// N???u ?????m value == 0 t???c l?? ng?????i d??ng ???? b??? ch???n h???t thi???t b???, s??? x??a lu??n
		// phi???u muon.
		if (demValue == 0) {

			// Th??m th??ng b??o th??nh c??ng khi x??a phi???u muon.
			model.addAttribute("update", new PhieuMuonDAO().delete(factory, phieumuon_sua));
			for (CT_PHIEUMUON elem : backup) {
				// ph???c h???i s??? l?????ng c???a thi???t b???
				THIETBI thietbii = new ThietBiDAO().getById(elem.getThietbi_muon().getMatb(), factory);
				thietbii.setSoluong(thietbii.getSoluong() + elem.getSoluong());
				new ThietBiDAO().update(factory, thietbii);
			}
			return home(model, session, rq);
		}

		// Tr?????ng h???p c?? s??? l?????ng nh??ng kh??ng ch???n thi???t b???, th??ng b??o
		boolean isEmptyThietBi = false;

		// Th??m t???ng c??i CT_ThietBi
		int i = 1;
		List<THIETBI> list = new ArrayList<>();
		for (i = 1; i <= indexValue; i++) {
			// T???o ph???n t??? ????? requestParameter
			String tb = "thietBi" + i;
			String slTb = "slThietBi" + i;

			// Ph???n t??? ch??? requestParameter

			String slTB = rq.getParameter(slTb);
			String thietBi = rq.getParameter(tb);

			if (thietBi != "" && slTB != null) {
				int slThietBi = Integer.valueOf(slTB);

				THIETBI tb_them = new THIETBI();
				tb_them.setMatb(thietBi);
				tb_them.setSoluong(slThietBi);
				// T??m ki???m m?? thi???t b??? xem ???? t???n t???i hay ch??a, lu??n check tr?????c khi th??m

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
			if (thietBi == "" && slTB != null) {
				isEmptyThietBi = true;
				break;
			}

		}

		// X??? l?? ph???n thi???t b??? r???ng ho???c ko c?? thi???t b???
		if (isEmptyThietBi) {
			model.addAttribute("emptyThietBi", true);
		}
		if (list.isEmpty()) {

			model.addAttribute("emptyThietBi", true);

		}

		/// Ki???m tra c?? thi???t b??? n??o ??m kh??ng
		for (THIETBI ele : list) {
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);

			float soluongbackup = 0;
			for (CT_PHIEUMUON elem : backup) {
				if (elem.getThietbi_muon().getMatb() == thietbii.getMatb()) {
					soluongbackup = elem.getSoluong();
					break;
				}
			}

			float checkSoLuong = thietbii.getSoluong() - ele.getSoluong() + soluongbackup;

			if (checkSoLuong < 0) {
//				new PhieuMuonDAO().delete(factory, phieumuon_cansua);

				for (CT_PHIEUMUON elem : backup) {
					CT_PHIEUMUON ct_phieumuon_backup = new CT_PHIEUMUON();
					ct_phieumuon_backup.setPhieumuon(phieumuon_cansua);
					ct_phieumuon_backup.setSoluong(elem.getSoluong());
					ct_phieumuon_backup.setThietbi_muon(elem.getThietbi_muon());
					new CT_PhieuMuonDAO().save(factory, ct_phieumuon_backup);
				}

				model.addAttribute("phieumuon_sua", new PHIEUMUON());
				model.addAttribute("update", false);
				return home(model, session, rq);
			}

		}

		String LOCKED = "locked";
		for (THIETBI ele : list) {
			CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);
			thietbii.setTrangthai(LOCKED);
			ct_tb.setThietbi_muon(thietbii);
			ct_tb.setSoluong(ele.getSoluong());
			ct_tb.setPhieumuon(phieumuon_cansua);
			result1 = new CT_PhieuMuonDAO().save(factory, ct_tb);

			thietbii.setSoluong(thietbii.getSoluong() - ele.getSoluong());
			new ThietBiDAO().update(factory, thietbii);

		}

		model.addAttribute("update", new PhieuMuonDAO().update(factory, phieumuon_sua));

		// tr??? v??? gi?? tr??? cho database khi x??a CT_PHIEUMUON
		for (CT_PHIEUMUON elem : backup) {
			// ph???c h???i s??? l?????ng c???a thi???t b???
			THIETBI thietbii = new ThietBiDAO().getById(elem.getThietbi_muon().getMatb(), factory);
			thietbii.setSoluong(thietbii.getSoluong() + elem.getSoluong());
			new ThietBiDAO().update(factory, thietbii);
		}

		model.addAttribute("update", result1);
		return home(model, session, rq);
	}

	// Delete
	@RequestMapping(value = "phieumuon/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("mapm") String mapm, HttpSession session,
			HttpServletRequest rq) {
		PHIEUMUON phieumuon_xoa = new PhieuMuonDAO().getById(mapm, factory);
		if (phieumuon_xoa.getThoigiantra() != null) {
			model.addAttribute("delete", false);
		} else {
			// N?? c?? th??? v?????ng nh???ng c??i ct_phieumuon n??n kh??ng x??a ???????c. ki???m tra. done
			for (CT_PHIEUMUON elem : phieumuon_xoa.getCt_phieumuons()) {
				new CT_PhieuMuonDAO().delete(factory, elem);
			}
			PHIEUMUON phieumuon_canxoa = new PHIEUMUON();
			phieumuon_canxoa.setMapm(mapm);
			model.addAttribute("delete", new PhieuMuonDAO().delete(factory, phieumuon_canxoa));
		}
		return home(model, session, rq);
	}

	@RequestMapping(value = "phieumuon/mail", method = RequestMethod.POST)
	public String sendMail(ModelMap model, @RequestParam("tieude_mail") String tieude_mail,
			@RequestParam("noidung_mail") String noidung_mail, HttpSession session, HttpServletRequest rq) {
		List<PHIEUMUON> list = new PhieuMuonDAO().getAll(factory);

		for (PHIEUMUON elem : list) {
			if (elem.getThoigiantra() == null) {
				double songay = elem.laySoNgay_Date(elem.getThoigianmuon());
				if (songay >= 3) {
					String from = "thanhthang32k@gmail.com";
					String to = elem.getNm().getGmail();
					String subject = tieude_mail;
					String body = "<!DOCTYPE html>\r\n"
							+ "<!-- saved from url=(0042)file:///C:/Users/n18dc/Downloads/test.html -->\r\n"
							+ "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
							+ "	<style>\r\n" + "table, td, th {\r\n" + "  border: 1px solid black;\r\n" + "}\r\n"
							+ "\r\n" + "table {\r\n" + "  font-family: Arial, Helvetica, sans-serif;\r\n"
							+ "  border-collapse: collapse;\r\n" + "  width: 100%;\r\n" + "}\r\n" + "\r\n"
							+ "table, td, th {\r\n" + "  border: 1px solid #432874;\r\n" + "  padding: 8px;\r\n"
							+ "}\r\n" + "\r\n" + "table tr:nth-child(even){background-color: #f2f2f2;}\r\n" + "\r\n"
							+ "table tr:hover {background-color: #ecc24e;}\r\n" + "\r\n" + "table th {\r\n"
							+ "  padding-top: 12px;\r\n" + "  padding-bottom: 12px;\r\n" + "  text-align: left;\r\n"
							+ "  background-color: #432874;\r\n" + "  color: white;\r\n" + "}\r\n" + "</style>\r\n"
							+ "</head>\r\n" + "<body>\r\n"
							+ "	<div style=\"border: 5px solid #17a2b8;margin: 20px;padding: 20px; width: 1000px;\">\r\n"
							+ "		<h2 style=\"text-align: center; color: #17a2b8; font-size: 35px\">Qu???n l?? thi???t b??? H???c Vi???n C?? S???</h2>\r\n"
							+ "		<h4 style=\"text-align: left;\"> Ch??o b???n " + elem.getNm().getHo() + " "
							+ elem.getNm().getTen() + "! </h4>\r\n"
							+ "	<p  style=\"font-size: 18px;  font-family: &#39;Times New Roman&#39;\">" + noidung_mail
							+ "</p>\r\n" + "		\r\n" + "		<h2>Danh s??ch c??c thi???t b??? m?????n g???m: </h2>\r\n"
							+ "		<div>\r\n"
							+ "			<table style=\"width: 100%;border-collapse: collapse;\">	\r\n"
							+ "			<colgroup>\r\n" + "				<col span=\"1\" style=\"width: 15%\">\r\n"
							+ "				<col span=\"1\" style=\"width: 50%\">\r\n"
							+ "				<col span=\"1\" style=\"width: 35%\">\r\n"

							+ "			</colgroup>												\r\n"
							+ "				<thead>\r\n" + "					<tr>\r\n"
							+ "						<th style=\"text-align: center;\">M?? thi???t b???</th>\r\n"
							+ "						<th style=\"text-align: center;\">T??n thi???t b???</th>\r\n"
							+ "						<th style=\"text-align: center;\">S??? l?????ng m?????n</th>\r\n"

							+ "					</tr>													\r\n"
							+ "				</thead>			\r\n"
							+ "				<tbody  style=\"text-align: center;\">\r\n";
					for (CT_PHIEUMUON e : elem.getCt_phieumuons()) {
						body += "<tr>\r\n" + " <td>" + e.getThietbi_muon().getMatb() + "</td>\r\n" + " <td>"
								+ e.getThietbi_muon().getTen() + "</td>\r\n" + " <td>" + e.getSoluong() + "</td>\r\n"
								+ "</tr>\r\n";
					}

					body += "\r\n" + "\r\n" + "				</tbody>\r\n" + "			</table>\r\n"
							+ "		</div>\r\n"
							+ "		<p style=\"font-size: 18px;  font-family: &#39;Times New Roman&#39;\">Tr??n tr???ng!!! </p>\r\n"
							+ "	</div>\r\n" + "\r\n" + "</body></html>";

					try {
						// tao mail
						MimeMessage mail = mailer.createMimeMessage();
						// su dung lop tro giup
						MimeMessageHelper helper = new MimeMessageHelper(mail);
						helper.setFrom(from, from);
						helper.setTo(to);
						helper.setReplyTo(from, from);
						helper.setSubject(subject);
						helper.setText(body, true);

						// gui mail
						mailer.send(mail);
						model.addAttribute("message", "G???i email th??nh c??ng!");
					} catch (Exception ex) {
						model.addAttribute("message", "G???i email th???t b???i!");
					}
				}
			}
		}
		return home(model, session, rq);
	}

	@RequestMapping(value = "phieumuon/detail/{mapm}", method = RequestMethod.GET)
	public String show_form_detail(ModelMap model, @ModelAttribute("phieumuon_chitiet") PHIEUMUON phieumuon_chitiet,
			@PathVariable("mapm") String mapm, HttpSession session, HttpServletRequest request) {
		model.addAttribute("form_detail", true);
		phieumuon_chitiet = new PhieuMuonDAO().getById(mapm, factory);
		model.addAttribute("indexValue", 0);
		model.addAttribute("slThietBiChiTiet", phieumuon_chitiet.getCt_phieumuons().size());
		model.addAttribute("phieumuon_chitiet", phieumuon_chitiet);
		return home(model, session, request);
	}

	@RequestMapping(value = "phieumuon/import-excel", method = RequestMethod.POST)
	public RedirectView importExcel(@RequestParam("file") MultipartFile input, RedirectAttributes model, HttpSession session)
			throws IOException {
		if (!input.getContentType().toString()
				.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			model.addFlashAttribute("insert", false);
			return new RedirectView("../phieumuon");
		}
		XSSFWorkbook workbook = new XSSFWorkbook(input.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(1);
		Row row;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		boolean kq = true;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			PHIEUMUON pm = new PHIEUMUON();
			CT_PHIEUMUON ct_pm = new CT_PHIEUMUON();
			pm.setMapm(getRandomMa());
			String cmnd = new BigDecimal(row.getCell(1).getNumericCellValue()).toBigInteger().toString();
			pm.setNm(new NguoiMuonDAO().getByCMND(cmnd, factory));
			pm.setPhong(row.getCell(2).toString());
			pm.setThoigianmuon(Date.valueOf(LocalDate.now()));
			pm.setGhichu(row.getCell(4).toString());
			
			ACCOUNT curent_account = (ACCOUNT) session.getAttribute("account_db");
			pm.setNhanvien_pm(curent_account.getNhanviens().get(0));
			kq = new PhieuMuonDAO().save(factory, pm);
			if(!kq) break;
			
			String dsMuon = row.getCell(3).toString();
			String[] list = dsMuon.split(",");
			for (String soLuongAndthietBi : list) {
				soLuongAndthietBi = soLuongAndthietBi.trim();
				String[] soLuongAndthietBi_splited = soLuongAndthietBi.split(" ");
				ct_pm.setPhieumuon(pm);
				ct_pm.setSoluong( Integer.parseInt(soLuongAndthietBi_splited[0]) );
				ct_pm.setThietbi_muon(new ThietBiDAO().getById(soLuongAndthietBi_splited[1], factory));
				
				kq = new CT_PhieuMuonDAO().save(factory, ct_pm);
				if(!kq) break;
					
			}
			if(!kq) break;

		}
		model.addFlashAttribute("insert", kq);
		return new RedirectView("../phieumuon");
	}
}
