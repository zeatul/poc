drop index i_prd_attr_name_pvid on t_prd_attr_name;

drop index i_prd_attr_name_pid on t_prd_attr_name;

drop table if exists t_prd_attr_name;

drop index i_prd_attr_value_cat on t_prd_attr_value;

drop table if exists t_prd_attr_value;

drop index ui_prd_category_code on t_prd_category;

drop index ui_prd_category_id_path on t_prd_category;

drop table if exists t_prd_category;

drop index ui_prd_c_s_m on t_prd_category_supplier_map;

drop table if exists t_prd_category_supplier_map;

drop table if exists t_prd_pic;

drop index ui_prd_store_product on t_prd_product;

drop table if exists t_prd_product;

drop table if exists t_prd_product_base_attr;

drop index ui_prd_sku_attr on t_prd_product_sku_attr;

drop table if exists t_prd_product_sku_attr;

drop table if exists t_prd_sku;

drop table if exists t_prd_small_number_sequence;

drop table if exists t_prd_stock;

drop index ui_svp_supplier_code on t_prd_supplier;

drop table if exists t_prd_supplier;

/*==============================================================*/
/* Table: t_prd_attr_name                                       */
/*==============================================================*/
create table t_prd_attr_name
(
   id                   bigint unsigned not null comment '主键',
   category_id          bigint unsigned not null comment '产品目录主键',
   pid                  bigint unsigned not null comment '父属性名主键',
   pvid                 bigint unsigned not null comment '父属性值主键',
   attr_name_code       varchar(50) not null comment '属性名编号',
   attr_name_business_type smallint unsigned not null comment '属性名业务功能分类(品牌,供应商,其它)',
   attr_name_type       tinyint unsigned not null comment '属性类型（关键属性,销售属性,一般属性）',
   attr_value_type      tinyint unsigned not null comment '属性值类型',
   attr_name            varchar(200) not null comment '属性名名称',
   is_search            tinyint unsigned not null comment '是否搜索',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_attr_name comment '属性名表';

/*==============================================================*/
/* Index: i_prd_attr_name_pid                                   */
/*==============================================================*/
create index i_prd_attr_name_pid on t_prd_attr_name
(
   pid
);

/*==============================================================*/
/* Index: i_prd_attr_name_pvid                                  */
/*==============================================================*/
create index i_prd_attr_name_pvid on t_prd_attr_name
(
   pvid
);

/*==============================================================*/
/* Table: t_prd_attr_value                                      */
/*==============================================================*/
create table t_prd_attr_value
(
   id                   bigint unsigned not null comment '主键',
   category_id          bigint unsigned not null comment '产品目录主键',
   attr_name_id         bigint unsigned not null comment '属性名主键',
   attr_value           varchar(50) not null comment '属性值',
   attr_display_value   varchar(50) comment '属性值别名',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_attr_value comment '属性值表';

/*==============================================================*/
/* Index: i_prd_attr_value_cat                                  */
/*==============================================================*/
create index i_prd_attr_value_cat on t_prd_attr_value
(
   category_id
);

/*==============================================================*/
/* Table: t_prd_category                                        */
/*==============================================================*/
create table t_prd_category
(
   id                   bigint unsigned not null comment '主键',
   pid                  bigint unsigned not null comment '父ID',
   id_path              varchar(200) not null comment '主键PATH',
   object_order         integer unsigned not null comment '产品目录序号',
   depth                tinyint unsigned not null comment '产品目录深度',
   category_code        varchar(50) not null comment '产品目录编号',
   category_name        varchar(50) not null comment '产品目录名称',
   category_status      tinyint unsigned not null comment '产品目录状态',
   category_desc        varchar(200) comment '产品目录描述',
   category_logo        varchar(200) comment '产品目录logo',
   category_home_page   varchar(200) comment '产品目录主页',
   is_leaf              tinyint unsigned not null comment '是否为最终目录分类，最终目录分类才能有商品模板',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_category comment '产品目录';

/*==============================================================*/
/* Index: ui_prd_category_id_path                               */
/*==============================================================*/
create unique index ui_prd_category_id_path on t_prd_category
(
   id_path
);

/*==============================================================*/
/* Index: ui_prd_category_code                                  */
/*==============================================================*/
create unique index ui_prd_category_code on t_prd_category
(
   category_code
);

/*==============================================================*/
/* Table: t_prd_category_supplier_map                           */
/*==============================================================*/
create table t_prd_category_supplier_map
(
   id                   bigint unsigned not null comment '主键',
   category_id          bigint unsigned not null comment '产品目录主键',
   supplier_id          bigint unsigned not null comment '供应商主键',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_category_supplier_map comment '供应商产品目录对应关系表';

/*==============================================================*/
/* Index: ui_prd_c_s_m                                          */
/*==============================================================*/
create unique index ui_prd_c_s_m on t_prd_category_supplier_map
(
   category_id,
   supplier_id
);

/*==============================================================*/
/* Table: t_prd_pic                                             */
/*==============================================================*/
create table t_prd_pic
(
   id                   bigint unsigned not null comment '主键',
   owner_type           tinyint unsigned not null comment '所有者类型',
   ownert_id            bigint unsigned not null comment '所有者主键',
   pic_name             varchar(50) comment '图片名称',
   pic_url              varchar(200) not null comment '图片地址',
   pic_type             tinyint unsigned not null comment '图片类型',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_pic comment '图片表';

/*==============================================================*/
/* Table: t_prd_product                                         */
/*==============================================================*/
create table t_prd_product
(
   id                   bigint unsigned not null comment '主键',
   category_id          bigint unsigned not null comment '产品目录主键',
   store_code           varchar(50) not null comment '商户编号',
   product_code         varchar(50) not null comment '产品编号',
   product_name         varchar(200) not null comment '产品名称',
   product_status       tinyint unsigned not null comment '产品状态',
   product_home_page    varchar(200) comment '产品主页',
   product_desc         varchar(1000) comment '产品描述',
   product_memo         varchar(200) comment '产品备注',
   on_sale_stdt         timestamp(3) null comment '上架开始时间',
   on_sale_endt         timestamp(3) null comment '上架结束时间',
   is_virtual           tinyint unsigned not null comment '是否为虚拟物品',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_product comment '产品';

/*==============================================================*/
/* Index: ui_prd_store_product                                  */
/*==============================================================*/
create unique index ui_prd_store_product on t_prd_product
(
   store_code,
   product_code
);

/*==============================================================*/
/* Table: t_prd_product_base_attr                               */
/*==============================================================*/
create table t_prd_product_base_attr
(
   id                   bigint unsigned not null comment '主键',
   product_id           bigint unsigned not null comment '产品主键',
   attr_name_id         bigint unsigned not null comment '属性名主键',
   attr_value_id        bigint unsigned not null comment '属性值主键',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_product_base_attr comment '产品基本属性表';

/*==============================================================*/
/* Table: t_prd_product_sku_attr                                */
/*==============================================================*/
create table t_prd_product_sku_attr
(
   id                   bigint unsigned not null comment '主键',
   sku_id               bigint unsigned not null comment '产品SKU主键',
   attr_name_id         bigint unsigned not null comment '属性名主键',
   attr_value_id        bigint unsigned not null comment '属性值主键',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_product_sku_attr comment '产品SKU属性';

/*==============================================================*/
/* Index: ui_prd_sku_attr                                       */
/*==============================================================*/
create unique index ui_prd_sku_attr on t_prd_product_sku_attr
(
   sku_id,
   attr_name_id
);

/*==============================================================*/
/* Table: t_prd_sku                                             */
/*==============================================================*/
create table t_prd_sku
(
   id                   bigint unsigned not null comment '主键',
   product_id           bigint unsigned not null comment '产品主键',
   sku_code             varchar(50) not null comment 'SKU编号',
   sku_name             varchar(200) comment 'SKU名称',
   market_price         decimal(15,4) comment '市场价',
   sale_price           decimal(15,4) comment '销售价',
   is_special           decimal(15,4) not null comment '是否有特价',
   width                smallint unsigned comment '宽度',
   depth                smallint unsigned comment '深度',
   heigh                smallint unsigned comment '高度',
   length_unit          tinyint unsigned comment '长度单位',
   weight               smallint unsigned not null comment '重量',
   weight_unit          tinyint unsigned not null comment '重量单位',
   SKU备注                varchar(200) comment 'SKU备注',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_sku comment '产品SKU';

/*==============================================================*/
/* Table: t_prd_small_number_sequence                           */
/*==============================================================*/
create table t_prd_small_number_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_prd_small_number_sequence comment '商品模块非流水类数据唯一ID生成表';

/*==============================================================*/
/* Table: t_prd_stock                                           */
/*==============================================================*/
create table t_prd_stock
(
   id                   bigint unsigned not null comment '主键',
   sku_id               bigint unsigned not null comment '产品SKU主键',
   warehouse_code       varchar(50) comment '仓库编号',
   stock_item_code      varchar(50) comment '仓库货物编号',
   stock_quantity       integer not null comment '库存数量',
   stock_memo           varchar(200) comment '备注',
   stock_operation      tinyint unsigned not null comment '库存操作类型',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_stock comment '库存';

/*==============================================================*/
/* Table: t_prd_supplier                                        */
/*==============================================================*/
create table t_prd_supplier
(
   id                   bigint unsigned not null comment '主键',
   supplier_code        varchar(50) not null comment '供应商编号',
   supplier_name        varchar(200) not null comment '供应商名称',
   store_code           varchar(50) not null comment '商户编号',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Index: ui_svp_supplier_code                                  */
/*==============================================================*/
create unique index ui_svp_supplier_code on t_prd_supplier
(
   supplier_code
);
