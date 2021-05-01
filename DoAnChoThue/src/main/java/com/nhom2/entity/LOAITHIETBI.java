package com.nhom2.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="LOAITHIETBI")
public class LOAITHIETBI {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="TEN")
	private String ten;
	
	
	@OneToMany(mappedBy = "loai", fetch = FetchType.EAGER)
	private Collection<THIETBI> thietbis;


	public LOAITHIETBI() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTen() {
		return ten;
	}


	public void setTen(String ten) {
		this.ten = ten;
	}


	public Collection<THIETBI> getThietbis() {
		return thietbis;
	}


	public void setThietbis(Collection<THIETBI> thietbis) {
		this.thietbis = thietbis;
	}
	
	
	
	
}
