drop table if exists t_bas_district;

drop table if exists t_bas_district_type;

drop table if exists t_bas_mobile_number_segment;

drop table if exists t_bas_mobile_operator;

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
