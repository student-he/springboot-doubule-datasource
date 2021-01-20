create table if not exists master.user
(
    id varchar(10) null comment '用户ID',
    name varchar(20) null comment '用户名',
    description varchar(50) null comment '用户描述'
    );