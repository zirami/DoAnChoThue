package com.nhom2.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_PHIEUNHAP")
public class CT_PHIEUNHAP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "MAPN")
	public PHIEUNHAP phieunhap;

	@ManyToOne
	@JoinColumn(name = "MATB")
	public THIETBI thietbi;

	@Column(name = "SOLUONG")
	public Integer soluongnhap;
	@Column(name = "DONGIA")
	public Double dongia;

	public CT_PHIEUNHAP() {
		super();
	}

	public CT_PHIEUNHAP(Integer id, THIETBI thietbi, Integer soluongnhap, Double dongia) {
		super();
		this.id = id;
		this.thietbi = thietbi;
		this.soluongnhap = soluongnhap;
		this.dongia = dongia;
	}
	
	
	@Override
	public String toString() {
		return "CT_PHIEUNHAP [id=" + id + ", phieunhap=" + phieunhap + ", thietbi=" + thietbi + ", soluongnhap=" + soluongnhap
				+ ", dongia=" + dongia + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PHIEUNHAP getPhieunhap() {
		return phieunhap;
	}

	public void setPhieunhap(PHIEUNHAP phieunhap) {
		this.phieunhap = phieunhap;
	}

	public THIETBI getThietbi() {
		return thietbi;
	}

	public void setThietbi(THIETBI thietbi) {
		this.thietbi = thietbi;
	}

	public Integer getSoluongnhap() {
		return soluongnhap;
	}

	public void setSoluongnhap(Integer soluongnhap) {
		this.soluongnhap = soluongnhap;
	}

	public Double getDongia() {
		return dongia;
	}

	public void setDongia(Double dongia) {
		this.dongia = dongia;
	}

}
