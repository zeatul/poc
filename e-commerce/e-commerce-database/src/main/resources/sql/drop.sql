drop index i_mall_user_login on t_mal_mall_login;

drop index ui_mall_user_token on t_mal_mall_login;

drop table if exists t_mal_mall_login;

drop table if exists t_mal_mall_login_history;

drop index ui_mall_right_id_path on t_mal_mall_right;

drop index ui_mall_right_code on t_mal_mall_right;

drop table if exists t_mal_mall_right;

drop index ui_role_code on t_mal_mall_role;

drop table if exists t_mal_mall_role;

drop index ui_mall_role_right on t_mal_mall_role_right;

drop table if exists t_mal_mall_role_right;

drop index ui_mall_role_user on t_mal_mall_role_user;

drop table if exists t_mal_mall_role_user;

drop index ui_mall_id_number on t_mal_mall_user;

drop index ui_mall_user_email on t_mal_mall_user;

drop index ui_mall_userr_account on t_mal_mall_user;

drop index ui_mall_user_mobile on t_mal_mall_user;

drop index ui_mall_user_code on t_mal_mall_user;

drop table if exists t_mal_mall_user;

drop table if exists t_mal_mall_user_code_sequence;

drop table if exists t_mal_mall_user_history;

drop index ui_mal_sr_code on t_mal_system_resource;

drop index ui_mal_sr_code_path on t_mal_system_resource;

drop index ui_mal_sr_id_path on t_mal_system_resource;

drop table if exists t_mal_system_resource;

drop table if exists t_mal_system_short_sequence;
