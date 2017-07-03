drop index ui_prd_attr_name on t_prd_attr_name;

drop index i_prd_attr_name_pvid on t_prd_attr_name;

drop index i_prd_attr_name_pid on t_prd_attr_name;

drop table if exists t_prd_attr_name;

drop index ui_prd_attr_value on t_prd_attr_value;

drop table if exists t_prd_attr_value;

drop table if exists t_prd_brand;

drop index ui_prd_category_name on t_prd_category;

drop index ui_prd_category_code on t_prd_category;

drop index ui_prd_category_id_path on t_prd_category;

drop table if exists t_prd_category;

drop index ui_prd_category_brand on t_prd_category_brand_map;

drop table if exists t_prd_category_brand_map;

drop index ui_prd_c_s_m on t_prd_category_supplier_map;

drop table if exists t_prd_category_supplier_map;

drop table if exists t_prd_pic;

drop index ui_prd_store_prod_attr on t_prd_product;

drop index ui_prd_store_product on t_prd_product;

drop table if exists t_prd_product;

drop index i_prd_attr on t_prd_product_attr;

drop table if exists t_prd_product_attr;

drop table if exists t_prd_product_history;

drop index ui_prd_sku_attr_ids on t_prd_sku;

drop index ui_prd_sku_st_sku_code on t_prd_sku;

drop index I_prd_prd_id on t_prd_sku;

drop table if exists t_prd_sku;

drop table if exists t_prd_sku_history;

drop index ui_prd_snapshoot on t_prd_sku_snapshoot;

drop table if exists t_prd_sku_snapshoot;

drop table if exists t_prd_small_number_sequence;

drop table if exists t_prd_stock;

drop table if exists t_prd_stock_history;

drop index ui_svp_supplier_code on t_prd_supplier;

drop table if exists t_prd_supplier;

/*==============================================================*/
/* Table: t_prd_attr_name                                       */
/*==============================================================*/
create table t_prd_attr_name
(
   id                   integer unsigned not null comment '主键',
   store_code           varchar(50) comment '商户编号',
   category_id          integer unsigned not null comment '产品目录主键',
   pid                  integer unsigned not null comment '父属性名主键',
   pvid                 integer unsigned not null comment '父属性值主键',
   attr_name_business_type smallint unsigned not null comment '属性名业务功能分类(品牌,供应商,其它)',
   attr_value_type      tinyint unsigned not null comment '属性值类型',
   attr_name            varchar(200) not null comment '属性名名称',
   attr_name_status     tinyint unsigned not null comment '属性名状态',
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
/* Index: ui_prd_attr_name                                      */
/*==============================================================*/
create unique index ui_prd_attr_name on t_prd_attr_name
(
   category_id,
   attr_name,
   pid,
   pvid
);

/*==============================================================*/
/* Table: t_prd_attr_value                                      */
/*==============================================================*/
create table t_prd_attr_value
(
   id                   integer unsigned not null comment '主键',
   store_code           varchar(50) comment '商户编号',
   category_id          integer unsigned not null comment '产品目录主键',
   attr_name_id         integer unsigned not null comment '属性名主键',
   attr_value           varchar(200) not null comment '属性值',
   attr_display_value   varchar(500) comment '属性值显示名称',
   attr_display_en_value varchar(500) comment '属性值显示英文名称',
   attr_value_status    tinyint unsigned comment '属性值状态',
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
/* Index: ui_prd_attr_value                                     */
/*==============================================================*/
create unique index ui_prd_attr_value on t_prd_attr_value
(
   attr_name_id,
   attr_value
);

/*==============================================================*/
/* Table: t_prd_brand                                           */
/*==============================================================*/
create table t_prd_brand
(
   id                   integer unsigned not null comment '主键',
   brand_cname          varchar(200) not null comment '品牌中文名',
   brand_ename          varchar(200) not null comment '品牌英文名',
   brand_home_page      varchar(200) comment '品牌主页',
   brand_home_logo      varchar(200) comment '品牌logo',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_brand comment '产品品牌';

/*==============================================================*/
/* Table: t_prd_category                                        */
/*==============================================================*/
create table t_prd_category
(
   id                   integer unsigned not null comment '主键',
   pid                  integer unsigned not null comment '父ID',
   id_path              varchar(200) not null comment '主键PATH',
   object_order         integer unsigned not null comment '产品目录序号',
   depth                tinyint unsigned not null comment '产品目录深度',
   category_code        varchar(50) not null comment '产品目录编号',
   category_name        varchar(50) not null comment '产品目录名称',
   category_status      tinyint unsigned not null comment '产品目录状态',
   category_desc        varchar(200) comment '产品目录描述',
   category_logo        varchar(200) comment '产品目录logo',
   category_home_page   varchar(200) comment '产品目录主页',
   is_leaf              tinyint unsigned not null comment '是否为最终产品目录分类，最终产品目录分类才能有商品模板',
   category_variant_status tinyint unsigned not null comment '最终产品目录变式状态',
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
/* Index: ui_prd_category_name                                  */
/*==============================================================*/
create unique index ui_prd_category_name on t_prd_category
(
   pid,
   category_name
);

/*==============================================================*/
/* Table: t_prd_category_brand_map                              */
/*==============================================================*/
create table t_prd_category_brand_map
(
   id                   integer unsigned not null comment '主键',
   category_id          integer unsigned not null comment '产品目录主键',
   brand_id             integer unsigned not null comment '品牌主键',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_category_brand_map comment '品牌和产品目录的对应关系';

/*==============================================================*/
/* Index: ui_prd_category_brand                                 */
/*==============================================================*/
create unique index ui_prd_category_brand on t_prd_category_brand_map
(
   category_id,
   brand_id
);

/*==============================================================*/
/* Table: t_prd_category_supplier_map                           */
/*==============================================================*/
create table t_prd_category_supplier_map
(
   id                   integer unsigned not null comment '主键',
   category_id          integer unsigned not null comment '产品目录主键',
   supplier_id          integer unsigned not null comment '供应商主键',
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
   id                   integer unsigned not null comment '主键',
   sku_id               integer unsigned not null comment '产品SKU主键',
   product_id           integer unsigned not null comment '产品主键',
   store_code           varchar(50) not null comment '商户编号',
   pic_name             varchar(50) comment '图片名称',
   pic_memo             varchar(200) comment '图片备注',
   pic_outer_id         varchar(200) comment '图片外部主键',
   pic_url              varchar(200) comment '图片地址',
   pic_type             tinyint unsigned not null comment '图片类型',
   object_order         integer unsigned not null comment '图片序号',
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
   id                   integer unsigned not null comment '主键',
   category_id          integer unsigned not null comment '产品目录主键',
   store_code           varchar(50) not null comment '商户编号',
   product_code         varchar(50) not null comment '产品编号',
   product_name         varchar(200) not null comment '产品名称',
   product_key_attr_value_ids varchar(200) comment '产品关键属性值ID集合',
   product_key_attr_value_values varchar(200) comment '产品关键属性值集合',
   product_sku_attr_name_ids varchar(200) comment '产品SKU属性名ID集合',
   product_status       tinyint unsigned not null comment '产品状态',
   product_home_page    varchar(200) comment '产品主页',
   thumbnail            varchar(200) comment '缩略图',
   product_desc         varchar(1000) comment '产品描述',
   product_memo         varchar(200) comment '产品备注',
   product_min_price    decimal(15,4) comment '产品最低价格',
   product_max_price    decimal(15,4) comment '产品最高价格',
   on_sale_stdt         timestamp(3) null comment '上架开始时间',
   on_sale_endt         timestamp(3) null comment '上架结束时间',
   is_virtual           tinyint unsigned not null comment '是否为虚拟物品',
   delivery_type        smallint unsigned not null comment '交付方式',
   product_version      smallint unsigned not null comment ' 产品版本号',
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
/* Index: ui_prd_store_prod_attr                                */
/*==============================================================*/
create unique index ui_prd_store_prod_attr on t_prd_product
(
   store_code,
   product_key_attr_value_ids
);

/*==============================================================*/
/* Table: t_prd_product_attr                                    */
/*==============================================================*/
create table t_prd_product_attr
(
   id                   integer unsigned not null comment '主键',
   product_id           integer unsigned not null comment '产品主键',
   sku_id               integer unsigned not null comment '产品SKU主键',
   attr_name_id         integer unsigned not null comment '属性名主键',
   attr_value_id        integer unsigned not null comment '属性值主键',
   attr_name_type       tinyint unsigned not null comment '属性类型（关键属性,销售属性,一般属性）',
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
/* Index: i_prd_attr                                            */
/*==============================================================*/
create index i_prd_attr on t_prd_product_attr
(
   product_id,
   sku_id,
   attr_name_id,
   attr_value_id
);

/*==============================================================*/
/* Table: t_prd_product_history                                 */
/*==============================================================*/
create table t_prd_product_history
(
   id                   integer unsigned not null comment '主键',
   category_id          integer unsigned not null comment '产品目录主键',
   store_code           varchar(50) not null comment '商户编号',
   product_code         varchar(50) not null comment '产品编号',
   product_name         varchar(200) not null comment '产品名称',
   product_key_attr_value_ids varchar(200) comment '产品关键属性值ID集合',
   product_key_attr_value_values varchar(200) comment '产品关键属性值集合',
   product_sku_attr_name_ids varchar(200) comment '产品SKU属性名ID集合',
   product_status       tinyint unsigned not null comment '产品状态',
   product_home_page    varchar(200) comment '产品主页',
   thumbnail            varchar(200) comment '缩略图',
   product_desc         varchar(1000) comment '产品描述',
   product_memo         varchar(200) comment '产品备注',
   product_min_price    decimal(15,4) comment '产品最低价格',
   product_max_price    decimal(15,4) comment '产品最高价格',
   on_sale_stdt         timestamp(3) null comment '上架开始时间',
   on_sale_endt         timestamp(3) null comment '上架结束时间',
   is_virtual           tinyint unsigned not null comment '是否为虚拟物品',
   delivery_type        smallint unsigned not null comment '交付方式',
   product_version      smallint unsigned not null comment '产品版本号',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id, product_version)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_product_history comment '产品';

/*==============================================================*/
/* Table: t_prd_sku                                             */
/*==============================================================*/
create table t_prd_sku
(
   id                   integer unsigned not null comment '主键',
   product_id           integer unsigned not null comment '产品主键',
   store_code           varchar(50) not null comment '商户编号',
   sku_code             varchar(50) not null comment 'SKU编号',
   sku_name             varchar(200) comment 'SKU名称',
   sku_attr_value_ids   varchar(200) comment 'SKU属性值ID集合',
   sku_attr_value_values varchar(1000) comment 'SKU属性值集合',
   product_key_attr_value_ids varchar(200) comment '产品关键属性值ID集合',
   product_key_attr_value_values varchar(200) comment '产品关键属性值集合',
   sku_status           tinyint unsigned not null comment 'SKU状态',
   thumbnail            varchar(200) comment '缩略图',
   market_price         decimal(15,4) comment '市场价',
   sale_price           decimal(15,4) comment '销售价',
   currency             smallint unsigned comment '币种',
   sku_stock_quantity   integer not null comment 'SKU库存数量',
   is_special_price     tinyint unsigned not null comment '是否有特价',
   width                integer unsigned comment '宽度',
   depth                integer unsigned comment '深度',
   height               integer unsigned comment '高度',
   length_unit          tinyint unsigned comment '长度单位',
   weight               integer unsigned not null comment '重量',
   weight_unit          tinyint unsigned not null comment '重量单位',
   sku_memo             varchar(200) comment 'SKU备注',
   sku_snapshoot_id     integer unsigned comment 'SKU快照ID',
   product_version      smallint unsigned not null comment '产品版本号',
   sku_version          smallint unsigned not null comment 'sku版本号',
   delivery_type        smallint unsigned not null comment '交付方式',
   stock_version        integer unsigned not null comment '库存版本号',
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
/* Index: I_prd_prd_id                                          */
/*==============================================================*/
create index I_prd_prd_id on t_prd_sku
(
   product_id
);

/*==============================================================*/
/* Index: ui_prd_sku_st_sku_code                                */
/*==============================================================*/
create unique index ui_prd_sku_st_sku_code on t_prd_sku
(
   store_code,
   sku_code
);

/*==============================================================*/
/* Index: ui_prd_sku_attr_ids                                   */
/*==============================================================*/
create unique index ui_prd_sku_attr_ids on t_prd_sku
(
   product_id,
   sku_attr_value_ids
);

/*==============================================================*/
/* Table: t_prd_sku_history                                     */
/*==============================================================*/
create table t_prd_sku_history
(
   id                   integer unsigned not null comment '主键',
   product_id           integer unsigned not null comment '产品主键',
   store_code           varchar(50) not null comment '商户编号',
   sku_code             varchar(50) not null comment 'SKU编号',
   sku_name             varchar(200) comment 'SKU名称',
   sku_attr_value_ids   varchar(200) comment 'SKU属性值ID集合',
   sku_attr_value_values varchar(1000) comment 'SKU属性值集合',
   product_key_attr_value_ids varchar(200) comment '产品关键属性值ID集合',
   product_key_attr_value_values varchar(200) comment '产品关键属性值集合',
   sku_status           tinyint unsigned not null comment 'SKU状态',
   thumbnail            varchar(200) comment '缩略图',
   market_price         decimal(15,4) comment '市场价',
   sale_price           decimal(15,4) comment '销售价',
   sku_stock_quantity   integer not null comment 'SKU库存数量',
   is_special_price     tinyint unsigned not null comment '是否有特价',
   width                smallint unsigned comment '宽度',
   depth                smallint unsigned comment '深度',
   height               smallint unsigned comment '高度',
   length_unit          tinyint unsigned comment '长度单位',
   weight               smallint unsigned not null comment '重量',
   weight_unit          tinyint unsigned not null comment '重量单位',
   sku_memo             varchar(200) comment 'SKU备注',
   sku_snapshoot_id     integer unsigned comment 'SKU快照ID',
   product_version      smallint unsigned not null comment '产品版本号',
   sku_version          smallint unsigned not null comment 'sku版本号',
   stock_version        integer unsigned not null comment '库存版本号',
   delivery_type        smallint unsigned not null comment '交付方式',
   create_user_code     varchar(50) comment '创建者',
   create_date          timestamp(3) null comment '创建日期',
   update_user_code     varchar(50) comment '更新者',
   update_date          timestamp(3) null comment '更新日期',
   delete_user_code     varchar(50) comment '删除者',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id, sku_version)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_prd_sku_history comment '产品SKU';

/*==============================================================*/
/* Table: t_prd_sku_snapshoot                                   */
/*==============================================================*/
create table t_prd_sku_snapshoot
(
   id                   integer unsigned not null comment '主键',
   product_id           integer unsigned not null comment '产品主键',
   sku_id               integer unsigned not null comment '产品sku主键',
   product_code         varchar(50) comment '产品编号',
   product_name         varchar(200) comment '产品名称',
   product_key_attr_value_ids varchar(200) comment '产品关键属性值ID集合',
   product_key_attr_value_values varchar(200) comment '产品关键属性值集合',
   product_sku_attr_name_ids varchar(200) comment '产品SKU属性名ID集合',
   product_memo         varchar(200) comment '产品备注',
   store_code           varchar(50) comment '商户编号',
   sku_code             varchar(50) comment 'SKU编号',
   sku_name             varchar(200) comment 'SKU名称',
   sku_attr_value_ids   varchar(200) comment 'SKU属性值ID集合',
   sku_attr_value_values varchar(1000) comment 'SKU属性值集合',
   market_price         decimal(15,4) comment '市场价',
   sale_price           decimal(15,4) comment '销售价',
   thumbnail            varchar(200) comment '缩略图',
   sku_memo             varchar(200) comment 'SKU备注',
   product_version      smallint unsigned not null comment '产品版本号',
   sku_version          smallint unsigned not null comment 'sku版本号',
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
/* Index: ui_prd_snapshoot                                      */
/*==============================================================*/
create unique index ui_prd_snapshoot on t_prd_sku_snapshoot
(
   product_id,
   sku_id,
   product_version,
   sku_version
);

/*==============================================================*/
/* Table: t_prd_small_number_sequence                           */
/*==============================================================*/
create table t_prd_small_number_sequence
(
   stub                 char(1) comment 'stub',
   id                   integer not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_prd_small_number_sequence comment '商品模块非流水类数据唯一ID生成表';

/*==============================================================*/
/* Table: t_prd_stock                                           */
/*==============================================================*/
create table t_prd_stock
(
   id                   integer unsigned not null comment '主键',
   product_id           integer unsigned not null comment '产品主键',
   sku_id               integer unsigned not null comment '产品SKU主键',
   sku_name             varchar(200) comment 'SKU名称',
   store_code           varchar(50) not null comment '商户编号',
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
/* Table: t_prd_stock_history                                   */
/*==============================================================*/
create table t_prd_stock_history
(
   id                   integer unsigned not null comment '主键',
   product_id           integer unsigned not null comment '产品主键',
   sku_id               integer unsigned not null comment '产品SKU主键',
   store_code           varchar(50) not null comment '商户编号',
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

alter table t_prd_stock_history comment '库存';

/*==============================================================*/
/* Table: t_prd_supplier                                        */
/*==============================================================*/
create table t_prd_supplier
(
   id                   integer unsigned not null comment '主键',
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
