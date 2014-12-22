package cn.sun.service;

import org.springframework.transaction.annotation.Transactional;

import cn.sun.domain.Wrfzjs;

@Transactional
public interface WrfzjsService {
	/**
	 * 添加技术
	 * 
	 * @param model
	 */
	public void addWrfzjs(Wrfzjs wrfzjs);

	public void editWrfzjs(Wrfzjs wrfzjs);
}
