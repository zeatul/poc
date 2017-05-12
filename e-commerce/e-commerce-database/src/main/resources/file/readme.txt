###########服务器

#pub
source /usr/local/source/poc/e-commerce/e-commerce-database/src/main/resources/sql/pub/create_db_pub.sql;

#svp
source /usr/local/source/poc/e-commerce/e-commerce-database/src/main/resources/sql/svp/create_db_svp.sql;
source /usr/local/source/poc/e-commerce/e-commerce-database/src/main/resources/sql/svp/init_data_svp_test.sql;

#数据字典
source /usr/local/source/poc/framework-database/src/main/resources/sql/create_db_dic.sql;
source /usr/local/source/poc/framework-database/src/main/resources/sql/init_data_dic.sql;

#用户表 
source C:/mydata/workspace/projects/poc/e-commerce/e-commerce-database/src/main/resources/sql/user/create_db_user.sql

#短信表
source /usr/local/source/poc/e-commerce/e-commerce-database/src/main/resources/sql/sms/create_db_sms.sql
source /usr/local/source/poc/e-commerce/e-commerce-database/src/main/resources/sql/sms/init_data_sms.sql

code_gen 删文件有bug

#################local

#svp
source C:/mydata/workspace/projects/poc/e-commerce/e-commerce-database/src/main/resources/sql/svp/create_db_svp.sql;
source C:/mydata/workspace/projects/poc/e-commerce/e-commerce-database/src/main/resources/sql/svp/init_data_svp_test.sql;


#数据字典 
source C:/mydata/workspace/projects/poc/framework-database/src/main/resources/sql/init_data_dic.sql;

#用户表 
source /usr/local/source/poc/e-commerce/e-commerce-database/src/main/resources/sql/user/create_db_user.sql


#短信表

source C:/mydata/workspace/projects/poc/e-commerce/e-commerce-database/src/main/resources/sql/sms/create_db_sms.sql
source C:/mydata/workspace/projects/poc/e-commerce/e-commerce-database/src/main/resources/sql/sms/init_data_sms.sql



