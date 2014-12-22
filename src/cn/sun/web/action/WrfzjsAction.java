package cn.sun.web.action;

import cn.sun.domain.User;
import cn.sun.domain.Wrfzjs;

public class WrfzjsAction extends BaseAction {
	
	// 创建污染防治技术对象
	public WrfzjsAction(String modelClassName) {
		try {
			model = Class.forName(modelClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String msg;
	
	// 添加技术 ===================================
	public String add() {
		// 将model 传递给 业务层 保存到数据表
		if(((Wrfzjs)model).getWrfzjsId()==null||"".equals(((Wrfzjs)model).getWrfzjsId())){
			wrfzjsService.addWrfzjs((Wrfzjs)model);		
		}else
		{
			wrfzjsService.editWrfzjs((Wrfzjs)model);	
		}
		return "addSUCCESS";
	}
}
