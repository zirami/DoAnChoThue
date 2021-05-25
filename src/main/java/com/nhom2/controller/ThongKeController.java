package com.nhom2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ThongKeController {
	@RequestMapping("thong-ke")
	public String home() {
		
		return "thong_ke/thong_ke";
	}
}
