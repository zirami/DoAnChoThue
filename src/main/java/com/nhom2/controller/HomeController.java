package com.nhom2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	

	@RequestMapping("home")
	public String home() {
		return "home";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("register")
	public String register() {
		return "register";
	}

	@RequestMapping("datatable")
	public String datatable() {
		return "datatable";
	}

	@RequestMapping("muonthietbi")
	public String muonthietbi() {
		return "muonthietbi";
	}

	@RequestMapping("thanhli")
	public String thanhli() {
		return "thanhli";
	}

}
