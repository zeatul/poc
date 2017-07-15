#########初始化支付应用###########
INSERT INTO t_pay_payment_application(id,application_code,application_name,application_status,create_user_code,create_date,update_user_code,update_date) 
value (1,'SVP','虚拟产品电商平台',100,'admin',now(),'admin',now());