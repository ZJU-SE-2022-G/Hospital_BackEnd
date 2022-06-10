drop table if exists `t_simple`;
create table `t_simple`
(
    id          int primary key comment 'id',
    name        varchar(50) comment '姓名',
    age         int comment '年龄',
    delete_flag tinyint(1) comment '删除标识1',
    deleted tinyint(1) comment '删除标识2',
    version     bigint comment '版本',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    primary key (id)
) COMMENT = '测试表';

/*create table problem_feedback(
                                 id bigint auto_increment,
                                 problem_type varchar(50) default null,
                                 problem text,
                                 asker_id int,
                                 ask_time timestamp,
                                 answer text default null,
                                 respondent_id int default null,
                                 res_time timestamp,
                                 deleted int default 0,
                                 version int default 1,
                                 primary key(id)
)charset utf8;

create table Notice(
                       id bigint auto_increment,
                       title varchar(100),
                       author_id int,
                       Release_time timestamp,
                       update_time timestamp,
                       version int default 1,
                       deleted int default 0,
                       content text,
                       primary key(id)
)charset utf8;

create table Guide(
                      id bigint auto_increment,
                      title varchar(100),
                      author_id int,
                      Release_time timestamp,
                      update_time timestamp,
                      version int default 1,
                      deleted int default 0,
                      content text,
                      primary key(id)
)charset utf8;*/