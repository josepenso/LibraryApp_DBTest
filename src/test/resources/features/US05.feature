Feature:As a librarian, I want to know what genre of books is being borrowed the most

	
	@TS4-188  @smoke
	Scenario: Verify the common book genre that’s being borrowed

		Given Establish the database connection
		When I execute query to find most popular book genre
		Then verify "Fantasy" is the most popular book genre.