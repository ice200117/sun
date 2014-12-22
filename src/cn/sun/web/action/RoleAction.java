package cn.sun.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.sun.domain.Function;
import cn.sun.domain.Role;
import cn.sun.domain.User;
import cn.sun.utils.PageRequestBean;
import cn.sun.utils.PageResponseBean;

/**
 * 用户操作
 * 
 * @author seawind
 * 
 */
public class RoleAction extends BaseAction {

	private String msg;
//	private  String id;
	private int page;
	private int rows;
	private PageResponseBean pageResponseBean;
	private String ids;

	// 创建 model对象
	public RoleAction(String modelClassName) {
		try {
			model = Class.forName(modelClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	// 添加用户 ===================================
	public String add() {
		// 将model 传递给 业务层 保存到数据表
		Role role = (Role)model;
		role.setOperator(((User)getSession().getAttribute("user")).getUsername());
		if(((Role)model).getId()==null||"".equals(((Role)model).getId())){
			roleService.addRole((Role) model);
		
		}else
		{
			roleService.editRole((Role)model);	
		}
		return "addSUCCESS";
	}

	// 查询用户（Ajax 分页） =======================
	public String paginationQuery() {
		// 将查询 参数 保存 PageRequestBean
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);

		// 判断查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Role.class);
		Role role = (Role) model;
		if (role.getRoleName() != null
				&& role.getRoleName().trim().length() > 0) {
			detachedCriteria.add(Restrictions.like("roleName",
					"%" + role.getRoleName() + "%"));
		}
		
		pageRequestBean.setDetachedCriteria(detachedCriteria);

		// 调用业务层
		pageResponseBean = roleService.pageQuery(pageRequestBean);

		return "paginationQuerySUCCESS";
	}
	// 删除用户 ==========================

	public String delete() {
		// 获得每个要删除id
		String[] idArr = ids.split(",");
		// 调用业务层
		roleService.deleteBatch(idArr);

		// 结果信息
		msg = "success";

		return "deleteSUCCESS";
	}

	
	// 根据id 查询用户 ===========================
	public String editView() {
		// model 在值栈中
		model = roleService.findRoleById(((Role) model).getId());
		return "editViewSUCCESS";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
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
	
	public void setIds(String ids) {
		this.ids = ids;
	}
}
