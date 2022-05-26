-- drop table if exists `vac_app`;
-- create table `vac_app`
-- (
--     usr_id         char(18) comment '身份证号',
--     usr_name        varchar(50) comment '姓名',
--     age            int comment '年龄',
--     sex            ENUM('男', '女') not null comment '性别',
--     vac_num         int comment '预约接种第几针疫苗',
--     vac_date       date comment '疫苗预约时间',
--     check ( vac_num>0 and vac_num<4 ),
--     primary key (usr_id, vac_num)
-- ) COMMENT = '疫苗预约接种表';

drop table if exists `nuc_testApp`;
create table `nuc_testApp`
(
    usr_name        varchar(50) comment '姓名',
    usr_id         char(18) comment '身份证号',
    test_type       ENUM('单检', '混检') comment '核酸检测类型:单检/混检',
    test_date       date comment '核酸检测预约时间',
    primary key (usr_id)
) COMMENT = '核酸检测预约表';

INSERT INTO nuc_testApp VALUES ('测试','220220200002020202','单检','2022-05-29');