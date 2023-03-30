
-- US 01
select count(id) from users; --
-- 1855

select count(distinct id) from users;
-- 1855


-- RESULT --> MANUALLY IT IS PASSED


-- US 02
select * from users;



-- US 03
select count(*) from book_borrow
where is_returned=0;

-- US5

select bc.name, count(*) from book_borrow
inner join books b on book_borrow.book_id = b.id
inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

-- US7
select full_name,b.name,bb.borrowed_date from users u
inner join book_borrow bb on u.id = bb.user_id
inner join books b on bb.book_id = b.id
where full_name='Test Student 5'
order by 3 desc;



