package cn.sun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.commons.beanutils.BeanUtils;

import cn.sun.domain.User;
import cn.sun.utils.MD5Utils;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 用户操作
 * 
 */
@Transactional
public interface UserService{
	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 修改密码
	 * 
	 * @param user
	 */
	public void ModifyPassword(User user) ;

	/**
	 * 添加用户
	 * 
	 * @param model
	 */
	public void addUser(User model);

	/**
	 * 分页 查询方法
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) ;

	/**
	 * 删除用户
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
	public User findUser(Integer id);

	/**
	 * 修改用户
	 * @param model
	 */
	public void editUser(User model) ;

}
