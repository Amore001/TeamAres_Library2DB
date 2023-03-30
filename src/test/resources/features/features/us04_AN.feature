
Feature: As a data consumer, I want UI and DB book information are match.
@wip @ui @db
  Scenario: Verify book information with DB
    Given the "librarian" on the home page AN
    And the user navigates to "Books" page AN
    When the user searches for "Fireflies & Family Ties (South Carolina Sunsets Book 3)" book AN
    And  the user clicks edit book button AN
    Then book information must match the Database AN