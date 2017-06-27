/*==============================================================*/
/* 创建产品分类：虚拟产品->(流量充值,碎屏险)                              */
/*==============================================================*/
insert into t_prd_category(id,pid,id_path,object_order,depth,category_code,category_name,category_status,is_leaf,category_variant_status,
create_user_code,create_date,update_user_code,update_date)
values(1,0,'/0/1',100,1,'1','虚拟产品',100,0,100,'admin',now(),'admin',now());

insert into t_prd_category(id,pid,id_path,object_order,depth,category_code,category_name,category_status,is_leaf,category_variant_status,
create_user_code,create_date,update_user_code,update_date)
values(2,0,'/0/1/2',100,1,'2','碎屏险',100,1,100,'admin',now(),'admin',now());

insert into t_prd_category(id,pid,id_path,object_order,depth,category_code,category_name,category_status,is_leaf,category_variant_status,
create_user_code,create_date,update_user_code,update_date)
values(3,0,'/0/1/3',100,1,'3','流量充值',100,1,100,'admin',now(),'admin',now());


/*==============================================================*/
/* 创建产品分类流量充值的属性名和属性值 , 属性名：移动服务商(电信,移动,联通)*/
/*==============================================================*/
insert into t_prd_attr_name(id,store_code,category_id,pid,pvid,attr_name_business_type,attr_value_type,attr_name,attr_name_status,is_search,
create_user_code,create_date,update_user_code,update_date)
values(1,null,3,0,0,999,1,'移动服务商',1,1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(1,null,3,1,'0','电信','telecom',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(2,null,3,1,'1','联通','unicom',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(3,null,3,1,'2','移动','mobile',1,'admin',now(),'admin',now());
/*==============================================================*/
/* 创建产品分类流量充值的属性名和属性值   ,属性名：流量大小(100M,1000M,5000M)*/
/*==============================================================*/
insert into t_prd_attr_name(id,store_code,category_id,pid,pvid,attr_name_business_type,attr_value_type,attr_name,attr_name_status,is_search,
create_user_code,create_date,update_user_code,update_date)
values(2,null,3,0,0,999,2,'流量大小',1,1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(4,null,3,2,'100','100M','100M',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(5,null,3,2,'1000','1000M','1000M',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(6,null,3,2,'5000','5000M','5000M',1,'admin',now(),'admin',now());
/*==============================================================*/
/* 创建产品分类流量充值的属性名和属性值 , 属性名:地区(江苏,上海,广东)            */
/*==============================================================*/
insert into t_prd_attr_name(id,store_code,category_id,pid,pvid,attr_name_business_type,attr_value_type,attr_name,attr_name_status,is_search,
create_user_code,create_date,update_user_code,update_date)
values(3,null,3,0,0,999,1,'地区',1,1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(7,null,3,3,'320000','江苏','Jiangsu',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(8,null,3,3,'310000','上海','Shanghai',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(9,null,3,3,'440000','广东','Guangdong',1,'admin',now(),'admin',now());



/*==============================================================*/
/* 创建产品分类流量充值的属性名和属性值 , 属性名：流量类型(全国,本地)            */
/*==============================================================*/
insert into t_prd_attr_name(id,store_code,category_id,pid,pvid,attr_name_business_type,attr_value_type,attr_name,attr_name_status,is_search,
create_user_code,create_date,update_user_code,update_date)
values(4,null,3,0,0,999,1,'流量类型',1,1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(10,null,3,4,'0','本地','province',1,'admin',now(),'admin',now());

insert into t_prd_attr_value(id,store_code,category_id,attr_name_id,attr_value,attr_display_value,attr_display_en_value,attr_value_status,
create_user_code,create_date,update_user_code,update_date)
values(11,null,3,4,'0','全国','country',1,'admin',now(),'admin',now());


/*==============================================================*/
/* 创建产品            */
/*==============================================================*/
insert into t_prd_product(id,category_id,store_code,product_code,product_name)



