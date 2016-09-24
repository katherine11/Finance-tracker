USE finance_track_test;

insert into users values (null, 'vasko', 'vasil_v@abv.bg');

insert into incomes values (null, 'Grants');

insert into repeatings values (null, 'Yearly');

insert into users_has_incomes values (14, 7, 4, 600, curdate(), 'Rent from Apartment');	

select * from users_has_incomes where user_id = 8;

select i.incomes_id, i.category,r.value, uhi.amount, uhi.date, uhi.description from users u
join users_has_incomes uhi on (uhi.user_id = 8 and u.user_id = 8)
join incomes i on (i.incomes_id in (select uhi.incomes_id where uhi.user_id = 8))
join repeatings r on (r.repeating_id in (select uhi.repeating_id where uhi.user_id = 8));

insert into expenses values (null, 'Taxes');

insert into users_has_expenses values (14, 4, 1, 500, '2016-09-01', 'BMX');

select * from users_has_expenses where user_id = 9;

select e.expenses_id, e.category, r.value, r.repeating_id, uhe.amount, uhe.date, uhe.description from users u
join users_has_expenses uhe on (uhe.user_id = 14 and u.user_id = 14)
join expenses e on (uhe.expenses_id = e.expenses_id)
join repeatings r on (r.repeating_id = uhe.repeating_id);

insert into obligations values (null, 'Fast Credit');

insert into period values (null, 'Years');

insert into users_has_obligations 
values (8, 2, 1, 2, 2000, now(), 'Vzeh ot syseda pari', null);

select * from users_has_obligations where users_user_id = 8;

TRUNCATE TABLE users;

insert into users values (null, 'Pesho', 'pesho@abv.bg', md5(1234));

select expenses_id from expenses where category like 'Foot & Drinks';


