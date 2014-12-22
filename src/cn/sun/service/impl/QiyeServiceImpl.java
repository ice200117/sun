package cn.sun.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import cn.sun.dao.GenericDAO;
import cn.sun.domain.Qiye;
import cn.sun.service.QiyeService;
import cn.sun.utils.CommonUtility;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 用户操作
 * 
 * @author seawind
 * 
 */
@Transactional
public class QiyeServiceImpl extends BaseServiceImpl implements QiyeService {
	

	/**
	 * 添加企业
	 * 
	 * @param model
	 */
	public void addOrUpdateQiye(Qiye qiye){
		if (qiye.getQyId() == null || qiye.getQyId().trim().length() == 0) {
			// 添加一个新标准
			qiyeDAO.save(qiye);
		} else {
			// 修改一个标准
			qiyeDAO.update(qiye);
		}
	}

	public PageResponseBean paginationQuery(PageRequestBean pageRequestBean) {
		// TODO Auto-generated method stub
		
		if(qiyeDAO==null)
		{
			qiyeDAO=new GenericDAO<Qiye>("Qiye");
		}
		PageResponseBean pageResponseBean =paginationQuery(pageRequestBean, qiyeDAO);
		List<Qiye> qiyes  = pageResponseBean.getRows();
		// 手动加载关联用户
		for (Qiye qiye : qiyes) {
			Hibernate.initialize(qiye.getUser());
		}
		return pageResponseBean;
	}

	/**
	 * 分页 查询方法
	 */
/*
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		// 根据 page �?rows 计算 firstResult �?maxResults
		int firstResult = (pageRequestBean.getPage() - 1)
				* pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();

		// 结果数据
		List list = userDAO.findByDetachedCriteria(
				pageRequestBean.getDetachedCriteria(),
				firstResult, maxResults);

		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);

		int total = userDAO.findByTotalCount();
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}
*/
	/*
	public void deleteBatch(String[] idArr) {
		for (String idStr : idArr) {
			int id = Integer.parseInt(idStr);
			User user = new User();
			user.setId(id);

			userDAO.delete(user);
		}
	}

*/
	public void deleteBatch(String[] idArr) {
		for (String id : idArr) {
			Qiye qiye = qiyeDAO.findById(id);
			qiye.setQyScbj("1");// 标记为删除
		}
	}
	public PageResponseBean SqlPaginationQuery(PageRequestBean pageRequestBean,
			String sql,Map map) {
		// 自定义SQL查询
		PageResponseBean pageResponseBean =SQLPaginationQuery(pageRequestBean, qiyeDAO,sql,map);
		List<Qiye> qiyes  = pageResponseBean.getRows();
		//CommonUtility.PrintInfo(qiyes.get(0).getName()+">>>>>>");
		// 手动加载关联用户
		for (Qiye qiye : qiyes) {
			Hibernate.initialize(qiye.getUser());
		}
		return pageResponseBean;
	}

	

}
