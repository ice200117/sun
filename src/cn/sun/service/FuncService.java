package cn.sun.service;

import org.springframework.transaction.annotation.Transactional;

import cn.sun.domain.Function;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 用户操作
 * 
 */
@Transactional
public interface FuncService{
	/**
	 * 添加功能
	 * 
	 * @param model
	 */
	public void addFunc(Function model);

	/**
	 * 分页 查询方法
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) ;

	/**
	 * 删除功能
	 * 
	 * @param idArr
	 */
	public void deleteBatch(String[] idArr) ;

	
	/**
	 * 根据id 查询
	 * 
	 * @param id
	 * @return
	 */
	public Function findFuncById(String id);

	/**
	 * 修改用户
	 * @param model
	 */
	public void editFunc(Function model) ;

}
