drop table if exists t_tra_order;

drop index ui_tra_detail on t_tra_order_detail;

drop table if exists t_tra_order_detail;

drop index i_tra_delivery on t_tra_order_detail_delivery_data;

drop index ui_tra_delivery on t_tra_order_detail_delivery_data;

drop table if exists t_tra_order_detail_delivery_data;

drop index I_tra_express on t_tra_order_express;

drop table if exists t_tra_order_express;

drop table if exists t_tra_order_operation;

drop table if exists t_tra_order_out_sequence;

drop index I_tra_recipient on t_tra_order_recipient;

drop table if exists t_tra_order_recipient;

drop table if exists t_tra_order_sequence;

/*==============================================================*/
/* Table: t_tra_order                                           */
/*==============================================================*/
create table t_tra_order
(
   id                   integer unsigned not null comment '主键',
   store_code           varchar(50) not null comment '商户编号',
   order_code           varchar(50) not null comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   order_type           tinyint unsigned not null comment '订单类型',
   order_status         tinyint unsigned not null comment '订单状态',
   order_customer_memo  varchar(200) comment '订单客户备注',
   order_store_memo     varchar(200) comment '订单商家备注',
   order_original_total_price decimal(15,4) not null comment '订单原价',
   order_deduction      decimal(15,4) not null comment '订单减免',
   order_pay_total_price decimal(15,4) not null comment '订单实价',
   order_pay_expire_time timestamp(3) null not null comment '订单支付失效时间',
   freight_charge       decimal(15,4) not null comment '运费',
   order_desc           varchar(200) not null comment '订单描述',
   pay_type             tinyint unsigned comment '支付方式',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_tra_order comment '订单';

/*==============================================================*/
/* Table: t_tra_order_detail                                    */
/*==============================================================*/
create table t_tra_order_detail
(
   id                   integer unsigned not null comment '主键',
   order_id             integer unsigned not null comment '订单主键',
   store_code           varchar(50) not null comment '商户编号',
   order_code           varchar(50) not null comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   product_id           integer unsigned not null comment '产品主键',
   sku_id               integer unsigned not null comment '产品sku主键',
   order_detail_type    tinyint unsigned comment '明细类型',
   sku_quantity         integer unsigned not null comment '产品SKU数量',
   unit_quantity        integer not null comment '计价个数',
   discount             integer not null comment '折扣',
   discount_quantity    integer not null comment '折扣数量',
   unit_original_price  decimal(15,4) not null comment '显示单价',
   unit_pay_price       decimal(15,4) not null comment '单价',
   order_detail_deduction decimal(15,4) not null comment '订单明细减免',
   order_detail_total_pay_price decimal(15,4) not null comment '订单明细支付总价',
   order_detail_memo    varchar(200) comment '备注',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_tra_order_detail comment '订单明细';

/*==============================================================*/
/* Index: ui_tra_detail                                         */
/*==============================================================*/
create unique index ui_tra_detail on t_tra_order_detail
(
   order_id,
   sku_id,
   order_detail_type
);

/*==============================================================*/
/* Table: t_tra_order_detail_delivery_data                      */
/*==============================================================*/
create table t_tra_order_detail_delivery_data
(
   order_detail_id      integer unsigned not null comment '订单明细主键',
   order_id             integer unsigned not null comment '订单主键',
   store_code           varchar(50) not null comment '商户编号',
   order_code           varchar(50) not null comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   supplier_code        varchar(50) not null comment '供应商编号',
   outer_phone_model_id varchar(50) comment '手机型号ID',
   outer_product_id     varchar(50) comment '供应商产品ID',
   imei                 varchar(200) comment '设备唯一的串号',
   benef_id_typ         varchar(50) comment '受益人证件类型',
   benef_id_number      varchar(50) comment '受益人证件号码',
   benef_birthday       varchar(50) comment '受益人生日',
   benef_sex            varchar(50) comment '受益人性别',
   benef_name           varchar(50) comment '受益人姓名',
   benef_mobile_number  varchar(20) comment '受益人手机号',
   outer_order_code     varchar(50) comment '外部交易编号',
   task_code            varchar(200) comment '任务号',
   task_status          tinyint unsigned comment '任务状态',
   schedule_exec_date   timestamp(3) null comment '计划执行时间',
   exec_times           tinyint unsigned comment '已经执行次数',
   max_exec_times       tinyint unsigned comment '最大允许执行次数',
   last_exec_err_code   varchar(50) comment '最后一次执行错误代码',
   last_exec_err_msg    varchar(1000) comment '最后一次执行错误原因',
   last_exec_begin_time timestamp(3) null comment '最后一次执行开始时间',
   last_exec_end_time   timestamp(3) null comment '最后一次执行完成时间',
   last_exec_computer   varchar(200) comment '最后一次执行机器',
   last_exec_process_id varchar(50) comment '最后一次执行进程ID',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (order_detail_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_tra_order_detail_delivery_data comment '交付数据';

/*==============================================================*/
/* Index: ui_tra_delivery                                       */
/*==============================================================*/
create unique index ui_tra_delivery on t_tra_order_detail_delivery_data
(
   task_code
);

/*==============================================================*/
/* Index: i_tra_delivery                                        */
/*==============================================================*/
create index i_tra_delivery on t_tra_order_detail_delivery_data
(
   order_id,
   store_code,
   order_code,
   user_code
);

/*==============================================================*/
/* Table: t_tra_order_express                                   */
/*==============================================================*/
create table t_tra_order_express
(
   id                   integer unsigned not null comment '主键',
   order_id             integer unsigned not null comment '订单主键',
   store_code           varchar(50) not null comment '商户编号',
   order_code           varchar(50) not null comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   express_company_code varchar(50) not null comment '快递公司编号',
   express_company_name varchar(200) not null comment '快递公司名称',
   express_order_code   varchar(200) not null comment '快递单号',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_tra_order_express comment '订单快递';

/*==============================================================*/
/* Index: I_tra_express                                         */
/*==============================================================*/
create index I_tra_express on t_tra_order_express
(
   order_id,
   store_code,
   order_code,
   user_code
);

/*==============================================================*/
/* Table: t_tra_order_operation                                 */
/*==============================================================*/
create table t_tra_order_operation
(
   id                   integer unsigned not null comment '主键',
   order_id             integer unsigned not null comment '订单主键',
   store_code           varchar(50) not null comment '商户编号',
   order_code           varchar(50) not null comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   order_pre_status     tinyint unsigned comment '操作前状态',
   order_next_status    tinyint unsigned comment '操作后状态',
   operation_desc       varchar(200) comment '操作描述',
   operation_memo       varchar(200) comment '操作备注',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_tra_order_operation comment '订单操作记录';

/*==============================================================*/
/* Table: t_tra_order_out_sequence                              */
/*==============================================================*/
create table t_tra_order_out_sequence
(
   stub                 char(1) comment 'stub',
   id                   integer unsigned not null comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_tra_order_out_sequence comment '部订单号生成表';

/*==============================================================*/
/* Table: t_tra_order_recipient                                 */
/*==============================================================*/
create table t_tra_order_recipient
(
   id                   integer unsigned not null comment '主键',
   order_id             integer unsigned not null comment '订单主键',
   store_code           varchar(50) not null comment '商户编号',
   order_code           varchar(50) not null comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   recipient_name       varchar(50) not null comment '收件人姓名',
   recipient_mobilephone varchar(20) not null comment '收件人手机号',
   recipient_telephone  varchar(20) comment '收件人座机号',
   recipient_address    varchar(200) not null comment '收件人地址',
   recipient_post_code  varchar(50) comment '收件人地址邮编',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_tra_order_recipient comment '收货人信息';

/*==============================================================*/
/* Index: I_tra_recipient                                       */
/*==============================================================*/
create index I_tra_recipient on t_tra_order_recipient
(
   order_id,
   store_code,
   order_code,
   user_code
);

/*==============================================================*/
/* Table: t_tra_order_sequence                                  */
/*==============================================================*/
create table t_tra_order_sequence
(
   stub                 char(1) comment 'stub',
   id                   integer unsigned not null comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_tra_order_sequence comment '内部订单编号生成表';
