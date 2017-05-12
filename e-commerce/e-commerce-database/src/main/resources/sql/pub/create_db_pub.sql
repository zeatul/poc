drop table if exists t_fun_global_sequence;

/*==============================================================*/
/* Table: t_fun_global_sequence                                 */
/*==============================================================*/
create table t_fun_global_sequence
(
   stub                 char(1) comment 'stub',
   id                   bigint not null auto_increment comment '主键',
   primary key (id)
)
engine=myisam default charset=utf8;

alter table t_fun_global_sequence comment '全局唯一ID生成表';
