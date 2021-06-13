
package com.nhom2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.nhom2.DAO.CT_PhieuMuonDAO;
import com.nhom2.DAO.MailDAO;
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

	@Autowired
	JavaMailSender mailer;
	
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
	
	@ModelAttribute("tieude_mail")
	public String tieude_mail() {
		return "Cảnh báo mượn thiết bị!!!";
	}
	
	@ModelAttribute("noidung_mail")
	public String noidung_mail() {
		return "Phòng Cơ Sở Vật Chất xin thông báo việc mượn thiết bị của bạn đã quá 3 ngày. "
				+ "Vui lòng hoàn tất thủ tục trả thiết bị mượn.";
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

//		// Thêm từng cái CT_ThietBi

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
		
		///Kiểm tra có thiết bị nào âm không
		for (THIETBI ele : list) {
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);
			
			float checkSoLuong = thietbii.getSoluong()-ele.getSoluong();
			
			if(checkSoLuong < 0) {
				new PhieuMuonDAO().delete(factory, phieumuon_moi);
				model.addAttribute("phieumuon_moi",new PHIEUMUON());
				model.addAttribute("insert", false);
				return home(model, session);
			}
			
		}
		

		for (THIETBI ele : list) {
			CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);
			ct_tb.setThietbi_muon(thietbii);
			ct_tb.setSoluong(ele.getSoluong());
			ct_tb.setPhieumuon(phieumuon_moi);
			result = new CT_PhieuMuonDAO().save(factory, ct_tb);
			
			thietbii.setSoluong(thietbii.getSoluong()-ele.getSoluong());
			new ThietBiDAO().update(factory, thietbii);
			
			
		}

		result = new PhieuMuonDAO().update(factory, phieumuon_moi);
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
		// lấy phiếu mua bằng mã của phiếu mua sửa.
		PHIEUMUON phieumuon_cansua = new PhieuMuonDAO().getById(phieumuon_sua.getMapm(), factory);

		List<CT_PHIEUMUON> backup = new ArrayList<>();
		// Xóa hết các ct phiếu mua hiện tại
		for (CT_PHIEUMUON elem : phieumuon_cansua.getCt_phieumuons()) {
			
			backup.add(elem);
			new CT_PhieuMuonDAO().delete(factory, elem);
		}

		// Nếu đếm value == 0 tức là người dùng đã bỏ chọn hết thiết bị, sẽ xóa luôn
		// phiếu muon.
		if (demValue == 0) {

			// Thêm thông báo thành công khi xóa phiếu muon.
			model.addAttribute("update", new PhieuMuonDAO().delete(factory, phieumuon_sua));
			for (CT_PHIEUMUON elem : backup) {
				//phục hồi số lượng của thiết bị
				THIETBI thietbii = new ThietBiDAO().getById(elem.getThietbi_muon().getMatb(), factory);
				thietbii.setSoluong(thietbii.getSoluong()+ elem.getSoluong());
				new ThietBiDAO().update(factory, thietbii);
			}
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
		
		
		///Kiểm tra có thiết bị nào âm không
		for (THIETBI ele : list) {
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);

			float soluongbackup = 0;
			for (CT_PHIEUMUON elem : backup) {
				if(elem.getThietbi_muon().getMatb()==thietbii.getMatb()) {
					soluongbackup = elem.getSoluong();
					break;
				}
			}
			
			float checkSoLuong = thietbii.getSoluong()-ele.getSoluong() + soluongbackup;
			
			if(checkSoLuong < 0) {
//				new PhieuMuonDAO().delete(factory, phieumuon_cansua);

				for (CT_PHIEUMUON elem : backup) {
					CT_PHIEUMUON ct_phieumuon_backup = new CT_PHIEUMUON();
					ct_phieumuon_backup.setPhieumuon(phieumuon_cansua);
					ct_phieumuon_backup.setSoluong(elem.getSoluong());
					ct_phieumuon_backup.setThietbi_muon(elem.getThietbi_muon());
					new CT_PhieuMuonDAO().save(factory, ct_phieumuon_backup);
				}		
		
				model.addAttribute("phieumuon_sua",new PHIEUMUON());
				model.addAttribute("update", false);
				return home(model, session);
			}
			
		}
		

		for (THIETBI ele : list) {
			CT_PHIEUMUON ct_tb = new CT_PHIEUMUON();
			THIETBI thietbii = new ThietBiDAO().getById(ele.getMatb(), factory);
			ct_tb.setThietbi_muon(thietbii);
			ct_tb.setSoluong(ele.getSoluong());
			ct_tb.setPhieumuon(phieumuon_cansua);
			result1 = new CT_PhieuMuonDAO().save(factory, ct_tb);
			
			thietbii.setSoluong(thietbii.getSoluong()-ele.getSoluong());
			new ThietBiDAO().update(factory, thietbii);
			
		
		}

		model.addAttribute("update", new PhieuMuonDAO().update(factory, phieumuon_sua));
		
		//trả về giá trị cho database khi xóa CT_PHIEUMUON
		for (CT_PHIEUMUON elem : backup) {
			//phục hồi số lượng của thiết bị
			THIETBI thietbii = new ThietBiDAO().getById(elem.getThietbi_muon().getMatb(), factory);
			thietbii.setSoluong(thietbii.getSoluong()+ elem.getSoluong());
			new ThietBiDAO().update(factory, thietbii);
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
	
	@RequestMapping(value = "phieumuon/mail", method= RequestMethod.POST)
	public String sendMail(ModelMap model, @RequestParam("tieude_mail") String tieude_mail,
			@RequestParam("noidung_mail") String noidung_mail, HttpSession session) {
		List<PHIEUMUON> list = new PhieuMuonDAO().getAll(factory);
		
		for(PHIEUMUON elem : list) {
			if(elem.getThoigiantra()==null) {
				int songay = elem.laySoNgay_Date( elem.getThoigianmuon() );
				if(songay>=3) {	
					String from = "thanhthang32k@gmail.com";
					String to = elem.getNm().getGmail();
					String subject = tieude_mail;
					String body = "<!DOCTYPE html>\r\n"
							+ "<!-- saved from url=(0042)file:///C:/Users/n18dc/Downloads/test.html -->\r\n"
							+ "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
							+ "	<style>\r\n"
							+ "table, td, th {\r\n"
							+ "  border: 1px solid black;\r\n"
							+ "}\r\n"
							+ "\r\n"
							+ "table {\r\n"
							+ "  font-family: Arial, Helvetica, sans-serif;\r\n"
							+ "  border-collapse: collapse;\r\n"
							+ "  width: 100%;\r\n"
							+ "}\r\n"
							+ "\r\n"
							+ "table, td, th {\r\n"
							+ "  border: 1px solid #432874;\r\n"
							+ "  padding: 8px;\r\n"
							+ "}\r\n"
							+ "\r\n"
							+ "table tr:nth-child(even){background-color: #f2f2f2;}\r\n"
							+ "\r\n"
							+ "table tr:hover {background-color: #ecc24e;}\r\n"
							+ "\r\n"
							+ "table th {\r\n"
							+ "  padding-top: 12px;\r\n"
							+ "  padding-bottom: 12px;\r\n"
							+ "  text-align: left;\r\n"
							+ "  background-color: #432874;\r\n"
							+ "  color: white;\r\n"
							+ "}\r\n"
							+ "</style>\r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "	<div style=\"border: 5px solid #17a2b8;margin: 20px;padding: 20px; width: 1000px;\">\r\n"
							+ "		<h2 style=\"text-align: center; color: #17a2b8; font-size: 35px\">Quản lí thiết bị Học Viện Cơ Sở</h2>\r\n"
							+ "		<h4 style=\"text-align: left;\"> Chào bạn " + elem.getNm().getHo() + " " + elem.getNm().getTen() +"! </h4>\r\n"
							+ "	<p  style=\"font-size: 18px;  font-family: &#39;Times New Roman&#39;\">"  + noidung_mail+  "</p>\r\n"
							+ "		\r\n"
							+ "		<h2>Danh sách các thiết bị mượn gồm: </h2>\r\n"
							+ "		<div>\r\n"
							+ "			<table style=\"width: 100%;border-collapse: collapse;\">	\r\n"
							+ "			<colgroup>\r\n"
							+ "				<col span=\"1\" style=\"width: 15%\">\r\n"
							+ "				<col span=\"1\" style=\"width: 50%\">\r\n"
							+ "				<col span=\"1\" style=\"width: 35%\">\r\n"

							+ "			</colgroup>												\r\n"
							+ "				<thead>\r\n"
							+ "					<tr>\r\n"
							+ "						<th style=\"text-align: center;\">Mã thiết bị</th>\r\n"
							+ "						<th style=\"text-align: center;\">Tên thiết bị</th>\r\n"
							+ "						<th style=\"text-align: center;\">Số lượng mượn</th>\r\n"

							+ "					</tr>													\r\n"
							+ "				</thead>			\r\n"
							+ "				<tbody  style=\"text-align: center;\">\r\n";
							 for(CT_PHIEUMUON e : elem.getCt_phieumuons()) {
								 body += "<tr>\r\n"
								 + " <td>" + e.getThietbi_muon().getMatb() + "</td>\r\n"
								 + " <td>" + e.getThietbi_muon().getTen() + "</td>\r\n"
								 + " <td>" + e.getSoluong() + "</td>\r\n"
								 + "</tr>\r\n";
							 }	

							body+= "\r\n"
							+ "\r\n"
							+ "				</tbody>\r\n"
							+ "			</table>\r\n"
							+ "		</div>\r\n"
							+ "		<p style=\"font-size: 18px;  font-family: &#39;Times New Roman&#39;\">Trân trọng!!! </p>\r\n"
							+ "	</div>\r\n"
							+ "\r\n"
							+ "</body></html>";
					
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
						model.addAttribute("message", "Gửi email thành công!");
					} catch (Exception ex) {
						model.addAttribute("message", "Gửi email thất bại!");
					}
				}
			}
		}
		return home(model,session);
	}
}
