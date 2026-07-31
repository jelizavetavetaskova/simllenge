create table career_branch (
    career_branch_id int not null primary key auto_increment,
    name varchar(100) not null,
    career_id int not null,
    foreign key (career_id) references career(career_id) on delete cascade
);

alter table sim_career drop column branch;
alter table sim_career add column career_branch_id int not null;
alter table sim_career add foreign key (career_branch_id) references career_branch(career_branch_id) on delete cascade;

alter table sim_career drop foreign key sim_career_ibfk_2;
alter table sim_career drop column career_id;