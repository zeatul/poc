drop index ui_store_code on t_sto_store;

drop table if exists t_sto_store;

/*==============================================================*/
/* Table: t_sto_store                                           */
/*==============================================================*/
create table t_sto_store
(
   id                   bigint not null comment '主键',
   store_code           varchar(50) comment '商户编号',
   store_name           varchar(100),
   store_status         char(1),
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_sto_store comment '商铺';

/*==============================================================*/
/* Index: ui_store_code                                         */
/*==============================================================*/
create unique index ui_store_code on t_sto_store
(
   store_code
);
