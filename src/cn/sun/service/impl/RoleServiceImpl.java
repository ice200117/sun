package cn.sun.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.sun.domain.Function;
import cn.sun.domain.Role;
import cn.sun.service.RoleService;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * 用户操作
 * 
 * @author seawind
 * 
 */
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	/**
	 * 分页 查询方法
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		// 根据 page �?rows 计算 firstResult �?maxResults
		int firstResult = (pageRequestBean.getPage() - 1)
				* pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();

		// 结果数据
		List list = roleDAO.findByDetachedCriteria(
				pageRequestBean.getDetachedCriteria(),
				firstResult, maxResults);

		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);

		int total = roleDAO.findByTotalCount();
		pageResponseBean.setTotal(total);

		return pageResponseBean;
	}

	/**
	 * 删除用户
	 * 
	 * @param idArr
	 */
	public void deleteBatch(String[] idArr) {
		for (String idStr : idArr) {
			//int id = Integer.parseInt(idStr);
			Role role = new Role();
			role.setId(idStr);

			roleDAO.delete(role);
		}
	}

	public void addRole(Role model) {
		roleDAO.save(model);
	}

	public Role findRoleById(String id) {
		return roleDAO.findById(id);
	}

	public void editRole(Role model) {
		Role old=(Role)roleDAO.findById(model.getId());
		try {
			//把model赋给持久层对象，自动更新数据�?
			BeanUtils.copyProperties(old, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
