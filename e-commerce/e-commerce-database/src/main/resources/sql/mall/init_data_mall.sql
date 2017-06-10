
##创建superadmin用户
insert into t_mal_mall_user(id,is_reserved,user_name,user_code,user_type,mobile_number,login_pwd,id_type,id_number,user_status,create_user_code,create_date,update_user_code,update_date)
value(1,1,'superadmin','000001',1,'10000000000','ac60c1526aece05db6bb4cf56752afb2',1,'000000000000000000', 100,'superadmin','2017-05-24 00:00:00','superadmin',now());

##创建admin用户
insert into t_mal_mall_user(id,is_reserved,user_name,user_code,user_type,mobile_number,login_pwd,id_type,id_number,user_status,create_user_code,create_date,update_user_code,update_date)
value(2,1,'admin','000002',1,'10000000001','9c5d23ad47bd5a6c8da1c22f7c5144e8',1,'000000000000000001', 100,'superadmin','2017-05-24 00:00:00','superadmin',now());


##创建superadmin角色
insert into t_mal_mall_role(id,is_reserved,role_code,role_type,role_name,create_user_code,create_date,update_user_code,update_date)
value(1,1,'superadmin',1,'superadmin','superadmin',now(),'superadmin',now());
##将superadmin加入到superadmin角色
insert into t_mal_mall_role_user(id,role_id,user_id,create_user_code,create_date,update_user_code,update_date)
values(1,1,1,'superadmin',now(),'superadmin',now());

##创建admin角色
insert into t_mal_mall_role(id,is_reserved,role_code,role_type,role_name,create_user_code,create_date,update_user_code,update_date)
value(2,1,'admin',1,'admin','superadmin',now(),'superadmin',now());

##admin加入到admin角色
insert into t_mal_mall_role_user(id,role_id,user_id,create_user_code,create_date,update_user_code,update_date)
values(2,2,2,'superadmin',now(),'superadmin',now());


##ROOT.setNodeCode("root");
##		ROOT.setId(0l);
##		ROOT.setDepth(0);
##		ROOT.setIdPath("/0");
##		ROOT.setNodeName("root");
##		ROOT.setNamePath("/root");
##		ROOT.setCodePath("/root");

##创建移动端h5首页资源根节点
insert into t_mal_system_resource
(id,pid,id_path,name_path,code_path,depth,node_code,reserved,node_name,node_type,node_sub_type,
node_value_type,node_value,object_order,node_status,node_desc,
create_user_code,create_date,update_user_code,update_date)
value
(1,0,'/0/1','/root/h5main','/root/h5main',1,'h5main',1,'h5main',10,100,
10,'h5main',100,100,'h5main',
'superadmin',now(),'superadmin',now());



