# 外键删除
SET foreign_key_checks = 0;
DROP TABLE if EXISTS `patient`;
DROP TABLE if EXISTS `workday`;
DROP TABLE if EXISTS `record`;

# alter table `workday` drop foreign key `fk_doctor_workday`;
# alter table `record` drop foreign key `fk_patient_record`;
# alter table `record` drop foreign key `fk_account_record`;
# alter table `record` drop foreign key `fk_workday_record`;
# alter table `record` drop foreign key `fk_doctor_record`;
# DROP TABLE if EXISTS `integrity`;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS `patient`
(
    pid int primary key auto_increment comment '主键',
    name varchar(30) comment '姓名',
    sex enum('男','女') default '男' comment '性别',
    age int comment '年龄',
    pwd varchar(20) not null comment '身份证号'
);
CREATE TABLE IF NOT EXISTS `workday`
(
    wid int primary key auto_increment comment '主键',
    did char(8) not null comment '医生id',
    work_time char(4) comment '医生工作时间',
    total_num int  comment '医生这天的号源数',
    ordered_num int  comment '已预约人数',
    state char(8) comment '状态：已满，预约，停诊',
    constraint fk_doctor_workday foreign key(did) references doctor_intro(doc_id)
);

CREATE TABLE IF NOT EXISTS `record`
(
    rid int primary key auto_increment comment '预订号',
    pid int not null comment '病人id',
    uid int not null comment '用户id',
    wid int not null comment '工作日id',
    did char(8) not null comment '医生id',
    serialnumber int comment '就诊序号',
    order_data datetime not null comment '预约时间',
    visit_data time not null comment '就诊日期',
    state char(8) not null comment '预约状态：成功，取消，完成，爽约',
    constraint fk_patient_record foreign key(pid) references patient(pid),
    constraint fk_account_record foreign key(uid) references account(uid),
    constraint fk_workday_record foreign key(wid) references workday(wid),
    constraint fk_doctor_record foreign key(did) references doctor_intro(doc_id)
);

# CREATE TABLE IF NOT EXISTS `integrity`
# (
#     iid int primary key auto_increment,
#     pid int comment '用户id',
#     score tinyint comment '分值',
#     time datetime comment '违约时间',
#     constraint fk_user_integrity foreign key(pid) references user(pid)
# );
