package cn.sun.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.sun.service.FuncService;
import cn.sun.service.QiyeService;
import cn.sun.service.RoleService;
import cn.sun.service.UserService;
import cn.sun.service.WrfzjsService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 公共Action
 * 
 * @author seawind
 * 
 */
public class BaseAction extends ActionSupport implements ModelDriven{

	// 完成模型驱动
	protected Object model;

	public Object getModel() {
		return model;
	}


	// 注入Service
	@Autowired
	@Qualifier("userService")
	protected UserService userService;	
	@Autowired
	@Qualifier("funcService")
	protected FuncService funcService;
	@Autowired
	@Qualifier("roleService")
	protected RoleService roleService;
	
	// 注入Service
	@Autowired
	@Qualifier("wrfzjsService")
	protected WrfzjsService wrfzjsService;
	@Autowired
	@Qualifier("qiyeService")
	protected QiyeService qiyeService;

	// 简化Servlet API
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
}
