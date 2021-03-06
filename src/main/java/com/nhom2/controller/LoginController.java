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
	public String login(ModelMap model, @ModelAttribute("account_test") ACCOUNT account_login, HttpServletRequest request, HttpSession session) {
		//Truy v???n username t??? DB
		ACCOUNT account_db = new AccountDAO().getById(account_login.getUsername(), factory);
		
		//M???c ?????nh l?? l???i ????ng nh???p
		
		model.addAttribute("failLogin", true);
		//N???u kh??ng t??m ???????c t??n username
		if (account_db == null) {
			//Th??ng b??o ngo??i view - ch??a l??m
			model.addAttribute("message", "Username kh??ng t???n t???i !");
			model.addAttribute("failLogin", true);
			System.out.println("username kh??ng t???n t???i");
			return login(model, session);
		}
		String passwordHash = new AccountDAO().getMd5(account_login.getPassword());
		//N???u sai m???t kh???u
		if (passwordHash.equals(account_db.getPassword()) == false) {
			//Th??ng b??o ngo??i view - ch??a l??m
			model.addAttribute("message", "M???t kh???u kh??ng ch??nh x??c !");
			model.addAttribute("failLogin", true);
			System.out.println("sai m???t kh???u");
			return login(model, session);
		}
		
		//Sau c??c b?????c tr??n kh??ng l???i th?? ???? ????ng nh???p th??nh c??ng
		model.remove("failLogin");
		session.setAttribute("account_db", account_db);
		//N???u l?? admin th?? ?????y v??? phieumuon
		if (account_db.getPhanquyen().getMapq().equals("admin")) {
			
			// Cho ?????i phi???u m?????n l?? admin
			session.setAttribute("role", "admin");
			session.setAttribute("account_login", account_db);
			return "redirect: phieumuon";
		}
		
		//N???u l?? staff th?? ?????y v??? qlnguoimuon
		if (account_db.getPhanquyen().getMapq().equals("staff")) {
			
			// Cho ?????i ng?????i m?????n l?? staff
			session.setAttribute("role", "staff");
			session.setAttribute("account_login", account_db);
			return "redirect: phieumuon";
		}
		
		
		//Tr?????ng h???p l???i kh??ng x??c ?????nh (v?? d??? m?? ph??n quy???n b??? xo?? nh???m) th?? v??? home
		//Code t???m ????? ???? t??? t??? r??ng d??? li???u l???i sau
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
			String subject = "M?? x??c nh???n";
			String body = "[+] M?? x??c nh???n l??: " + verifyCode;

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

			System.out.println("verifyCode" + verifyCode);

			model.addAttribute("modify_account", testAccount);
			model.addAttribute("verifyCode", verifyCode);
			model.addAttribute("message", "M?? x??c nh???n ???? ???????c g???i qua email!");
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
			
			confirmPassword = new AccountDAO().getMd5(confirmPassword);
			
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
		//session.removeAttribute("account_db");
		return login(model, session);
	}

//	@RequestMapping(value = "modify-account",method = RequestMethod.GET)
//	public String modify(ModelMap model, @RequestAttribute("modify_account") ACCOUNT modify_account) {
//		model.addAttribute("modify_account",modify_account);
//		return "modifyAccount";
//	}

}
