package com.ccb.acten.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ccb.acten.ibatis.data.po.EmployeePo;
import com.ccb.acten.ibatis.service.EmployeeService;

public class TestIbatisApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:ibatis/spring/applicationContext-persist.xml" });
		EmployeeService employeeService = context.getBean(EmployeeService.class);
//		EmployeePo employeePo = employeeService.create("panda", "zoo", "eating bamboo");
//		System.out.println("employeePo.id="+employeePo.getId());;;;;;
		
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("name", "panda");
//		List<EmployeePo> list = employeeService.list(params);
//		assert(list.size()==1);
//		System.out.println("mainSkill="+list.get(0).getMainSkill());
		
		employeeService.createWithException2("gorilla", "zoo", "Strong an clever");
	}
}
