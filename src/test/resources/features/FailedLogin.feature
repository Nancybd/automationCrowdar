Feature: Failed login with non valid credentials
  @login
    Scenario Outline: User cannot login in page
      Given I am in Login page
      When I enter <user> in Username field
      And I enter <pass> in Password field
      And I click on Login button
      Then I should be able to see warning message <redMsg>
      When I click on burger menu
      And I click on Logout link
      Then I should be able to see Login page
      Examples:
        | user                      | pass             | redMsg                                                                       |
        | ""                        | ""               | "Epic sadface: Username is required"                                         |
        | "standard_user"           | ""               | "Epic sadface: Password is required"                                         |
        | "standard_user"           | "wrongPassword"  | "Epic sadface: Username and password do not match any user in this service"  |
        | "locked_out_user"         | "wrongPassword"  | "Epic sadface: Sorry, this user has been locked out."                        |

