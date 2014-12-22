package cn.sun.domain;

import java.util.Date;

/**
 * 功能信息表
 * 
 * @author seawind
 * 
 */
public class Function {
	private String id;
	private String funcName;
	private String url;
	private String order;
	private String parentId;
	private String page;
	private String operator;
	private Date insertTime;
	//private Date updateTime;
	private String delMark;
	private String yuliu1;
	private String yuliu2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
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
	/*public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}*/
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
