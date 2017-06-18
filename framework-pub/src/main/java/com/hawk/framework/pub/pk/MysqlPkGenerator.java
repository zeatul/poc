package com.hawk.framework.pub.pk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;



public class MysqlPkGenerator implements PkGenService{
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public String getSql() {
		return sql;
	}



	public void setSql(String sql) {
		this.sql = sql;
	}



	private JdbcTemplate jdbcTemplate;
	
	private String sql ;
	
	private int start = 0 ;
	
	/**
	 * 
	 * @param start 起点
	 */
	public MysqlPkGenerator(int start){
		this.start = start;
	}
	
//	private final static String sql = "replace into t_km_global_sequence(stub) values('a')";

	public int genPk(){
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
		        return ps;
			}
		},keyHolder);
		
				
		return start + (Integer)(keyHolder.getKeyList().get(0).get("GENERATED_KEY"));
		
		

	}
	


}
