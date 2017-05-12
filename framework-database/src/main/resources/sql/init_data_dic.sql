-- MySQL dump 10.13  Distrib 5.6.27, for Win64 (x86_64)
--
-- Host: localhost    Database: dic
-- ------------------------------------------------------
-- Server version	5.6.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_dic_word`
--

DROP TABLE IF EXISTS `t_dic_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dic_word` (
  `object_id` varchar(50) NOT NULL COMMENT '对象ID',
  `use_type` varchar(50) DEFAULT NULL COMMENT '用途类型',
  `data_type` varchar(50) DEFAULT NULL COMMENT '数据类型',
  `object_code` varchar(50) DEFAULT NULL COMMENT '编码',
  `object_name` varchar(200) DEFAULT NULL COMMENT '名称',
  `object_comment` varchar(1024) DEFAULT NULL COMMENT '描述',
  `object_display_name` varchar(200) DEFAULT NULL COMMENT '显示名称',
  `object_pattern` varchar(200) DEFAULT NULL COMMENT '格式',
  `object_pattern_comment` varchar(1000) DEFAULT NULL COMMENT '格式描述',
  `regex` varchar(1000) DEFAULT NULL COMMENT '正则表达式',
  `object_extra_validation` varchar(1000) DEFAULT NULL COMMENT '扩展校验',
  `char_max_length` int(11) DEFAULT NULL COMMENT '最大长度',
  `char_min_length` int(11) DEFAULT NULL COMMENT '最小长度',
  `is_only_ascii` int(11) DEFAULT NULL COMMENT '是否有超过1个byte长度的的字符',
  `max_value` varchar(200) DEFAULT NULL COMMENT '最大值',
  `min_value` varchar(200) DEFAULT NULL COMMENT '最小值',
  `datetime_precision` int(11) DEFAULT NULL COMMENT '时间精度',
  `numeric_precision` int(11) DEFAULT NULL COMMENT '数据精度',
  `numeric_scale` int(11) DEFAULT NULL COMMENT '数据小数精度',
  `is_enum` int(11) DEFAULT NULL COMMENT '是否枚举(yes/no)',
  `enum_values` varchar(2000) DEFAULT NULL COMMENT '枚举值',
  `system_code` varchar(50) DEFAULT NULL COMMENT '系统编码(区分不同项目，不同集团)',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `create_date` timestamp(3) NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` timestamp(3) NULL DEFAULT NULL COMMENT '更新日期',
  `delete_date` timestamp(3) NULL DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`object_id`),
  UNIQUE KEY `ui_dic_data_def_code` (`object_code`,`system_code`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典定义';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dic_word`
--

LOCK TABLES `t_dic_word` WRITE;
/*!40000 ALTER TABLE `t_dic_word` DISABLE KEYS */;
INSERT INTO `t_dic_word` VALUES ('00eb9471-a664-46bc-a696-0acd0dc363ae','technology','string','bsi_benef_name','投保者姓名','投保者姓名','投保者姓名',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.742','2017-05-05 02:11:04.742',NULL),('01430948-1c13-452c-a240-d8d7127ba79b','technology','integer','exec_times','已经执行次数','已经执行次数','已经执行次数',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.327','2017-05-05 02:09:33.327',NULL),('0228da16-e18a-4137-95bd-5c258acee7b0','technology','integer','numeric_precision','数据精度','数据精度','数据精度',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.913','2017-05-05 02:09:33.913',NULL),('03069073-7d1f-4aa1-8c90-845df27bd9d5','technology','string','enum_value','枚举值','枚举值','枚举值',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.127','2017-05-05 02:09:33.127',NULL),('044dbf47-6776-4723-9bf6-112937c15372','technology','integer','bsi_product_id','碎屏险产品ID','碎屏险产品ID','碎屏险产品ID',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.551','2017-05-05 02:11:04.551',NULL),('058a0089-4fe6-4d3f-9f8d-bc52b017a9ff','technology','integer','duration_second','有效时间(秒)','有效时间(秒)','有效时间(秒)',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:20.968','2017-05-05 02:11:20.968',NULL),('0a361911-a30b-4be2-935a-92a2fac04e12','technology','string','regex','正则表达式','正则表达式','正则表达式',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.752','2017-05-05 02:09:33.752',NULL),('0a560c86-12c3-4f60-bf0a-aa2646cf76c5','technology','numeric','price','价格','价格','价格',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,2,0,NULL,'ecom',1,'2017-05-05 02:09:33.213','2017-05-05 02:09:33.213',NULL),('0b21bc18-b371-4de9-af57-b0e118539d4f','technology','integer','bsi_cash_coupon_status','代金券状态','代金券状态','代金券状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.325','2017-05-05 02:11:04.325',NULL),('0d70d9eb-f998-4501-9fdc-fa4775f1bb43','technology','string','mobile_operator','手机号运营商','手机号运营商','手机号运营商',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.143','2017-05-05 02:11:21.143',NULL),('0f83b6a1-9815-4486-822e-100144e9d45e','technology','string','synonym_type','同义词类型','同义词类型','同义词类型',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.551','2017-05-05 02:09:33.551',NULL),('120216bb-f212-40f6-b5cc-f1c7a27b3597','technology','string','bsi_benef_birthday','投保者生日','投保者生日','投保者生日',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.684','2017-05-05 02:11:04.684',NULL),('1364662e-c2b5-4598-a409-414a6896911b','technology','string','store_status','','','',NULL,NULL,NULL,NULL,1,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:48.791','2017-05-05 02:10:48.791',NULL),('15ec6842-6ce9-47a5-9f29-db1491b2e378','technology','string','object_pattern_comment','格式描述','格式描述','格式描述',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.725','2017-05-05 02:09:33.725',NULL),('18c7a724-92e4-4057-8f4d-95b1cb969dbc','technology','integer','is_pk','是否为主键(1/0)','是否为主键(1/0)','是否为主键(1/0)',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.051','2017-05-05 02:09:33.051',NULL),('18f675a7-eb6b-4d72-af43-9c5a68c4846d','technology','string','spell_abbr','拼首','拼首','拼首',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.193','2017-05-05 02:09:33.193',NULL),('1bb0585b-2adf-4ca4-a942-7571e10a706d','technology','integer','bsi_benef_sex','投保者性别','投保者性别','投保者性别',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.717','2017-05-05 02:11:04.717',NULL),('1e2c95bb-2f3a-4547-b6e2-520375206f27','technology','string','order_error_cause','订单失败原因','订单失败原因','订单失败原因',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.217','2017-05-05 02:11:05.217',NULL),('230c1cc9-192f-4ea2-8e36-e5ff851fed11','technology','date','expire_date','失效日期','失效日期','失效日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:20.993','2017-05-05 02:11:20.993',NULL),('23f487ab-edd8-479c-8d2d-020f29d17130','technology','integer','bsi_phone_brand_status','手机品牌状态','手机品牌状态','手机品牌状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.817','2017-05-05 02:11:04.817',NULL),('25df56c8-9d11-458e-94fd-6841d99be580','technology','integer','bsi_task_status','任务状态','任务状态','任务状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.500','2017-05-05 02:11:04.500',NULL),('27f61753-f36c-4d29-a4e4-0a1218813eea','technology','string','order_code','订单编号','订单编号','订单编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.425','2017-05-05 02:11:04.425',NULL),('2aedcac4-9cee-4bba-8091-ae2de38a7e71','technology','string','object_pattern','格式','格式','格式',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.677','2017-05-05 02:09:33.677',NULL),('2b8513a4-da66-4750-a7d5-aa1ad45405ca','technology','string','sms_receipt','短信发送回执','短信发送回执','短信发送回执',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.381','2017-05-05 02:10:35.381',NULL),('2d3aac0a-dc57-4d5e-9250-ddb49b17c875','technology','integer','is_enum','是否枚举(yes/no)','是否枚举(yes/no)','是否枚举(yes/no)',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.943','2017-05-05 02:09:33.943',NULL),('2d42bf37-c6be-4eae-b6fe-bc52f1e66590','technology','integer','bsi_cash_coupon_period','代金券保险月份数','代金券保险月份数','代金券保险月份数',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.384','2017-05-05 02:11:04.384',NULL),('3067a49d-1514-4128-9eb3-b9ea53e2c705','technology','string','min_value','最小值','最小值','最小值',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.868','2017-05-05 02:09:33.868',NULL),('3081ed0d-001f-4296-b74a-91bb00d214c7','technology','string','enum_values','枚举值','枚举值','枚举值',NULL,NULL,NULL,NULL,2000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.960','2017-05-05 02:09:33.960',NULL),('31f40198-d80a-4540-9436-09fcb0dc68c7','technology','string','order_desc','订单描述','订单描述','订单描述',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.192','2017-05-05 02:11:05.192',NULL),('335960e6-9324-4413-970d-1d54865d4073','technology','string','user_nickname','用户昵称','用户昵称','用户昵称',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.385','2017-05-05 02:11:21.385',NULL),('337a62f0-44d7-451c-8ffe-99785d4d24b0','technology','string','store_code','商户编号','商户编号','商户编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.226','2017-05-05 02:09:33.226',NULL),('34779605-754c-47fa-bf6a-e6e69a4a9f97','technology','integer','is_email_verified','用户邮箱是否已验证','用户邮箱是否已验证','用户邮箱是否已验证',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.886','2017-05-05 02:11:21.886',NULL),('3493281f-eab5-45b7-aad5-716d257173e4','technology','date','last_exec_date','最后一次执行时间','最后一次执行时间','最后一次执行时间',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.401','2017-05-05 02:09:33.401',NULL),('353f208a-dbcb-4aff-b6dc-63109fd5d63e','technology','integer','charge_status','充值状态','充值状态','充值状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.092','2017-05-05 02:11:05.092',NULL),('35690a0a-9156-40d6-bbaf-8fd6a39afec2','technology','string','id_number','证件号码','证件号码','证件号码',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.719','2017-05-05 02:11:21.719',NULL),('36dc538f-5152-416f-8184-3201360ac3aa','technology','string','user_status_change_cause','状态变更原因','状态变更原因','状态变更原因',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.252','2017-05-05 02:11:21.252',NULL),('375c4fda-39f7-4e48-abc0-2430adb7431b','technology','integer','max_exec_times','最大允许执行次数','最大允许执行次数','最大允许执行次数',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.354','2017-05-05 02:09:33.354',NULL),('389842e4-64c0-4401-8a04-3e000c443075','technology','integer','is_only_ascii','是否有超过1个byte长度的的字符','是否有超过1个byte长度的的字符','是否有超过1个byte长度的的字符',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.835','2017-05-05 02:09:33.835',NULL),('39b7b604-8f23-491d-a6d5-ed47a22973f8','technology','string','device_brand','三星/华为/苹果','三星/华为/苹果','三星/华为/苹果',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.068','2017-05-05 02:11:21.068',NULL),('3ad93e07-10b7-4bf5-bb04-9d3898d939b2','technology','string','bsi_task_code','任务号,与小宝对接用','任务号,与小宝对接用','任务号,与小宝对接用',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.475','2017-05-05 02:11:04.475',NULL),('40b1e7db-b85b-41cf-aa65-fd86e8732570','technology','string','order_type','订单类型','订单类型','订单类型',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.159','2017-05-05 02:11:05.159',NULL),('41dbf268-62e4-4e28-a99d-79df735fc077','technology','string','bsi_phone_model','手机型号','手机型号','手机型号',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.892','2017-05-05 02:11:04.892',NULL),('44038b9e-8e3e-4be9-82d3-e95ec0425051','technology','string','synonym_display_name','同义词显示名称','同义词显示名称','同义词显示名称',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.538','2017-05-05 02:09:33.538',NULL),('446540cd-95fd-429f-84d4-08b84ac5c5d3','technology','date','user_status_change_date','状态变更日期','状态变更日期','状态变更日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.302','2017-05-05 02:11:21.302',NULL),('475a7897-96d3-4d09-8fb7-f2566a5df62d','technology','string','sms_batch_no','批次号','批次号','批次号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.356','2017-05-05 02:10:35.356',NULL),('480443bf-e8e5-4936-9fba-30946feb5a0b','technology','string','bsi_benef_id_number','证件号码','证件号码','证件号码',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.659','2017-05-05 02:11:04.659',NULL),('4b8597e1-ca46-4476-9d0d-4508560c91ce','technology','integer','user_status','用户状态','用户状态','用户状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.194','2017-05-05 02:11:21.194',NULL),('4cd5b554-ffd9-414b-85fa-334645766988','technology','string','sms_model_name','模板名称','模板名称','模板名称',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.306','2017-05-05 02:10:35.306',NULL),('4d7f5c9e-4b6f-48a3-ae7f-ca8f70c6deb0','technology','string','user_sex','用户性别','用户性别','用户性别',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.469','2017-05-05 02:11:21.469',NULL),('4d91c4bf-6fd9-4391-bacb-979927ce015f','technology','integer','object_order','序号','序号','序号',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.010','2017-05-05 02:09:33.010',NULL),('4f097003-a96f-4081-8900-6a473503a783','technology','string','bsi_cash_coupon_activate_error','代金券激活失败原因','代金券激活失败原因','代金券激活失败原因',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.400','2017-05-05 02:11:04.400',NULL),('4fae9f4b-8f87-4ecf-97ec-d625465e565a','technology','string','sms_operator_code','短信运营商编号','短信运营商编号','短信运营商编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.415','2017-05-05 02:10:35.415',NULL),('50818f62-37b5-46ca-9944-2e08323f561e','technology','string','object_name','名称','名称','名称',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.776','2017-05-05 02:09:32.776',NULL),('51110cdc-6311-4633-bbb9-28eee0235ff0','technology','integer','user_level','用户级别','用户级别','用户级别',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.511','2017-05-05 02:11:21.511',NULL),('541ecd34-996b-487e-b023-7a2ecb4d25ba','technology','integer','login_type','登录类型(长期固定/短期固定/短期弹性)','登录类型(长期固定/短期固定/短期弹性)','登录类型(长期固定/短期固定/短期弹性)',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:20.927','2017-05-05 02:11:20.927',NULL),('55bf24ca-0f62-4ab4-ba5c-8ac1ae27458c','technology','string','imei','设备唯一的串号','设备唯一的串号','设备唯一的串号',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.576','2017-05-05 02:11:04.576',NULL),('57b8a446-d395-4646-9942-76cb50292bf2','technology','string','ip','登录IP','登录IP','登录IP',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:20.900','2017-05-05 02:11:20.900',NULL),('57efe3f6-88e0-40f7-84b1-0e3109ad5990','technology','string','bsi_product_status','产品状态','产品状态','产品状态',NULL,NULL,NULL,NULL,1,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.051','2017-05-05 02:11:05.051',NULL),('58123f1f-df49-495c-82c1-4bd06d4defb8','technology','date','create_date','创建日期','创建日期','创建日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.901','2017-05-05 02:09:32.901',NULL),('5caecf4c-ccd2-4344-9800-1cb5a0387ccb','technology','string','object_extra_validation','扩展校验','扩展校验','扩展校验',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.768','2017-05-05 02:09:33.768',NULL),('5fa64d45-447b-46b2-b513-e3e76a177ed3','technology','date','bsi_cash_coupon_create_date','代金券生成日期','代金券生成日期','代金券生成日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.276','2017-05-05 02:11:04.276',NULL),('611dae08-5a5c-492a-a555-b85e6a43f972','technology','integer','bsi_product_valid_period','有效期(月)','有效期(月)','有效期(月)',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.942','2017-05-05 02:11:04.942',NULL),('62b40455-ca7e-4139-9e04-94da1da77414','technology','string','object_type','类型','类型','类型',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.570','2017-05-05 02:09:33.570',NULL),('63b360be-bffa-49ed-9659-73daa2b0b437','technology','string','store_name','','','',NULL,NULL,NULL,NULL,100,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:48.732','2017-05-05 02:10:48.732',NULL),('67618642-e857-45db-99af-6b4361f6932e','technology','string','bsi_insurance_code','小宝订单编号','小宝订单编号','小宝订单编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.767','2017-05-05 02:11:04.767',NULL),('6b4d9dfb-fb69-4f16-b323-ac9d63ec76bb','technology','string','object_comment','描述','描述','描述',NULL,NULL,NULL,NULL,1024,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.818','2017-05-05 02:09:32.818',NULL),('6c98eec4-dfb6-4baa-8759-1a8778f80a03','technology','string','operators','需要支持的运算符，等于默认支持','需要支持的运算符，等于默认支持','需要支持的运算符，等于默认支持',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.072','2017-05-05 02:09:33.072',NULL),('6db3bc8f-9fc5-4207-b64b-a4303089c679','technology','string','bsi_phone_model_status','型号状态','型号状态','型号状态',NULL,NULL,NULL,NULL,1,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.917','2017-05-05 02:11:04.917',NULL),('72f75afc-5014-4a5c-8432-8bf3b0c63cec','technology','string','stub','stub','stub','stub',NULL,NULL,NULL,NULL,1,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.249','2017-05-05 02:10:35.249',NULL),('732d891e-0cdf-44cc-bdef-c78699a0c0c7','technology','date','update_date','更新日期','更新日期','更新日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.973','2017-05-05 02:09:32.973',NULL),('75e42203-e77a-4ffc-bef5-7f4cd36d5d8b','technology','string','user_account','用户账号','用户账号','用户账号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.794','2017-05-05 02:11:21.794',NULL),('7622fa7e-f4d3-4e76-848c-311015a82703','technology','integer','char_max_length','最大长度','最大长度','最大长度',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.785','2017-05-05 02:09:33.785',NULL),('7b3ce3a2-6dc7-4fc3-9d81-a89540c859c4','technology','string','token','token','token','token',NULL,NULL,NULL,NULL,100,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:20.823','2017-05-05 02:11:20.823',NULL),('80abc9cd-20a3-4684-bae2-5c2b9c69f1b5','technology','string','operating_system','设备操作系统','设备操作系统','设备操作系统',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.018','2017-05-05 02:11:21.018',NULL),('879d3592-bdab-4beb-b38d-2ad7f54bc200','technology','string','mobile_number','手机号','手机号','手机号',NULL,'11位数字','1[0-9]{10}',NULL,11,11,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.283','2017-05-05 06:37:54.000',NULL),('88a956df-68f0-4d3d-bfb3-c5e134ac8b76','technology','string','enum_key','枚举键','枚举键','枚举键',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.093','2017-05-05 02:09:33.093',NULL),('896c3508-155c-4e98-9064-7455fc5bee9a','technology','string','bsi_cash_coupon_type','代金券类型','代金券类型','代金券类型',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.367','2017-05-05 02:11:04.367',NULL),('8b9c1ef7-8094-492b-85bc-7415b731662f','technology','string','bsi_cash_coupon_name','代金券名称','代金券名称','代金券名称',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.250','2017-05-05 02:11:04.250',NULL),('8cdf06d6-82b2-4a61-a942-a4d147b8a44c','technology','integer','bsi_phone_model_id','手机型号ID','手机型号ID','手机型号ID',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.525','2017-05-05 02:11:04.525',NULL),('8eac82fe-96e9-432c-b1c4-6e4c1309f782','technology','date','bsi_cash_coupon_invalid_date','代金券失效日期','代金券失效日期','代金券失效日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.292','2017-05-05 02:11:04.292',NULL),('9235467d-5a2a-4ca4-8dfb-bd37fdda2f60','technology','string','bsi_cash_coupon_code','代金券编号','代金券编号','代金券编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.186','2017-05-05 02:11:04.186',NULL),('96bc6633-fe23-49e3-9e09-865d5055c7a8','technology','string','physical_option','表的物理特性','表的物理特性','表的物理特性',NULL,NULL,NULL,NULL,2000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.593','2017-05-05 02:09:33.593',NULL),('96f09076-4b05-400b-8109-3a3507412b10','technology','string','origin_object_id','原单词id','原单词id','原单词id',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.501','2017-05-05 02:09:33.501',NULL),('9abf0a1e-132a-4c03-b297-a011542b1166','technology','date','current_exec_start_date','当前任务启动时间','当前任务启动时间','当前任务启动时间',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.468','2017-05-05 02:09:33.468',NULL),('9b989bbb-ad2a-474c-a475-a52c5232056e','technology','integer','char_min_length','最小长度','最小长度','最小长度',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.822','2017-05-05 02:09:33.822',NULL),('9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','technology','string','object_id','对象ID','对象ID','对象ID',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.695','2017-05-05 02:09:32.695',NULL),('a02f5a02-4ff4-4bd0-aeb2-12d0cc40fe40','technology','integer','numeric_scale','数据小数精度','数据小数精度','数据小数精度',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.928','2017-05-05 02:09:33.928',NULL),('a4b0ef70-b428-4a66-a53e-9d5899a5acc2','technology','string','user_code','用户编号','用户编号','用户编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.259','2017-05-05 02:09:33.259',NULL),('a7707db6-f95e-4147-8972-b64302c67357','technology','string','sms_operator_name','短信运营商编号','短信运营商编号','短信运营商编号',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.440','2017-05-05 02:10:35.440',NULL),('a79f8271-546f-4860-af53-81fec36da300','technology','string','order_number','订单编号','订单编号','订单编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.243','2017-05-05 02:09:33.243',NULL),('aa90d400-5eb9-4ad5-8842-73b79c4964a6','technology','date','last_access_date','最近访问日期','最近访问日期','最近访问日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.484','2017-05-05 02:09:33.484',NULL),('abab6ef0-15ee-4fbb-bcc9-a18545bd49e5','technology','integer','is_unique','是唯一索引','是唯一索引','是唯一索引',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.151','2017-05-05 02:09:33.151',NULL),('acc7fc5b-b2b0-4746-9bd4-c12c87701feb','technology','integer','is_mobile_verified','手机号是否已经验证','手机号是否已经验证','手机号是否已经验证',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.118','2017-05-05 02:11:21.118',NULL),('ade10112-4145-4b81-80fc-284dee732e0f','technology','string','synonym_object_code','同义词编码','同义词编码','同义词编码',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.518','2017-05-05 02:09:33.518',NULL),('afadac98-0d53-48f8-8d19-b3ef1f753334','technology','string','login_pwd','登录密码','登录密码','登录密码',NULL,'长度6到20位,支持数字,字母(区分大小写)和特殊字符!@#$*','([!@#$*]|[0-9]|[A-Z]|[a-z]){6,20}',NULL,20,6,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.160','2017-05-05 06:56:31.000',NULL),('b08a3519-47c1-4552-bf45-466226c33a05','technology','string','sms_msg_data','模板填充数据','模板填充数据','模板填充数据',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.581','2017-05-05 02:10:35.581',NULL),('b291576a-d2cf-40e2-851e-3007e8e31956','technology','date','login_date','登录日期','登录日期','登录日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:20.943','2017-05-05 02:11:20.943',NULL),('b4b27d51-e745-49a0-9f30-7b14ef65e1f3','technology','integer','user_type','用户类别','用户类别','用户类别',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.644','2017-05-05 02:11:21.644',NULL),('b621f9af-8857-43a3-8953-fd65d89d99c2','technology','string','bsi_product_name','产品名称','产品名称','产品名称',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.967','2017-05-05 02:11:04.967',NULL),('b71e002d-c0b2-4679-a67e-18f177ef0068','technology','integer','sms_is_batch','是否是批量发送','是否是批量发送','是否是批量发送',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.473','2017-05-05 02:10:35.473',NULL),('b7a21096-001f-47e9-b60e-af4affdc4250','technology','string','system_code','系统编码(区分不同项目，不同集团)','系统编码(区分不同项目，不同集团)','系统编码(区分不同项目，不同集团)',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.851','2017-05-05 02:09:32.851',NULL),('babba288-76f4-4463-8a3a-9b0732aef082','technology','string','charge_task_code','充值任务号','充值任务号','充值任务号',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.109','2017-05-05 02:11:05.109',NULL),('bc5e81eb-0f22-4458-b6d4-172ee7518055','technology','integer','charge_data_size','充值流量','充值流量','充值流量',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.075','2017-05-05 02:11:05.075',NULL),('bf539ea6-c7e3-49b8-bd58-febc5624b753','technology','integer','datetime_precision','时间精度','时间精度','时间精度',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.889','2017-05-05 02:09:33.889',NULL),('bfecc953-0791-4266-a396-45263b37f06c','technology','string','object_display_name','显示名称','显示名称','显示名称',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.650','2017-05-05 02:09:33.650',NULL),('c30a3ad6-9a6d-46c8-9c27-324ab6b71873','technology','string','user_agent','http请求的user_agent原始信息','http请求的user_agent原始信息','http请求的user_agent原始信息',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.102','2017-05-05 02:11:21.102',NULL),('c51db6b5-69f0-4911-a2b4-b4e7531a44f8','technology','integer','id_type','证件类型','证件类型','证件类型',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.686','2017-05-05 02:11:21.686',NULL),('c59d670e-ab3b-4429-bcff-7c0f28623680','technology','integer','nullable','可否为空(1/0)','可否为空(1/0)','可否为空(1/0)',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.034','2017-05-05 02:09:33.034',NULL),('c7802b96-d5c9-4b7b-aada-19eb8c7f4296','technology','string','last_exec_err_msg','最后一次执行错误原因','最后一次执行错误原因','最后一次执行错误原因',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.384','2017-05-05 02:09:33.384',NULL),('c7fbb5b1-d1a4-4538-87d0-3eff2abb97ff','technology','string','current_exec_computer','当前执行机器','当前执行机器','当前执行机器',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.434','2017-05-05 02:09:33.434',NULL),('c84659b6-e859-4c42-8fa9-d41d6f13e102','technology','integer','order_status','订单状态','订单状态','订单状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:05.134','2017-05-05 02:11:05.134',NULL),('ca0cd342-d025-4a7c-b644-c8d38e5a82f6','technology','string','use_type','用途类型','用途类型','用途类型',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.610','2017-05-05 02:09:33.610',NULL),('cb6cc057-f80d-42fd-a96f-94b4afffbba1','technology','string','operating_system_version','设备操作系统版本号','设备操作系统版本号','设备操作系统版本号',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.043','2017-05-05 02:11:21.043',NULL),('ce64568a-b113-4045-9e8a-79eeb17fdbdd','technology','string','object_code','编码','编码','编码',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.755','2017-05-05 02:09:32.755',NULL),('d03caa76-82ed-4723-a12f-86aa1b853f1f','technology','long','id','长整型主键','长整型主键','长整型主键',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,19,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.176','2017-05-05 02:09:33.176',NULL),('d05e84ce-e61c-4fd8-903b-f964ea4000b9','technology','string','user_name','用户姓名','用户姓名','用户姓名',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.428','2017-05-05 02:11:21.428',NULL),('d0c01e31-2aed-469d-8a9c-2aa3220bff33','technology','integer','bsi_benef_id_typ','证件类型','证件类型','证件类型',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.596','2017-05-05 02:11:04.596',NULL),('d392cd86-eaee-4d48-9cd3-49fc661b336e','technology','string','last_exec_err_code','最后一次执行错误代码','最后一次执行错误代码','最后一次执行错误代码',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.368','2017-05-05 02:09:33.368',NULL),('d57e6ecc-0e60-4115-8c0c-1a2fc307c995','technology','string','register_channel','注册渠道','注册渠道','注册渠道',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.344','2017-05-05 02:11:21.344',NULL),('d703f8fc-e75b-428d-a9a6-418a3146a814','technology','string','data_type','数据类型','数据类型','数据类型',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.627','2017-05-05 02:09:33.627',NULL),('da133f1e-7330-4ef4-aa73-79f5c2bc57e5','technology','string','bsi_phone_brand','手机品牌','手机品牌','手机品牌',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.792','2017-05-05 02:11:04.792',NULL),('da3f00af-9a0d-4462-a625-c41a987e317d','technology','string','user_picture','用户头像','用户头像','用户头像',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.603','2017-05-05 02:11:21.603',NULL),('da4eed87-473a-4173-8b2b-a19633f01dfe','technology','string','sms_msg_content','短信内容','短信内容','短信内容',NULL,NULL,NULL,NULL,1000,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.648','2017-05-05 02:10:35.648',NULL),('e2171d3f-09fb-49ec-a0e9-5633a79f5334','technology','string','device_model','厂商给设备定义的编号','厂商给设备定义的编号','厂商给设备定义的编号',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.085','2017-05-05 02:11:21.085',NULL),('e2d69893-26a9-4958-8c90-d2f67ef3cbf3','technology','string','current_exec_process_id','当前执行进程ID','当前执行进程ID','当前执行进程ID',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.454','2017-05-05 02:09:33.454',NULL),('e53d4472-119a-4ca2-864d-9280b49beb65','technology','string','bsi_order_status','订单状态','订单状态','订单状态',NULL,NULL,NULL,NULL,1,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:04.451','2017-05-05 02:11:04.451',NULL),('e667f01b-8d48-4746-9547-9c24ee2a3506','technology','string','max_value','最大值','最大值','最大值',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.851','2017-05-05 02:09:33.851',NULL),('e7b6eb4b-caf7-4e69-95af-ef114a00fd54','technology','date','schedule_exec_date','计划执行时间','计划执行时间','计划执行时间',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:33.418','2017-05-05 02:09:33.418',NULL),('e95cdd9c-2971-4c70-a8a9-88910fb6b632','technology','integer','user_activeness','用户活跃度','用户活跃度','用户活跃度',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.561','2017-05-05 02:11:21.561',NULL),('f1b5e84b-30c4-4057-8837-b0f456f3d591','technology','integer','version','版本号','版本号','版本号',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.868','2017-05-05 02:09:32.868',NULL),('f5e7a3c7-860c-4652-9da7-bfa866adb5bd','technology','string','user_email','用户邮箱','用户邮箱','用户邮箱',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:11:21.827','2017-05-05 02:11:21.827',NULL),('fa71e064-6b7d-4566-b5e5-9d73988f6b65','technology','integer','sms_status','短信发送状态','短信发送状态','短信发送状态',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,0,10,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.506','2017-05-05 02:10:35.506',NULL),('fc2b0777-2022-418d-bb77-948e9c5592ee','technology','string','sms_model_code','模板编号','模板编号','模板编号',NULL,NULL,NULL,NULL,50,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.281','2017-05-05 02:10:35.281',NULL),('fd24c2ae-d201-4151-93d2-e007bb1c3136','technology','date','delete_date','删除日期','删除日期','删除日期',NULL,NULL,NULL,NULL,0,0,1,NULL,NULL,3,0,0,0,NULL,'ecom',1,'2017-05-05 02:09:32.993','2017-05-05 02:09:32.993',NULL),('fe25e187-4c3c-4bc9-9573-036ab67d2af4','technology','string','sms_model_content','模板内容','模板内容','模板内容',NULL,NULL,NULL,NULL,200,0,1,NULL,NULL,0,0,0,0,NULL,'ecom',1,'2017-05-05 02:10:35.331','2017-05-05 02:10:35.331',NULL);
/*!40000 ALTER TABLE `t_dic_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dic_synonym`
--

DROP TABLE IF EXISTS `t_dic_synonym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dic_synonym` (
  `object_id` varchar(50) NOT NULL COMMENT '对象ID',
  `origin_object_id` varchar(50) DEFAULT NULL COMMENT '原单词id',
  `synonym_object_code` varchar(50) DEFAULT NULL COMMENT '同义词编码',
  `synonym_display_name` varchar(200) DEFAULT NULL COMMENT '同义词显示名称',
  `synonym_type` varchar(50) DEFAULT NULL COMMENT '同义词类型',
  `system_code` varchar(50) DEFAULT NULL COMMENT '系统编码(区分不同项目，不同集团)',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `create_date` timestamp(3) NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` timestamp(3) NULL DEFAULT NULL COMMENT '更新日期',
  `delete_date` timestamp(3) NULL DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`object_id`),
  UNIQUE KEY `ui_synonym` (`origin_object_id`,`synonym_object_code`,`synonym_type`,`system_code`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同义词定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dic_synonym`
--

LOCK TABLES `t_dic_synonym` WRITE;
/*!40000 ALTER TABLE `t_dic_synonym` DISABLE KEYS */;
INSERT INTO `t_dic_synonym` VALUES ('37b99a75-d138-4944-a2d7-9a167069c4f7','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','column_object_id','字段对象id','word','ecom',1,'2017-05-05 02:09:40.552','2017-05-05 02:09:40.552',NULL),('3bb9f399-bf4c-4a6e-8363-eff0e682e700','879d3592-bdab-4beb-b38d-2ad7f54bc200','charge_mobile_number','充值手机号码','word','ecom',1,'2017-05-05 02:11:09.159','2017-05-05 02:11:09.159',NULL),('40d42048-8268-44f5-b6d6-7dc1285d98d4','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','parent_column_object_id','主表字段对象id','word','ecom',1,'2017-05-05 02:09:40.510','2017-05-05 02:09:40.510',NULL),('4ef4e592-e206-4ebc-b0cb-69137a511427','879d3592-bdab-4beb-b38d-2ad7f54bc200','bsi_benef_mobile_number','受益人手机号码','word','ecom',1,'2017-05-05 02:11:09.192','2017-05-05 02:11:09.192',NULL),('57fb9da3-7423-4a01-baa9-2eff2757ca07','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','child_table_object_id','子表对象id','word','ecom',1,'2017-05-05 02:09:40.460','2017-05-05 02:09:40.460',NULL),('58322d0c-abc3-48cc-910a-87f00bac2406','57b8a446-d395-4646-9942-76cb50292bf2','register_ip','注册IP','word','ecom',1,'2017-05-05 02:11:25.435','2017-05-05 02:11:25.435',NULL),('5a7a2346-01a6-4213-96e4-240886ad2950','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','table_object_id','表对象id','word','ecom',1,'2017-05-05 02:09:40.535','2017-05-05 02:09:40.535',NULL),('67d6130f-60aa-409f-9a49-badd3ea617a6','57b8a446-d395-4646-9942-76cb50292bf2','login_ip','登录IP','word','ecom',1,'2017-05-05 02:11:25.452','2017-05-05 02:11:25.452',NULL),('6dbb8ae4-8699-4b98-b4bc-0a459e74cb08','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','parent_table_object_id','主表对象id','word','ecom',1,'2017-05-05 02:09:40.602','2017-05-05 02:09:40.602',NULL),('9806d0b9-77f0-486b-b33e-5dbba06ba58f','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','index_object_id','索引对象id','word','ecom',1,'2017-05-05 02:09:40.435','2017-05-05 02:09:40.435',NULL),('a3c0fa77-0891-4db5-b91f-63ed28a6ff69','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','fk_object_id','外键对象id','word','ecom',1,'2017-05-05 02:09:40.476','2017-05-05 02:09:40.476',NULL),('ab9ec86e-de0b-460f-9d54-eecf823ec829','d03caa76-82ed-4723-a12f-86aa1b853f1f','user_id','用户id','word','ecom',1,'2017-05-05 02:11:25.415','2017-05-05 02:11:25.415',NULL),('b903627c-2d0c-42d7-9f30-55283ebb9f14','d03caa76-82ed-4723-a12f-86aa1b853f1f','sms_task_id','短信任务id','word','ecom',1,'2017-05-05 02:10:37.867','2017-05-05 02:10:37.867',NULL),('c0fa7355-2886-4ed0-8f23-1f7a992c397d','0a560c86-12c3-4f60-bf0a-aa2646cf76c5','bsi_retail_price','零售价','word','ecom',1,'2017-05-05 02:11:09.084','2017-05-05 02:11:09.084',NULL),('c5e63964-cb1f-413b-b12a-e263572b8736','0a560c86-12c3-4f60-bf0a-aa2646cf76c5','bsi_display_price','显示价','word','ecom',1,'2017-05-05 02:11:09.142','2017-05-05 02:11:09.142',NULL),('cdec32be-8ee1-4f7e-9343-dbd1353c40ec','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','application_object_id','应用对象id','word','ecom',1,'2017-05-05 02:09:40.577','2017-05-05 02:09:40.577',NULL),('ced40be6-1f0d-4a82-aee0-521fc93dd476','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','word_object_id','单词对象id','word','ecom',1,'2017-05-05 02:09:40.493','2017-05-05 02:09:40.493',NULL),('df57c353-11e2-4d5f-b512-5975cecbbc4c','9f6dfdbe-77b0-422d-a09e-9ac5c5fb26e2','child_column_object_id','子表字段对象id','word','ecom',1,'2017-05-05 02:09:40.415','2017-05-05 02:09:40.415',NULL),('efe47407-c4a1-450e-9f61-61dc21ff2309','0a560c86-12c3-4f60-bf0a-aa2646cf76c5','bsi_trade_price','批发价','word','ecom',1,'2017-05-05 02:11:09.051','2017-05-05 02:11:09.051',NULL),('f6a002d4-69df-44cc-bfa4-f8d246773077','d03caa76-82ed-4723-a12f-86aa1b853f1f','order_id','订单ID','word','ecom',1,'2017-05-05 02:11:09.176','2017-05-05 02:11:09.176',NULL);
/*!40000 ALTER TABLE `t_dic_synonym` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-08  0:39:15
