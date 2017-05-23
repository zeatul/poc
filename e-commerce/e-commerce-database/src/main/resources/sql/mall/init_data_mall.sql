
##创建superadmin用户
insert into t_mal_mall_user(id,user_code,user_type,mobile_number,login_pwd,id_type,id_number,user_status,create_user_code,create_date,update_user_code,update_date)
value(1,'000001',1,'superadmin','login_pwd',1,'31010119771108234', 1,'superadmin',now(),'superadmin',now());
##创建superadmin角色
insert into t_mal_mall_role(id,role_code,role_type,role_name,create_user_code,create_date,update_user_code,update_date)
value(1,'superadmin',1,'superadmin','superadmin',now(),'superadmin',now());
##将superadmin加入到superadmin角色
insert into t_mal_mall_role_user(id,role_id,user_id,create_user_code,create_date,update_user_code,update_date)
values(1,1,1,'superadmin',now(),'superadmin',now());
##创建admin角色
insert into t_mal_mall_role(id,role_code,role_type,role_name,create_user_code,create_date,update_user_code,update_date)
value(2,'admin',1,'admin','superadmin',now(),'superadmin',now());
