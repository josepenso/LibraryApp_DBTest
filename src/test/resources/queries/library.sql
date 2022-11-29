select count(*)
from users;

select count(*)
from books;

select count(*)
from book_borrow
where is_returned=0;

select name
from book_categories;

select *
from books where name= 'Agile Testing';



select bc.name,count(*)
from book_borrow inner join books b on book_borrow.book_id = b.id
inner join book_categories bc on b.book_category_id = bc.id
group by name order by 2 desc

select id
from users;

select *
from users;


