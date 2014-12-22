package cn.sun.utils;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 存放 分页查询 请求数据
 * 
 * @author seawind
 * 
 */
public class PageRequestBean {
	private int page; // 页码
	private int rows; // 每页多少条

	private DetachedCriteria detachedCriteria; // 所有条件

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

}
