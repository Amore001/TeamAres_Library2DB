Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.
  Background:
    Given Establish the database connection AJ
  @us01 @db
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
