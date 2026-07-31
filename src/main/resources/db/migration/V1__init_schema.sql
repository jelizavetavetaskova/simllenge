create table challenge (
    challenge_id int not null primary key auto_increment,
    title varchar(255) not null
);

create table skill (
   skill_id int not null primary key auto_increment,
   name varchar(255) not null
);

create table career (
    career_id int not null primary key auto_increment,
    name varchar(255) not null,
    level_cap int default 10,
    trait_conditions varchar(255)
);

create table trait (
    trait_id int not null primary key auto_increment,
    name varchar(50) not null
);

create table stage (
   stage_id int not null primary key auto_increment,
   name varchar(255) not null,
   stage_order int not null,
   tax_applies boolean default true,
   challenge_id int not null,
   foreign key (challenge_id) references challenge(challenge_id) on delete cascade
);

create table run (
    run_id int not null primary key auto_increment,
    budget int not null,
    challenge_id int not null,
    foreign key (challenge_id) references challenge(challenge_id) on delete cascade
);

create table requirement (
    requirement_id int not null primary key auto_increment,
    description varchar(1000) not null,
    measurable boolean default false,
    scope varchar(255) not null,
    metric_type varchar(255),
    target varchar(50),
    stage_id int not null,
    foreign key (stage_id) references stage(stage_id) on delete cascade
);

create table sim (
    sim_id int not null primary key auto_increment,
    name varchar(255) not null,
    role varchar(255) not null,
    life_stage varchar(255) not null,
    alive boolean default true,
    run_id int not null,
    stage_id int not null,
    foreign key (run_id) references run(run_id) on delete cascade,
    foreign key (stage_id) references stage(stage_id)
);

create table sim_skill (
    sim_skill_id int not null primary key auto_increment,
    level int not null,
    updated_at datetime not null,
    sim_id int not null,
    skill_id int not null,
    foreign key (sim_id) references sim(sim_id) on delete cascade,
    foreign key (skill_id) references skill(skill_id) on delete cascade
);

create table sim_career (
    sim_career_id int not null primary key auto_increment,
    branch varchar(30) not null,
    level int not null,
    updated_at datetime not null,
    sim_id int not null,
    career_id int not null,
    foreign key (sim_id) references sim(sim_id) on delete cascade,
    foreign key (career_id) references career(career_id) on delete cascade
);

create table sim_trait (
    sim_id int not null,
    trait_id int not null,
    foreign key (sim_id) references sim(sim_id) on delete cascade,
    foreign key (trait_id) references trait(trait_id) on delete cascade,
    primary key (sim_id, trait_id)
);

