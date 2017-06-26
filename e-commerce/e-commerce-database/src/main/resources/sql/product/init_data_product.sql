/*==============================================================*/
/* 创建产品分类：虚拟产品->(流量充值,碎屏险)                              */
/*==============================================================*/
insert into t_prd_category(id,pid,id_path,object_order,depth,category_code,category_name,category_status,is_leaf,category_variant_status,
create_user_code,create_date,update_user_code,update_date)
values(1,0,'/0/1',100,1,'1','虚拟产品',100,0,100,'admin',now(),'admin',now());