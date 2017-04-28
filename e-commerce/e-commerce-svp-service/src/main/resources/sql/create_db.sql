drop index i_bsi_user_code on t_svp_bsi_cash_coupon;

drop index ui_bsi_cc_code on t_svp_bsi_cash_coupon;

drop table if exists t_svp_bsi_cash_coupon;

drop table if exists t_svp_bsi_cash_coupon_sequence;

drop index i_bsi_coupon_code on t_svp_bsi_order_detail;

drop index ui_bsi_task_code on t_svp_bsi_order_detail;

drop table if exists t_svp_bsi_order_detail;

drop table if exists t_svp_bsi_out_order_sequence;

drop table if exists t_svp_bsi_phone_brand;

drop index us_svp_bpm1 on t_svp_bsi_phone_model;

drop table if exists t_svp_bsi_phone_model;

drop table if exists t_svp_bsi_phone_product_map;

drop table if exists t_svp_bsi_product;

drop table if exists t_svp_charge_sequence;

drop index ui_od_task_code on t_svp_mobile_data_order_detail;

drop table if exists t_svp_mobile_data_order_detail;

drop table if exists t_svp_order;

drop table if exists t_svp_order_sequence;

/*==============================================================*/
/* Table: t_svp_bsi_cash_coupon                                 */
/*==============================================================*/
create table t_svp_bsi_cash_coupon
(
   id                   bigint not null comment '主键',
   user_code            varchar(50) not null comment '用户编号',
   mobile_number        varchar(20) comment '手机号码',
   bsi_cash_coupon_code varchar(50) not null comment '代金券编号',
   bsi_cash_coupon_name varchar(200) comment '代金券名称',
   bsi_cash_coupon_create_date timestamp(3) null comment '代金券生成日期',
   bsi_cash_coupon_invalid_date timestamp(3) null comment '代金券失效日期',
   bsi_cash_coupon_status integer comment '代金券状态',
   bsi_cash_coupon_type varchar(50) comment '代金券类型',
   bsi_cash_coupon_period integer comment '代金券保险月份数',
   bsi_cash_coupon_activate_error varchar(1000) comment '代金券激活失败原因',
   order_code           varchar(50) comment '订单编号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_cash_coupon comment '代金券';

/*==============================================================*/
/* Index: ui_bsi_cc_code                                        */
/*==============================================================*/
create unique index ui_bsi_cc_code on t_svp_bsi_cash_coupon
(
   bsi_cash_coupon_code
);

/*==============================================================*/
/* Index: i_bsi_user_code                                       */
/*==============================================================*/
create index i_bsi_user_code on t_svp_bsi_cash_coupon
(
   user_code
);

/*==============================================================*/
/* Table: t_svp_bsi_cash_coupon_sequence                        */
/*==============================================================*/
create table t_svp_bsi_cash_coupon_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_svp_bsi_cash_coupon_sequence comment '代金券编号生成表';

/*==============================================================*/
/* Table: t_svp_bsi_order_detail                                */
/*==============================================================*/
create table t_svp_bsi_order_detail
(
   id                   bigint not null comment '主键',
   order_id             bigint comment '碎屏险订单id',
   bsi_task_code        varchar(200) comment '任务号,与小宝对接用',
   bsi_task_status      integer comment '任务状态',
   bsi_phone_model_id   integer comment '手机型号ID',
   bsi_product_id       integer comment '碎屏险产品ID',
   imei                 varchar(200) comment '设备唯一的串号',
   bsi_benef_id_typ     integer comment '证件类型',
   bsi_benef_id_number  varchar(50) comment '证件号码',
   bsi_benef_birthday   varchar(50) comment '投保者生日',
   bsi_benef_sex        integer comment '投保者性别',
   bsi_benef_name       varchar(50) comment '投保者姓名',
   bsi_benef_mobile_number varchar(20) comment '投保者手机号',
   bsi_cash_coupon_code varchar(50) comment '代金券编号',
   bsi_insurance_code   varchar(50) comment '小宝订单编号',
   exec_times           integer comment '已经执行次数',
   max_exec_times       integer comment '最大允许执行次数',
   last_exec_err_code   varchar(50) comment '最后一次执行错误代码',
   last_exec_err_msg    varchar(1000) comment '最后一次执行错误原因',
   last_exec_date       timestamp(3) null comment '最后一次执行时间',
   schedule_exec_date   timestamp(3) null comment '计划执行时间',
   current_exec_computer varchar(200) comment '当前执行机器',
   current_exec_process_id varchar(50) comment '当前执行进程ID',
   current_exec_start_date timestamp(3) null comment '当前任务启动时间',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_order_detail comment '碎屏险订单明细';

/*==============================================================*/
/* Index: ui_bsi_task_code                                      */
/*==============================================================*/
create unique index ui_bsi_task_code on t_svp_bsi_order_detail
(
   bsi_task_code
);

/*==============================================================*/
/* Index: i_bsi_coupon_code                                     */
/*==============================================================*/
create index i_bsi_coupon_code on t_svp_bsi_order_detail
(
   bsi_cash_coupon_code
);

/*==============================================================*/
/* Table: t_svp_bsi_out_order_sequence                          */
/*==============================================================*/
create table t_svp_bsi_out_order_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_svp_bsi_out_order_sequence comment '碎屏险外部订单号生成表';

/*==============================================================*/
/* Table: t_svp_bsi_phone_brand                                 */
/*==============================================================*/
create table t_svp_bsi_phone_brand
(
   bsi_phone_brand      varchar(200) not null comment '手机品牌',
   spell_abbr           varchar(50) comment '拼首',
   object_order         integer comment '序号',
   bsi_phone_brand_status integer comment '手机品牌状态',
   primary key (bsi_phone_brand)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_svp_bsi_phone_model                                 */
/*==============================================================*/
create table t_svp_bsi_phone_model
(
   bsi_phone_model_id   integer not null comment '手机型号ID',
   bsi_phone_brand      varchar(200) not null comment '手机品牌',
   bsi_phone_model      varchar(200) not null comment '手机型号',
   bsi_phone_model_status char(1) comment '型号状态',
   primary key (bsi_phone_model_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_phone_model comment '碎屏险手机品牌型号';

/*==============================================================*/
/* Index: us_svp_bpm1                                           */
/*==============================================================*/
create unique index us_svp_bpm1 on t_svp_bsi_phone_model
(
   bsi_phone_brand,
   bsi_phone_model
);

/*==============================================================*/
/* Table: t_svp_bsi_phone_product_map                           */
/*==============================================================*/
create table t_svp_bsi_phone_product_map
(
   bsi_product_id       integer not null comment '产品ID',
   bsi_phone_model_id   integer not null comment '手机型号Id',
   bsi_product_valid_period integer comment '有效期(月)',
   primary key (bsi_product_id, bsi_phone_model_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_phone_product_map comment '碎屏险手机型号产品的对应关系';

/*==============================================================*/
/* Table: t_svp_bsi_product                                     */
/*==============================================================*/
create table t_svp_bsi_product
(
   bsi_product_id       integer not null comment '碎屏险产品ID',
   bsi_product_name     varchar(200) comment '产品名称',
   bsi_product_valid_period integer comment '有效期(月)',
   bsi_product_status   char(1) comment '产品状态',
   bsi_display_price    numeric(10,2) comment '显示价格',
   bsi_trade_price      numeric(10,2) comment '批发价格',
   bsi_retail_price     numeric(10,2) comment '销售价格',
   primary key (bsi_product_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_product comment '碎屏险产品';

/*==============================================================*/
/* Table: t_svp_charge_sequence                                 */
/*==============================================================*/
create table t_svp_charge_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_svp_charge_sequence comment '流量充值任务号生成表';

/*==============================================================*/
/* Table: t_svp_mobile_data_order_detail                        */
/*==============================================================*/
create table t_svp_mobile_data_order_detail
(
   id                   bigint not null comment '主键',
   order_id             bigint comment '碎屏险订单id',
   charge_mobile_number varchar(20) comment '手机号码',
   charge_data_size     integer comment '充值流量',
   charge_status        integer comment '充值状态',
   charge_task_code     varchar(200) comment '充值任务号',
   exec_times           integer comment '已经执行次数',
   max_exec_times       integer comment '最大允许执行次数',
   last_exec_err_code   varchar(50) comment '最后一次执行错误代码',
   last_exec_err_msg    varchar(1000) comment '最后一次执行错误原因',
   last_exec_date       timestamp(3) null comment '最后一次执行时间',
   schedule_exec_date   timestamp(3) null comment '计划执行时间',
   current_exec_computer varchar(200) comment '当前执行机器',
   current_exec_process_id varchar(50) comment '当前执行进程ID',
   current_exec_start_date timestamp(3) null comment '当前任务启动时间',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_mobile_data_order_detail comment '联通流量订单明细';

/*==============================================================*/
/* Index: ui_od_task_code                                       */
/*==============================================================*/
create unique index ui_od_task_code on t_svp_mobile_data_order_detail
(
   charge_task_code
);

/*==============================================================*/
/* Table: t_svp_order                                           */
/*==============================================================*/
create table t_svp_order
(
   id                   bigint not null comment '主键',
   order_code           varchar(50) comment '订单编号',
   store_code           varchar(50) comment '商户编号',
   user_code            varchar(50) comment '用户编号',
   mobile_number        varchar(20) comment '手机号码',
   order_status         integer comment '订单状态',
   order_type           varchar(50) comment '订单类型',
   order_desc           varchar(1000) comment '订单描述',
   order_error_cause    varchar(1000) comment '订单失败原因',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_order comment '碎屏险订单';

/*==============================================================*/
/* Table: t_svp_order_sequence                                  */
/*==============================================================*/
create table t_svp_order_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_svp_order_sequence comment '订单编号生成表';
