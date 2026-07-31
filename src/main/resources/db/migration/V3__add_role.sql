create table family_role (
    family_role_id int not null primary key auto_increment,
    name varchar(50) not null
);

alter table sim drop column role;
alter table sim add column family_role_id int not null;
alter table sim add foreign key (family_role_id) references family_role(family_role_id);