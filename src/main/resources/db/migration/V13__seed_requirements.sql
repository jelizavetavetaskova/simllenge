insert into
    requirement(requirement_id, description, scope, metric_type, target, stage_id, career_branch_id, min_life_stage,
                role_id)
values
    (1, '25 000 simoleons', 'DYNASTY', 'MONEY', '25000', 1, null, null, null),
    (2, 'The sim has completed Gardening or Fishing skill', 'ROLE', 'SKILL', '1', 1, null, null, 1),
    (3, '100 000 simoleons', 'DYNASTY', 'MONEY', '100000', 2, null, null, null),
    (4, 'Every family member from teen to elder has completed Gardening or Fishing skill', 'EACH_MEMBER', 'SKILL', '1', 2, null, 'TEEN', null),
    (5, 'Heir has completed both Fishing and Gardening skills', 'ROLE', 'SKILL', '2', 2, null, null, 2),
    (6, '200 000 simoleons', 'DYNASTY', 'MONEY', '200000', 3, null, null, null),
    (7, 'Every family member from teen to elder has completed two of production skills - Painting, Handiness, Knitting, Fabrication', 'EACH_MEMBER', 'SKILL', '2', 3, null, 'TEEN', null),
    (8, 'Head of the family has completed one of two skills - Painting or Knitting', 'ROLE', 'SKILL', '1', 3, null, null, 1),
    (9, 'Head of the family has completed one of two skills - Cooking or Flower arranging', 'ROLE', 'SKILL', '1', 3, null, null, 1),
    (10, 'Head of the family has completed one of two skills - Handiness or Fabrication', 'ROLE', 'SKILL', '1', 3, null, null, 1),
    (11, 'One sim from the family has completed Gardener career - Floral designer', 'DYNASTY', 'CAREER', null, 3, 41, null, null);

insert into requirement_skill(requirement_id, skill_id) values
    (2, 13),
    (2, 15),
    (4, 13),
    (4, 15),
    (5, 13),
    (5, 15),
    (7, 22),
    (7, 18),
    (7, 52),
    (7, 50),
    (8, 22),
    (8, 52),
    (9, 12),
    (9, 45),
    (10, 18),
    (10, 50);

