Feature: As a data consumer, User wants UI and DB book categories are match.

	
	@TS4-184 @db @smoke @ui
	Scenario: Verify UI and DB book categories are match
		    Given the user logged in as "librarian"
		    When the user navigates to "Books" page
		    And the user takes all book categories in UI
		    And the user execute query to get book categories from db
		    Then verify book categories must match book_categories table from db
				| Action and Adventure    |
				| Anthology               |
				| Classic                 |
				| Comic and Graphic Novel |
				| Crime and Detective     |
				| Drama                   |
				| Fable                   |
				| Fairy Tale              |
				| Fan-Fiction             |
				| Fantasy                 |
				| Historical Fiction      |
				| Horror                  |
				| Science Fiction         |
				| Biography/Autobiography |
				| Humor                   |
				| Romance                 |
				| Short Story             |
				| Essay                   |
				| Memoir                  |
				| Poetry                  |