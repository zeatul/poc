drop index ui_d_a_1 on t_dic_application;

drop table if exists t_dic_application;

drop index ui_d_t_a_1 on t_dic_application_table;

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

drop table if exists t_dic_pair;

drop table if exists t_dic_schema;

drop table if exists t_dic_schema_application;

drop index ui_dt_1 on t_dic_table;

drop table if exists t_dic_table;

/*==============================================================*/
/* Table: t_dic_application                                     */
/*==============================================================*/
create table t_dic_application
(
   object_id            varchar(50) not null comment '对象ID',
   name                 varchar(50) comment '对象名称',
   "desc"               varchar(50) comment '对象描述',
   primary key (object_id)
)
engine=myisam default charset=utf8;

alter table t_dic_application comment '应用';

/*==============================================================*/
/* Index: ui_d_a_1                                              */
/*==============================================================*/
create unique index ui_d_a_1 on t_dic_application
(
   name
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
engine=myisam default charset=utf8;

alter table t_dic_application_table comment '应用拥有的表';

/*==============================================================*/
/* Index: ui_d_t_a_1                                            */
/*==============================================================*/
create unique index ui_d_t_a_1 on t_dic_application_table
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
   primary key (object_id)
)
engine=myisam default charset=utf8;

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
   use_type             varchar(50) comment '用途类型',
   data_type            varchar(50) comment '数据类型',
   name                 varchar(50) comment '名称',
   "desc"               varchar(50) comment '描述',
   display_name         varchar(50) comment '显示名称',
   regex                varchar(50) comment '正则',
   max_length           int comment '最大长度',
   min_length           int comment '最小长度',
   max_value            varchar(50) comment '最大值',
   min_value            varchar(50) comment '最小值',
   primary key (object_id)
)
engine=myisam default charset=utf8;

/*==============================================================*/
/* Index: ui_dd_1                                               */
/*==============================================================*/
create unique index ui_dd_1 on t_dic_data_definition
(
   name
);

/*==============================================================*/
/* Table: t_dic_fk                                              */
/*==============================================================*/
create table t_dic_fk
(
   object_id            varchar(50) not null comment '主键对象ID',
   name                 varchar(50) comment '外键名称',
   "desc"               varchar(50) comment '外键描述',
   parent_table_object_id varchar(50) comment '主表对象ID',
   child_table_object_id varchar(50) comment '子表对象ID',
   primary key (object_id)
)
engine=myisam default charset=utf8;

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
engine=myisam default charset=utf8;

alter table t_dic_fk_map comment '外键字段匹配';

/*==============================================================*/
/* Table: t_dic_index                                           */
/*==============================================================*/
create table t_dic_index
(
   object_id            varchar(50) not null comment '对象ID',
   table_object_id      varchar(50) comment '表对象ID',
   name                 varchar(50) comment '名称',
   "desc"               varchar(50) comment '描述',
   unique               int comment '是唯一索引',
   pk                   int comment '是主键',
   primary key (object_id)
)
engine=myisam default charset=utf8;

alter table t_dic_index comment '索引';

/*==============================================================*/
/* Index: ui_di_1                                               */
/*==============================================================*/
create unique index ui_di_1 on t_dic_index
(
   name
);

/*==============================================================*/
/* Table: t_dic_index_column                                    */
/*==============================================================*/
create table t_dic_index_column
(
   index_object_id      varchar(50) not null comment '索引对象ID',
   column_object_id     varchar(50) not null comment '表字段对象ID',
   primary key (index_object_id, column_object_id)
);

alter table t_dic_index_column comment '索引字段';

/*==============================================================*/
/* Table: t_dic_pair                                            */
/*==============================================================*/
create table t_dic_pair
(
   data_definition_object_id varchar(50) not null comment '对象id',
   key                  varchar(50) not null comment 'key',
   value                varchar(50) comment 'value',
   primary key (data_definition_object_id, key)
)
engine=myisam default charset=utf8;

alter table t_dic_pair comment '对值(Map<Key,Value>';

/*==============================================================*/
/* Table: t_dic_schema                                          */
/*==============================================================*/
create table t_dic_schema
(
   object_id            varchar(50) not null comment '主键ID',
   name                 varchar(50) comment '名称',
   "desc"               varchar(50) comment '描述',
   primary key (object_id)
)
engine=myisam default charset=utf8;

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
engine=myisam default charset=utf8;

alter table t_dic_schema_application comment 'schema拥有的应用';

/*==============================================================*/
/* Table: t_dic_table                                           */
/*==============================================================*/
create table t_dic_table
(
   object_id            varchar(50) not null comment '对象ID',
   name                 varchar(50) not null comment '名称',
   "desc"               varchar(50) comment '描述',
   primary key (object_id)
)
engine=myisam default charset=utf8;

alter table t_dic_table comment '表对象';

/*==============================================================*/
/* Index: ui_dt_1                                               */
/*==============================================================*/
create unique index ui_dt_1 on t_dic_table
(
   name
);

alter table t_dic_application_table add constraint fk_reference_8 foreign key (application_object_id)
      references t_dic_application (object_id) on delete restrict on update restrict;

alter table t_dic_application_table add constraint fk_reference_9 foreign key (table_object_id)
      references t_dic_table (object_id) on delete restrict on update restrict;

alter table t_dic_column add constraint fk_reference_2 foreign key (table_object_id)
      references t_dic_table (object_id) on delete restrict on update restrict;

alter table t_dic_column add constraint fk_reference_3 foreign key (data_definition_object_id)
      references t_dic_data_definition (object_id) on delete restrict on update restrict;

alter table t_dic_fk_map add constraint fk_reference_7 foreign key (fk_object_id)
      references t_dic_fk (object_id) on delete restrict on update restrict;

alter table t_dic_index add constraint fk_reference_4 foreign key (table_object_id)
      references t_dic_table (object_id) on delete restrict on update restrict;

alter table t_dic_index_column add constraint fk_reference_5 foreign key (index_object_id)
      references t_dic_index (object_id) on delete restrict on update restrict;

alter table t_dic_index_column add constraint fk_reference_6 foreign key (column_object_id)
      references t_dic_column (object_id) on delete restrict on update restrict;

alter table t_dic_pair add constraint fk_reference_1 foreign key (data_definition_object_id)
      references t_dic_data_definition (object_id) on delete restrict on update restrict;

alter table t_dic_schema_application add constraint fk_reference_10 foreign key (schema_object_id)
      references t_dic_schema (object_id) on delete restrict on update restrict;

alter table t_dic_schema_application add constraint fk_reference_11 foreign key (application_object_id)
      references t_dic_application (object_id) on delete restrict on update restrict;
