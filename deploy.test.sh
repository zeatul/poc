cd /usr/local/source/poc
git pull
cd /usr/local/source/poc/framework-parent
mvn clean install -Dmaven.test.skip=true -Ptest
cd /usr/local/source/poc/e-commerce/e-commerce-parent
mvn clean install -Dmaven.test.skip=true -Ptest

cd /usr/local/tomcat/ecom-query-web/bin
sh shutdown.sh 
cd /usr/local/tomcat/ecom-query-web/webapps
rm -rf ecom_query_test
\cp /usr/local/source/poc/e-commerce/e-commerce-ecom-query-web/target/e-commerce-ecom-query-web-1.0.0-SNAPSHOT.war ./ecom_query_test.war -f
cd /usr/local/tomcat/ecom-query-web/bin
sh startup.sh

cd /usr/local/tomcat/ecom-trans-web/bin
sh shutdown.sh
cd /usr/local/tomcat/ecom-trans-web/webapps
rm -rf ecom_trans_test
\cp /usr/local/source/poc/e-commerce/e-commerce-ecom-trans-web/target/e-commerce-ecom-trans-web-1.0.0-SNAPSHOT.war ./ecom_trans_test.war -f
cd /usr/local/tomcat/ecom-trans-web/bin
sh startup.sh

cd /usr/local/tomcat/mall-admin-web/bin
sh shutdown.sh
cd /usr/local/tomcat/mall-admin-web/webapps
rm -rf mall_admin_test 
\cp /usr/local/source/poc/e-commerce/e-commerce-mall-admin-web/target/e-commerce-mall-admin-web-1.0.0-SNAPSHOT.war ./mall_admin_test.war -f 
cd /usr/local/tomcat/mall-admin-web/bin
sh startup.sh

cd /usr/local/tomcat/ecom-task-web/bin
sh shutdown.sh
cd /usr/local/tomcat/ecom-task-web/webapps
rm -rf ecom_task_test
\cp /usr/local/source/poc/e-commerce/e-commerce-ecom-task-web/target/e-commerce-ecom-task-web-1.0.0-SNAPSHOT.war ./ecom_task_test.war 
cd /usr/local/tomcat/ecom-task-web/bin
sh startup.sh
