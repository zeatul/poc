drop index ui_sms_receiver on t_sms_msg_batch_receiver;

drop table if exists t_sms_msg_batch_receiver;

drop index ui_sms_operator_code on t_sms_operator;

drop table if exists t_sms_operator;

drop index ui_batch_no on t_sms_task;

drop table if exists t_sms_task;

/*==============================================================*/
/* Table: t_sms_msg_batch_receiver                              */
/*==============================================================*/
create table t_sms_msg_batch_receiver
(
   id                   bigint not null comment '主键',
   sms_task_id          bigint comment '短信发送记录主键',
   sms_batch_no         varchar(50) comment '批次号',
   mobile_number        varchar(20) comment '手机号码',
   sms_receipt          varchar(50) comment '短信发送回执',
   last_exec_err_code   varchar(50) comment '最后一次执行错误代码',
   last_exec_err_msg    varchar(1000) comment '最后一次执行错误原因',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_sms_msg_batch_receiver comment '批量发送的短信手机号码';

/*==============================================================*/
/* Index: ui_sms_receiver                                       */
/*==============================================================*/
create unique index ui_sms_receiver on t_sms_msg_batch_receiver
(
   sms_batch_no,
   mobile_number
);

/*==============================================================*/
/* Table: t_sms_operator                                        */
/*==============================================================*/
create table t_sms_operator
(
   id                   bigint not null comment '主键',
   sms_operator_code    varchar(50) comment '短信运营商编号',
   sms_operator_name    varchar(100) comment '短信运营商编号',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_sms_operator comment '短信运营商';

/*==============================================================*/
/* Index: ui_sms_operator_code                                  */
/*==============================================================*/
create unique index ui_sms_operator_code on t_sms_operator
(
   sms_operator_code
);

/*==============================================================*/
/* Table: t_sms_task                                            */
/*==============================================================*/
create table t_sms_task
(
   id                   bigint not null comment '主键',
   sms_operator_code    varchar(50) comment '短信运营商编号',
   sms_batch_no         varchar(50) comment '批次号',
   sms_is_batch         integer comment '是否是批量发送',
   mobile_number        varchar(20) comment '手机号码',
   sms_status           integer comment '短信发送状态',
   sms_msg              varchar(1000) comment '短信内容',
   sms_receipt          varchar(50) comment '短信发送回执',
   exec_times           integer comment '已经执行次数',
   max_exec_times       integer comment '最大允许执行次数',
   last_exec_err_code   varchar(50) comment '最后一次执行错误代码',
   last_exec_err_msg    varchar(1000) comment '最后一次执行错误原因',
   last_exec_date       timestamp(3) null comment '最后一次执行时间',
   schedule_exec_date   timestamp(3) null comment '计划执行时间',
   create_date          timestamp(3) null comment '创建日期',
   update_date          timestamp(3) null comment '更新日期',
   delete_date          timestamp(3) null comment '删除日期',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_sms_task comment '短信发送记录';

/*==============================================================*/
/* Index: ui_batch_no                                           */
/*==============================================================*/
create unique index ui_batch_no on t_sms_task
(
   sms_batch_no
);
