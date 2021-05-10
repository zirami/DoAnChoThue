package com.nhom2.entity;

import java.util.Collection;
import java.util.Date;

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
@Table(name="PHIEUTHANHLY")
public class PHIEUTHANHLY {
	@Id
	@Column(name = "MAPTL")
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String maptl;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "THOIGIAN")
	private Date thoigian;
	
	@Column(name = "GHICHU")
	private String ghichu;
	
	@ManyToOne	
	@JoinColumn(name = "MANV")
	private NHANVIEN nv;
	
	@OneToMany(mappedBy = "phieuthanhly", fetch = FetchType.EAGER)
	private Collection<CT_PHIEUTHANHLY> ct_phieuthanhlys;

	public PHIEUTHANHLY() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaptl() {
		return maptl;
	}

	public void setMaptl(String maptl) {
		this.maptl = maptl;
	}

	public Date getThoigian() {
		return thoigian;
	}

	public void setThoigian(Date thoigian) {
		this.thoigian = thoigian;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public NHANVIEN getNv() {
		return nv;
	}

	public void setNv(NHANVIEN nv) {
		this.nv = nv;
	}

	public Collection<CT_PHIEUTHANHLY> getCt_phieuthanhlys() {
		return ct_phieuthanhlys;
	}

	public void setCt_phieuthanhlys(Collection<CT_PHIEUTHANHLY> ct_phieuthanhlys) {
		this.ct_phieuthanhlys = ct_phieuthanhlys;
	}

	}
