Feature: Test Orange HRM Application

  Scenario: Test Login Page Functionality
    Given user is on Login Page
    When user enter valid credentails
      | Admin | admin123 |
    And user click on login button

  Scenario: Test Home Page Functionality
    When user is on home page validate home page title
      | OrangeHRM |
    And user validate home page url
    And user validate home page logo
      | true |

  Scenario: Test PIM Page Functionality
    When user click on pim page link
    And user validate user is on pim page by using url
      | pim |
    And user click add employee and enter firstname, lastname and click on save button
      | Anita   | Walke   |
      | Kundan  | Patel   |
     
    And user capture employee id number and user click on employeelist
    And user enter employee id is employee id text  box and click on search button
    And user selected searched employee and click on delete
    Then validate employee is deleted or not

  Scenario: Test Logout functionality
    When user click on profile and click on logout button
