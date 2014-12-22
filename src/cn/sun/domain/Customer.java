package cn.sun.domain;

import java.util.Date;

/**
 * 绯荤粺鐢ㄦ埛绫�
 * 
 * @author seawind
 * 
 */
public class Customer {
	private Integer id;
	private String customername; // 鐢ㄦ埛鍚�
	private String password; // 瀵嗙爜

	private String repassword; // 閲嶅瀵嗙爜

	private Double salary; // 宸ヨ祫
	private Date birthday; // 鐢熸棩

	private String gender; // 鎬у埆
	private String station; // 鍗曚綅

	private String telephone; // 鎵嬫満鍙�
	private String remark; // 澶囨敞

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
