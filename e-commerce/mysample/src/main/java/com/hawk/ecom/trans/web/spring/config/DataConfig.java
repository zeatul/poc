//package com.hawk.ecom.trans.web.spring.config;
//
//import javax.sql.DataSource;
//
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.hawk.ecom.muser.persist.domain.MallUserDomain;
//import com.hawk.ecom.muser.persist.mapper.MallUserMapper;
//import com.hawk.ecom.muser.persist.mapperex.MallUserExMapper;
//import com.hawk.ecom.pay.persist.domain.PaymentBillDomain;
//import com.hawk.ecom.pay.persist.mapper.PaymentBillMapper;
//import com.hawk.ecom.pay.persist.mapperex.PaymentBillExMapper;
//import com.hawk.ecom.product.persist.domain.ProductDomain;
//import com.hawk.ecom.product.persist.mapper.ProductMapper;
//import com.hawk.ecom.product.persist.mapperex.ProductExMapper;
//import com.hawk.ecom.sms.persist.domain.TaskDomain;
//import com.hawk.ecom.sms.persist.mapper.TaskMapper;
//import com.hawk.ecom.sms.persist.mapperex.TaskExMapper;
//import com.hawk.ecom.trans.persist.domain.OrderDomain;
//import com.hawk.ecom.trans.persist.mapper.OrderMapper;
//import com.hawk.ecom.trans.persist.mapperex.OrderExMapper;
//import com.hawk.ecom.user.persist.domain.UserDomain;
//import com.hawk.ecom.user.persist.mapper.UserMapper;
//import com.hawk.ecom.user.persist.mapperex.UserExMapper;
//import com.hawk.framework.dic.persist.domain.WordDomain;
//import com.hawk.framework.dic.persist.mapper.WordMapper;
//import com.hawk.framework.dic.persist.mapperex.WordExMapper;
//import com.hawk.framework.utility.tools.StringTools;
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
//@Configuration
//@PropertySource("classpath:/com/hawk/ecom/trans/web/env/jdbc.properties")
//@MapperScan(basePackageClasses = { OrderMapper.class, OrderExMapper.class, // 订单
//		WordMapper.class, WordExMapper.class, // 数据字典
//		TaskMapper.class, TaskExMapper.class, // 短消息
//		ProductMapper.class, ProductExMapper.class, // 短消息
//		MallUserMapper.class,MallUserExMapper.class,//商城用户
//		UserMapper.class, UserExMapper.class ,// 客户
//		PaymentBillMapper.class,PaymentBillExMapper.class //支付
//})
//public class DataConfig {
//
//	@Autowired
//	private Environment env;
//
//	@Bean
//	public DataSource dataSource() throws Exception {
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//
//		dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
//		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//		dataSource.setUser(env.getProperty("jdbc.username"));
//		dataSource.setPassword(env.getProperty("jdbc.password"));
//
//		/**
//		 * 连接池中保留的最小连接数，默认为：3
//		 */
//		dataSource.setMinPoolSize(3);
//
//		/**
//		 * 接池中保留的最大连接数。默认为15
//		 */
//		dataSource.setMaxPoolSize(200);
//
//		/**
//		 * 始化时创建的连接数，应在minPoolSize与maxPoolSize之间取值。默认为3
//		 */
//		dataSource.setInitialPoolSize(3);
//
//		/**
//		 * 最大空闲时间(秒)，超过空闲时间的连接将被丢弃。为0或负数则永不丢弃。默认为0
//		 */
//		dataSource.setMaxIdleTime(1800);
//
//		/**
//		 * 当连接池用完时客户端调用getConnection()后等待获取新连接的时间，超时后将抛出SQLException，如设为0则无限期等待。单位毫秒，默认为0
//		 */
//		dataSource.setCheckoutTimeout(10000);
//
//		/**
//		 * 当连接池中的连接用完时，C3P0一次性创建新连接的数目
//		 */
//		dataSource.setAcquireIncrement(3);
//
//		/**
//		 * 定义在从数据库获取新连接失败后重复尝试的次数。默认值: 30 ；小于等于0表示无限次
//		 */
//		dataSource.setAcquireRetryAttempts(0);
//
//		/**
//		 * 重新尝试的时间间隔，默认为：1000毫秒
//		 */
//		dataSource.setAcquireRetryDelay(1000);
//
//		/**
//		 * 关闭连接时，是否提交未提交的事务，默认为false，即关闭连接，回滚未提交的事务
//		 */
//		dataSource.setAutoCommitOnClose(false);
//
//		/**
//		 * 如果为false，则获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常，但是数据源仍有效保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试获取连接失败后该数据源将申明已断开并永久关闭。默认:
//		 * false
//		 */
//		dataSource.setBreakAfterAcquireFailure(false);
//
//		/**
//		 * 每60秒检查所有连接池中的空闲连接。默认值: 0，不检查
//		 */
//		dataSource.setIdleConnectionTestPeriod(900);
//
//		/**
//		 * 定义所有连接测试都执行的测试语句。在使用连接测试的情况下这个一显著提高测试速度。注意：测试的表必须在初始数据源的时候就存在。Default:
//		 * null
//		 */
//		dataSource.setPreferredTestQuery("select count(1) from dual");
//
//		/**
//		 * JDBC的标准参数，用以控制数据源内加载的PreparedStatement数量。但由于预缓存的Statement属
//		 * 于单个Connection而不是整个连接池。所以设置这个参数需要考虑到多方面的因素，如果maxStatements与
//		 * maxStatementsPerConnection均为0，则缓存被关闭。默认为0
//		 */
//		dataSource.setMaxStatements(0);
//
//		/**
//		 * 连接池内单个连接所拥有的最大缓存Statement数。默认为0
//		 */
//		dataSource.setMaxStatementsPerConnection(0);
//
//		/**
//		 * C3P0是异步操作的，缓慢的JDBC操作通过帮助进程完成。扩展这些操作可以有效的提升性能，通过多线程实现多个操作同时被执行。默认为3
//		 */
//		dataSource.setNumHelperThreads(3);
//
//		/**
//		 * 用户修改系统配置参数执行前最多等待的秒数。默认为300
//		 */
//		dataSource.setPropertyCycle(600);
//
//		return dataSource;
//
//	}
//
//	
//
//	@Bean
//	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
//		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//		sqlSessionFactory.setDataSource(dataSource);
//		String ecomTransPackageName = OrderDomain.class.getPackage().getName(); // ecom-trans-service
//		String dicPackageName = WordDomain.class.getPackage().getName(); // 数据字典
//		String userPackageName = UserDomain.class.getPackage().getName(); // 用户
//		String smsPackageName = TaskDomain.class.getPackage().getName(); // 短信
//		String productPackageName = ProductDomain.class.getPackage().getName(); // 短信
//		String muserPackageName = MallUserDomain.class.getPackage().getName(); //商城用户
//		String payPackageName = PaymentBillDomain.class.getPackage().getName(); //支付
//		String str = StringTools.concatWithSymbol(";", ecomTransPackageName, ecomTransPackageName + "ex", //
//				dicPackageName, dicPackageName + "ex", //
//				smsPackageName, smsPackageName + "ex", //
//				productPackageName, productPackageName + "ex", //
//				muserPackageName, muserPackageName + "ex", //
//				userPackageName, userPackageName + "ex",//
//				payPackageName,payPackageName+ "ex"//
//		);
//		sqlSessionFactory.setTypeAliasesPackage(str);
//		return sqlSessionFactory;
//	}
//
//	@Configuration
//	@EnableTransactionManagement
//	public static class TransactionConfig {
//		@Bean
//		public PlatformTransactionManager transactionManager(DataSource dataSource) {
//			DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//			transactionManager.setDataSource(dataSource);
//			return transactionManager;
//		}
//	}
//
//}
