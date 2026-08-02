create table requirement_skill (
    requirement_id int not null,
    skill_id int not null,
    foreign key (requirement_id) references requirement(requirement_id) on delete cascade,
    foreign key (skill_id) references skill(skill_id) on delete cascade,
    primary key (requirement_id, skill_id)
);

alter table requirement
add column career_branch_id int;

alter table requirement
add foreign key (career_branch_id) references career_branch(career_branch_id);

alter table requirement
drop column measurable;