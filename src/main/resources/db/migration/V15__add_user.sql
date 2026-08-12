create table user (
    user_id int not null primary key auto_increment,
    username varchar(50) not null unique,
    email varchar(254) not null unique,
    password_hash varchar(255),
    authority varchar(20) not null default 'USER'
);

alter table run
add column user_id int not null;

alter table run
add foreign key (user_id) references user(user_id);

