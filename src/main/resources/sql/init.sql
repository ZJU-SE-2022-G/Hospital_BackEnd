# 外键删除
SET foreign_key_checks = 0;
DROP TABLE if EXISTS `workday`;
DROP TABLE if EXISTS `record`;

SET foreign_key_checks = 1;

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
    uid int not null comment '用户id',
    wid int not null comment '工作日id',
    did char(8) not null comment '医生id',
    serialnumber int comment '就诊序号',


    order_data varchar(255) not null comment '预约时间',
    visit_data varchar(255) not null comment '就诊日期',
    state char(8) not null comment '预约状态：成功，取消，完成，爽约',
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
