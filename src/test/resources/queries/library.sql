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
group by name order by 2 desc;

select full_name,b.name,bb.borrowed_date from users u
                                                  inner join book_borrow bb on u.id = bb.user_id
                                                  inner join books b on bb.book_id = b.id
where full_name='Test Student 38'
order by 3 desc;


select name
from books where name = 'CLean Code';

select name, isbn from books where name like 'Clean Code' and isbn like '09112021' order by isbn desc

select full_name,b.name,bb.borrowed_date from users u
                                                  inner join book_borrow bb on u.id = bb.user_id
                                                  inner join books b on bb.book_id = b.id
where full_name='Test Student 29' and name = 'Head First Java'
order by 3 desc;


select full_name,email,status
from users where email ='alesha.stroman@gmail.com' ;


select *
from book_categories;

select count(*)
from book_borrow where is_returned=0;

select *
from books where name like '%HEAD JAVA FIRST%';


select bc.name,count(*)
from book_borrow bb inner join books b on bb.book_id = b.id
    inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;


select *
from books where name like '%KING OF JAVA%';








