@B28G7-273
Feature: As a data consumer, I want UI and DB book categories are match.


@db  @B28G7-277

  Scenario: verify book categories with DB
    Given the "librarian" on the home page PM
    When the user navigates to "Books" page PM
    And the user clicks book categories PM
    Then verify book categories must match book_categories table from db PM