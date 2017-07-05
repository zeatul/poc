package com.flow.client;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 渠道订单请求
 * 
 * @author Administrator
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderRequest {
	private String productCode;
	private String clientCode;
	private String clientOrderNo;
	private String phone;
	private String notifyUrl;
	private String sign;
	private String subClientCode;
	private int orderType = 1;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientOrderNo() {
		return clientOrderNo;
	}

	public void setClientOrderNo(String clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSubClientCode() {
		return subClientCode;
	}

	public void setSubClientCode(String subClientCode) {
		this.subClientCode = subClientCode;
	}

	
	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	/**
	 * 检查请求参数签名,请求的签名原串格式为clientCode=XXXclientOrderNo=XXXXphone=XXXproductCode=
	 * XXXnotifyUrl=XXXXKEY
	 * 
	 * @param key
	 * @return
	 */
	public boolean checkSign(String key) {
		return StringUtil.equals(this.sign, this.getSignText(key));
	}

	public String getSignText(String key) {
		StringBuffer buf = new StringBuffer();

		buf.append("clientCode=").append(this.clientCode);
		if (!StringUtil.isNullOrEmpty(subClientCode)) {
			buf.append("subClientCode=").append(this.subClientCode);
		}
		buf.append("clientOrderNo=").append(this.clientOrderNo);
		buf.append("phone=").append(this.phone);
		buf.append("productCode=").append(this.productCode);
		if (!StringUtil.isNullOrEmpty(this.notifyUrl)) {
			buf.append("notifyUrl=").append(this.notifyUrl);
		}
		if (this.orderType > 0 && 1 != this.orderType) {
			buf.append("orderType=").append(this.orderType);
		}
		buf.append(key);
		return EncrpytionUtil.str2MD5(buf.toString());
		// return sign;
	}

}
