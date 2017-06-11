drop index ui_mal_sr_code on t_mal_system_resource;

drop index ui_mal_sr_code_path on t_mal_system_resource;

drop index ui_mal_sr_id_path on t_mal_system_resource;

drop table if exists t_mal_system_resource;

drop table if exists t_mal_system_short_sequence;

/*==============================================================*/
/* Table: t_mal_system_resource                                 */
/*==============================================================*/
create table t_mal_system_resource
(
   id                   bigint unsigned not null comment '主键',
   pid                  bigint unsigned not null comment '父ID',
   id_path              varchar(200) not null comment '主键PATH',
   name_path            varchar(1000) not null comment '名称PATH',
   code_path            varchar(200) comment '编号PATH',
   depth                tinyint unsigned not null comment '深度',
   node_code            varchar(50) not null comment '节点编号',
   is_reserved          tinyint unsigned not null comment '系统保留',
   node_name            varchar(50) comment '节点名称',
   node_type            tinyint unsigned comment '节点类型',
   node_sub_type        tinyint unsigned comment '节点子类型',
   node_value_type      tinyint unsigned comment '节点值类型',
   node_value           varchar(200) comment '节点值',
   object_order         integer unsigned not null comment '节点序号',
   node_status          tinyint unsigned comment '节点状态',
   node_desc            varchar(200) comment '节点描述',
   node_ico             varchar(200) comment '节点图标',
   node_checked_ico     varchar(200) comment '节点选中图标',
   node_grey_ico        varchar(200) comment '节点禁用图标',
   node_rise_ico        varchar(200) comment '节点鼠标浮动图标',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_mal_system_resource comment '导航类资源管理';

/*==============================================================*/
/* Index: ui_mal_sr_id_path                                     */
/*==============================================================*/
create unique index ui_mal_sr_id_path on t_mal_system_resource
(
   id_path
);

/*==============================================================*/
/* Index: ui_mal_sr_code_path                                   */
/*==============================================================*/
create unique index ui_mal_sr_code_path on t_mal_system_resource
(
   code_path
);

/*==============================================================*/
/* Index: ui_mal_sr_code                                        */
/*==============================================================*/
create unique index ui_mal_sr_code on t_mal_system_resource
(
   node_code
);

/*==============================================================*/
/* Table: t_mal_system_short_sequence                           */
/*==============================================================*/
create table t_mal_system_short_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_mal_system_short_sequence comment '商场短ID生成表';
