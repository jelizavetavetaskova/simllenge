alter table run
add column  stage_id int not null;

alter table run
add foreign key (stage_id) references stage(stage_id);

alter table sim
drop foreign key sim_ibfk_2;

alter table sim
drop column stage_id;