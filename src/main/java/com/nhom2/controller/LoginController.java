package com.nhom2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom2.DAO.AccountDAO;
import com.nhom2.DAO.MailDAO;
import com.nhom2.DAO.NguoiMuonDAO;
import com.nhom2.DAO.PhanQuyenDAO;
import com.nhom2.DAO.ThietBiDAO;
import com.nhom2.entity.ACCOUNT;
import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.PHANQUYEN;
import com.nhom2.entity.PHIEUMUON;
import com.nhom2.entity.THIETBI;
import com.nhom2.controller.PhieuMuonController;

@Transactional
@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	SessionFactory factory;

	@Autowired
	JavaMailSender mailer;

	@ModelAttribute("account_test")
	public ACCOUNT account() {
		return new ACCOUNT();
	}

//	@ModelAttribute("modify_account")
//	public ACCOUNT modify_account() {
//		return new ACCOUNT();
//	}

	@ModelAttribute("listPhanQuyen")
	public List<PHANQUYEN> getListPhanQuyen() {
		List<PHANQUYEN> list = new PhanQuyenDAO().getAll(factory);
		return list;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpSession session) {
		session.setAttribute("role", null);
		session.setAttribute("user", null);
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("account_test") ACCOUNT account_login, HttpServletRequest request,
			HttpSession session) {
		//Truy vấn username từ DB
		ACCOUNT account_db = new AccountDAO().getById(account_login.getUsername(), factory);
		
		//Mặc định là lỗi đăng nhập
		model.addAttribute("failLogin", true);
		
		//Nếu không tìm được tên username
		if (account_db == null) {
			//Thông báo ngoài view - chưa làm
			model.addAttribute("message", "Username không tồn tại !");
			System.out.println("username không tồn tại");
			return login(model, session);
		}
		
		//Nếu sai mật khẩu
		if (account_login.getPassword().equals(account_db.getPassword()) == false) {
			//Thông báo ngoài view - chưa làm
			model.addAttribute("message", "Mật khẩu không chính xác !");
			System.out.println("sai mật khẩu");
			return login(model, session);
		}
		
		//Sau các bước trên không lỗi thì đã đăng nhập thành công
		model.addAttribute("failLogin", false);
		
		//Nếu là admin thì đẩy về phieumuon
		if (account_db.getPhanquyen().getMapq().equals("admin")) {
			
			// Cho Đại phiếu mượn là admin
			session.setAttribute("role", "admin");
			return "redirect: phieumuon";
		}
		
		//Nếu là staff thì đẩy về qlnguoimuon
		if (account_db.getPhanquyen().getMapq().equals("staff")) {
			
			// Cho Đại người mượn là staff
			session.setAttribute("role", "staff");
			return "redirect: qlnguoimuon";
		}
		
		
//		if (account_db.getUsername() == null) {
//			model.addAttribute("failLogin", true);
//			return login(model, session);
//		} else {
//			if (account_login.getPassword().compareTo(account_db.getPassword()) != 0) {
//				model.addAttribute("failLogin", true);
//				return login(model, session);
//			} else {
//				if (account_db.getPhanquyen().getMapq().compareTo("admin") == 0) {
//					// Cho Đại phiếu mượn là admin
////					request.setAttribute("role", "admin");
//					session.setAttribute("role", "admin");
//					return "redirect: phieumuon";
//				}
//
//				else if (account_db.getPhanquyen().getMapq().compareTo("D") == 0) {
//					// Cho Đại người mượn là staff
//					session.setAttribute("role", "staff");
//					return "redirect: qlnguoimuon";
//				} else {
//					model.addAttribute("failLogin", true);
//					return login(model, session);
//				}
//			}
//		}
		
		//Trường hợp lỗi không xác định (ví dụ mã phân quyền bị xoá nhầm) thì về home
		//Code tạm để đó từ từ ràng dữ liệu lại sau
		return "redirect: home";

	}

	@RequestMapping(value = "forgot-password", method = RequestMethod.GET)
	public String forgotPassword() {
		return "forgotPassword";
	}

	@RequestMapping(value = "forgot-password", method = RequestMethod.POST)
	public String forgotPassword(ModelMap model, @ModelAttribute("account_test") ACCOUNT account_test,
			HttpServletRequest rq) {
		ACCOUNT testAccount = new AccountDAO().getByGmail(account_test.getGmail(), factory);
		if (testAccount == null) {
			model.addAttribute("account_test", account_test);
			model.addAttribute("failVerify", true);
			return forgotPassword();
		} else {

			String verifyCode = new MailDAO().getVerifyCode(6);
			String from = "thanhthang32k@gmail.com";
			String to = account_test.getGmail();
			String subject = "Mã xác nhận";
			String body = "[+] Mã xác nhận là: " + verifyCode;

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

			System.out.println("verifyCode" + verifyCode);

			model.addAttribute("modify_account", testAccount);
			model.addAttribute("verifyCode", verifyCode);
			model.addAttribute("message", "Mã xác nhận đã được gởi qua email!");
			return "verifyGmail";
		}
	}

	@RequestMapping(value = "verify", method = RequestMethod.GET)
	public String verify(ModelMap model) {
		return "verifyGmail";
	}

	@RequestMapping(value = "verify", method = RequestMethod.POST)
	public String verify(ModelMap model, @RequestParam("inputVerifyCode") String inputVerifyCode,
			@RequestParam("verifyCode") String verifyCode, @ModelAttribute("modify_account") ACCOUNT modify_account) {
		if (verifyCode.compareTo(inputVerifyCode) == 0) {
			model.addAttribute("modify_account", modify_account);
			return "modifyAccount";
		}
		model.addAttribute("failVerify", true);
		model.addAttribute("verifyCode", verifyCode);
		model.addAttribute("modify_account", new AccountDAO().getById(modify_account.getUsername(), factory));
		return "verifyGmail";
	}

	@RequestMapping(value = "modify-account", method = RequestMethod.POST)
	public String modify(ModelMap model, @ModelAttribute("modify_account") ACCOUNT modify_account,
			@RequestParam("confirmPassword") String confirmPassword) {
		if (modify_account.getPassword().compareTo(confirmPassword) == 0) {
			modify_account = new AccountDAO().getById(modify_account.getUsername(), factory);
			modify_account.setPassword(confirmPassword);
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(modify_account);
				t.commit();
				model.addAttribute("ModifySuccess", true);
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("ModifySuccess", false);

			} finally {
				session.close();
			}

			return "login";
		} else {
			model.addAttribute("modify_account", modify_account);
			return "modifyAccount";
		}
	}

	@RequestMapping(value = "dangxuat", method = RequestMethod.GET)
	public String dangxuat(ModelMap model, HttpSession session) {
//		session.setAttribute("role", null);
//		session.setAttribute("user", null);
		return login(model, session);
	}

//	@RequestMapping(value = "modify-account",method = RequestMethod.GET)
//	public String modify(ModelMap model, @RequestAttribute("modify_account") ACCOUNT modify_account) {
//		model.addAttribute("modify_account",modify_account);
//		return "modifyAccount";
//	}

}
