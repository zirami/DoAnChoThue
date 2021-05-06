package com.nhom2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="NHANVIEN")
public class NHANVIEN {
	@Id
	@Column(name="MANV")
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String manv;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="HO")
	private String ho;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="TEN")
	private String ten;
	 
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="GIOITINH")
	private String gioitinh;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="NGAYSINH")
	private Date ngaysinh;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="DIACHI")
	private String diachi;
	
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="CMND")
	private String cmnd;
	
	@ManyToOne	
	@JoinColumn(name = "MAQL")
	private QUANLI quanli;
	
	@NotNull
	@Column(name="USERNAME")
	private String username;

	public NHANVIEN() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public NHANVIEN(String manv, String ho, String ten, String gioitinh, Date ngaysinh, String diachi,QUANLI quanli,  String username) {
		super();
		this.manv = manv;
		this.ho = ho;
		this.ten = ten;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.quanli = quanli;
		this.username = username;
	}

	
	public String getCmnd() {
		return cmnd;
	}


	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}


	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public QUANLI getQuanli() {
		return quanli;
	}

	public void setQuanli(QUANLI quanli) {
		this.quanli = quanli;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
