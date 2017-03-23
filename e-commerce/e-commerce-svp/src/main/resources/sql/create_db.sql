drop table if exists t_bsi_phone_brand;

drop index us_svp_bpm1 on t_svp_bsi_phone_model;

drop table if exists t_svp_bsi_phone_model;

drop table if exists t_svp_bsi_phone_prodcut_map;

drop table if exists t_svp_bsi_product;

/*==============================================================*/
/* Table: t_bsi_phone_brand                                     */
/*==============================================================*/
create table t_bsi_phone_brand
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
   bsi_phone_model_id   bigint not null comment '型号ID',
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
   primary key (bsi_product_id, bsi_phone_model_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_phone_prodcut_map comment '碎屏险手机型号产品的对应关系';

/*==============================================================*/
/* Table: t_svp_bsi_product                                     */
/*==============================================================*/
create table t_svp_bsi_product
(
   bsi_product_id       bigint not null comment '产品ID',
   bsi_product_name     varchar(100) comment '产品名称',
   bsi_product_valid_period integer comment '有效期(月)',
   bsi_product_status   char(1) comment '产品状态',
   primary key (bsi_product_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_svp_bsi_product comment '碎屏险产品';
