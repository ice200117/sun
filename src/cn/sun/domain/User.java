package cn.sun.domain;

import java.util.Date;

/**
 * 系统用户类
 * 
 * @author seawind
 * 
 */
public class User {
	private Integer id;
	private String username; // 用户名
	private String password; // 密码
	
	private String repassword; // 重复密码

	
	private String userType;
	private String userMail;
	private String userPhone;
	private String operator;
	private Date createTime;
	private Date alterTime;
	private String deleteMark;
	private String yuliu1;
	private String yuliu2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(Date alterTime) {
		this.alterTime = alterTime;
	}

	public String getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(String deleteMark) {
		this.deleteMark = deleteMark;
	}

	public String getYuliu1() {
		return yuliu1;
	}

	public void setYuliu1(String yuliu1) {
		this.yuliu1 = yuliu1;
	}

	public String getYuliu2() {
		return yuliu2;
	}

	public void setYuliu2(String yuliu2) {
		this.yuliu2 = yuliu2;
	}

	
}
