package cn.sun.web.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.sun.domain.User;
import cn.sun.utils.MD5Utils;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 用户操作
 * 
 * @author seawind
 * 
 */
public class UserAction extends BaseAction {

	private String msg;
//	private  String id;

	// 创建 model对象
	public UserAction(String modelClassName) {
		try {
			model = Class.forName(modelClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改密码 (ajax 请求) ==========================================
	public String modifyPwd() {
		// 先从 Session 中取出当前 用户
		User user = (User) getSession().getAttribute("user");
		// 从model 取出 新密码 放入 user中
		user.setPassword(MD5Utils.md5(((User) model).getPassword()));

		// 调用Service 完成修改操作
		userService.ModifyPassword(user);

		msg = "success"; // 使用Ajax 发回客户端内容

		return "modifyPwdSUCCESS";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// 添加用户 ===================================
	public String add() {
		// 将model 传递给 业务层 保存到数据表
		//System.out.println(((User)model).getId());
		User user = (User)model;
		//System.out.println("----->>>>>"+(User)getSession().getAttribute("user"));
		user.setOperator(user.getUsername());

		if(((User)model).getId()==null||"".equals(((User)model).getId())){
			userService.addUser((User) model);
		
		}else
		{
			userService.editUser((User)model);	
		}
		return "addSUCCESS";
	}

	// 查询用户（Ajax 分页） =======================
	private int page;
	private int rows;

	private Date beginBirthday;
	private Date endBirthday;

	public void setBeginBirthday(Date beginBirthday) {
		this.beginBirthday = beginBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	public String paginationQuery() {
		// 将查询 参数 保存 PageRequestBean
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);

		// 判断查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(User.class);
		User user = (User) model;
		if (user.getUsername() != null
				&& user.getUsername().trim().length() > 0) {
			detachedCriteria.add(Restrictions.like("username",
					"%" + user.getUsername() + "%"));
		}
		//if (user.getGender() != null && user.getGender().trim().length() > 0) {
		//	detachedCriteria.add(Restrictions.eq("gender", user.getGender()));
		//}
		if (beginBirthday != null) {
			detachedCriteria.add(Restrictions.ge("birthday", beginBirthday));
		}
		if (endBirthday != null) {
			detachedCriteria.add(Restrictions.le("birthday", endBirthday));
		}
		pageRequestBean.setDetachedCriteria(detachedCriteria);

		// 调用业务层
		pageResponseBean = userService.pageQuery(pageRequestBean);

		return "paginationQuerySUCCESS";
	}

	private PageResponseBean pageResponseBean;

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageResponseBean getPageResponseBean() {
		return pageResponseBean;
	}

	public void setPageResponseBean(PageResponseBean pageResponseBean) {
		this.pageResponseBean = pageResponseBean;
	}

	// 删除用户 ==========================
	private String ids;

	public String delete() {
		// 获得每个要删除id
		String[] idArr = ids.split(",");
		// 调用业务层
		userService.deleteBatch(idArr);

		// 结果信息
		msg = "success";

		return "deleteSUCCESS";
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	// 根据id 查询用户 ===========================
	public String editView() {
		// model 在值栈中
		model = userService.findUser(((User) model).getId());
		return "editViewSUCCESS";
	}



}
