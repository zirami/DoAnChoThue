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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PHIEUNHAP")
public class PHIEUNHAP {
	@Id
	@Column(name = "MAPN")
	public String mapn;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "THOIGIANNHAP")
	public Date thoigiannhap;

	@ManyToOne
	@JoinColumn(name = "MANV")
	public NHANVIEN nhanvien;


	@Column(name = "GHICHU")
	public String ghinhu;
	
	@Column(name = "TRANGTHAI")
	public String trangthai;

	@OneToMany(mappedBy = "phieunhap", fetch = FetchType.EAGER)
	private List<CT_PHIEUNHAP> ct_phieunhaps;
	
	@ManyToOne
	@JoinColumn(name="MANCC")
	public NHACUNGCAP ncc;

	public PHIEUNHAP() {
		super();
	}
	
	

	public PHIEUNHAP(String mapn, Date thoigiannhap, NHANVIEN nhanvien, String ghinhu, List<CT_PHIEUNHAP> ct_phieunhaps,
			NHACUNGCAP ncc) {
		super();
		this.mapn = mapn;
		this.thoigiannhap = thoigiannhap;
		this.nhanvien = nhanvien;
		this.ghinhu = ghinhu;
		this.ct_phieunhaps = ct_phieunhaps;
		this.ncc = ncc;
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

	public String getGhinhu() {
		return ghinhu;
	}

	public void setGhinhu(String ghinhu) {
		this.ghinhu = ghinhu;
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
