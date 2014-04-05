Feature: User time line
  As a registered user on Twitter
  I want to see my time line
  So I know what's up with my friends

  Scenario: Get time line
    Given I am wagtail user
    When I ask for my time line
    Then I got the latest tweets