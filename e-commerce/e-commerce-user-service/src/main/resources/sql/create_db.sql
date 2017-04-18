drop table if exists t_usr_login;

drop table if exists t_usr_login_history;

drop index ui_user_email on t_usr_user;

drop index ui_user_account on t_usr_user;

drop index us_user_mobile on t_usr_user;

drop index ui_user_code on t_usr_user;

drop table if exists t_usr_user;

drop table if exists t_usr_user_code_sequence;

drop index ui_user_code_history on t_usr_user_history;

drop table if exists t_usr_user_history;

/*==============================================================*/
/* Table: t_usr_login                                           */
/*==============================================================*/
create table t_usr_login
(
   token                varchar(100) not null comment 'token',
   user_id              bigint comment '用户ID',
   mobile_number        varchar(20) comment '手机号',
   user_code            varchar(50) comment '用户编号',
   login_ip             varchar(50) comment '登录IP',
   login_type           char(1) comment '登录类型(长期固定/短期固定/短期弹性)',
   login_date           timestamp(3) null comment '登录日期',
   last_access_date     timestamp(3) null comment '最近访问日期',
   duration_second      integer comment '有效时间(秒)',
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

alter table t_usr_login comment '用户登录表';

/*==============================================================*/
/* Table: t_usr_login_history                                   */
/*==============================================================*/
create table t_usr_login_history
(
   token                varchar(100) not null comment 'token',
   user_id              bigint comment '用户ID',
   mobile_number        varchar(20) comment '手机号',
   user_code            varchar(50) comment '用户编号',
   login_ip             varchar(50) comment '登录IP',
   login_type           char(1) comment '登录类型(长期固定/短期固定/短期弹性)',
   login_date           timestamp(3) null comment '登录日期',
   last_access_date     timestamp(3) null comment '最近访问日期',
   duration_second      integer comment '有效时间(秒)',
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

alter table t_usr_login_history comment '用户登录历史表';

/*==============================================================*/
/* Table: t_usr_user                                            */
/*==============================================================*/
create table t_usr_user
(
   id                   bigint not null comment '主键',
   user_code            varchar(50) not null comment '用户编号',
   mobile_number        varchar(20) not null comment '手机号',
   is_mobile_verified   integer comment '手机号是否已经验证',
   mobile_operator      varchar(200) comment '手机号运营商',
   login_pwd            varchar(100) comment '登录密码',
   user_status          integer comment '用户状态',
   user_status_change_date timestamp(3) null comment '状态变更日期',
   register_channel     varchar(200) comment '注册渠道',
   user_nickname        varchar(200) comment '用户昵称',
   user_name            varchar(50) comment '用户姓名',
   user_sex             varchar(50) comment '用户性别',
   user_level           integer comment '用户级别',
   user_activeness      integer comment '用户活跃度',
   register_ip          varchar(200) comment '注册IP',
   id_type              integer comment '证件类型',
   id_number            varchar(50) comment '证件号码',
   imei                 varchar(200) comment '设备唯一的串号',
   operating_system     varchar(200) comment '设备操作系统',
   operating_system_version varchar(200) comment '设备操作系统版本号',
   device_brand         varchar(200) comment '三星/华为/苹果',
   device_model         varchar(200) comment '厂商给设备定义的编号',
   user_agent           varchar(1000) comment 'http请求的user_agent原始信息',
   last_access_date     timestamp(3) null comment '最近访问日期',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   user_account         varchar(50) comment '用户账号',
   user_email           varchar(200) comment '用户邮箱',
   is_email_verified    integer comment '用户邮箱是否已验证',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_usr_user comment '用户注册表';

/*==============================================================*/
/* Index: ui_user_code                                          */
/*==============================================================*/
create unique index ui_user_code on t_usr_user
(
   user_code
);

/*==============================================================*/
/* Index: us_user_mobile                                        */
/*==============================================================*/
create unique index us_user_mobile on t_usr_user
(
   mobile_number
);

/*==============================================================*/
/* Index: ui_user_account                                       */
/*==============================================================*/
create unique index ui_user_account on t_usr_user
(
   user_account
);

/*==============================================================*/
/* Index: ui_user_email                                         */
/*==============================================================*/
create unique index ui_user_email on t_usr_user
(
   user_email
);

/*==============================================================*/
/* Table: t_usr_user_code_sequence                              */
/*==============================================================*/
create table t_usr_user_code_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_usr_user_code_sequence comment '用户编号生成表';

/*==============================================================*/
/* Table: t_usr_user_history                                    */
/*==============================================================*/
create table t_usr_user_history
(
   id                   bigint not null comment '主键',
   user_code            varchar(50) not null comment '用户编号',
   mobile_number        varchar(20) not null comment '手机号',
   is_mobile_verified   integer comment '手机号是否已经验证',
   mobile_operator      varchar(200) comment '手机号运营商',
   login_pwd            varchar(100) comment '登录密码',
   user_status          integer comment '用户状态',
   user_status_change_date timestamp(3) null comment '状态变更日期',
   register_channel     varchar(200) comment '注册渠道',
   user_nickname        varchar(200) comment '用户昵称',
   user_name            varchar(50) comment '用户姓名',
   user_sex             varchar(50) comment '用户性别',
   user_level           integer comment '用户级别',
   user_activeness      integer comment '用户活跃度',
   register_ip          varchar(200) comment '注册IP',
   id_type              integer comment '证件类型',
   id_number            varchar(50) comment '证件号码',
   imei                 varchar(200) comment '设备唯一的串号',
   operating_system     varchar(200) comment '设备操作系统',
   operating_system_version varchar(200) comment '设备操作系统版本号',
   device_brand         varchar(200) comment '三星/华为/苹果',
   device_model         varchar(200) comment '厂商给设备定义的编号',
   user_agent           varchar(1000) comment 'http请求的user_agent原始信息',
   last_access_date     timestamp(3) null comment '最近访问日期',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   user_account         varchar(50) comment '用户账号',
   user_email           varchar(200) comment '用户邮箱',
   is_email_verified    integer comment '用户邮箱是否已验证',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_usr_user_history comment '用户历史表';

/*==============================================================*/
/* Index: ui_user_code_history                                  */
/*==============================================================*/
create unique index ui_user_code_history on t_usr_user_history
(
   user_code
);
