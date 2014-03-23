Feature: Login
  As a registered user on Twitter
  I want to login to Wagtail
  So that I can access to my twitter account from command line

  Scenario: Login with existing credentials
    Given I am a twitter user
    When I login with my valid credentials
    Then I will be greeted on my full name

  @wip
  Scenario: Login with invalid credentials
    Given I am a twitter user
    When I try to login with false credentials
    Then I am notified about the unsuccessful login
      And I can try the login again
