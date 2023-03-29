
Feature: As a data consumer, I want UI and DB book information are match.

  Scenario: Verify book information with DB
    Given the "librarian" on the home page AN
    And the user navigates to "Books" page AN
    When the user searches for "Clean Code" book AN
    And  the user clicks edit book button AN
    Then book information must match the Database AN