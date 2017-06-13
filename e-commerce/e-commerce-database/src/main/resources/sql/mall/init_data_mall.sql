##创建移动端h5首页资源根节点
insert into t_mal_system_resource
(id,pid,id_path,name_path,code_path,depth,node_code,is_reserved,node_name,node_type,node_sub_type,
node_value_type,node_value,object_order,node_status,node_desc,
create_user_code,create_date,update_user_code,update_date)
value
(1,0,'/0/1','/root/h5main','/root/h5main',1,'h5main',1,'h5main',10,100,
10,'h5main',100,100,'h5main',
'superadmin',now(),'superadmin',now());



