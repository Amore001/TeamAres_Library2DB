
Feature: Books module
  As a librarian, I should be able to add new book into library

  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page ZA
    And the user navigates to "Books" page ZA
    When the librarian click to add book ZA
    And the librarian enter book name "<Book Name>" ZA
    When the librarian enter ISBN "<ISBN>" ZA
    And the librarian enter year "<Year>" ZA
    When the librarian enter author "<Author>" ZA
    And the librarian choose the book category "<Book Category>" ZA
    And the librarian click to save changes ZA
    Then verify "The book has been created" message is displayed ZA
    And verify "<Book Name>" information must match with DB ZA
    Examples:
      | Book Name             | ISBN     | Year | Author          | Book Category        |
      | Head First Java       | 10112021 | 2021 | Kathy Sierra    | Action and Adventure |
      | The Scrum Field Guide | 11112021 | 2006 | Mitch Lacey     | Short Story          |