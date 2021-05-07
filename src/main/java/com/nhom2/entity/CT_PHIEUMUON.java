package com.nhom2.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="CT_PHIEUMUON")
public class CT_PHIEUMUON {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "MATB")
	private THIETBI thietbi_muon;
	
	@ManyToOne	
	@JoinColumn(name = "MAPM")
	private PHIEUMUON phieumuon;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Column(name="SOLUONG")
	private Integer soluong;

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

	public CT_PHIEUMUON() {
		super();
	}
	
	

	
	
}
