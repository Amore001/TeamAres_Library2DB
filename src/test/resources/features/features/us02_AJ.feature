Feature: Default

	#_*US:*_
	# * As a librarian, I want to know borrowed books number
	#
	#_*AC:*_
	# * Scenario: verify the total amount of borrowed books
	#Given the "librarian" on the home page
	#When the librarian gets borrowed books number
	#Then borrowed books number information must match with DB
	#
	#
	#select count(*) from book_borrow where is_returned=0;
  @B28G7-279 @db
  Scenario: US02_AC01_TC01_Verify librarian can see borrowed books number
    Given the "librarian" on the home page AH
    When the librarian gets borrowed books number AH
    Then borrowed books number information must match with DB AH