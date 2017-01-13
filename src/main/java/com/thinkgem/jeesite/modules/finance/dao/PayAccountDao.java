package com.thinkgem.jeesite.modules.finance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.PayAccount;

@MyBatisDao
public interface PayAccountDao extends CrudDao<PayAccount> {

}
