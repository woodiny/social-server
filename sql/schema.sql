-- users
drop table if exists users;

create table users (
    seq bigint not null auto_increment,
    email varchar(50) not null,
    passwd varchar(80) not null,
    login_count int not null default 0,
    last_login_at datetime default null,
    create_at datetime not null default current_timestamp(),
    primary key (seq),
    constraint unq_user_email unique (email)
);

-- posts
drop table if exists posts;

create table posts (
    seq bigint not null auto_increment,
    user_seq bigint not null,
    contents varchar(500) not null,
    like_count int not null default 0,
    comment_count int not null default 0,
    create_at datetime not null default current_timestamp(),
    primary key (seq),
    constraint fk_post_to_user foreign key (user_seq) references users (seq) on delete restrict on update restrict
);

-- connections
drop table if exists connections;

create table connections (
    seq bigint not null auto_increment,
    user_seq bigint not null,
    target_seq bigint not null,
    granted_at datetime default null,
    create_at datetime not null default current_timestamp(),
    primary key (seq),
    constraint fk_connection_to_user foreign key (user_seq) references users (seq) on delete restrict on update restrict,
    constraint fk_connection_to_user2 foreign key (target_seq) references users (seq) on delete restrict on update restrict
);