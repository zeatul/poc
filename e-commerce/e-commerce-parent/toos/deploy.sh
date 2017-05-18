cd /usr/local/source/poc
git pull
cd /usr/local/source/poc/framework-parent
mvn clean install -Dmaven.test.skip=true -Pprod
cd /usr/local/source/poc/e-commerce/e-commerce-parent
mvn clean package -Dmaven.test.skip=true -Pprod
cd /usr/local/tomcat/apache-tomcat-9.0.0.M21/bin
./shutdown.sh
cd /usr/local/tomcat/apache-tomcat-9.0.0.M21/webapps
rm -rf ecom
 \cp -rf /usr/local/source/poc/e-commerce/e-commerce-web/target/e-commerce-web-1.0.0-SNAPSHOT.war ./ecom.war
cd /usr/local/tomcat/apache-tomcat-9.0.0.M21/bin
./startup.sh

