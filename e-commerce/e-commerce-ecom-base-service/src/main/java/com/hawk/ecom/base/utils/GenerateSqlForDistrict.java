package com.hawk.ecom.base.utils;

import java.util.List;

import com.hawk.framework.utility.tools.ResourceTools;
import com.hawk.framework.utility.tools.StringTools;

public class GenerateSqlForDistrict {
	
	private static class District{
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getParentCode() {
			return parentCode;
		}
		public void setParentCode(String parentCode) {
			this.parentCode = parentCode;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private String code;
		private String name;
		private String parentCode;
		private String type;
	}
	
	public static void main(String[] args){		
		List<String> list = ResourceTools.readResource("/行政区.txt", GenerateSqlForDistrict.class);
		System.out.println("############ import districts ###############");
		System.out.println("truncate table t_bas_district;");
		
		for (String strs : list){
			String[] strArray = strs.trim().split(" ");
			District district = new District();
			for (String str : strArray){
				str = str.trim().replaceAll((char)(12288)+"", "");
				if (StringTools.isNotNullOrEmpty(str)){
					if (district.getCode()  == null){
						district.setCode(str);
					}else{
						district.setName(str);
					}
				}
			}
			if (district.getCode().endsWith("0000")){
				district.setType("01");
				district.setParentCode("root");
			}else if (district.getCode().endsWith("00")){
				district.setType("02");
				district.setParentCode(district.getCode().substring(0, 2)+"0000");
			}else{
				district.setType("03");
				district.setParentCode(district.getCode().substring(0, 4)+"00");
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO t_bas_district(district_code,district,parent_district_code,district_type) value(");
			sb.append("'").append(district.getCode()).append("',");
			sb.append("'").append(district.getName()).append("',");
			sb.append("'").append(district.getParentCode()).append("',");
			sb.append("'").append(district.getType()).append("');");
			System.out.println(sb.toString());
		}
	}
}
