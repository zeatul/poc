package com.ccb.acten.ibatis.data.dos;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccb.acten.ibatis.data.po.EmployeePo;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class EmployeeDos {

	@Autowired
	private SqlMapClient sqlMapClient;

	public List<EmployeePo> list(Map<String, Object> params) {
		try {
			return sqlMapClient.queryForList("ibatis.employee.list", params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insert(EmployeePo employeePo) {
		try {
			sqlMapClient.insert("ibatis.employee.insert", employeePo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int delete(int id) {
		try {
			return sqlMapClient.delete("ibatis.employee.delete");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int update(EmployeePo employeePo) {
		try {
			return sqlMapClient.update("ibatis.employee.update",employeePo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
