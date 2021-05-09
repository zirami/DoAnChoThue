package com.nhom2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PHANQUYEN")
public class PHANQUYEN {
	@Id
	@Column(name="MAPQ")
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String mapq;
	
	@NotNull(message="Trường này không được bỏ trống!")
	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="TENPQ")
	private String tenpq;

	public PHANQUYEN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PHANQUYEN(String mapq,String tenpq) {
		super();
		this.mapq = mapq;
		this.tenpq = tenpq;
	}

	public String getMapq() {
		return mapq;
	}

	public void setMapq(String mapq) {
		this.mapq = mapq;
	}

	public String getTenpq() {
		return tenpq;
	}

	public void setTenpq(String tenpq) {
		this.tenpq = tenpq;
	}
	
	
}
