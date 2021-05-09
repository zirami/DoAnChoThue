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
@Table(name="QUANLI")
public class QUANLI {
	@Id
	@Column(name="MAQL")
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String maql;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="HO")
	private String ho;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="TEN")
	private String ten;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="NGAYSINH")
	private Date ngaysinh;
	 
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="GIOITINH")
	private String gioitinh;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="NGAYTIEPNHAN")
	private Date ngaytiepnhan;
	
	@ManyToOne	
	@JoinColumn(name = "USERNAME")
	private ACCOUNT acc;

	public QUANLI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaql() {
		return maql;
	}

	public void setMaql(String maql) {
		this.maql = maql;
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

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public Date getNgaytiepnhan() {
		return ngaytiepnhan;
	}

	public void setNgaytiepnhan(Date ngaytiepnhan) {
		this.ngaytiepnhan = ngaytiepnhan;
	}

	public ACCOUNT getAcc() {
		return acc;
	}

	public void setAcc(ACCOUNT acc) {
		this.acc = acc;
	}

	
	
	
}
