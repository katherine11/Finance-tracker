USE finance_track_test;

insert into users values (null, 'vasko', 'vasil_v@abv.bg');

insert into incomes values (null, 'Grants');

insert into repeatings values (null, 'Yearly');

insert into users_has_incomes values (14, 7, 4, 600, curdate(), 'Rent from Apartment');	

select * from users_has_incomes where user_id = 8;

select i.incomes_id, i.category,r.value, r.repeating_id, uhi.amount, uhi.date, uhi.description from users u
join users_has_incomes uhi on (uhi.user_id = 14 and u.user_id = 14)
join incomes i on (i.incomes_id in (select uhi.incomes_id where uhi.user_id = 14))
join repeatings r on (r.repeating_id in (select uhi.repeating_id where uhi.user_id = 14));

insert into expenses values (null, 'Taxes');

insert into users_has_expenses values (14, 4, 1, 500, '2016-09-01', 'BMX');

select * from users_has_expenses where user_id = 9;

select e.expenses_id, e.category, r.value, r.repeating_id, uhe.amount, uhe.date, uhe.description from users u
join users_has_expenses uhe on (uhe.user_id = 14 and u.user_id = 14)
join expenses e on (uhe.expenses_id = e.expenses_id)
join repeatings r on (r.repeating_id = uhe.repeating_id);

select i.incomes_id, i.category,r.value, r.repeating_id,
uhi.amount, uhi.date, uhi.description from users u
join users_has_incomes uhi on (uhi.user_id = 14 and u.user_id = 14)
join incomes i on (uhi.incomes_id = i.incomes_id)
join repeatings r on (r.repeating_id = uhi.repeating_id) LIMIT 0, 1000;

insert into obligations values (null, 'Fast Credit');

insert into period values (null, 'Years');

insert into users_has_obligations 
values (8, 2, 1, 2, 2000, now(), 'Vzeh ot syseda pari', null);

select * from users_has_obligations where users_user_id = 8;

TRUNCATE TABLE users;

insert into users values (null, 'Pesho', 'pesho@abv.bg', md5(1234));

select expenses_id from expenses where category like 'Foot & Drinks';

DELETE FROM `finance_track_test`.`users_has_expenses` WHERE `id`='7';

insert into users_has_obligations values (14, 1, 4, 4, 10000, '2016-09-01', 'Kredit ot OBB', 5, null);

select o.obligation_id, o.category, r.value, r.repeating_id, uho.amount, uho.date, 
uho.description, uho.id, p.period_id, p.period_type, uho.period_quantity from users u
join users_has_obligations uho on (uho.user_id = 14 and u.user_id = 14)
join obligations o on (uho.obligation_id = o.obligation_id)
join repeatings r on (r.repeating_id = uho.repeating_id)
join period p on (p.period_id = uho.period_id);

insert into users_has_budgets values (14, 1, 4, 500, '2016-09-25', 'Description text');

DELETE FROM users_has_budgets WHERE user_id = 14 and expense_id = 1;

select e.expenses_id, e.category, r.repeating_id, r.value, uhb.amount, uhb.date, uhb.description from users u
join users_has_budgets uhb on (uhb.user_id = 14 and u.user_id = 14)
join expenses e on (uhb.expense_id = e.expenses_id)
join repeatings r on (r.repeating_id = uhb.repeating_id);
