package com.hawk.framework.codegen.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hawk.framework.codegen.database.config.DatabaseConfigure;
import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.codegen.database.config.IDbToDicConfigure;
import com.hawk.framework.codegen.database.config.IProjectConfigure;
import com.hawk.framework.codegen.database.config.ProjectConfigure;
import com.hawk.framework.codegen.database.convert.ITypeConverter;
import com.hawk.framework.codegen.database.convert.TypeConverterFactory;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.codegen.spring.config.CodeGenConfig;
import com.hawk.framework.codegen.utils.DatabaseTools;
import com.hawk.framework.dic.design.constant.ConstTableType;
import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.persist.domain.TableDomain;
import com.hawk.framework.dic.service.WordService;
import com.hawk.framework.utility.tools.ClassPathTools;

/**
 * 将数据库表翻译成数据字典，并存入到数据字典数据库
 * 
 * @author Administrator
 *
 */
public class DbToDicService {
	
	
	private final ApplicationContext context;
	
	public static String PACKAGE_NAME ;
	
	private final IDatabaseConfigure databaseConfigure;
	
	private final IProjectConfigure projectConfigure;
	
	private final ITypeConverter typeConverter;
	
	private final Database database ;
	
	private final int version = 1;
	private final String systemCode = "ecom";
	
	private final WordService wordService ;
	
	public DbToDicService(String packageName) throws Throwable{
		PACKAGE_NAME = packageName;
		
		databaseConfigure = DatabaseConfigure.build(PACKAGE_NAME);
		
		projectConfigure = ProjectConfigure.build(PACKAGE_NAME);
		
		typeConverter = TypeConverterFactory.build(databaseConfigure.getDialect());
		
		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);
		
		Date stdt = new Date();
		database = dbParser.parse();
		Date endt = new Date();
		System.out.println("Success parse database , find " + database.getTableList().size() + " tables, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		
		/**
		 * 启动Spring;
		 */
		context = new AnnotationConfigApplicationContext(CodeGenConfig.class);
		
		wordService = context.getBean(WordService.class);
		
	}
	
	
	
	public void execute() throws Throwable {
		
		writeWord();
		
		
	}
	
	private void writeWord(){
		List<Word> wordList = new ArrayList<Word>();
		
		for (Table table : database.getTableList()){
			System.out.println("---"+table.getCode()+"---");
			for (Column column : table.getColumnList()){
				System.out.println("+++"+column.getCode()+"+++");
				
				/**
				 * 检测该字段是否是同义词
				 */
				String columnCode = column.getCode();
				Synonym synonym = SynonymHelper.findSynonym(columnCode);
				if (synonym != null){
					continue;
				}
				
				Word word = DatabaseTools.convert(column, typeConverter);
				
				wordList.add(word);
			}
		}
		
		wordList.forEach(word->{
			wordService.insertOrUpdateWord(word, systemCode, version);
		});
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
