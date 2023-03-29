
Feature: Books module
  As a librarian, I should be able to add new book into library

  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page BZ
    And the user navigates to "Books" page BZ
    When the librarian click to add book BZ
    And the librarian enter book name "<Book Name>" BZ
    When the librarian enter ISBN "<ISBN>" BZ
    And the librarian enter year "<Year>" BZ
    When the librarian enter author "<Author>" BZ
    And the librarian choose the book category "<Book Category>" BZ
    And the librarian click to save changes BZ
    Then verify "The book has been created" message is displayed BZ
    And verify "<Book Name>" information must match with DB BZ
    Examples:
      | Book Name             | ISBN     | Year | Author          | Book Category        |
      | Head First Java       | 10112021 | 2021 | Kathy Sierra    | Action and Adventure |
      | The Scrum Field Guide | 11112021 | 2006 | Mitch Lacey     | Short Story          |