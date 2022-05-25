drop table if exists `nuc_testApp`;
create table `nuc_testApp`
(
    usr_name        varchar(50) comment '姓名',
    usr_id         char(18) comment '身份证号',
    test_type       int comment '核酸检测类型:0单检/1混检',
    test_date       date comment '核酸检测预约时间',
    primary key (usr_id)
) COMMENT = '核酸检测预约表';