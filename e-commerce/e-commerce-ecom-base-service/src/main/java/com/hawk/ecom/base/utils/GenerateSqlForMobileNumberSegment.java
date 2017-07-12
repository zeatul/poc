package com.hawk.ecom.base.utils;

import java.util.List;

import com.hawk.framework.utility.tools.ResourceTools;

public class GenerateSqlForMobileNumberSegment {
	
	private static class Segment{
		public String getPrefix() {
			return prefix;
		}
		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getProvinceCode() {
			return provinceCode;
		}
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}
		public String getMobileOperatorCode() {
			return mobileOperatorCode;
		}
		public void setMobileOperatorCode(String mobileOperatorCode) {
			this.mobileOperatorCode = mobileOperatorCode;
		}
		public String getIsp() {
			return isp;
		}
		public void setIsp(String isp) {
			this.isp = isp;
		}
		public String getIspProduct() {
			return ispProduct;
		}
		public void setIspProduct(String ispProduct) {
			this.ispProduct = ispProduct;
		}
		private String prefix;
		private String cityCode;
		private String provinceCode;
		private String mobileOperatorCode;
		private String isp;
		private String ispProduct;
	}

	public static void main(String[] args){
		List<String> list = ResourceTools.readResource("/手机号段-20170601-363952-全新版.txt", GenerateSqlForDistrict.class);
		System.out.println("############ import mobile number segment ###############");
		System.out.println("truncate table t_bas_mobile_number_segment;");
		
		for (int i=1; i<list.size(); i++){
			String[] strArray = list.get(i).split("\t");
			Segment segment = new Segment();
			segment.setCityCode(strArray[7].trim());
			segment.setIsp(strArray[4].trim());
			segment.setIspProduct(strArray[8].trim());
			String mobileOperatorCode = strArray[4].trim();
			if (mobileOperatorCode.endsWith("联通")){
				mobileOperatorCode = "unicom";
			}else if (mobileOperatorCode.endsWith("电信")){
				mobileOperatorCode = "telecom";
			}else if (mobileOperatorCode.endsWith("移动")){
				mobileOperatorCode = "mobile";
			}else{
				throw new RuntimeException("unknown mobile operator");
			}
			segment.setMobileOperatorCode(mobileOperatorCode);
			segment.setPrefix(strArray[1].trim());
			segment.setProvinceCode(segment.getCityCode().trim().substring(0,2)+"0000");
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO t_bas_mobile_number_segment(mobile_number_prefix,city_code,province_code,mobile_operator_code,isp,isp_product) value(");
			sb.append("'").append(segment.getPrefix()).append("',");
			sb.append("'").append(segment.getCityCode()).append("',");
			sb.append("'").append(segment.getProvinceCode()).append("',");
			sb.append("'").append(segment.getMobileOperatorCode()).append("',");
			sb.append("'").append(segment.getIsp()).append("',");
			sb.append("'").append(segment.getIspProduct()).append("');");
			System.out.println(sb.toString());
			
		}
	}
}
