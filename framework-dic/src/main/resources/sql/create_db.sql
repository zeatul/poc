drop index ui_d_a_1 on t_dic_application;

drop table if exists t_dic_application;

drop table if exists t_dic_application_table;

drop index ui_dc_1 on t_dic_column;

drop table if exists t_dic_column;

drop index ui_dd_1 on t_dic_data_definition;

drop table if exists t_dic_data_definition;

drop index ui_dic_fk_1 on t_dic_fk;

drop table if exists t_dic_fk;

drop table if exists t_dic_fk_map;

drop index ui_di_1 on t_dic_index;

drop table if exists t_dic_index;

drop table if exists t_dic_index_column;

drop table if exists t_dic_schema;

drop table if exists t_dic_schema_application;

drop index UI_DT_1 on t_dic_table;

drop table if exists t_dic_table;

drop index i_test_2 on t_dic_test;

drop index ui_test_1 on t_dic_test;

drop table if exists t_dic_test;

/*==============================================================*/
/* Table: t_dic_application                                     */
/*==============================================================*/
create table t_dic_application
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) comment '应用编码',
   object_name          varchar(50) comment '应用名称',
   object_comment       varchar(50) comment '应用描述',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_application comment '应用,一个应用一个package,对应很多表.';

/*==============================================================*/
/* Index: ui_d_a_1                                              */
/*==============================================================*/
create unique index ui_d_a_1 on t_dic_application
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_application_table                               */
/*==============================================================*/
create table t_dic_application_table
(
   application_object_id varchar(50) not null comment '应用对象ID',
   table_object_id      varchar(50) not null comment '表对象ID',
   primary key (application_object_id, table_object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_application_table comment '应用拥有的表';

/*==============================================================*/
/* Table: t_dic_column                                          */
/*==============================================================*/
create table t_dic_column
(
   object_id            varchar(50) not null comment '对象ID',
   table_object_id      varchar(50) not null comment '表对象ID',
   data_definition_object_id varchar(50) not null comment '引用的数据类型ID',
   object_code          varchar(50) comment '数据库字段编码（为空，则用数据字典名）',
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
/* Index: ui_dc_1                                               */
/*==============================================================*/
create unique index ui_dc_1 on t_dic_column
(
   table_object_id,
   data_definition_object_id
);

/*==============================================================*/
/* Table: t_dic_data_definition                                 */
/*==============================================================*/
create table t_dic_data_definition
(
   object_id            varchar(50) not null comment '对象ID',
   use_type             int comment '用途类型',
   data_type            varchar(50) comment '数据类型',
   object_code          varchar(50) comment '标签',
   object_name          varchar(50) comment '中文名',
   object_comment       varchar(2000) comment '描述',
   object_display_name  varchar(50) comment '显示名称',
   regex                varchar(50) comment '正则',
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
/* Index: ui_dd_1                                               */
/*==============================================================*/
create unique index ui_dd_1 on t_dic_data_definition
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_fk                                              */
/*==============================================================*/
create table t_dic_fk
(
   object_id            varchar(50) not null comment '主键对象ID',
   object_code          varchar(50) comment '外键编码',
   object_name          varchar(50) comment '外键名称',
   object_comment       varchar(50) comment '外键描述',
   parent_table_object_id varchar(50) comment '主表对象ID',
   child_table_object_id varchar(50) comment '子表对象ID',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_fk comment '外键对象';

/*==============================================================*/
/* Index: ui_dic_fk_1                                           */
/*==============================================================*/
create index ui_dic_fk_1 on t_dic_fk
(
   parent_table_object_id,
   child_table_object_id
);

/*==============================================================*/
/* Table: t_dic_fk_map                                          */
/*==============================================================*/
create table t_dic_fk_map
(
   fk_object_id         varchar(50) not null comment '外键对象ID',
   parent_column_object_id varchar(50) not null comment '主表字段对象ID',
   child_column_object_id varchar(50) not null comment '主表字段对象ID',
   primary key (fk_object_id, parent_column_object_id, child_column_object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_fk_map comment '外键字段匹配';

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
   index_object_id      varchar(50) not null comment '索引对象ID',
   column_object_id     varchar(50) not null comment '表字段对象ID',
   object_order         integer comment '字段在索引的序号',
   primary key (index_object_id, column_object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_index_column comment '索引字段';

/*==============================================================*/
/* Table: t_dic_schema                                          */
/*==============================================================*/
create table t_dic_schema
(
   object_id            varchar(50) not null comment '主键ID',
   object_label         varchar(50) comment '数据库编码',
   object_name          varchar(50) comment '数据库名称',
   object_comment       varchar(50) comment '数据库描述',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_schema comment 'schema代表一个库，一个库可以有很多应用';

/*==============================================================*/
/* Table: t_dic_schema_application                              */
/*==============================================================*/
create table t_dic_schema_application
(
   application_object_id varchar(50) not null,
   schema_object_id     varchar(50) not null,
   primary key (application_object_id, schema_object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_schema_application comment 'schema拥有的应用';

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
/* Index: UI_DT_1                                               */
/*==============================================================*/
create unique index UI_DT_1 on t_dic_table
(
   object_code
);

/*==============================================================*/
/* Table: t_dic_test                                            */
/*==============================================================*/
create table t_dic_test
(
   col1                 varchar(50) not null comment 'col1',
   col2                 varchar(50) not null comment 'col2',
   col3                 timestamp(3) not null comment 'col3',
   col4                 numeric(10,5) not null comment 'col4',
   col5                 decimal(10,5) comment 'col5',
   primary key (col1, col2)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_test comment '测试表的元数据使用,包括字段类型，索引，外键';

/*==============================================================*/
/* Index: ui_test_1                                             */
/*==============================================================*/
create unique index ui_test_1 on t_dic_test
(
   col2,
   col3,
   col4
);

/*==============================================================*/
/* Index: i_test_2                                              */
/*==============================================================*/
create index i_test_2 on t_dic_test
(
   col3,
   col4,
   col5
);
