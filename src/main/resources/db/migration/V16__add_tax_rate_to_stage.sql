alter table stage
add column tax_rate int not null default 0;

update stage
set tax_rate = 50
where tax_applies = true;