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

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "PHIEUMUON")
public class PHIEUMUON {
	@Id
	@Column(name = "MAPM")
	private String mapm;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "THOIGIANMUON")
	private Date thoigianmuon;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "THOIGIANTRA")
	private Date thoigiantra;

	@Column(name = "GHICHU")
	private String ghiChu;

	// Nhập thí người mượn trước, test, từ từ thêm obj người mượn vào sau
	@NotNull
	@ManyToOne
	@JoinColumn(name = "MANM")
	private NGUOIMUON manm;

	@NotNull
	@Column(name = "MANV")
	private String manv;

	@OneToMany(mappedBy = "phieumuon", fetch = FetchType.EAGER)
	private Collection<CT_PHIEUMUON> ct_phieumuons;

	public PHIEUMUON() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PHIEUMUON(String maPM, Date thoigianmuon, Date thoigiantra, String ghiChu, NGUOIMUON manm, String maNV) {
		super();
		this.mapm = maPM;
		this.thoigianmuon = thoigianmuon;
		this.thoigiantra = thoigiantra;
		this.ghiChu = ghiChu;
		this.manm = manm;
		this.manv = maNV;
	}

	public String getMaPM() {
		return mapm;
	}

	public void setMaPM(String maPM) {
		this.mapm = maPM;
	}

	public Date getTgMuon() {
		return thoigianmuon;
	}

	public void setTgMuon(Date tgMuon) {
		this.thoigianmuon = tgMuon;
	}

	public Date getTgTra() {
		return thoigiantra;
	}

	public void setTgTra(Date tgTra) {
		this.thoigiantra = tgTra;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public NGUOIMUON getMaNM() {
		return manm;
	}

	public void setMaNM(NGUOIMUON maNM) {
		this.manm = maNM;
	}

	public String getMaNV() {
		return manv;
	}

	public void setMaNV(String maNV) {
		this.manv = maNV;
	}

}
