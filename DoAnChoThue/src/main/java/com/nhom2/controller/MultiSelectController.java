package com.nhom2.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MultiSelectController {
   @RequestMapping(value = "multi", method = RequestMethod.GET)
   public String index(){
      return "multi/multi_input";
   }

   @RequestMapping(value = "multi", method = RequestMethod.POST)
   public String getMultiData(HttpServletRequest rq, ModelMap model){
      String[] array = rq.getParameterValues("multidata[]");
      model.addAttribute("array", array);
		
      List<String> list = Arrays.asList(array); model.addAttribute("list", list);
		 
      return "multi/multi_input";
   }
   
}
