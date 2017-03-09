drop table if exists t_usr_login;

drop table if exists t_usr_user;

/*==============================================================*/
/* Table: t_usr_login                                           */
/*==============================================================*/
create table t_usr_login
(
   token                varchar(100) not null comment 'token',
   user_id              bigint comment '用户ID',
   mobile_number        varchar(20) comment '用户手机号',
   user_code            varchar(50) comment '用户编号',
   login_ip             varchar(50) comment '登录IP',
   login_type           char(1) comment '登录类型(长期固定/短期固定/短期弹性)',
   login_date           timestamp(3) null comment '登录日期',
   last_access_date     timestamp(3) null comment '最近访问日期',
   token有效时间            integer comment 'token有效时间(分钟数/短期弹性)',
   token失效日期            timestamp(3) null comment 'token失效日期(长期固定/短期固定)',
   imei                 varchar(100) comment '设备唯一的串号',
   operating_system     varchar(100) comment '设备操作系统',
   operating_system_version varchar(100) comment '设备操作系统版本号',
   device_brand         varchar(100) comment '三星/华为/苹果',
   device_model         varchar(100) comment '厂商给设备定义的编号',
   user_agent           varchar(1000) comment 'http请求的user_agen原始信息',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (token)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_usr_login comment '用户登录表';

/*==============================================================*/
/* Table: t_usr_user                                            */
/*==============================================================*/
create table t_usr_user
(
   id                   bigint not null comment '主键',
   user_code            varchar(50) not null comment '用户编号（唯一)',
   mobile_number        varchar(20) not null comment '用户手机号(唯一)',
   is_mobile_verified   integer comment '手机号是否已经验证',
   mobile_operator      varchar(100) comment '手机号运营商',
   login_pwd            varchar(100) comment '登录密码',
   user_status          integer comment '用户状态',
   user_status_change_date timestamp(3) null comment '状态变更日期',
   register_channel     varchar(100) comment '注册渠道',
   nickname             varchar(100) comment '用户昵称',
   sex                  varchar(50) comment '用户性别',
   register_ip          varchar(100) comment '注册IP',
   id_type              integer comment '证件类型',
   id_number            varchar(50) comment '证件号码',
   imei                 varchar(100) comment '设备唯一的串号',
   operating_system     varchar(100) comment '设备操作系统',
   operating_system_version varchar(100) comment '设备操作系统版本号',
   device_brand         varchar(100) comment '三星/华为/苹果',
   device_model         varchar(100) comment '厂商给设备定义的编号',
   user_agent           varchar(1000) comment 'http请求的user_agen原始信息',
   last_access_date     timestamp(3) null comment '最近访问日期',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '修改日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_usr_user comment '用户注册表';
