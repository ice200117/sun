package cn.sun.domain;

import java.util.Date;

/**
 * 功能信息表
 * 
 * @author seawind
 * 
 */
public class Role {
	private String id;
	private String roleName;
	private String remark;
	private String defFunc;
	private String operator;
	private Date insertTime;
	private String delMark;
	private String yuliu1;
	private String yuliu2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDefFunc() {
		return defFunc;
	}
	public void setDefFunc(String defFunc) {
		this.defFunc = defFunc;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getDelMark() {
		return delMark;
	}
	public void setDelMark(String delMark) {
		this.delMark = delMark;
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
