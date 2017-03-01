drop index ui_dic_application_code on t_dic_application;

drop table if exists t_dic_application;

drop index ui_dic_applicatin_table on t_dic_application_table;

drop table if exists t_dic_application_table;

drop index ui_dic_col_code on t_dic_column;

drop table if exists t_dic_column;

drop index ui_dic_data_def_code on t_dic_data_definition;

drop table if exists t_dic_data_definition;

drop index ui_dic_fk_code on t_dic_fk;

drop index ui_dic_fk_table on t_dic_fk;

drop table if exists t_dic_fk;

drop index ui_dic_fk on t_dic_fk_map;

drop table if exists t_dic_fk_map;

drop table if exists t_dic_history;

drop index ui_di_1 on t_dic_index;

drop table if exists t_dic_index;

drop index ui_dic_index_column on t_dic_index_column;

drop table if exists t_dic_index_column;

drop index i_dic_module_search on t_dic_module;

drop index ui_dic_module_id on t_dic_module;

drop index ui_dic_module_code on t_dic_module;

drop table if exists t_dic_module;

drop index ui_dic_table_code on t_dic_table;

drop table if exists t_dic_table;

/*==============================================================*/
/* Table: t_dic_application                                     */
/*==============================================================*/
create table t_dic_application
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '应用编码',
   object_name          varchar(50) comment '应用名称',
   object_comment       varchar(50) comment '应用描述',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_application comment '应用,一个应用一个package,对应很多表.';

/*==============================================================*/
/* Index: ui_dic_application_code                               */
/*==============================================================*/
create unique index ui_dic_application_code on t_dic_application
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_application_table                               */
/*==============================================================*/
create table t_dic_application_table
(
   object_id            varchar(50) not null comment '主键',
   application_object_id varchar(50) not null comment '应用对象ID',
   table_object_id      varchar(50) not null comment '表对象ID',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_application_table comment '应用拥有的表';

/*==============================================================*/
/* Index: ui_dic_applicatin_table                               */
/*==============================================================*/
create index ui_dic_applicatin_table on t_dic_application_table
(
   application_object_id,
   table_object_id
);

/*==============================================================*/
/* Table: t_dic_column                                          */
/*==============================================================*/
create table t_dic_column
(
   object_id            varchar(50) not null comment '对象ID',
   table_object_id      varchar(50) not null comment '表对象ID',
   data_definition_object_id varchar(50) not null comment '引用的数据类型ID',
   object_code          varchar(50) not null comment '数据库字段编码（为空，则用数据字典名）',
   object_name          varchar(50) comment '数据库字段名称',
   object_comment       varchar(50) comment '数据库字段描述',
   object_order         integer comment '数据库字段在表的序号',
   nullable             integer comment '可否为空(1/0)',
   is_pk                integer comment '是否为主键(1/0)',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_column comment '表字段对象';

/*==============================================================*/
/* Index: ui_dic_col_code                                       */
/*==============================================================*/
create unique index ui_dic_col_code on t_dic_column
(
   table_object_id,
   object_code
);

/*==============================================================*/
/* Table: t_dic_data_definition                                 */
/*==============================================================*/
create table t_dic_data_definition
(
   object_id            varchar(50) not null comment '对象ID',
   use_type             varchar(50) comment '用途类型',
   data_type            varchar(50) comment '数据类型',
   object_code          varchar(50) comment '编码',
   object_name          varchar(50) comment '中文名',
   object_comment       varchar(2000) comment '描述',
   object_display_name  varchar(50) comment '显示名称',
   regex                varchar(50) comment '正则表达式',
   char_max_length      int comment '最大长度',
   char_min_length      int comment '最小长度',
   max_value            varchar(50) comment '最大值',
   min_value            varchar(50) comment '最小值',
   datetime_precision   int comment '时间精度',
   numeric_precision    int comment '数据精度',
   numeric_scale        int comment '数据小数精度',
   is_enum              int comment '是否枚举(yes/no)',
   enum_key             varchar(500) comment '枚举值',
   enum_value           varchar(2000) comment '枚举显示值',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_data_definition comment '数据字典定义';

/*==============================================================*/
/* Index: ui_dic_data_def_code                                  */
/*==============================================================*/
create unique index ui_dic_data_def_code on t_dic_data_definition
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_fk                                              */
/*==============================================================*/
create table t_dic_fk
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '外键编码',
   object_name          varchar(50) comment '外键名称',
   object_comment       varchar(50) comment '外键描述',
   parent_table_object_id varchar(50) not null comment '主表对象ID',
   child_table_object_id varchar(50) not null comment '子表对象ID',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_fk comment '外键对象';

/*==============================================================*/
/* Index: ui_dic_fk_table                                       */
/*==============================================================*/
create unique index ui_dic_fk_table on t_dic_fk
(
   parent_table_object_id,
   child_table_object_id
);

/*==============================================================*/
/* Index: ui_dic_fk_code                                        */
/*==============================================================*/
create unique index ui_dic_fk_code on t_dic_fk
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_fk_map                                          */
/*==============================================================*/
create table t_dic_fk_map
(
   object_id            varchar(50) not null comment '主键',
   fk_object_id         varchar(50) not null comment '外键对象ID',
   parent_column_object_id varchar(50) not null comment '主表字段对象ID',
   child_column_object_id varchar(50) not null comment '主表字段对象ID',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_fk_map comment '外键字段匹配';

/*==============================================================*/
/* Index: ui_dic_fk                                             */
/*==============================================================*/
create unique index ui_dic_fk on t_dic_fk_map
(
   fk_object_id,
   parent_column_object_id,
   child_column_object_id
);

/*==============================================================*/
/* Table: t_dic_history                                         */
/*==============================================================*/
create table t_dic_history
(
   table_name           varchar(50) not null comment '数据字典的表的名称',
   version              integer not null comment '版本号',
   object_id            varchar(50) not null comment '记录主键',
   object_content       text not null comment '记录(json格式)',
   create_date          timestamp(3) null not null comment '版本创建日期',
   primary key (table_name, version, object_id)
);

alter table t_dic_history comment '一张表缓存所有的数据字典，用来保留历史版本';

/*==============================================================*/
/* Table: t_dic_index                                           */
/*==============================================================*/
create table t_dic_index
(
   object_id            varchar(50) not null comment '对象ID',
   table_object_id      varchar(50) comment '表对象ID',
   object_code          varchar(50) comment '索引编码',
   object_name          varchar(50) comment '索引名称',
   object_comment       varchar(50) comment '索引描述',
   is_unique            int comment '是唯一索引',
   is_pk                int comment '是主键',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_index comment '索引';

/*==============================================================*/
/* Index: ui_di_1                                               */
/*==============================================================*/
create unique index ui_di_1 on t_dic_index
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_index_column                                    */
/*==============================================================*/
create table t_dic_index_column
(
   object_id            varchar(50) not null comment '主键',
   index_object_id      varchar(50) not null comment '索引对象ID',
   column_object_id     varchar(50) not null comment '表字段对象ID',
   object_order         integer comment '字段在索引的序号',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_index_column comment '索引字段';

/*==============================================================*/
/* Index: ui_dic_index_column                                   */
/*==============================================================*/
create unique index ui_dic_index_column on t_dic_index_column
(
   index_object_id,
   column_object_id
);

/*==============================================================*/
/* Table: t_dic_module                                          */
/*==============================================================*/
create table t_dic_module
(
   object_id            varchar(50) not null comment '主键',
   object_code          varchar(50) not null comment '编码',
   object_name          varchar(200) comment '名称',
   object_comment       varchar(1000) comment '描述',
   create_date          timestamp(3) null comment '创建时间',
   update_date          timestamp(3) null comment '更新时间',
   delete_date          timestamp(3) null comment '删除时间',
   id                   varchar(50) not null comment '长整型主键',
   code                 varchar(50) not null comment '编码',
   name                 varchar(200) comment '名称',
   comment              varchar(1000) comment '描述',
   uuid                 varchar(50) comment 'uuid主键',
   password             varchar(500) comment '密码',
   version              integer comment '版本号',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_module comment '测试表的元数据使用,包括字段类型,索引,外键,不维护数据';

/*==============================================================*/
/* Index: ui_dic_module_code                                    */
/*==============================================================*/
create unique index ui_dic_module_code on t_dic_module
(
   object_code
);

/*==============================================================*/
/* Index: ui_dic_module_id                                      */
/*==============================================================*/
create unique index ui_dic_module_id on t_dic_module
(
   id
);

/*==============================================================*/
/* Index: i_dic_module_search                                   */
/*==============================================================*/
create index i_dic_module_search on t_dic_module
(
   object_name,
   create_date,
   update_date,
   delete_date,
   id
);

/*==============================================================*/
/* Table: t_dic_table                                           */
/*==============================================================*/
create table t_dic_table
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '表的编码',
   object_name          varchar(50) comment '表的名称',
   object_comment       varchar(50) comment '表的描述',
   object_type          varchar(50) comment '表的类型',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_table comment '表对象';

/*==============================================================*/
/* Index: ui_dic_table_code                                     */
/*==============================================================*/
create unique index ui_dic_table_code on t_dic_table
(
   object_code
);
