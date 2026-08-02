alter table requirement
add column role_id int;

alter table requirement
add foreign key (role_id) references family_role(family_role_id);