Feature: Default

	
	@TS4-190 @db
	Scenario Outline: Verify added book is matching with DB
		Given the user logged in as "librarian"
		  And the user navigates to "Books" page
		  When the librarian click to add book
		  And the librarian enter book name "<Book Name>"
		  When the librarian enter ISBN "<ISBN>"
		  And the librarian enter year "<Year>"
		  When the librarian enter author "<Author>"
		  And the librarian choose the book category "<Book Category>"
		  And the librarian click to save changes
		  Then the librarian verify new book by "<Book Name>"
		  Then the librarian verify new book from database by "<Book Name>"
		  Examples:
		    | Book Name             | ISBN     | Year | Author          | Book Category        |
		    | Clean Code            | 79239923 | 2021 | Robert C.Martin | Drama                |
		    | Head First Java       | 976112021 | 2021 | Kathy Sierra    | Action and Adventure |
		    | The Scrum Field Guide | 9967112021 | 2006 | Mitch Lacey     | Short Story          |