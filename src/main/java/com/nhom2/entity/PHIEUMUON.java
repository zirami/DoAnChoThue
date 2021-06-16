package com.nhom2.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PHIEUMUON")
public class PHIEUMUON {
	@Id
	@Column(name = "MAPM")
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String mapm;

	@NotNull(message="Trường này không được bỏ trống!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "THOIGIANMUON")
	private Date thoigianmuon;

	@NotNull(message="Trường này không được bỏ trống!")
	@Column(name="PHONG")
	private String phong;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "THOIGIANTRA")
	private Date thoigiantra;

	@Column(name = "GHICHU")
	private String ghichu;

	// Nhập thí người mượn trước, test, từ từ thêm obj người mượn vào sau
	@ManyToOne
	@JoinColumn(name = "MANM")
	@NotNull(message="Trường này không được bỏ trống!")
	private NGUOIMUON nm;

//	@NotNull(message="Trường này không được bỏ trống!")
	@ManyToOne
	@JoinColumn(name = "MANV")
	private NHANVIEN nhanvien_pm;


	public NHANVIEN getNhanvien_pm() {
		return nhanvien_pm;
	}

	public void setNhanvien_pm(NHANVIEN nhanvien_pm) {
		this.nhanvien_pm = nhanvien_pm;
	}

	@OneToMany(mappedBy = "phieumuon", fetch = FetchType.EAGER)
	private Collection<CT_PHIEUMUON> ct_phieumuons;

	public PHIEUMUON() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}
	public String getMapm() {
		return mapm;
	}

	public void setMapm(String mapm) {
		this.mapm = mapm;
	}

	public Date getThoigianmuon() {
		return thoigianmuon;
	}

	public void setThoigianmuon(Date thoigianmuon) {
		this.thoigianmuon = thoigianmuon;
	}

	public Date getThoigiantra() {
		return thoigiantra;
	}

	public void setThoigiantra(Date thoigiantra) {
		this.thoigiantra = thoigiantra;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public NGUOIMUON getNm() {
		return nm;
	}

	public void setNm(NGUOIMUON nm) {
		this.nm = nm;
	}



	public Collection<CT_PHIEUMUON> getCt_phieumuons() {
		return ct_phieumuons;
	}

	public void setCt_phieumuons(Collection<CT_PHIEUMUON> ct_phieumuons) {
		this.ct_phieumuons = ct_phieumuons;
	}
	public Date getDate() {  
	    Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
	    String strDate= formatter.format(date);  
	    Date date1 = null;
	    try {
			date1 = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(strDate); 
	    return date1;
	}
	
	public double tinhKhoangCachHaiNgay_Date(Date d1,Date d2) { 
		double result = 0;
		result = (double) (d2.getTime()-d1.getTime());
		result = result / (24 * 60 * 60 * 1000) + 1;
		return result;
	}
	
	public double laySoNgay_Date(Date d1) {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate= formatter.format(date);  
	    Date date1 = null;
	    try {
			date1 = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		double result = 0;
		result = (double) (date1.getTime()-d1.getTime());
		result = result / (24 * 60 * 60 * 1000) + 1;
		return result;
	}
	
	public double laySoNgay(String t1) {
		 Date date = new Date();  
 	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate= formatter.format(date);  
	    Date date1 = null;
	    try {
			date1 = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Date date2=null;
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(t1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double result = 0;
		result = (double) (date1.getTime()-date2.getTime());
		result = result / (24 * 60 * 60 * 1000) + 1;
		return result;
	}


}
