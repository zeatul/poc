#########初始化支付应用###########
INSERT INTO t_pay_payment_application(id,application_code,application_name,application_status,create_user_code,create_date,update_user_code,update_date) 
value (1,'SVP','虚拟产品电商平台',100,'admin',now(),'admin',now());

#############支付分类############################
INSERT INTO t_pay_payment_category(id,pid,depth,payment_category_code,payment_category_name,payment_category_status,payment_category_logo,is_leaf,create_user_code,create_date,update_user_code,update_date)
value(1,0,1,'online_pay','在线支付',100,null,0,'admin',now(),'admin',now());

INSERT INTO t_pay_payment_category(id,pid,depth,payment_category_code,payment_category_name,payment_category_status,payment_category_logo,is_leaf,create_user_code,create_date,update_user_code,update_date)
value(2,1,2,'online_pay_platform','支付平台',100,null,0,'admin',now(),'admin',now());

INSERT INTO t_pay_payment_category(id,pid,depth,payment_category_code,payment_category_name,payment_category_status,payment_category_logo,is_leaf,create_user_code,create_date,update_user_code,update_date)
value(3,2,3,'alipay','支付宝',100,'alipay.jpg',1,'admin',now(),'admin',now());

INSERT INTO t_pay_payment_category(id,pid,depth,payment_category_code,payment_category_name,payment_category_status,payment_category_logo,is_leaf,create_user_code,create_date,update_user_code,update_date)
value(4,2,3,'weichat','微信支付',100,'weichat.jpg',1,'admin',now(),'admin',now());