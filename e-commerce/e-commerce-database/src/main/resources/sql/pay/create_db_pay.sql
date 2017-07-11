drop index ui_pay_application_code on t_pay_payment_application;

drop table if exists t_pay_payment_application;

drop index i_pay_payment on t_pay_payment_bill;

drop index ui_pay_payment_code on t_pay_payment_bill;

drop index ui_pay_order_code on t_pay_payment_bill;

drop table if exists t_pay_payment_bill;

drop index ui_pay_category_code on t_pay_payment_category;

drop table if exists t_pay_payment_category;

drop table if exists t_pay_payment_sequence;

/*==============================================================*/
/* Table: t_pay_payment_application                             */
/*==============================================================*/
create table t_pay_payment_application
(
   id                   integer unsigned not null comment '主键',
   application_code     varchar(200) not null comment '应用编号',
   application_name     varchar(200) not null comment '应用名称',
   application_status   tinyint unsigned not null comment '应用状态',
   application_desc     varchar(200) comment '应用描述',
   application_memo     varchar(200) comment '应用备注',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_pay_payment_application comment '支付应用';

/*==============================================================*/
/* Index: ui_pay_application_code                               */
/*==============================================================*/
create unique index ui_pay_application_code on t_pay_payment_application
(
   application_code
);

/*==============================================================*/
/* Table: t_pay_payment_bill                                    */
/*==============================================================*/
create table t_pay_payment_bill
(
   id                   integer unsigned not null comment '主键',
   order_code           varchar(50) not null comment '订单编号',
   order_desc           varchar(500) not null comment '订单描述',
   order_body           varchar(500) comment '订单内容',
   application_code     varchar(50) not null comment '应用编号',
   payment_bill_code    varchar(50) not null comment '支付单编号',
   store_code           varchar(50) not null comment '商户编号',
   user_code            varchar(50) not null comment '用户编号',
   order_trans_price    decimal(17,2) not null comment '订单成交价',
   currency             smallint unsigned not null comment '币种',
   payment_bill_status  tinyint unsigned not null comment '支付单状态',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_pay_payment_bill comment '支付单';

/*==============================================================*/
/* Index: ui_pay_order_code                                     */
/*==============================================================*/
create unique index ui_pay_order_code on t_pay_payment_bill
(
   order_code,
   application_code
);

/*==============================================================*/
/* Index: ui_pay_payment_code                                   */
/*==============================================================*/
create unique index ui_pay_payment_code on t_pay_payment_bill
(
   payment_bill_code
);

/*==============================================================*/
/* Index: i_pay_payment                                         */
/*==============================================================*/
create index i_pay_payment on t_pay_payment_bill
(
   store_code,
   user_code
);

/*==============================================================*/
/* Table: t_pay_payment_category                                */
/*==============================================================*/
create table t_pay_payment_category
(
   id                   integer unsigned not null comment '主键',
   pid                  integer unsigned not null comment '父ID',
   depth                tinyint unsigned not null comment '树深度',
   payment_category_code varchar(50) not null comment '支付分类编号',
   payment_category_name varchar(200) not null comment '支付分类名称',
   payment_category_status tinyint unsigned comment '支付分类状态',
   payment_category_desc varchar(200) comment '支付分类描述',
   payment_category_memo varchar(200) comment '支付分类备注',
   payment_category_logo varchar(200) comment '支付分类Logo',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_pay_payment_category comment '支付分类';

/*==============================================================*/
/* Index: ui_pay_category_code                                  */
/*==============================================================*/
create unique index ui_pay_category_code on t_pay_payment_category
(
   payment_category_code
);

/*==============================================================*/
/* Table: t_pay_payment_sequence                                */
/*==============================================================*/
create table t_pay_payment_sequence
(
   stub                 char(1) comment 'stub',
   id                   integer not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_pay_payment_sequence comment '支付单编号生成表';
