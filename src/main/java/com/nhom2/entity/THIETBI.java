package com.nhom2.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="THIETBI")
public class THIETBI {
	@Id
	@Column(name="MATB")
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String matb;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="TEN")
	private String ten;
	
	@NotNull(message="Trường này không được bỏ trống!")	
	@ManyToOne	
	@JoinColumn(name = "LOAI")
	private LOAITHIETBI loai;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@Column(name="SOLUONG")
	private Integer soluong;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="TINHTRANG")
	private String tinhtrang;
	
	@Column(name="GHICHU")
	private String ghichu;

	@OneToMany(mappedBy = "thietbi_muon", fetch = FetchType.EAGER)
	private Collection<CT_PHIEUMUON> ct_phieumuons;
	
	public THIETBI() {
		super();
	}

	public LOAITHIETBI getLoai() {
		return loai;
	}


	public void setLoai(LOAITHIETBI loai) {
		this.loai = loai;
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
