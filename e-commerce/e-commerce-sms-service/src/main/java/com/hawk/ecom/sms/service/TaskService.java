package com.hawk.ecom.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.sms.persist.domain.TaskDomain;
import com.hawk.ecom.sms.persist.mapper.TaskMapper;

@Service
public class TaskService {
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Transactional(propagation=Propagation.NEVER)
	public void insert(TaskDomain taskDomain ){
		taskMapper.insert(taskDomain);
	}

}
