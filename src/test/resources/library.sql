
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


select b.name,b.author, b.isbn, b.year, b.description from books b inner join
book_categories bc on b.book_category_id = bc.id
                where b.name = 'Fireflies & Family Ties (South Carolina Sunsets Book 3)';
