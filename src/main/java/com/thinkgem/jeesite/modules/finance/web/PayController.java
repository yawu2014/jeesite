package com.thinkgem.jeesite.modules.finance.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.service.PayAccountService;
import com.thinkgem.jeesite.modules.finance.service.PayFlowService;

@Controller
@RequestMapping(value = "${adminPath}/finance/pay")
public class PayController extends BaseController {
	@Autowired
	private PayFlowService payFlowService;
	@Autowired
	private PayAccountService payAccountService;

	@RequestMapping(value = { "/list", "" })
	public String list() {

		return "modules/finance/payList";
	}
}
