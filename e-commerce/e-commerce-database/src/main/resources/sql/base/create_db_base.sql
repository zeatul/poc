drop table if exists t_bas_bsi_phone_brand;

drop index us_bas_svp_bpm on t_bas_bsi_phone_model;

drop table if exists t_bas_bsi_phone_model;

drop table if exists t_bas_bsi_phone_product_map;

drop index ui_bas_bsi_product on t_bas_bsi_product;

drop table if exists t_bas_bsi_product;

drop table if exists t_bas_district;

drop table if exists t_bas_district_type;

drop table if exists t_bas_mobile_number_segment;

drop table if exists t_bas_mobile_operator;

/*==============================================================*/
/* Table: t_bas_bsi_phone_brand                                 */
/*==============================================================*/
create table t_bas_bsi_phone_brand
(
   bsi_phone_brand      varchar(200) not null comment '手机品牌',
   spell_abbr           varchar(50) comment '拼首',
   object_order         integer unsigned comment '序号',
   bsi_phone_brand_status tinyint unsigned comment '手机品牌状态',
   primary key (bsi_phone_brand)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_bas_bsi_phone_model                                 */
/*==============================================================*/
create table t_bas_bsi_phone_model
(
   bsi_phone_model_id   smallint unsigned not null comment '手机型号ID',
   bsi_phone_brand      varchar(200) not null comment '手机品牌',
   bsi_phone_model      varchar(200) not null comment '手机型号',
   bsi_phone_model_status tinyint unsigned comment '型号状态',
   object_order         integer unsigned comment '序号',
   primary key (bsi_phone_model_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_bsi_phone_model comment '碎屏险手机品牌型号';

/*==============================================================*/
/* Index: us_bas_svp_bpm                                        */
/*==============================================================*/
create unique index us_bas_svp_bpm on t_bas_bsi_phone_model
(
   bsi_phone_brand,
   bsi_phone_model
);

/*==============================================================*/
/* Table: t_bas_bsi_phone_product_map                           */
/*==============================================================*/
create table t_bas_bsi_phone_product_map
(
   bsi_product_id       integer unsigned not null comment '产品ID',
   bsi_phone_model_id   integer unsigned not null comment '手机型号Id',
   bsi_insurance_period_month tinyint unsigned not null comment '保险月数',
   primary key (bsi_phone_model_id, bsi_insurance_period_month)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_bsi_phone_product_map comment '碎屏险手机型号产品的对应关系';

/*==============================================================*/
/* Table: t_bas_bsi_product                                     */
/*==============================================================*/
create table t_bas_bsi_product
(
   bsi_product_id       smallint unsigned not null comment '碎屏险产品ID',
   bsi_product_name     varchar(200) comment '产品名称',
   bsi_insurance_period_month tinyint unsigned not null comment '保险月数',
   bsi_grade            tinyint unsigned not null comment '保险产品档次',
   bsi_product_status   tinyint unsigned comment '产品状态',
   bsi_display_price    decimal(17,2) comment '显示价格',
   bsi_trade_price      decimal(17,2) comment '批发价格',
   bsi_retail_price     decimal(17,2) comment '销售价格',
   primary key (bsi_product_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_bsi_product comment '碎屏险产品';

/*==============================================================*/
/* Index: ui_bas_bsi_product                                    */
/*==============================================================*/
create unique index ui_bas_bsi_product on t_bas_bsi_product
(
   bsi_insurance_period_month,
   bsi_grade
);

/*==============================================================*/
/* Table: t_bas_district                                        */
/*==============================================================*/
create table t_bas_district
(
   district_code        varchar(50) not null comment '行政区国标码',
   district             varchar(50) not null comment '行政区名',
   parent_district_code varchar(50) not null comment '上级行政区国标码',
   district_type        varchar(50) not null comment '行政区类型',
   primary key (district_code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_district comment '行政区';

/*==============================================================*/
/* Table: t_bas_district_type                                   */
/*==============================================================*/
create table t_bas_district_type
(
   district_type        varchar(50) not null comment '行政区类型',
   district_type_name   varchar(50) not null comment '行政区名称',
   primary key (district_type)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_district_type comment '行政区类型';

/*==============================================================*/
/* Table: t_bas_mobile_number_segment                           */
/*==============================================================*/
create table t_bas_mobile_number_segment
(
   mobile_number_prefix varchar(50) not null comment '手机号码前7位',
   city_code            varchar(50) not null comment '市级行政区代码',
   province_code        varchar(50) not null comment '省级行政区代码',
   mobile_operator_code varchar(50) not null comment '移动运营商代码',
   isp                  varchar(50) not null comment 'isp',
   isp_product          varchar(50) not null comment 'isp_product',
   primary key (mobile_number_prefix)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_mobile_number_segment comment '移动号码段';

/*==============================================================*/
/* Table: t_bas_mobile_operator                                 */
/*==============================================================*/
create table t_bas_mobile_operator
(
   mobile_operator_code varchar(50) not null comment '移动运营商编码',
   mobile_operator      varchar(50) not null comment '移动运营商',
   primary key (mobile_operator_code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_bas_mobile_operator comment '移动运营商';
