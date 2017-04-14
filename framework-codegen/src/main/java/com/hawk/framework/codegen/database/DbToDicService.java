package com.hawk.framework.codegen.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.hawk.framework.codegen.database.config.DatabaseConfigure;
import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.codegen.database.config.IProjectConfigure;
import com.hawk.framework.codegen.database.config.ProjectConfigure;
import com.hawk.framework.codegen.database.convert.ITypeConverter;
import com.hawk.framework.codegen.database.convert.TypeConverterFactory;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.dic.design.constant.ConstTableType;
import com.hawk.framework.dic.persist.domain.TableDomain;

/**
 * 将数据库表翻译成数据字典，并存入到数据字典数据库
 * 
 * @author Administrator
 *
 */
public class DbToDicService {

	public static void main(String[] args) {

	}

	public void execute(String packageName) throws Throwable {
		IDatabaseConfigure databaseConfigure = DatabaseConfigure.build(packageName);
		IProjectConfigure projectConfigure = ProjectConfigure.build(packageName);
		ITypeConverter typeConverter = TypeConverterFactory.build(databaseConfigure.getDialect());
		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);
		
		Date stdt = new Date();
		Database database = dbParser.parse();
		Date endt = new Date();
		System.out.println("Success parse database , find " + database.getTableList().size() + " tables, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

	}
	
	private void writeWord(Database database,int version,String systemCode,Date createDate,Date updateDate){
		for (Table table : database.getTableList()){
			System.out.println("---"+table.getCode()+"---");
			for (Column column : table.getColumnList()){
				System.out.println("+++"+column.getCode()+"+++");
			}
		}
	}
	
	
	private void writeDictionary(Database database,int version,String systemCode){
		Date createDate = new Date();
		Date updateDate = createDate;
		List<TableDomain> tableDomainList = new ArrayList<TableDomain>();
		for (Table table : database.getTableList()){
			System.out.println(table.getCode());
			
			TableDomain tableDomain = new TableDomain();
			tableDomainList.add(tableDomain);
			String objectCode = table.getCode();
			String objectComment = table.getComment();
			String objectId = UUID.randomUUID().toString();
			String objectName = table.getComment();
			String objectType = ConstTableType.NORMAL;
			String physicalOption = "ENGINE=InnoDB DEFAULT CHARSET=utf8";
			
			
			tableDomain.setCreateDate(createDate);
			tableDomain.setUpdateDate(updateDate);
			tableDomain.setObjectCode(objectCode);
			tableDomain.setObjectComment(objectComment);
			tableDomain.setObjectId(objectId);
			tableDomain.setObjectName(objectName);
			tableDomain.setObjectType(objectType);
			tableDomain.setPhysicalOption(physicalOption);
			tableDomain.setSystemCode(systemCode);
			tableDomain.setVersion(version);
			
		}
	}

}
