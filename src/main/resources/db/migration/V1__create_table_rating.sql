create table rating(
    id bigint not null auto_increment,
    userEmail varchar(100),
    rating float,
    comment varchar(1000),
    primary key (id)
);

