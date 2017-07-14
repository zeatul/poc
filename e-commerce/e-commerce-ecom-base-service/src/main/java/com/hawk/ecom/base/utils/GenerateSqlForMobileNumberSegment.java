package com.hawk.ecom.base.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.hawk.framework.utility.tools.ResourceTools;
import com.hawk.framework.utility.tools.StringTools;

public class GenerateSqlForMobileNumberSegment {

	private static class Segment {
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

	public static void main(String[] args) throws Exception {
		List<String> list = ResourceTools.readResource("/手机号段-20170601-363952-全新版.txt", GenerateSqlForDistrict.class);
		long stdt = System.currentTimeMillis();
		
		OutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(new File("c:/mydata/init_data_base_mobile_number_segment.sql"), false), 1000 * 1024 * 1024);
			os.write(("############ import mobile number segment ###############"+System.getProperty("line.separator")).getBytes("utf-8"));
			os.write(("truncate table t_bas_mobile_number_segment;"+System.getProperty("line.separator")).getBytes("utf-8"));

			for (int i = 1; i < list.size(); i++) {
				String line = list.get(i);
				Segment segment = new Segment();
				try {					
					String[] strArray = line.split("\t");
					
					segment.setCityCode(strArray[7].trim());
					segment.setIsp(strArray[4].trim());
					try {
						segment.setIspProduct(strArray[8].trim());
					} catch (Exception e) {
						segment.setIspProduct("unknown");
					}
					String mobileOperatorCode = strArray[4].trim();
					if (line.indexOf("联通") != -1) {
						mobileOperatorCode = "unicom";
					} else if (line.indexOf("电信") != -1) {
						mobileOperatorCode = "telecom";
					} else if (line.indexOf("移动") != -1) {
						mobileOperatorCode = "mobile";
					} else {
						throw new RuntimeException("unknown mobile operator");
					}
					segment.setMobileOperatorCode(mobileOperatorCode);
					segment.setPrefix(strArray[1].trim());
					segment.setProvinceCode(segment.getCityCode().trim().substring(0, 2) + "0000");

					StringBuilder sb = StringTools.getThreadSafeStringBuilder();
					sb.append("INSERT INTO t_bas_mobile_number_segment(mobile_number_prefix,city_code,province_code,mobile_operator_code,isp,isp_product) value(");
					sb.append("'").append(segment.getPrefix()).append("',");
					sb.append("'").append(segment.getCityCode()).append("',");
					sb.append("'").append(segment.getProvinceCode()).append("',");
					sb.append("'").append(segment.getMobileOperatorCode()).append("',");
					sb.append("'").append(segment.getIsp()).append("',");
					sb.append("'").append(segment.getIspProduct()).append("');");
					sb.append(System.getProperty("line.separator"));
					os.write(sb.toString().getBytes("utf-8"));

					if (i % 2000 == 0) {
						os.flush();
						System.out.println(i);
					}
				} catch (Exception e) {
					System.out.println("******line=" +line);
					e.printStackTrace();
				}

			}
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
				}
			}
			
			long endt = System.currentTimeMillis();
			
			System.out.println((endt-stdt)/1000/60 + " minutes");
		}
	}
}
