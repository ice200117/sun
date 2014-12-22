package cn.sun.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.sun.dao.GenericDAO;
import cn.sun.domain.Function;
import cn.sun.domain.Qiye;
import cn.sun.domain.Role;
import cn.sun.domain.User;
import cn.sun.domain.Wrfzjs;
import cn.sun.service.BaseService;
import cn.sun.utils.CommonUtility;
import cn.sun.utils.HibernateUtil;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 继承Service 提供通用 代码路径
 * 
 * @author seawind
 * 
 */
public class BaseServiceImpl implements BaseService{
	// 注入DAO
	@Autowired
	@Qualifier("userDAO")
	protected GenericDAO<User> userDAO;
	
	@Autowired
	@Qualifier("wrfzjsDAO")
	protected GenericDAO<Wrfzjs> wrfzjsDAO;
	@Autowired
	@Qualifier("qiyeDAO")
	protected GenericDAO<Qiye> qiyeDAO;
	
	@Autowired
	@Qualifier("funcDAO")
	protected GenericDAO<Function> funcDAO;
	
	@Autowired
	@Qualifier("roleDAO")
	protected GenericDAO<Role> roleDAO;
	
	/**
	 * 分页查询(抽取方法)
	 */
	public PageResponseBean paginationQuery(PageRequestBean pageRequestBean,
			GenericDAO dao) {
		
		// 根据 page 和 rows 计算 firstResult 和 maxResults
		int firstResult = (pageRequestBean.getPage() - 1)
				* pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();
		// 结果数据
		
		List list = dao.findByDetachedCriteria(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		
		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);

		int total = dao.findByTotalCount();
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}
	public PageResponseBean SQLPaginationQuery(PageRequestBean pageRequestBean,
			GenericDAO dao,String sql,Map map) {
		// 根据 page 和 rows 计算 firstResult 和 maxResults
		int firstResult = (pageRequestBean.getPage() - 1)
				* pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();

		// 结果数据
		List list = dao.execSQLQuery(firstResult, maxResults, sql,map);

		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);

		int total = dao.findByTotalCount();
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}
}
