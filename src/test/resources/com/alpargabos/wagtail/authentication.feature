Feature: Authentication
  As a registered user on Twitter
  I want to login to Wagtail
  So that I can access to my twitter account from command line

  Scenario: Login with existing credentials
    Given I am a twitter user
    When I grant access to my account for Wagtail
    Then I will be greeted on my full name

  Scenario: Login with invalid credentials
    Given I am a twitter user
    When I don't grant access to my account for Wagtail
    Then I will be notified about the unsuccessful login

  Scenario: Wagtail remembers the credentials after a successful login
    Given I am an authenticated user who closed the application
    When I start the application again
    Then I will be greeted on my full name

  Scenario: Wagtail forgot the credentials after logout
    Given I am an authenticated user who logs out
    When I start the application again
    Then I will be asked to grant access to my twitter account
