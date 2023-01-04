Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.

	Background:
		Given Establish the database connection

	@TS4-178 @smoke
	Scenario: verify users has unique IDs
		    When Execute query to get all IDs from users
		    Then verify all users has unique ID

	@TS4-180 @smoke
	Scenario: Verify user column names
	  	When Execute query to get all columns
		Then verify the below columns are listed in the result
			| id            |
			| full_name     |
			| email         |
			| password      |
			| user_group_id |
			| image         |
			| extra_data    |
			| status        |
			| is_admin      |
			| start_date    |
			| end_date      |
			| address       |