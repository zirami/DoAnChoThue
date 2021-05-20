package com.nhom2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ACCOUNT")
public class ACCOUNT {
	@Id
	@Column(name="USERNAME")
//	@NotNull(message="Trường này không được bỏ trống!")
//	@NotBlank(message = "Vui lòng điền vào trường này !")
	private String username;
	
//	@NotNull(message="Trường này không được bỏ trống!")
//	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="PASSWORD")
	private String password;
	
//	@NotNull(message="Trường này không được bỏ trống!")
//	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="GMAIL")
	private String gmail;
	
//	@NotNull(message="Trường này không được bỏ trống!")
//	@NotBlank(message = "Vui lòng điền vào trường này !")
	@Column(name="SDT")
	private String sdt;
	
	@ManyToOne	
	@JoinColumn(name = "MAPQ")
	private PHANQUYEN phanquyen;

	public ACCOUNT() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public PHANQUYEN getPhanquyen() {
		return phanquyen;
	}

	public void setPhanquyen(PHANQUYEN phanquyen) {
		this.phanquyen = phanquyen;
	}

	@Override
	public String toString() {
		return "ACCOUNT [username=" + username + ", password=" + password + ", gmail=" + gmail + ", sdt=" + sdt
				+ ", phanquyen=" + phanquyen + "]";
	}

	
	
}
