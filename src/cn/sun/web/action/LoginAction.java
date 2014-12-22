package cn.sun.web.action;

import cn.sun.domain.User;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 登陆 请求处理
 * 
 * @author seawind
 * 
 */
public class LoginAction extends BaseAction implements ModelDriven {

	private String key; // 验证码

	public LoginAction(String modelClassName) {
		try {
			//System.out.println(">>>>"+modelClassName);
			model = Class.forName(modelClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String execute() throws Exception {
		// 验证码判断
		String key_session = (String) getSession().getAttribute("key");
		if (key_session == null || !key_session.equals(key)) {
			this.addFieldError("keyfail", this.getText("keyfail"));
			return INPUT;
		}

		User user = userService.login((User) model);
		// 判断登陆是否成功
		if (user == null) {
			// 失败
			this.addActionError(this.getText("loginfail"));
			return INPUT;
		} else {
			// 成功
			getSession().setAttribute("user", user);
			return SUCCESS;
		}

	}

	public void setKey(String key) {
		this.key = key;
	}
}
