package com.nhom2.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
	@Column(name = "DONGIA")
	public String dongia;
	@Column(name = "MANV")
	public String manv;
	@Column(name = "MANCC")
	public String mancc;
	
	
	
	@OneToMany(mappedBy="mapn",fetch=FetchType.EAGER)
	private Collection<CT_PHIEUNHAP> ct_phieunhaps;
	
	
	public PHIEUNHAP() {
		super();
	}
	public PHIEUNHAP(String mapn, Date thoigiannhap, String dongia, String manv, String mancc) {
		super();
		this.mapn = mapn;
		this.thoigiannhap = thoigiannhap;
		this.dongia = dongia;
		this.manv = manv;
		this.mancc = mancc;
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
	public String getDongia() {
		return dongia;
	}
	public void setDongia(String dongia) {
		this.dongia = dongia;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getMancc() {
		return mancc;
	}
	public void setMancc(String mancc) {
		this.mancc = mancc;
	}


}
