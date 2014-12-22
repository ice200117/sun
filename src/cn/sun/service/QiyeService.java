package cn.sun.service;

import java.util.Map;

import cn.sun.domain.Qiye;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

public interface QiyeService {
	/**
	 * 添加企业
	 * 
	 * @param model
	 */

	public PageResponseBean paginationQuery(PageRequestBean pageRequestBean);
	/*
	 * 自定义SQL分页查询
	 */
	public PageResponseBean SqlPaginationQuery(PageRequestBean pageRequestBean,String sql,Map map);

	public void addOrUpdateQiye(Qiye qiye);

	public void deleteBatch(String[] idArr);
}
