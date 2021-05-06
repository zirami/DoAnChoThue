package com.nhom2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CT_PHIEUMUON")
public class CT_PHIEUMUON {
	@Id @GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "MATB")
	private THIETBI thietbi_muon;
	
	@ManyToOne	
	@JoinColumn(name = "MAPM")
	private PHIEUMUON phieumuon;
	
	@Column(name="SOLUONG")
	private Integer soluong;
	
	

	public CT_PHIEUMUON() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public CT_PHIEUMUON(Integer id, THIETBI thietbi_muon, PHIEUMUON phieumuon, Integer soluong) {
		super();
		this.id = id;
		this.thietbi_muon = thietbi_muon;
		this.phieumuon = phieumuon;
		this.soluong = soluong;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public THIETBI getThietbi_muon() {
		return thietbi_muon;
	}

	public void setThietbi_muon(THIETBI thietbi_muon) {
		this.thietbi_muon = thietbi_muon;
	}

	public PHIEUMUON getPhieumuon() {
		return phieumuon;
	}

	public void setPhieumuon(PHIEUMUON phieumuon) {
		this.phieumuon = phieumuon;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}
	
	
	
}
