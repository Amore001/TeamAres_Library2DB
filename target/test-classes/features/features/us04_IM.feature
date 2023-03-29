
Feature: As a data consumer, I want UI and DB book information are match.

  Scenario: Verify book information with DB
    Given the "librarian" on the home page IM
    And the user navigates to "Books" page IM
    When the user searches for "Clean Code" book IM
    And  the user clicks edit book button IM
    Then book information must match the Database IM