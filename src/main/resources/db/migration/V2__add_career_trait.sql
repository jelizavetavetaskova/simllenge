create table career_trait (
    career_id int not null,
    trait_id int not null,
    foreign key (career_id) references career(career_id) on delete cascade,
    foreign key (trait_id) references trait(trait_id) on delete cascade,
    primary key (career_id, trait_id)
);

alter table career
drop column trait_conditions;