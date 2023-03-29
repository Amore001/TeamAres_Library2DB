
Feature: Books module
  As a students, I should be able to borrow book
@wipAli
  Scenario: Student borrow new book
    Given the "student" on the home page AS
    And the user navigates to "Books" page AS
    And the user searches for "Self Confidence" book AS
    When the user clicks Borrow Book AS
    Then verify that book is shown in "Borrowing Books" page AS
    And  verify logged student has same book in database AS