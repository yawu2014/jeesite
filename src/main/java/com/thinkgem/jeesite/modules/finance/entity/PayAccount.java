package com.thinkgem.jeesite.modules.finance.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * <p>
 * Payer:支付账户
 * </p>
 * 
 * @author liuyujian
 * @version 0.0.1
 * @date 2017年1月12日 下午3:03:54
 * @since JDK 1.7
 */
public class PayAccount extends DataEntity<PayAccount> {

	/** serialVersionUID **/
	private static final long serialVersionUID = 1L;
	public static final int TYPE_IN = 0;// 来源
	public static final int TYPE_OUT = 2;// 支出
	public static final int TYPE_INNER = 1;// 个人内部账户
	private int type;//
	private String name;
	private float amount;

	public PayAccount() {
		super();
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
