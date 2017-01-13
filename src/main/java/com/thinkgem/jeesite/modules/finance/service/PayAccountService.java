package com.thinkgem.jeesite.modules.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finance.dao.PayAccountDao;
import com.thinkgem.jeesite.modules.finance.entity.PayAccount;

@Service
public class PayAccountService extends CrudService<PayAccountDao, PayAccount> {
	@Autowired
	private PayAccountDao payAccountDao;

	/**
	 * 
	 * <p>
	 * 获取所有的账户信息
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年1月13日 下午3:12:51
	 * @param payAccount
	 * @return
	 */
	public List<PayAccount> findAllList(PayAccount payAccount) {
		if (payAccount == null) {
			return payAccountDao.findAllList(new PayAccount());
		}
		return payAccountDao.findAllList(payAccount);
	}

}
