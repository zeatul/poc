package com.hawk.framework.codegen.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hawk.framework.codegen.database.config.DatabaseConfigure;
import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.codegen.database.config.IProjectConfigure;
import com.hawk.framework.codegen.database.config.ProjectConfigure;
import com.hawk.framework.codegen.database.convert.ITypeConverter;
import com.hawk.framework.codegen.database.convert.TypeConverterFactory;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Index;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.codegen.spring.config.CodeGenConfig;
import com.hawk.framework.codegen.utils.DatabaseTools;
import com.hawk.framework.dic.design.constant.ConstSynonymType;
import com.hawk.framework.dic.design.constant.ConstTableType;
import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.persist.domain.ApplicationDomain;
import com.hawk.framework.dic.persist.domain.ApplicationTableDomain;
import com.hawk.framework.dic.persist.domain.ColumnDomain;
import com.hawk.framework.dic.persist.domain.IndexColumnDomain;
import com.hawk.framework.dic.persist.domain.IndexDomain;
import com.hawk.framework.dic.persist.domain.TableDomain;
import com.hawk.framework.dic.service.ApplicationService;
import com.hawk.framework.dic.service.ApplicationTableService;
import com.hawk.framework.dic.service.ColumnService;
import com.hawk.framework.dic.service.IndexColumnService;
import com.hawk.framework.dic.service.IndexService;
import com.hawk.framework.dic.service.SynonymService;
import com.hawk.framework.dic.service.TableService;
import com.hawk.framework.dic.service.WordService;
import com.hawk.framework.utility.tools.StringTools;

/**
 * 将数据库表翻译成数据字典，并存入到数据字典数据库
 * 
 * @author Administrator
 *
 */
public class DbToDicService {

	private final ApplicationContext context;

	public static String PACKAGE_NAME;

	private final IDatabaseConfigure databaseConfigure;

	private final IProjectConfigure projectConfigure;

	private final ITypeConverter typeConverter;

	private final Database database;

	private final int version = 1;
	private final String systemCode = "ecom";

	private final WordService wordService;

	private final SynonymService synonymService;

	private final TableService tableService;

	private final ColumnService columnService;

	private final IndexService indexService;

	private final IndexColumnService indexColumnService;

	private final ApplicationService applicationService;

	private final ApplicationTableService applicationTableService;

	public DbToDicService(String packageName) throws Throwable {
		PACKAGE_NAME = packageName;

		System.out.println("PACKAGE_NAME=" + PACKAGE_NAME);

		databaseConfigure = DatabaseConfigure.build(PACKAGE_NAME);

		projectConfigure = ProjectConfigure.build(PACKAGE_NAME);

		typeConverter = TypeConverterFactory.build(databaseConfigure.getDialect());

		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);

		Date stdt = new Date();
		database = dbParser.parse();
		Date endt = new Date();
		System.out.println(
				"Success parse database , find " + database.getTableList().size() + " tables, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		/**
		 * 启动Spring;
		 */
		context = new AnnotationConfigApplicationContext(CodeGenConfig.class);

		tableService = context.getBean(TableService.class);

		wordService = context.getBean(WordService.class);

		synonymService = context.getBean(SynonymService.class);

		columnService = context.getBean(ColumnService.class);

		indexService = context.getBean(IndexService.class);

		indexColumnService = context.getBean(IndexColumnService.class);

		applicationService = context.getBean(ApplicationService.class);

		applicationTableService = context.getBean(ApplicationTableService.class);

		SynonymHelper.loadFromDatabase(synonymService, ConstSynonymType.WORD, systemCode, version);

	}

	public void execute() throws Throwable {

		write();

	}

	private void writeSynonym() {
		SynonymHelper.save(synonymService, wordService, systemCode, version);
	}

	private TableDomain buildTableDomain(Table table) {
		TableDomain tableDomain = new TableDomain();
		String objectCode = table.getCode();
		String objectComment = table.getComment();
		String objectId = UUID.randomUUID().toString();
		String objectName = table.getComment();
		String objectType = ConstTableType.NORMAL;
		String physicalOption = "ENGINE=InnoDB DEFAULT CHARSET=utf8";

		tableDomain.setCreateDate(new Date());
		tableDomain.setUpdateDate(tableDomain.getCreateDate());
		tableDomain.setObjectCode(objectCode);
		tableDomain.setObjectComment(objectComment);
		tableDomain.setObjectId(objectId);
		tableDomain.setObjectName(objectName);
		tableDomain.setObjectType(objectType);
		tableDomain.setPhysicalOption(physicalOption);
		tableDomain.setSystemCode(systemCode);
		tableDomain.setVersion(version);
		return tableDomain;
	}

	private ColumnDomain buildColumnDomain(Column column, String tableObjectId, String wordObjectId, int order) {
		ColumnDomain columnDomain = new ColumnDomain();

		columnDomain.setCreateDate(new Date());
		columnDomain.setIsPk(column.getIsPk());
		columnDomain.setNullable(column.getNullable());
		columnDomain.setObjectCode(column.getCode());
		columnDomain.setObjectComment(column.getComment());
		columnDomain.setObjectId(UUID.randomUUID().toString());
		columnDomain.setObjectName(column.getComment());
		columnDomain.setObjectOrder(order);
		columnDomain.setOperators(null);
		columnDomain.setSystemCode(systemCode);
		columnDomain.setTableObjectId(tableObjectId);
		columnDomain.setUpdateDate(columnDomain.getCreateDate());
		columnDomain.setVersion(version);
		columnDomain.setWordObjectId(wordObjectId);

		return columnDomain;
	}

	private IndexDomain buildIndexDomain(Index index, String tableObjectId) {
		IndexDomain indexDomain = new IndexDomain();

		indexDomain.setCreateDate(new Date());
		indexDomain.setIsPk(index.getIsPk());
		indexDomain.setIsUnique(index.getIsUnique());

		indexDomain.setObjectCode(index.getCode());
		indexDomain.setObjectComment(null);
		indexDomain.setObjectId(UUID.randomUUID().toString());
		indexDomain.setObjectName(null);
		indexDomain.setSystemCode(systemCode);
		indexDomain.setTableObjectId(tableObjectId);
		indexDomain.setUpdateDate(indexDomain.getCreateDate());
		indexDomain.setVersion(version);

		return indexDomain;
	}

	private IndexColumnDomain buildIndexColumnDomain(String indexObjectId, String columnObjectId, int order) {
		IndexColumnDomain indexColumnDomain = new IndexColumnDomain();

		indexColumnDomain.setColumnObjectId(columnObjectId);
		indexColumnDomain.setCreateDate(new Date());
		indexColumnDomain.setIndexObjectId(indexObjectId);
		indexColumnDomain.setObjectId(UUID.randomUUID().toString());
		indexColumnDomain.setObjectOrder(order);
		indexColumnDomain.setSystemCode(systemCode);
		indexColumnDomain.setUpdateDate(indexColumnDomain.getCreateDate());
		indexColumnDomain.setVersion(version);

		return indexColumnDomain;
	}

	private ApplicationDomain buildApplicationDomain(String code, String comment, String name) {
		ApplicationDomain applicationDomain = new ApplicationDomain();

		applicationDomain.setCreateDate(new Date());
		applicationDomain.setObjectCode(code);
		applicationDomain.setObjectComment(comment);
		applicationDomain.setObjectId(UUID.randomUUID().toString());
		applicationDomain.setObjectName(name);
		applicationDomain.setSystemCode(systemCode);
		applicationDomain.setUpdateDate(applicationDomain.getCreateDate());
		applicationDomain.setVersion(version);

		return applicationDomain;
	}

	private ApplicationTableDomain buildApplicationTableDomain(String applicationObjectId, String tableObjectId) {
		ApplicationTableDomain applicationTableDomain = new ApplicationTableDomain();

		applicationTableDomain.setApplicationObjectId(applicationObjectId);
		applicationTableDomain.setCreateDate(new Date());
		applicationTableDomain.setObjectId(UUID.randomUUID().toString());
		applicationTableDomain.setSystemCode(systemCode);
		applicationTableDomain.setTableObjectId(tableObjectId);
		applicationTableDomain.setUpdateDate(applicationTableDomain.getCreateDate());
		applicationTableDomain.setVersion(version);

		return applicationTableDomain;
	}

	private void write() {

		List<TableDomain> tableDomainList = new ArrayList<TableDomain>();
		List<ColumnDomain> columnDomainList = new ArrayList<ColumnDomain>();
		List<IndexDomain> indexDomainList = new ArrayList<IndexDomain>();
		List<IndexColumnDomain> indexColumnDomainList = new ArrayList<IndexColumnDomain>();

		List<ApplicationTableDomain> applicationTableDomainList = new ArrayList<ApplicationTableDomain>();

		List<Word> wordList = new ArrayList<Word>();

		ApplicationDomain applicationDomain = buildApplicationDomain(projectConfigure.getSubPackage(), projectConfigure.getSubPackage(),
				projectConfigure.getSubPackage());

		Map<String, Word> filterWordMap = new HashMap<String, Word>();
		wordService.loadWord(systemCode, version).forEach(word -> {
			filterWordMap.put(word.getCode(), word);
		});

		for (Table table : database.getTableList()) {
			System.out.println("---" + table.getCode() + "---");
			TableDomain tableDomain = buildTableDomain(table);
			tableDomainList.add(tableDomain);
			Map<String, String> columnCodeIdMap = new HashMap<String, String>();

			int columnIndex = 0;
			for (Column column : table.getColumnList()) {
				String columnCode = column.getCode();
				System.out.println("columnCode=" + columnCode);

				Word word = null;
				/**
				 * 检测该字段是否是同义词
				 */

				Synonym synonym = SynonymHelper.findSynonym(columnCode);
				String wordCode = null;
				if (synonym != null) {
					wordCode = synonym.getOriginCode();
				} else {
					wordCode = columnCode;
				}
				
				word = filterWordMap.get(wordCode);
				if (word != null) {
					if (!compareDataType(word, column, typeConverter)) {
						throw new RuntimeException("Found duplicated word code = " + columnCode + " in table.code = " + table.getCode());
					}
				} else {
					word = DatabaseTools.convert(column, typeConverter);
					word.setCode(wordCode); //同义词的wordcode 是 originCode；
					wordList.add(word);
					filterWordMap.put(word.getCode(), word);
				}

				columnIndex = columnIndex + 100;
				ColumnDomain columnDomain = buildColumnDomain(column, tableDomain.getObjectId(), word.getId(), columnIndex);
				columnDomainList.add(columnDomain);
				columnCodeIdMap.put(columnDomain.getObjectCode(), columnDomain.getObjectId());
			}

			columnCodeIdMap.forEach((key, value) -> {
				System.out.println(key + ":" + value);
			});

			for (Index index : table.getIndexList()) {
				IndexDomain indexDomain = buildIndexDomain(index, tableDomain.getObjectId());
				indexDomainList.add(indexDomain);
				int order = 0;
				for (Column column : index.getColumnList()) {
					order = order + 100;
					String columnObjectId = columnCodeIdMap.get(column.getCode());
					if (StringTools.isNullOrEmpty(columnObjectId)) {
						throw new RuntimeException("couldn't find the columnObjectId of column.getCode()=" + column.getCode());
					}
					IndexColumnDomain indexColumnDomain = buildIndexColumnDomain(indexDomain.getObjectId(), columnObjectId, order);
					indexColumnDomainList.add(indexColumnDomain);
				}
			}

			ApplicationTableDomain applicationTableDomain = buildApplicationTableDomain(applicationDomain.getObjectId(), tableDomain.getObjectId());
			applicationTableDomainList.add(applicationTableDomain);
		}

		System.out.println("wordList.size()=" + wordList.size());
		wordList.forEach(word -> {
			wordService.insertOrUpdateWord(word, systemCode, version);
		});

		System.out.println("talbeDomainList.size()=" + tableDomainList.size());
		tableDomainList.forEach(tableDomain -> {
			tableService.insertOrUpdate(tableDomain);
		});

		columnDomainList.forEach(columnDomain -> {
			columnService.insertOrUpdate(columnDomain);
		});

		indexDomainList.forEach(indexDomain -> {
			indexService.insertOrUpdate(indexDomain);
		});

		indexColumnDomainList.forEach(indexColumnDomain -> {
			indexColumnService.insertOrUpdate(indexColumnDomain);
		});

		applicationService.insertOrUpdate(applicationDomain);

		applicationTableDomainList.forEach(applicationTableDomain -> {
			applicationTableService.insertOrUpdate(applicationTableDomain);
		});

		writeSynonym();
	}

	/**
	 * 比较wor和column的数据类型是否完全一致
	 * 
	 * @param def
	 * @param column
	 */
	private static boolean compareDataType(Word def, Column column, ITypeConverter typeConverter) {

		if (def.getDataType() == EnumDataType.String && column.getCharMaxLength() != def.getCharMaxLength()) {
			System.out.println("CharMaxLength is not same");
			return false;
		}
		if (def.getDataType() == EnumDataType.String && column.getCharMinLength() != def.getCharMinLength()) {
			System.out.println("CharMinLength is not same");
			return false;
		}
		if (EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())) != def.getDataType()) {
			System.out.println("DataType is not same");
			return false;
		}
		if (def.getDataType() == EnumDataType.Date && column.getDatetimePrecision() != def.getDatetimePrecision()) {
			System.out.println("DatetimePrecision is not same");
			return false;
		}
		// if (!StringTools.compare(column.getComment(), def.getName())){
		// System.out.println("Comment is not same");
		// return false;
		// }
		if (def.getDataType() == EnumDataType.Numeric && column.getNumericPrecision() != def.getNumericPrecision()) {
			System.out.println("NumericPrecision is not same");
			return false;
		}
		if (def.getDataType() == EnumDataType.Numeric && column.getNumericScale() != def.getNumericScale()) {
			System.out.println("NumericScale is not same");
			return false;
		}

		return true;
	}

}
