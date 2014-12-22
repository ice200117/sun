package cn.sun.utils;

import java.util.List;

/**
 * 存放分页查询 结果数据 ，在序列化时 生成 datagrid 需要json 格式
 * 
 * @author seawind
 * 
 */
@SuppressWarnings("rawtypes")
public class PageResponseBean {
	private int total;
	private List rows; // 集合 存放每个对象

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
