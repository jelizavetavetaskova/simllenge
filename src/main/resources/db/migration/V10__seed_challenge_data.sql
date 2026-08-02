insert into challenge (challenge_id, title) values
    (1, 'The Kingdom: Path to the Throne');

insert into stage(stage_id, name, stage_order, tax_applies, challenge_id) values
    (1,  'Debt-bonded peasants', 1,  true,  1),
    (2,  'Free peasants',        2,  true,  1),
    (3,  'Artisans',             3,  true,  1),
    (4,  'Masters',              4,  true,  1),
    (5,  'Merchants',            5,  true,  1),
    (6,  'Military',             6,  false, 1),
    (7,  'Scientists',           7,  false, 1),
    (8,  'Mages',                8,  false, 1),
    (9,  'Archmages',            9,  false, 1),
    (10, 'Aristocracy',          10, false, 1),
    (11, 'Royal blood',          11, false, 1);

