drop index i_msr_user_login on t_msr_mall_login;

drop index ui_msr_user_token on t_msr_mall_login;

drop table if exists t_msr_mall_login;

drop table if exists t_msr_mall_login_history;

drop index ui_msr_right_id_path on t_msr_mall_right;

drop index ui_msr_right_code on t_msr_mall_right;

drop table if exists t_msr_mall_right;

drop index ui_msr_role_code on t_msr_mall_role;

drop table if exists t_msr_mall_role;

drop index ui_msr_role_right on t_msr_mall_role_right;

drop table if exists t_msr_mall_role_right;

drop index ui_msr_role_user on t_msr_mall_role_user;

drop table if exists t_msr_mall_role_user;

drop index ui_msr_id_number on t_msr_mall_user;

drop index ui_msr_user_email on t_msr_mall_user;

drop index ui_msr_userr_account on t_msr_mall_user;

drop index ui_msrl_user_mobile on t_msr_mall_user;

drop index ui_msrl_user_code on t_msr_mall_user;

drop table if exists t_msr_mall_user;

drop table if exists t_msr_mall_user_code_sequence;

drop table if exists t_msr_mall_user_history;

/*==============================================================*/
/* Table: t_msr_mall_login                                      */
/*==============================================================*/
create table t_msr_mall_login
(
   token                varchar(100) not null comment 'token',
   user_id              bigint unsigned comment '用户ID',
   user_code            varchar(50) not null comment '用户编号',
   user_account         varchar(50) comment '用户账号',
   user_email           varchar(200) comment '用户邮箱',
   mobile_number        varchar(20) not null comment '手机号',
   login_ip             varchar(200) comment '登录IP',
   login_type           tinyint unsigned comment '登录类型(长期固定/短期固定/短期弹性)',
   login_date           timestamp(3) null comment '登录日期',
   login_status         tinyint unsigned comment '登录状态',
   login_channel        varchar(200) comment '登录渠道',
   last_access_date     timestamp(3) null comment '最近访问日期',
   duration_second      integer unsigned comment '有效时间(秒)',
   expire_date          timestamp(3) null comment '失效日期',
   imei                 varchar(200) comment '设备唯一的串号',
   operating_system     varchar(200) comment '设备操作系统',
   operating_system_version varchar(200) comment '设备操作系统版本号',
   device_brand         varchar(200) comment '三星/华为/苹果',
   device_model         varchar(200) comment '厂商给设备定义的编号',
   user_agent           varchar(1000) comment 'http请求的user_agent原始信息',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (token)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_login comment '商城用户登录表';

/*==============================================================*/
/* Index: ui_msr_user_token                                     */
/*==============================================================*/
create unique index ui_msr_user_token on t_msr_mall_login
(
   token
);

/*==============================================================*/
/* Index: i_msr_user_login                                      */
/*==============================================================*/
create index i_msr_user_login on t_msr_mall_login
(
   user_code,
   user_account,
   user_email,
   mobile_number
);

/*==============================================================*/
/* Table: t_msr_mall_login_history                              */
/*==============================================================*/
create table t_msr_mall_login_history
(
   token                varchar(100) not null comment 'token',
   user_id              bigint unsigned comment '用户ID',
   user_code            varchar(50) not null comment '用户编号',
   user_account         varchar(50) comment '用户账号',
   user_email           varchar(200) comment '用户邮箱',
   mobile_number        varchar(20) not null comment '手机号',
   login_ip             varchar(200) comment '登录IP',
   login_type           tinyint unsigned comment '登录类型(长期固定/短期固定/短期弹性)',
   login_date           timestamp(3) null comment '登录日期',
   login_status         tinyint unsigned comment '登录状态',
   login_channel        varchar(200) comment '登录渠道',
   last_access_date     timestamp(3) null comment '最近访问日期',
   duration_second      integer unsigned comment '有效时间(秒)',
   expire_date          timestamp(3) null comment '失效日期',
   imei                 varchar(200) comment '设备唯一的串号',
   operating_system     varchar(200) comment '设备操作系统',
   operating_system_version varchar(200) comment '设备操作系统版本号',
   device_brand         varchar(200) comment '三星/华为/苹果',
   device_model         varchar(200) comment '厂商给设备定义的编号',
   user_agent           varchar(1000) comment 'http请求的user_agent原始信息',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (token)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_login_history comment '商城用户登录历史表';

/*==============================================================*/
/* Table: t_msr_mall_right                                      */
/*==============================================================*/
create table t_msr_mall_right
(
   id                   bigint unsigned not null comment '主键',
   pid                  bigint unsigned not null comment '父ID',
   right_code           varchar(50) not null comment '权限编号',
   right_name           varchar(200) not null comment '权限名称',
   id_path              varchar(200) not null comment '主键PATH',
   name_path            varchar(1000) not null comment '名称PATH',
   code_path            varchar(1000) comment '编号PATH',
   depth                tinyint unsigned not null comment '深度',
   is_reserved          tinyint unsigned not null comment '系统保留',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_right comment '商城权限表';

/*==============================================================*/
/* Index: ui_msr_right_code                                     */
/*==============================================================*/
create unique index ui_msr_right_code on t_msr_mall_right
(
   right_code
);

/*==============================================================*/
/* Index: ui_msr_right_id_path                                  */
/*==============================================================*/
create unique index ui_msr_right_id_path on t_msr_mall_right
(
   id_path
);

/*==============================================================*/
/* Table: t_msr_mall_role                                       */
/*==============================================================*/
create table t_msr_mall_role
(
   id                   bigint unsigned not null comment '主键',
   role_code            varchar(50) not null comment '角色编号',
   role_name            varchar(200) not null comment '角色名称',
   role_type            tinyint unsigned not null comment '角色类型',
   is_reserved          tinyint unsigned not null comment '系统保留',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_role comment '商城角色表';

/*==============================================================*/
/* Index: ui_msr_role_code                                      */
/*==============================================================*/
create unique index ui_msr_role_code on t_msr_mall_role
(
   role_code
);

/*==============================================================*/
/* Table: t_msr_mall_role_right                                 */
/*==============================================================*/
create table t_msr_mall_role_right
(
   id                   bigint unsigned not null comment '主键',
   role_id              bigint unsigned not null comment '角色ID',
   right_id             bigint unsigned not null comment '权限ID',
   right_op             tinyint unsigned not null comment '访问行为',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_role_right comment '商城角色权限表';

/*==============================================================*/
/* Index: ui_msr_role_right                                     */
/*==============================================================*/
create unique index ui_msr_role_right on t_msr_mall_role_right
(
   role_id,
   right_id
);

/*==============================================================*/
/* Table: t_msr_mall_role_user                                  */
/*==============================================================*/
create table t_msr_mall_role_user
(
   id                   bigint unsigned not null comment '主键',
   role_id              bigint unsigned not null comment '角色主键',
   user_id              bigint unsigned not null comment '用户主键',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_role_user comment '商城角色用户表';

/*==============================================================*/
/* Index: ui_msr_role_user                                      */
/*==============================================================*/
create unique index ui_msr_role_user on t_msr_mall_role_user
(
   role_id,
   user_id
);

/*==============================================================*/
/* Table: t_msr_mall_user                                       */
/*==============================================================*/
create table t_msr_mall_user
(
   id                   bigint unsigned not null comment '主键',
   user_code            varchar(50) not null comment '用户编号',
   user_account         varchar(50) comment '用户账号',
   user_email           varchar(200) comment '用户邮箱',
   user_type            tinyint unsigned comment '用户类型',
   mobile_number        varchar(20) not null comment '手机号',
   login_pwd            varchar(100) comment '登录密码',
   pwd_update_times     smallint unsigned comment '密码已经修改次数',
   last_pwd_update_date timestamp(3) null comment '密码最近修改时间',
   user_nickname        varchar(200) comment '用户昵称',
   user_name            varchar(50) comment '用户姓名',
   user_sex             varchar(50) comment '用户性别',
   user_birthday        timestamp(3) null comment '用户生日',
   user_picture         varchar(1000) comment '用户头像',
   id_type              tinyint unsigned not null comment '证件类型',
   id_number            varchar(50) not null comment '证件号码',
   user_status          tinyint unsigned comment '用户状态',
   user_status_change_cause varchar(1000) comment '状态变更原因',
   user_status_change_date timestamp(3) null comment '状态变更日期',
   last_access_date     timestamp(3) null comment '最近访问日期',
   is_reserved          tinyint unsigned not null comment '系统保留',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_user comment '商城用户表';

/*==============================================================*/
/* Index: ui_msrl_user_code                                     */
/*==============================================================*/
create unique index ui_msrl_user_code on t_msr_mall_user
(
   user_code
);

/*==============================================================*/
/* Index: ui_msrl_user_mobile                                   */
/*==============================================================*/
create unique index ui_msrl_user_mobile on t_msr_mall_user
(
   mobile_number
);

/*==============================================================*/
/* Index: ui_msr_userr_account                                  */
/*==============================================================*/
create unique index ui_msr_userr_account on t_msr_mall_user
(
   user_account
);

/*==============================================================*/
/* Index: ui_msr_user_email                                     */
/*==============================================================*/
create unique index ui_msr_user_email on t_msr_mall_user
(
   user_email
);

/*==============================================================*/
/* Index: ui_msr_id_number                                      */
/*==============================================================*/
create unique index ui_msr_id_number on t_msr_mall_user
(
   id_type,
   id_number
);

/*==============================================================*/
/* Table: t_msr_mall_user_code_sequence                         */
/*==============================================================*/
create table t_msr_mall_user_code_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_msr_mall_user_code_sequence comment '商城用户编号生成表';

/*==============================================================*/
/* Table: t_msr_mall_user_history                               */
/*==============================================================*/
create table t_msr_mall_user_history
(
   id                   bigint unsigned not null comment '主键',
   user_code            varchar(50) not null comment '用户编号',
   user_account         varchar(50) comment '用户账号',
   user_email           varchar(200) comment '用户邮箱',
   user_type            integer comment '用户类型',
   mobile_number        varchar(20) not null comment '手机号',
   login_pwd            varchar(100) comment '登录密码',
   pwd_update_times     smallint unsigned comment '密码已经修改次数',
   last_pwd_update_date timestamp(3) null comment '密码最近修改时间',
   user_nickname        varchar(200) comment '用户昵称',
   user_name            varchar(50) comment '用户姓名',
   user_sex             varchar(50) comment '用户性别',
   user_birthday        timestamp(3) null comment '用户生日',
   user_picture         varchar(1000) comment '用户头像',
   id_type              tinyint unsigned comment '证件类型',
   id_number            varchar(50) not null comment '证件号码',
   user_status          tinyint unsigned comment '用户状态',
   user_status_change_cause varchar(1000) comment '状态变更原因',
   user_status_change_date timestamp(3) null comment '状态变更日期',
   last_access_date     timestamp(3) null comment '最近访问日期',
   is_reserved          tinyint unsigned not null comment '系统保留',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_msr_mall_user_history comment '商城用户历史表';
