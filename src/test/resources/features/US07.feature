Feature: As a student, I should be able to borrow a book

	
	@TS4-192 @db @smoke
	Scenario: Verify user is able to borrow new books
		Given the user logged in as "student"
		  And the user navigates to "Books" page
		  And the user search book "Head First Java"
		  When the user clicks Borrow Book
		  Then  verify that book is shown in "Borrowing Books" page
		  And  verify logged student has same book in database