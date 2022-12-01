Feature:As a data consumer, I want UI and DB book information are match.

	
	@TS4-186 @db @smoke @ui
	Scenario: Verify UI and DB book information are match
		Given the user logged in as "librarian"
		    And the user navigates to "Books" page
		    When the user open book "Chordeiles minor"
		    Then book information must match the database for "Chordeiles minor"