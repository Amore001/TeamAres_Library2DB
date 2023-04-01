Feature: Default

  @B28G7-297 @db
  Scenario: US04 Test case01
    Given the "librarian" on the home page AN
    And the user navigates to "Books" page AN
    When the user searches for "Fireflies & Family Ties (South Carolina Sunsets Book 3)" book AN
    And  the user clicks edit book button AN
    Then book information must match the Database AN