package cn.sun.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.commons.beanutils.BeanUtils;

import cn.sun.domain.User;
import cn.sun.service.UserService;
import cn.sun.utils.MD5Utils;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 用户操作
 * 
 * @author seawind
 * 
 */
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user) {
		// 根据用户名和密码查询
		List<User> users = userDAO.findByNamedQuery("loginQuery",
				user.getUsername(), MD5Utils.md5(user.getPassword()));
		return users.isEmpty() ? null : users.get(0);
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 */
	public void ModifyPassword(User user) {
		userDAO.update(user);
	}

	/**
	 * 添加用户
	 * 
	 * @param model
	 */
	public void addUser(User model) {
		// 密码 明文
		System.out.println(model.getPassword());
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userDAO.save(model);
	}

	/**
	 * 分页 查询方法
	 */
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

	/**
	 * 删除用户
	 * 
	 * @param idArr
	 */
	public void deleteBatch(String[] idArr) {
		for (String idStr : idArr) {
			int id = Integer.parseInt(idStr);
			User user = new User();
			user.setId(id);

			userDAO.delete(user);
		}
	}


	
	/**
	 * 根据id 查询
	 * 
	 * @param id
	 * @return
	 */
	public User findUser(Integer id) {
		return userDAO.findById(id);
	}

	public void editUser(User model) {

		User old=(User)userDAO.findById(model.getId());
		String oldPass=	old.getPassword();
		if(!model.getPassword().equals(oldPass)){
		model.setPassword(MD5Utils.md5(model.getPassword()));
		}
		try {
			//把model赋给持久层对象，自动更新数据�?
			BeanUtils.copyProperties(old, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
