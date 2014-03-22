Feature: Login
  As a registerd user on Twitter
  I want to login to Wagtail
  So that I can acces to my twitter account from command line

  Scenario: Login with existing credentials
    Given I am a twitter user
    When I provide my credentials
    Then I will be logged in to my twitter account from command line

  Scenario: Login with invalid credentials
    Given I am a twitter user
    When I provide invalid credentials
    Then I am notified about my invalid credentials
      And I can type my credentials again
