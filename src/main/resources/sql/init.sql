# 外键删除
SET foreign_key_checks = 0;
DROP TABLE if EXISTS `admin`;
DROP TABLE if EXISTS `doctor`;
DROP TABLE if EXISTS `workday`;
DROP TABLE if EXISTS `record`;
DROP TABLE if EXISTS `department`;
DROP TABLE if EXISTS `user`;
DROP TABLE if EXISTS `integrity`;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS `admin`
(
    account char(30) primary key comment '用户名',
    password char(30) not null comment '密码'
);
INSERT INTO `admin` VALUES('admin', 'admin');

CREATE TABLE IF NOT EXISTS `user`
(
    pid int primary key auto_increment comment '主键',
    account char(30) not null comment '用户名',
    password char(30) not null comment '密码',
    phone char(16) comment '联系电话',
    integrity tinyint comment '信誉值'
);

CREATE TABLE IF NOT EXISTS `department`
(
    mid int primary key auto_increment comment '科室号',
    mname varchar(80) not null comment '科室名',
    room char(20) null comment '科室地址',
    phone varchar(20) null comment '科室电话',
    description varchar(255) null comment '科室介绍',
    doctor_num int
);

CREATE TABLE IF NOT EXISTS `doctor`
(
    did int primary key auto_increment comment '医生编号',
    dname varchar(20) not null comment '姓名',
    gender char(2) not null comment '性别',
    age tinyint not null comment '年龄',
    title varchar(20) null comment '职称',
    mid int not null comment '科室号',
    description varchar(255) null comment '介绍',
    constraint fk_doctor_department foreign key(mid) references department(mid)
);

CREATE TABLE IF NOT EXISTS `workday`
(
    wid int primary key auto_increment comment '主键',
    did int not null comment '医生id',
    work_time char(4) comment '医生工作日，星期几',
    ampm char(4) comment '医生工作日的上午或下午',
    nsnum int comment '医生这天上午或下午的号源数量',
    state char(8) comment '状态：已满，预约，停诊',
    constraint fk_doctor_workday foreign key(did) references doctor(did)
);

CREATE TABLE IF NOT EXISTS `record`
(
    rid int primary key auto_increment comment '预订号',
    pid int not null comment '用户id',
    wid int not null comment '工作日id',
    did int not null comment '医生id',
    serialnumber int comment '就诊序号',
    order_data datetime not null comment '预约时间',
    visit_data time not null comment '就诊日期',
    visit_ampm char(4) not null comment '就诊上午/下午',
    visit_time time not null comment '就诊时间',
    state char(8) not null comment '预约状态：成功，取消，完成，爽约',
    constraint fk_user_record foreign key(pid) references user(pid),
    constraint fk_workday_record foreign key(wid) references workday(wid),
    constraint fk_doctor_record foreign key(did) references doctor(did)
);

CREATE TABLE IF NOT EXISTS `integrity`
(
    iid int primary key auto_increment,
    pid int comment '用户id',
    score tinyint comment '分值',
    time datetime comment '违约时间',
    constraint fk_user_integrity foreign key(pid) references user(pid)
);

DROP TRIGGER IF EXISTS setintegrity;
DROP TRIGGER IF EXISTS scheduling;
