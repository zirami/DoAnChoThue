package com.nhom2.controller;

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

import com.nhom2.DAO.AccountDAO;
import com.nhom2.entity.ACCOUNT;

@Transactional
@Controller
@RequestMapping("/")
public class AccountController {

	public AccountController() {
		super();
	}

	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listAccount", new AccountDAO().getAll(factory));
		return "account/ds_account";
	}

	// THÊM
	@ModelAttribute("account_moi")
	public ACCOUNT account_moi() {
		return new ACCOUNT();
	}

	// SỬA
	@ModelAttribute("account_sua")
	public ACCOUNT account_sua() {
		return new ACCOUNT();
	}

	// THÊM
	@RequestMapping(value = "account", method = RequestMethod.POST)
	public String insert(ModelMap model, @Valid @ModelAttribute("account_moi") ACCOUNT account_moi,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.hasErrors());
		model.addAttribute("them_saidinhdang", reusult.hasErrors());
		model.addAttribute("account_moi", account_moi);

		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("insert", new AccountDAO().save(factory, account_moi)); // Xử lý thông báo thêm thành công

		return home(model);
	}

	// LẤY RA THIẾT BỊ BẰNG ID ĐỂ SHOW FORM EDIT
	@RequestMapping(value = "account/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("account_sua") ACCOUNT account_sua,
			@PathVariable String id) {

		model.addAttribute("form_edit", true);
		model.addAttribute("account_sua", new AccountDAO().getByName(id, factory));
		return home(model);
	}

	// UPDATE
	@RequestMapping(value = "account/update", method = RequestMethod.POST)
	public String update(ModelMap model, @Valid @ModelAttribute("account_sua") ACCOUNT account_sua,
			BindingResult reusult) {
		System.out.println("has error: " + reusult.getFieldErrors().toString());
		model.addAttribute("sua_saidinhdang", reusult.hasErrors());
		model.addAttribute("account_sua", account_sua);
		if (reusult.hasErrors())
			return home(model);

		model.addAttribute("update", new AccountDAO().update(factory, account_sua));
		return home(model);
	}

	// DELETE
	@RequestMapping(value = "account/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("id") String id) {
		System.out.println("id = " + id);
		ACCOUNT account_xoa = new ACCOUNT();
		account_xoa.setUsername(id);
		model.addAttribute("delete", new AccountDAO().del(factory, account_xoa));
		return home(model);
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public String changePassWord(ModelMap model) {
		model.addAttribute("accountChange", new ACCOUNT());
		return "changePassword";
	}
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassWord(ModelMap model, @ModelAttribute("accountChange") ACCOUNT accountChange,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			HttpSession session) {
		ACCOUNT testAccount = new AccountDAO().getById(accountChange.getUsername(), factory);
		
		String passwordHash = new AccountDAO().getMd5(accountChange.getPassword());
		
		if(testAccount!=null) {
			if(confirmPassword.compareTo(newPassword)==0 && testAccount.getPassword().compareTo(passwordHash)==0) {
				confirmPassword = new AccountDAO().getMd5(confirmPassword);
						
				testAccount.setPassword(confirmPassword);
				new AccountDAO().update(factory, testAccount);
				model.addAttribute("changeSuccess", true);
			}
			else {
				model.addAttribute("changeSuccess", false);
				return changePassWord(model);
			}
		}
		else {
			model.addAttribute("changeSuccess", false);
			model.addAttribute("message", "Username không tồn tại");
			return changePassWord(model);
		}
		model.addAttribute("changeSuccess", true);
		session.setAttribute("role", null);
		session.setAttribute("user", null);
		return changePassWord(model);
	}
}
