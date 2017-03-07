package com.hawk.framework.dic.service;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hawk.framework.dic.design.Application;
import com.hawk.framework.dic.design.Dictionary;
import com.hawk.framework.dic.design.data.DataDefinition;
import com.hawk.framework.dic.design.database.Column;
import com.hawk.framework.dic.design.database.Index;
import com.hawk.framework.dic.design.database.Table;
import com.hawk.framework.dic.domain.ApplicationDomain;
import com.hawk.framework.dic.domain.ApplicationTableDomain;
import com.hawk.framework.dic.domain.ColumnDomain;
import com.hawk.framework.dic.domain.DataDefinitionDomain;
import com.hawk.framework.dic.domain.IndexColumnDomain;
import com.hawk.framework.dic.domain.IndexDomain;
import com.hawk.framework.dic.domain.TableDomain;
import com.hawk.framework.utility.DomainTools;

@Component
public class ReporsitoryService {
	
//	@Autowired
//	private ApplicationMapper applicationMapper;
//	@Autowired
//	private ApplicationTableMapper applicationTableMapper;
//	@Autowired
//	private ColumnMapper columnMapper;
//	@Autowired
//	private DataDefinitionMapper dataDefinitionMapper;
//	@Autowired
//	private FkMapper fkMapper;
//	@Autowired
//	private FkMapMapper fkMapMapper;
//	@Autowired
//	private IndexMapper indexMapper;
//	@Autowired
//	private IndexColumnMapper indexColumnMapper;
//	@Autowired
//	private TableMapper tableMapper;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	

	/**
	 * 存到当前库
	 */
	public void saveToDatabase(Dictionary dictionary) {
		/**
		 * TODO:校验一下dictionary的逻辑完整性
		 */

		DictionaryDomainWrap dictionaryDomainWrap = build(dictionary);
		
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		
		try {
			/**
			 * 物理删除旧数据
			 */		
			cleanDictionary(dictionaryDomainWrap,sqlSession);
			
			/**
			 * 插入新数据
			 */
			createDictionary(dictionaryDomainWrap,sqlSession);

			sqlSession.commit();
			
		} catch (Exception e) {
			sqlSession.rollback();
		}finally{
			if (sqlSession != null)
			sqlSession.close();
		}
	}
	
	private void cleanDictionary(DictionaryDomainWrap dictionaryDomainWrap,SqlSession sqlSession){
		Iterator<Class<?>> it = dictionaryDomainWrap.getMap().keySet().iterator();
		while(it.hasNext()){
			Class<?> clazz = it.next();
			sqlSession.delete(clazz.getName()+".deleteDynamic");
		}
		sqlSession.flushStatements();
	}
	
	private void createDictionary(DictionaryDomainWrap dictionaryDomainWrap,SqlSession sqlSession){
		Iterator<Class<?>> it = dictionaryDomainWrap.getMap().keySet().iterator();
		int i = 0;
		while(it.hasNext()){
			Class<?> clazz = it.next();
			List<?> list = dictionaryDomainWrap.getMap().get(clazz);
			for (Object object : list){
				sqlSession.insert(clazz.getName()+".insert", object);
				i++;
				if (i%500==0){
					sqlSession.flushStatements();
				}
			}
		}
		
		if (i%500>0){
			sqlSession.flushStatements();
		}
	}
	
	
	
	
	private  DictionaryDomainWrap build (Dictionary dictionary){
		
		DictionaryDomainWrap dictionaryDomainWrap = new DictionaryDomainWrap();
		
		/**
		 * 转换 data_definition 为 入库记录
		 */
		for (DataDefinition dataDefinition : dictionary.getDataDefinitionList()) {
			DataDefinitionDomain dataDefinitionDomain = new DataDefinitionDomain();
			DomainTools.copy(dataDefinition, dataDefinitionDomain);
			dictionaryDomainWrap.add(dataDefinitionDomain);
		}

		/**
		 * 转换 表，字段，索引，索引字段 ，外键，外键字段匹配为入库记录 转换应用，应用的表为入库记录
		 */
		/**
		 * application
		 */
		for (Application application : dictionary.getApplicationList()) {
			ApplicationDomain applicationDomain = new ApplicationDomain();
			DomainTools.copy(application, applicationDomain);
			dictionaryDomainWrap.add(applicationDomain);

			
			for (Table table : application.getTableList()) {
				/**
				 * table
				 */
				TableDomain tableDomain = new TableDomain();
				DomainTools.copy(table, tableDomain);
				dictionaryDomainWrap.add(tableDomain);
				/**
				 * application's table
				 */
				ApplicationTableDomain applicationTableDomain = new ApplicationTableDomain();
				applicationTableDomain.setApplicationObjectId(application.getId());
				applicationTableDomain.setTableObjectId(table.getId());
				applicationTableDomain.setObjectId(UUID.randomUUID().toString());
				dictionaryDomainWrap.add(applicationTableDomain);
				
				
				/**
				 * tables's column
				 */
				int i = 0;
				for (Column column : table.getColumnList()){
					ColumnDomain columnDomain = new ColumnDomain();
					DomainTools.copy(column, columnDomain);
					columnDomain.setDataDefinitionObjectId(column.getDataDefinition().getId());
					columnDomain.setTableObjectId(table.getId());
					i = i +100;
					columnDomain.setObjectOrder(i);
					dictionaryDomainWrap.add(columnDomain);
				}
				/**
				 * table's index
				 */
				for (Index index : table.getIndexList()){
					IndexDomain indexDomain = new IndexDomain();
					DomainTools.copy(index, indexDomain);
					dictionaryDomainWrap.add(indexDomain);
					/**
					 * index's column
					 */
					int j = 0;
					for (Column column : index.getColumnList()){
						IndexColumnDomain indexColumnDomain = new IndexColumnDomain();
						indexColumnDomain.setColumnObjectId(column.getId());
						indexColumnDomain.setIndexObjectId(index.getId());
						j = j + 100;
						indexColumnDomain.setObjectOrder(i);
						indexColumnDomain.setObjectId(UUID.randomUUID().toString());
						dictionaryDomainWrap.add(indexColumnDomain);
					}
				}
				
				/**
				 * TODO:Foreign key
				 */
				
			}
		}
		
		return dictionaryDomainWrap;
	}

	/**
	 * 存为xml
	 */
	public void saveToXml(Dictionary dictionary) {

	}

	

}
