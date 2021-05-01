package com.nhom2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_PHIEUNHAP")
public class CT_PHIEUNHAP {
	@Id @GeneratedValue
	public Double id;
	
	@ManyToOne
	@JoinColumn(name = "MAPN")
//	@Column(name = "MAPN")
	public PHIEUNHAP mapn;
	
	@ManyToOne
	@JoinColumn(name = "MATB")
//	@Column(name = "MATB")
	public THIETBI matb;
	
	@Column(name = "SOLUONG")
	public Double soluong;
	@Column(name = "DONGIA")
	public Double dongia;
	public CT_PHIEUNHAP() {
		super();
	}
	public CT_PHIEUNHAP(Double id, PHIEUNHAP mapn, THIETBI matb, Double soluong, Double dongia) {
		super();
		this.id = id;
		this.mapn = mapn;
		this.matb = matb;
		this.soluong = soluong;
		this.dongia = dongia;
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public PHIEUNHAP getMapn() {
		return mapn;
	}
	public void setMapn(PHIEUNHAP mapn) {
		this.mapn = mapn;
	}
	public THIETBI getMatb() {
		return matb;
	}
	public void setMatb(THIETBI matb) {
		this.matb = matb;
	}
	public Double getSoluong() {
		return soluong;
	}
	public void setSoluong(Double soluong) {
		this.soluong = soluong;
	}
	public Double getDongia() {
		return dongia;
	}
	public void setDongia(Double dongia) {
		this.dongia = dongia;
	}
	
}
