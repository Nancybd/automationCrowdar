Feature: Login with valid credentials

  @login
  Scenario Outline: User log in page
    Given I am in Login page
    When I enter <user> in Username field
    And I enter <pass> in Password field
    And I click on Login button
    Then I should be able to see <title> title
    When I click on burger menu
    And I click on Logout link
    Then I should be able to see Login page
    Examples:
      | user                      | pass           | title      |
      | "standard_user"           | "secret_sauce" | "PRODUCTS" |
      | "problem_user"            | "secret_sauce" | "PRODUCTS" |
      | "performance_glitch_user" | "secret_sauce" | "PRODUCTS" |
