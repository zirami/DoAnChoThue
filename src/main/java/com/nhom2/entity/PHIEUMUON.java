package com.nhom2.entity;

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

	@NotNull
	@Column(name = "MANV")
	@NotNull(message="Trường này không được bỏ trống!")
	private String manv;

	@OneToMany(mappedBy = "phieumuon", fetch = FetchType.EAGER)
	private Collection<CT_PHIEUMUON> ct_phieumuons;

	public PHIEUMUON() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public Collection<CT_PHIEUMUON> getCt_phieumuons() {
		return ct_phieumuons;
	}

	public void setCt_phieumuons(Collection<CT_PHIEUMUON> ct_phieumuons) {
		this.ct_phieumuons = ct_phieumuons;
	}

}
