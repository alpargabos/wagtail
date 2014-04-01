Feature: Login
  As a registered user on Twitter
  I want to login to Wagtail
  So that I can access to my twitter account from command line

  Scenario: Login with existing credentials
    Given I am a twitter user
    When I grant access to my account for Wagtail
    Then I will be greeted on my full name