Feature: As a librarian, I want to know borrowed books number

	
	@TS4-182 @db @smoke @ui
		Scenario: verify the total amount of borrowed books
		  Given the user logged in as "librarian"
		  When user take borrowed books number
		  Then borrowed books number information must match with DB