package com.thinkgem.jeesite.modules.finance.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finance.dao.PayFlowDao;
import com.thinkgem.jeesite.modules.finance.entity.PayFlow;

@Service
@Transactional(readOnly = true)
public class PayFlowService extends CrudService<PayFlowDao, PayFlow> {
	@Override
	public void save(PayFlow entity) {
		entity.setSerialNo(new Date().getTime());
		super.save(entity);
	}
}
