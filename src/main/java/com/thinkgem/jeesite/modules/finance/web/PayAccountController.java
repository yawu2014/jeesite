package com.thinkgem.jeesite.modules.finance.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.PayAccount;
import com.thinkgem.jeesite.modules.finance.service.PayAccountService;

@Controller
@RequestMapping(value = "${adminPath}/finance/payaccount")
public class PayAccountController extends BaseController {
	@Autowired
	private PayAccountService payAccountService;

	@ModelAttribute
	public PayAccount get(String id) {
		if (StringUtils.isNotBlank(id)) {
			return payAccountService.get(id);
		} else {
			return new PayAccount();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(PayAccount payAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PayAccount> list = payAccountService.findList(payAccount);
		model.addAttribute("page", list);
		return "modules/finance/payAccountList";
	}

	@RequestMapping(value = "form")
	public String form(PayAccount payAccount, Model model) {
		model.addAttribute("payAccount", payAccount);
		return "modules/finance/payAccountForm";
	}

	@RequestMapping(value = "save")
	public String save(PayAccount payAccount, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/finance/payaccount/list?repage";
		}
		payAccountService.save(payAccount);
		addMessage(redirectAttributes, "保存账户" + payAccount.getName() + "成功!");
		return "redirect:" + adminPath + "/finance/payaccount/list?repage";
	}
}
