package com.ccb.acten.ibatis.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccb.acten.ibatis.data.dos.EmployeeDos;
import com.ccb.acten.ibatis.data.po.EmployeePo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDos employeeDos;
	
	public EmployeePo create(String name ,String company ,String mainSkill){
		EmployeePo employeePo = new EmployeePo();
		employeePo.setCompany(company);
		employeePo.setName(name);
		employeePo.setMainSkill(mainSkill);
		employeePo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		employeeDos.insert(employeePo);
		return employeePo;		
	}
	
	public List<EmployeePo> list(Map<String,Object> params){
		return employeeDos.list(params);
	}
	
	@Transactional
	public void createWithException(String name ,String company ,String mainSkill){
		EmployeePo employeePo = new EmployeePo();
		employeePo.setCompany(company);
		employeePo.setName(name);
		employeePo.setMainSkill(mainSkill);
		employeePo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		employeeDos.insert(employeePo);
		throw new RuntimeException();
	}
	
	public void createWithException2(String name ,String company ,String mainSkill){
		EmployeePo employeePo = new EmployeePo();
		employeePo.setCompany(company);
		employeePo.setName(name);
		employeePo.setMainSkill(mainSkill);
		employeePo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		employeeDos.insert(employeePo);
		throw new RuntimeException();
	}
}
