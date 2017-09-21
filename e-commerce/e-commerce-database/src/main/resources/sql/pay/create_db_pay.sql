drop index i_pay_alipay_info on t_pay_alipay_info;

drop table if exists t_pay_alipay_info;

drop index ui_pay_application_code on t_pay_payment_application;

drop table if exists t_pay_payment_application;

drop index i_pay_payment on t_pay_payment_bill;

drop index ui_pay_payment_code on t_pay_payment_bill;

drop index ui_pay_order_code on t_pay_payment_bill;

drop table if exists t_pay_payment_bill;

drop index i_pay_payment_history on t_pay_payment_bill_history;

drop table if exists t_pay_payment_bill_history;

drop index ui_pay_category_code on t_pay_payment_category;

drop table if exists t_pay_payment_category;

drop table if exists t_pay_payment_sequence;

drop index i_pay_wxpay on t_pay_wxpay_info;

drop table if exists t_pay_wxpay_info;

/*==============================================================*/
/* Table: t_pay_alipay_info                                     */
/*==============================================================*/
create table t_pay_alipay_info
(
   notify_time          timestamp(3) null comment '通知时间',
   app_id               varchar(200) comment '支付宝分配给开发者的应用Id',
   trade_no             varchar(200) comment '支付宝交易凭证号',
   out_trade_no         varchar(200) comment '原支付请求的商户订单号 ',
   out_biz_no           varchar(200) comment '商户业务ID',
   buyer_id             varchar(200) comment '买家支付宝账号对应的支付宝唯一用户号',
   buyer_logon_id       varchar(50) comment ' 买家支付宝账号',
   seller_id            varchar(50) comment '卖家支付宝用户号',
   seller_email         varchar(50) comment '卖家支付宝账号',
   trade_status         varchar(50) comment '交易状态',
   total_amount         decimal(17,2) comment '本次交易支付的订单金额',
   receipt_amount       decimal(17,2) comment '商家在交易中实际收到的款项',
   invoice_amount       decimal(17,2) comment '用户在交易中支付的可开发票的金额',
   buyer_pay_amount     decimal(17,2) comment '用户在交易中支付的金额',
   point_amount         decimal(17,2) comment '使用集分宝支付的金额',
   refund_fee           decimal(17,2) comment '总退款金额',
   subject              varchar(500) comment '订单标题',
   body                 varchar(500) comment '商品描述',
   gmt_create           timestamp(3) null comment '交易创建时间',
   gmt_payment          timestamp(3) null comment '交易付款时间',
   gmt_refund           timestamp(3) null comment '交易退款时间',
   gmt_close            timestamp(3) null comment '交易结束时间',
   fund_bill_list       varchar(1000) comment '支付金额信息',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_pay_alipay_info comment '支付宝支付信息';

/*==============================================================*/
/* Index: i_pay_alipay_info                                     */
/*==============================================================*/
create index i_pay_alipay_info on t_pay_alipay_info
(
   trade_no,
   out_trade_no
);

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
   total_amount         decimal(17,2) not null comment '订单支付总价',
   currency             smallint unsigned not null comment '币种',
   payment_bill_status  tinyint unsigned not null comment '支付单状态',
   payment_category_code varchar(50) not null comment '支付分类编号',
   payment_bill_memo    varchar(200) comment '支付单备注',
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
/* Table: t_pay_payment_bill_history                            */
/*==============================================================*/
create table t_pay_payment_bill_history
(
   id                   integer unsigned not null comment '主键',
   order_code           varchar(50) not null comment '订单编号',
   order_desc           varchar(500) not null comment '订单描述',
   order_body           varchar(500) comment '订单内容',
   application_code     varchar(50) not null comment '应用编号',
   payment_bill_code    varchar(50) not null comment '支付单编号',
   store_code           varchar(50) not null comment '商户编号',
   user_code            varchar(50) not null comment '用户编号',
   total_amount         decimal(17,2) not null comment '订单成交价',
   currency             smallint unsigned not null comment '币种',
   payment_bill_status  tinyint unsigned not null comment '支付单状态',
   payment_category_code varchar(50) not null comment '支付分类编号',
   payment_bill_memo    varchar(200) comment '支付单备注',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_pay_payment_bill_history comment '支付单';

/*==============================================================*/
/* Index: i_pay_payment_history                                 */
/*==============================================================*/
create index i_pay_payment_history on t_pay_payment_bill_history
(
   store_code,
   user_code,
   order_code,
   application_code,
   payment_bill_code
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
   is_leaf              tinyint unsigned not null comment '是否为叶子节点',
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

/*==============================================================*/
/* Table: t_pay_wxpay_info                                      */
/*==============================================================*/
create table t_pay_wxpay_info
(
   appid                varchar(50) comment '公众账号ID',
   mch_id               varchar(50) comment '商户号',
   device_info          varchar(50) comment '设备号',
   result_code          varchar(50) comment '业务结果',
   err_code             varchar(50) comment '错误代码',
   err_code_des         varchar(500) comment '错误代码描述',
   openid               varchar(200) comment '用户在商户appid下的唯一标识',
   trade_type           varchar(50) comment '交易类型JSAPI JSAPI、NATIVE、APP',
   bank_type            varchar(50) comment '付款银行',
   total_fee            integer comment '订单总金额',
   settlement_total_fee integer comment '应结订单金额',
   fee_type             varchar(50) comment '货币种类',
   cash_fee             integer comment '现金支付金额',
   cash_fee_type        varchar(50) comment '现金支付货币类型',
   coupon_fee           integer comment '总代金券金额',
   coupon_count         integer comment '代金券使用数量',
   transaction_id       varchar(200) comment '微信支付订单号',
   out_trade_no         varchar(200) comment '商户订单号',
   time_end             varchar(200) comment '支付完成时间',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_pay_wxpay_info comment '微信支付信息';

/*==============================================================*/
/* Index: i_pay_wxpay                                           */
/*==============================================================*/
create index i_pay_wxpay on t_pay_wxpay_info
(
   appid,
   mch_id,
   openid,
   transaction_id,
   out_trade_no
);
