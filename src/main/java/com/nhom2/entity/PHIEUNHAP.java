package com.nhom2.entity;

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
@Table(name = "PHIEUNHAP")
public class PHIEUNHAP {
	@Id
	@Column(name = "MAPN")
	@NotBlank
	private String mapn;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "THOIGIANNHAP")
	@NotNull
	private Date thoigiannhap;

	@ManyToOne
	@JoinColumn(name = "MANV")
	@NotNull
	private NHANVIEN nhanvien;

	@Column(name = "GHICHU")
	private String ghichu;

	@Column(name = "TRANGTHAI")
	@NotBlank
	private String trangthai;

	@OneToMany(mappedBy = "phieunhap", fetch = FetchType.LAZY)
	private List<CT_PHIEUNHAP> ct_phieunhaps;

	@ManyToOne
	@JoinColumn(name = "MANCC")
	private NHACUNGCAP ncc;

	public PHIEUNHAP() {
		super();
	}

	@Override
	public String toString() {
		return "PHIEUNHAP [mapn=" + mapn + ", thoigiannhap=" + thoigiannhap + ", nhanvien=" + nhanvien + ", ghichu="
				+ ghichu + ", trangthai=" + trangthai + ", ncc=" + ncc + "]";
	}

	public PHIEUNHAP(String mapn, Date thoigiannhap, NHANVIEN nhanvien, String ghichu, List<CT_PHIEUNHAP> ct_phieunhaps,
			NHACUNGCAP ncc) {
		super();
		this.mapn = mapn;
		this.thoigiannhap = thoigiannhap;
		this.nhanvien = nhanvien;
		this.ghichu = ghichu;
		this.ct_phieunhaps = ct_phieunhaps;
		this.ncc = ncc;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getMapn() {
		return mapn;
	}

	public void setMapn(String mapn) {
		this.mapn = mapn;
	}

	public Date getThoigiannhap() {
		return thoigiannhap;
	}

	public void setThoigiannhap(Date thoigiannhap) {
		this.thoigiannhap = thoigiannhap;
	}

	public NHANVIEN getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NHANVIEN nhanvien) {
		this.nhanvien = nhanvien;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public List<CT_PHIEUNHAP> getCt_phieunhaps() {
		return ct_phieunhaps;
	}

	public void setCt_phieunhaps(List<CT_PHIEUNHAP> ct_phieunhaps) {
		this.ct_phieunhaps = ct_phieunhaps;
	}

	public NHACUNGCAP getNcc() {
		return ncc;
	}

	public void setNcc(NHACUNGCAP ncc) {
		this.ncc = ncc;
	}

}
