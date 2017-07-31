package com.thinkgem.jeesite.modules.finance.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * <p>
 * Payment:收支流,记录每笔的支付方,支付对象,金额
 * </p>
 * 
 * @author liuyujian
 * @version 0.0.1
 * @date 2017年1月12日 下午3:00:25
 * @since JDK 1.7
 */
public class PayFlow extends DataEntity<PayFlow> {

	/** serialVersionUID **/
	private static final long serialVersionUID = 1L;

	public PayFlow() {
		super();
	}
	private long serialNo;//支付号,以当前时间戳未记录
	private String fromWay;// 支付来源
	private String toWay;// 支付对象
	private String payName;// 款项
	private float amount;// 支付金额

	public String getFromWay() {
		return fromWay;
	}

	public void setFromWay(String fromWay) {
		this.fromWay = fromWay;
	}

	public String getToWay() {
		return toWay;
	}

	public void setToWay(String toWay) {
		this.toWay = toWay;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

}
