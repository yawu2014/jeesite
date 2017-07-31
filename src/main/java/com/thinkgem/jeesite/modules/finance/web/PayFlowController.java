package com.thinkgem.jeesite.modules.finance.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.PayAccount;
import com.thinkgem.jeesite.modules.finance.entity.PayFlow;
import com.thinkgem.jeesite.modules.finance.service.PayAccountService;
import com.thinkgem.jeesite.modules.finance.service.PayFlowService;

@Controller
@RequestMapping(value = "${adminPath}/finance/payflow")
public class PayFlowController extends BaseController {
	@Autowired
	private PayFlowService payFlowService;
	@Autowired
	private PayAccountService payAccountService;

	@ModelAttribute
	public PayFlow get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return payFlowService.get(id);
		} else {
			return new PayFlow();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(PayFlow payFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
//		List<PayFlow> list = payFlowService.findList(payFlow);
		Page<PayFlow> page = payFlowService.findPage(new Page<PayFlow>(request,response), payFlow);
		model.addAttribute("page", page);
		return "modules/finance/payFlowList";
	}

	@RequestMapping(value = "form")
	public String form(PayFlow payFlow, Model model) {
		List<PayAccount> payAccounts = payAccountService.findAllList(null);
		List<PayAccount> fromWay = new ArrayList<PayAccount>();
		List<PayAccount> toWay = new ArrayList<PayAccount>();
		if (!CollectionUtils.isEmpty(payAccounts)) {
			for (PayAccount payAccount : payAccounts) {
				switch (payAccount.getType()) {
				case PayAccount.TYPE_INNER:
					fromWay.add(payAccount);
					toWay.add(payAccount);
					break;
				case PayAccount.TYPE_IN:
					fromWay.add(payAccount);
					break;
				case PayAccount.TYPE_OUT:
					toWay.add(payAccount);
					break;
				}
			}
		}
		model.addAttribute("fromWay", fromWay);
		model.addAttribute("toWay", toWay);

		model.addAttribute("payFlow", payFlow);
		return "modules/finance/payFlowForm";
	}

	@RequestMapping(value = "save")
	public String save(PayFlow payFlow, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/finance/payflow/list?repage";
		}
		
		payFlowService.save(payFlow);
		addMessage(redirectAttributes, "保存流水" + payFlow.getPayName() + "成功!");
		return "redirect:" + adminPath + "/finance/payflow/list?repage";
	}
}
