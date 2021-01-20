create table if not exists slave.city
(
    id varchar(10) null comment '城市ID',
    city_code varchar(20) null comment '城市码',
    city_name varchar(50) null comment '城市名称',
    remark varchar(50) null comment '评价'
    );