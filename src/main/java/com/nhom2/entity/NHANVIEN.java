package com.nhom2.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NHANVIEN")
public class NHANVIEN {
	@Id
	@Column(name = "MANV")
	@NotNull(message = "Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String manv;

	@NotNull(message = "Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name = "HO")
	private String ho;

	@NotNull(message = "Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name = "TEN")
	private String ten;

	@NotNull(message = "Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name = "GIOITINH")
	private String gioitinh;

	@NotNull(message = "Trường này không được bỏ trống!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "NGAYSINH")
	private Date ngaysinh;

	@NotNull(message = "Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name = "DIACHI")
	private String diachi;

	@NotNull(message = "Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name = "CMND")
	private String cmnd;

	@Column(name = "HINH")
	private String hinh;
	
	@Column(name = "ISADMIN")
	private String isadmin;
	
	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	@ManyToOne
	@JoinColumn(name = "MAQL")
	private QUANLI ql;

	@ManyToOne
	@JoinColumn(name = "USERNAME")
	private ACCOUNT acc;

	@OneToMany(mappedBy = "nhanvien", fetch = FetchType.LAZY)
	private Collection<PHIEUNHAP> phieunhaps;
	
	@OneToMany(mappedBy = "nhanvien_thanhly", fetch = FetchType.LAZY)
	private Collection<PHIEUTHANHLY> phieuthanhlys;
	
	@OneToMany(mappedBy = "nhanvien_pm", fetch = FetchType.EAGER)
	private Collection<PHIEUMUON> phieumuons;

	public NHANVIEN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<PHIEUNHAP> getPhieunhaps() {
		return phieunhaps;
	}

	public void setPhieunhaps(Collection<PHIEUNHAP> phieunhaps) {
		this.phieunhaps = phieunhaps;
	}

//	public Collection<PHIEUTHANHLY> getPhieuthanhlys() {
//		return phieuthanhlys;
//	}
//
//	public void setPhieuthanhlys(Collection<PHIEUTHANHLY> phieuthanhlys) {
//		this.phieuthanhlys = phieuthanhlys;
//	}

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

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public QUANLI getQl() {
		return ql;
	}

	public void setQl(QUANLI ql) {
		this.ql = ql;
	}

	public ACCOUNT getAcc() {
		return acc;
	}

	public void setAcc(ACCOUNT acc) {
		this.acc = acc;
	}

}
