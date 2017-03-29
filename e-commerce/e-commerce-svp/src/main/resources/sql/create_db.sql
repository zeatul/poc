drop index i_bsi_user_code on t_svp_bsi_cash_coupon;

drop index ui_bsi_cc_code on t_svp_bsi_cash_coupon;

drop table if exists t_svp_bsi_cash_coupon;

drop table if exists t_svp_bsi_order_detail;

drop table if exists t_svp_bsi_phone_brand;

drop index us_svp_bpm1 on t_svp_bsi_phone_model;

drop table if exists t_svp_bsi_phone_model;

drop table if exists t_svp_bsi_phone_prodcut_map;

drop table if exists t_svp_bsi_product;

drop table if exists t_svp_mobile_data_order_detail;

drop table if exists t_svp_order;

/*==============================================================*/
/* Table: t_svp_bsi_cash_coupon                                 */
/*==============================================================*/
create table t_svp_bsi_cash_coupon
(
   id                   bigint not null comment '主键',
   bsi_cash_coupon_code varchar(50) not null comment '代金券编号',
   bsi_cash_coupon_name varchar(100) comment '代金券名称',
   bsi_cash_coupon_create_date timestamp(3) null comment '代金券生成日期',
   user_code            varchar(50) not null comment '用户编号',
   bsi_cash_coupon_owner varchar(50) comment '代金券所有者',
   bsi_cash_coupon_invalid_date timestamp(3) null comment '代金券失效效日期',
   bsi_cash_coupon_status char(1) comment '代金券状态',
   order_number         char(10) comment '订单编号',
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
/* Table: t_svp_bsi_order_detail                                */
/*==============================================================*/
create table t_svp_bsi_order_detail
(
   id                   bigint not null comment '主键',
   order_id             bigint comment '碎屏险订单id',
   bsi_phone_model_id   bigint not null comment '手机型号ID',
   bsi_product_id       bigint not null comment '碎屏险产品ID',
   imei                 varchar(100) comment '设备唯一的串号',
   bsi_benef_id_typ     integer comment '证件类型',
   bsi_benef_id_number  varchar(50) comment '证件号码',
   bsi_benef_birthday   varchar(50) comment '投保者生日',
   bsi_benef_sex        char(1) comment '投保者性别',
   bsi_benef_name       varchar(50) comment '投保者姓名',
   bsi_benef_mobile_number varchar(20) not null comment '投保者手机号',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_order_detail comment '碎屏险订单明细';

/*==============================================================*/
/* Table: t_svp_bsi_phone_brand                                 */
/*==============================================================*/
create table t_svp_bsi_phone_brand
(
   bsi_phone_brand      varchar(100) not null comment '手机品牌',
   spell_abbr           varchar(50) comment '拼首',
   object_order         integer comment '序号',
   primary key (bsi_phone_brand)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_svp_bsi_phone_model                                 */
/*==============================================================*/
create table t_svp_bsi_phone_model
(
   bsi_phone_model_id   bigint not null comment '手机型号ID',
   bsi_phone_brand      varchar(100) not null comment '手机品牌',
   bsi_phone_model      varchar(100) not null comment '手机型号',
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
/* Table: t_svp_bsi_phone_prodcut_map                           */
/*==============================================================*/
create table t_svp_bsi_phone_prodcut_map
(
   bsi_product_id       bigint not null comment '产品ID',
   bsi_phone_model_id   bigint not null comment '手机型号Id',
   bsi_product_valid_period integer comment '有效期(月)',
   primary key (bsi_product_id, bsi_phone_model_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_phone_prodcut_map comment '碎屏险手机型号产品的对应关系';

/*==============================================================*/
/* Table: t_svp_bsi_product                                     */
/*==============================================================*/
create table t_svp_bsi_product
(
   bsi_product_id       bigint not null comment '碎屏险产品ID',
   bsi_product_name     varchar(100) comment '产品名称',
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
/* Table: t_svp_mobile_data_order_detail                        */
/*==============================================================*/
create table t_svp_mobile_data_order_detail
(
   id                   bigint not null comment '主键',
   order_id             bigint comment '碎屏险订单id',
   charge_mobile_number varchar(20) comment '手机号码',
   charge_data_size     integer comment '充值流量',
   charge_status        char(1) comment '充值状态',
   charge_task_id       varchar(100) comment '充值任务号'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_mobile_data_order_detail comment '联通流量订单明细';

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
   order_type           char(1) comment '订单类型',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_order comment '碎屏险订单';
