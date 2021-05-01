package com.nhom2.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="THIETBI")
public class THIETBI {
	@Id
	@Column(name="MATB")
	private String matb;
	
	@NotNull
	@Column(name="TEN")
	private String ten;
	
	@NotNull
	@Column(name="LOAI")
	private String loai;
	
	@NotNull
	@Column(name="SOLUONG")
	private Integer soluong;
	
	@NotNull
	@Column(name="TINHTRANG")
	private String tinhtrang;
	
	@Column(name="GHICHU")
	private String ghichu;

	@OneToMany(mappedBy = "thietbi_muon", fetch = FetchType.LAZY)
	private Collection<CT_PHIEUMUON> ct_phieumuons;
	
	@OneToMany(mappedBy = "matb", fetch = FetchType.LAZY)
	private Collection<CT_PHIEUNHAP> ct_phieunhaps;
	
	public THIETBI() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public THIETBI(String matb, String ten, String loai, Integer soluong, String tinhtrang, String ghichu,
			Collection<CT_PHIEUMUON> ct_phieumuons) {
		super();
		this.matb = matb;
		this.ten = ten;
		this.loai = loai;
		this.soluong = soluong;
		this.tinhtrang = tinhtrang;
		this.ghichu = ghichu;
		this.ct_phieumuons = ct_phieumuons;
	}



	public String getMatb() {
		return matb;
	}

	public void setMatb(String matb) {
		this.matb = matb;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}



	public Collection<CT_PHIEUMUON> getCt_phieumuons() {
		return ct_phieumuons;
	}



	public void setCt_phieumuons(Collection<CT_PHIEUMUON> ct_phieumuons) {
		this.ct_phieumuons = ct_phieumuons;
	}

	
	
	
	
}
