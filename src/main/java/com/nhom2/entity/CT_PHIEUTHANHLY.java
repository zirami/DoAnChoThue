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
@Table(name="CT_PHIEUTHANHLY")
public class CT_PHIEUTHANHLY {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "MATB")
	private THIETBI thietbi_thanhly;
	
	@ManyToOne	
	@JoinColumn(name = "MAPTL")
	private PHIEUTHANHLY phieuthanhly;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Column(name="SOLUONG")
	private Integer soluong;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Column(name="DONGIA")
	private Float dongia;

	public CT_PHIEUTHANHLY() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_PHIEUTHANHLY(Integer id, THIETBI thietbi_thanhly, PHIEUTHANHLY phieuthanhly, Integer soluong,Float dongia) {
		super();
		this.id = id;
		this.thietbi_thanhly = thietbi_thanhly;
		this.phieuthanhly = phieuthanhly;
		this.soluong = soluong;
		this.dongia = dongia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public THIETBI getThietbi_thanhly() {
		return thietbi_thanhly;
	}

	public void setThietbi_thanhly(THIETBI thietbi_thanhly) {
		this.thietbi_thanhly = thietbi_thanhly;
	}

	public PHIEUTHANHLY getPhieuthanhly() {
		return phieuthanhly;
	}

	public void setPhieuthanhly(PHIEUTHANHLY phieuthanhly) {
		this.phieuthanhly = phieuthanhly;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public Float getDongia() {
		return dongia;
	}

	public void setDongia(Float dongia) {
		this.dongia = dongia;
	}
	
	
}
