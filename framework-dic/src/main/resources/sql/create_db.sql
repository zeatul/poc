drop index ui_dic_application_code on t_dic_application;

drop table if exists t_dic_application;

drop index ui_dic_applicatin_table on t_dic_application_table;

drop table if exists t_dic_application_table;

drop index ui_dic_col_code on t_dic_column;

drop table if exists t_dic_column;

drop index ui_dic_fk_code on t_dic_fk;

drop index ui_dic_fk_table on t_dic_fk;

drop table if exists t_dic_fk;

drop index ui_dic_fk on t_dic_fk_map;

drop table if exists t_dic_fk_map;

drop index ui_di_1 on t_dic_index;

drop table if exists t_dic_index;

drop index ui_dic_index_column on t_dic_index_column;

drop table if exists t_dic_index_column;

drop index i_dic_module_search on t_dic_model;

drop index ui_dic_module_id on t_dic_model;

drop index ui_dic_module_code on t_dic_model;

drop table if exists t_dic_model;

drop index ui_synonym on t_dic_synonym;

drop table if exists t_dic_synonym;

drop index ui_dic_table_code on t_dic_table;

drop table if exists t_dic_table;

drop index ui_dic_data_def_code on t_dic_word;

drop table if exists t_dic_word;

/*==============================================================*/
/* Table: t_dic_application                                     */
/*==============================================================*/
create table t_dic_application
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '编码',
   object_name          varchar(50) comment '名称',
   object_comment       varchar(1024) comment '描述',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_application comment '应用,一个应用一个package,对应很多表.';

/*==============================================================*/
/* Index: ui_dic_application_code                               */
/*==============================================================*/
create unique index ui_dic_application_code on t_dic_application
(
   object_code,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_application_table                               */
/*==============================================================*/
create table t_dic_application_table
(
   object_id            varchar(50) not null comment '对象ID',
   application_object_id varchar(50) not null comment '应用对象ID',
   table_object_id      varchar(50) not null comment '表对象ID',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
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
   table_object_id,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_column                                          */
/*==============================================================*/
create table t_dic_column
(
   object_id            varchar(50) not null comment '对象ID',
   table_object_id      varchar(50) not null comment '表对象ID',
   word_object_id       varchar(50) not null comment '引用的数据类型ID',
   object_code          varchar(50) not null comment '编码',
   object_name          varchar(1000) comment '名称',
   object_comment       varchar(1024) comment '描述',
   object_order         integer comment '序号',
   nullable             integer comment '可否为空(1/0)',
   is_pk                integer comment '是否为主键(1/0)',
   operators            varchar(1000) comment '需要支持的运算符，等于默认支持',
   system_code          varchar(2000) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
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
   object_code,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_fk                                              */
/*==============================================================*/
create table t_dic_fk
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '编码',
   object_name          varchar(200) comment '名称',
   object_comment       varchar(1024) comment '描述',
   parent_table_object_id varchar(50) not null comment '主表对象ID',
   child_table_object_id varchar(50) not null comment '子表对象ID',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
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
   child_table_object_id,
   system_code,
   version
);

/*==============================================================*/
/* Index: ui_dic_fk_code                                        */
/*==============================================================*/
create unique index ui_dic_fk_code on t_dic_fk
(
   object_code,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_fk_map                                          */
/*==============================================================*/
create table t_dic_fk_map
(
   object_id            varchar(50) not null comment '对象ID',
   fk_object_id         varchar(50) not null comment '外键对象ID',
   parent_column_object_id varchar(50) not null comment '主表字段对象ID',
   child_column_object_id varchar(50) not null comment '子表字段对象ID',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
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
   child_column_object_id,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_index                                           */
/*==============================================================*/
create table t_dic_index
(
   object_id            varchar(50) not null comment '对象ID',
   table_object_id      varchar(50) comment '表对象ID',
   object_code          varchar(50) comment '编码',
   object_name          varchar(200) comment '名称',
   object_comment       varchar(1024) comment '描述',
   is_unique            integer comment '是唯一索引',
   is_pk                integer comment '是否为主键(1/0)',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_index comment '索引';

/*==============================================================*/
/* Index: ui_di_1                                               */
/*==============================================================*/
create unique index ui_di_1 on t_dic_index
(
   object_code,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_index_column                                    */
/*==============================================================*/
create table t_dic_index_column
(
   object_id            varchar(50) not null comment '对象ID',
   index_object_id      varchar(50) not null comment '索引对象ID',
   column_object_id     varchar(50) not null comment '字段对象ID',
   object_order         integer comment '序号',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
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
   column_object_id,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_model                                           */
/*==============================================================*/
create table t_dic_model
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '编码',
   object_name          varchar(200) comment '名称',
   object_comment       varchar(1024) comment '描述',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   id                   bigint not null comment '长整型主键',
   spell_abbr           varchar(50) comment '拼首',
   price                numeric(10,2) comment '价格',
   store_code           varchar(50) comment '商户编号',
   order_number         varchar(50) comment '订单编号',
   user_code            varchar(50) not null comment '用户编号',
   mobile_number        varchar(20) not null comment '手机号',
   exec_times           integer comment '已经执行次数',
   max_exec_times       integer comment '最大允许执行次数',
   last_exec_err_code   varchar(50) comment '最后一次执行错误代码',
   last_exec_err_msg    varchar(1000) comment '最后一次执行错误原因',
   last_exec_date       timestamp(3) null comment '最后一次执行时间',
   schedule_exec_date   timestamp(3) null comment '计划执行时间',
   current_exec_computer varchar(200) comment '当前执行机器',
   current_exec_process_id varchar(50) comment '当前执行进程ID',
   current_exec_start_date timestamp(3) null comment '当前任务启动时间',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_model comment '测试表的元数据使用,包括字段类型,索引,外键,不维护数据';

/*==============================================================*/
/* Index: ui_dic_module_code                                    */
/*==============================================================*/
create unique index ui_dic_module_code on t_dic_model
(
   object_code
);

/*==============================================================*/
/* Index: ui_dic_module_id                                      */
/*==============================================================*/
create unique index ui_dic_module_id on t_dic_model
(
   id
);

/*==============================================================*/
/* Index: i_dic_module_search                                   */
/*==============================================================*/
create index i_dic_module_search on t_dic_model
(
   object_name,
   create_date,
   update_date,
   delete_date,
   id
);

/*==============================================================*/
/* Table: t_dic_synonym                                         */
/*==============================================================*/
create table t_dic_synonym
(
   object_id            varchar(50) not null comment '对象ID',
   origin_object_code   varchar(50) comment '原单词编码',
   synonym_object_code  varchar(50) comment '同义词编码',
   synonym_display_name varchar(200) comment '同义词显示名称',
   synonym_type         varchar(50) comment '同义词类型',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_synonym comment '同义词定义表';

/*==============================================================*/
/* Index: ui_synonym                                            */
/*==============================================================*/
create unique index ui_synonym on t_dic_synonym
(
   origin_object_code,
   synonym_object_code,
   synonym_type,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_table                                           */
/*==============================================================*/
create table t_dic_table
(
   object_id            varchar(50) not null comment '对象ID',
   object_code          varchar(50) not null comment '编码',
   object_name          varchar(200) comment '名称',
   object_comment       varchar(1024) comment '描述',
   object_type          varchar(50) comment '类型',
   physical_option      varchar(2000) comment '表的物理特性',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_table comment '表对象';

/*==============================================================*/
/* Index: ui_dic_table_code                                     */
/*==============================================================*/
create unique index ui_dic_table_code on t_dic_table
(
   object_code,
   system_code,
   version
);

/*==============================================================*/
/* Table: t_dic_word                                            */
/*==============================================================*/
create table t_dic_word
(
   object_id            varchar(50) not null comment '对象ID',
   use_type             varchar(50) comment '用途类型',
   data_type            varchar(50) comment '数据类型',
   object_code          varchar(50) comment '编码',
   object_name          varchar(200) comment '名称',
   object_comment       varchar(1024) comment '描述',
   object_display_name  varchar(200) comment '显示名称',
   regex                varchar(1000) comment '正则表达式',
   char_max_length      integer comment '最大长度',
   char_min_length      integer comment '最小长度',
   is_only_ascii        integer comment '是否有超过1个byte长度的的字符',
   max_value            varchar(200) comment '最大值',
   min_value            varchar(200) comment '最小值',
   datetime_precision   integer comment '时间精度',
   numeric_precision    integer comment '数据精度',
   numeric_scale        integer comment '数据小数精度',
   is_enum              integer comment '是否枚举(yes/no)',
   enum_key             varchar(2000) comment '枚举值',
   enum_value           varchar(2000) comment '枚举显示值',
   system_code          varchar(50) comment '系统编码(区分不同项目，不同集团)',
   version              integer comment '版本号',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_dic_word comment '数据字典定义';

/*==============================================================*/
/* Index: ui_dic_data_def_code                                  */
/*==============================================================*/
create unique index ui_dic_data_def_code on t_dic_word
(
   object_code,
   system_code,
   version
);
