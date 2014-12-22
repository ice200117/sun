package cn.sun.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.commons.beanutils.BeanUtils;

import cn.sun.domain.Function;
import cn.sun.domain.User;
import cn.sun.service.FuncService;
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
public class FuncServiceImpl extends BaseServiceImpl implements FuncService {

	/**
	 * 分页 查询方法
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		// 根据 page �?rows 计算 firstResult �?maxResults
		int firstResult = (pageRequestBean.getPage() - 1)
				* pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();

		// 结果数据
		List list = funcDAO.findByDetachedCriteria(
				pageRequestBean.getDetachedCriteria(),
				firstResult, maxResults);

		PageResponseBean pageResponseBean = new PageResponseBean();
		pageResponseBean.setRows(list);

		int total = funcDAO.findByTotalCount();
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
			Function func = new Function();
			func.setId(idStr);

			funcDAO.delete(func);
		}
	}


	public void addFunc(Function model) {
		//System.out.println(model.getPassword());
		//model.setPassword(MD5Utils.md5(model.getPassword()));
		funcDAO.save(model);
	}

	public Function findFuncById(String id) {
		return funcDAO.findById(id);
	}

	public void editFunc(Function model) {
		Function old=(Function)funcDAO.findById(model.getId());
		/*String oldPass=	old.getPassword();
		if(!model.getPassword().equals(oldPass)){
		model.setPassword(MD5Utils.md5(model.getPassword()));
		}*/
		try {
			//把model赋给持久层对象，自动更新数据�?
			BeanUtils.copyProperties(old, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
