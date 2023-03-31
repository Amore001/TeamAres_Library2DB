@B28G7-289
Feature: Default


  @B28G7-288
  Scenario: US01 TC01 verify users has unique IDs and users table columns
    Given Establish the database connection AJ
  Scenario: verify users has unique IDs
    When Execute query to get all IDs from users AJ
    Then verify all users has unique ID AG


  Scenario: verify users table columns
    When Execute query to get all columns AJ
    Then verify the below columns are listed in result AJ

      | id            |
      | full_name     |
      | email         |
      | password      |
      | user_group_id |
      | image         |
      | extra_data    |
      | status        |
      | is_admin      |
      | start_date    |
      | end_date      |
      | address       |