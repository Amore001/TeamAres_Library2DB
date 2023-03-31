Feature: Default


  @B28G7-290
  Scenario: US02AC01TC01_AH_Verify librarian can see borrowed books number
    Given the "librarian" on the home page AHU
    When the librarian gets borrowed books number AHU
    Then borrowed books number information must match with DB AHU



