package cn.sun.service.impl;

import cn.sun.domain.Wrfzjs;
import cn.sun.service.WrfzjsService;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class WrfzjsServiceImpl extends BaseServiceImpl implements WrfzjsService{

	//添加污染防治技术信息
	public void addWrfzjs(Wrfzjs wrfzjs) {
		// TODO Auto-generated method stub
		wrfzjsDAO.save(wrfzjs);
	}

	//修改污染防治技术信息
	public void editWrfzjs(Wrfzjs model) {
		// TODO Auto-generated method stub
		Wrfzjs old=(Wrfzjs)wrfzjsDAO.findById(model.getWrfzjsId());
		
		try {
			//把model赋给持久层对象，自动更新数据
			BeanUtils.copyProperties(old, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
