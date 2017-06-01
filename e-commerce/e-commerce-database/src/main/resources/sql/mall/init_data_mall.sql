
##创建superadmin用户
insert into t_mal_mall_user(id,user_name,user_code,user_type,mobile_number,login_pwd,id_type,id_number,user_status,create_user_code,create_date,update_user_code,update_date)
value(1,'superadmin','000001',1,'10000000000','ac60c1526aece05db6bb4cf56752afb2',1,'000000000000000000', 1,'superadmin','2017-05-24 00:00:00','superadmin',now());

insert into t_mal_mall_user(id,user_name,user_code,user_type,mobile_number,login_pwd,id_type,id_number,user_status,create_user_code,create_date,update_user_code,update_date)
value(2,'admin','000002',1,'10000000001','ac60c1526aece05db6bb4cf56752afb2',1,'000000000000000001', 1,'superadmin','2017-05-24 00:00:00','superadmin',now());


##创建superadmin角色
insert into t_mal_mall_role(id,role_code,role_type,role_name,create_user_code,create_date,update_user_code,update_date)
value(1,'superadmin',1,'superadmin','superadmin',now(),'superadmin',now());
##将superadmin加入到superadmin角色
insert into t_mal_mall_role_user(id,role_id,user_id,create_user_code,create_date,update_user_code,update_date)
values(1,1,1,'superadmin',now(),'superadmin',now());
##创建admin角色
insert into t_mal_mall_role(id,role_code,role_type,role_name,create_user_code,create_date,update_user_code,update_date)
value(2,'admin',1,'admin','superadmin',now(),'superadmin',now());
