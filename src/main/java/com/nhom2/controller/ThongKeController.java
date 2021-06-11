package com.nhom2.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
@RequestMapping("/")
public class ThongKeController {
	@Autowired
	SessionFactory factory;

	@ModelAttribute("loaiThongkes")
	public List<String> getLoaiThongke() {
		List<String> loaiThongkes = Arrays.asList("Phiếu Mượn", "Thiết Bị");
		return loaiThongkes;
	}

	@ModelAttribute("optionPM")
	public List<String> getOptionPM() {
		List<String> optionPM = Arrays.asList("Tất cả", "Đã trả", "Chưa trả");
		return optionPM;
	}

	@ModelAttribute("optionTB")
	public List<String> getOptionTB() {
		List<String> optionPM = Arrays.asList("Tất cả", "Còn tốt", "Hư hỏng");
		return optionPM;
	}

	@RequestMapping("thong-ke")
	public String home(ModelMap model) {

		return "thong_ke/thong_ke";
	}

	@RequestMapping(value = "thong-ke", method = RequestMethod.POST)
	public String thongke(@RequestParam("loaiThongke") String loaiThongke, @RequestParam("option") String option,
			@RequestParam("ngaybatdau") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaybatdau,
			@RequestParam("ngayketthuc") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayketthuc, ModelMap model) {
		final String case1 = "Phiếu Mượn";
		final String case2 = "Thiết Bị";
		final String case3 = "Phiếu Nhập";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		
		
		List<Object[]> kqThongke = null;
		String hql = "";
		List<String> options = getOptionTB();
		switch (loaiThongke) {
			// Nếu là Phiếu Mượn
			case case1: {
				kqThongke = getKqThongkePM(option, ngaybatdau, ngayketthuc);
				options = getOptionPM();
				break;
			}
			
			
			// Nếu là Thiết Bị
			case case2: {
				
				break;
			}
			
			
			default:
				model.addAttribute("error", true);
				model.addAttribute("msg", "Loại thiết bị không xác định !!!");
				break;
				
		}

		model.addAttribute("loaiThongke", loaiThongke);		
		model.addAttribute("options", options);
		model.addAttribute("ngaybatdau", formatter.format(ngaybatdau));
		model.addAttribute("ngayketthuc", formatter.format(ngayketthuc));
		
		model.addAttribute("optionThongke", option);
		model.addAttribute("kqThongke", kqThongke);
		
		if (loaiThongke.equals("Thiết Bị")) {
			for (Object[] objects : kqThongke) {
				System.out.println("ten: " + objects[0]);
				System.out.println("soluong: " + objects[1]);
				System.out.println("------------------");
			}
			return home(model);
		}
		return home(model);
		
		
	}
	
	//	KẾT QUẢ THỐNG KÊ PHIẾU MƯỢN
	public List<Object[]> getKqThongkePM(String option, Date ngaybatdau, Date ngayketthuc){
		Session session;
		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "select thoigianmuon, count(*) " + "from PHIEUMUON "
				+ "where thoigianmuon between :ngaybatdau and :ngayketthuc ";

		// Nếu thoigiantra khác NULL thì đã trả
		if (option.equals("Đã trả"))
			hql += "and thoigiantra is not null ";
		else if (option.equals("Chưa trả"))
			hql += "and thoigiantra is null ";

		hql += "group by thoigianmuon";				
						
		
		/* Bắt đầu quá trình truy vấn vào DB */
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter("ngaybatdau", ngaybatdau);
		query.setParameter("ngayketthuc", ngayketthuc);
				
		return query.list();
	}
	
//	KẾT QUẢ THỐNG KÊ THIẾT BỊ
	public List<Object[]> getKqThongkePM(String tinhtrang){
		Session session;
		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from THIETBI "
				+ "where tinhtrang = :tinhtrang ";

		// Nếu thoigiantra khác NULL thì đã trả
		if (tinhtrang.equals("Còn tốt"))
			hql += "and thoigiantra is not null ";
		else if (tinhtrang.equals("Hư hỏng"))
			hql += "and thoigiantra is null ";

		hql += "group by thoigianmuon";				
						
		
		/* Bắt đầu quá trình truy vấn vào DB */
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter("tinhtrang", tinhtrang);
		query.setParameter("tinhtrang", tinhtrang);
				
		return query.list();
	}

}
