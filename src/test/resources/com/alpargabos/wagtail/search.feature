Feature: Search
  As a registered user on Twitter
  I want to search among tweets
  So that I can read relevant tweets

  Scenario: Search for a word
    Given I am wagtail user
    When I search for "YOLO"
    Then I got tweets which contains the word "YOLO"

  Scenario: Search for a hastag
    Given I am wagtail user
    When I search for hastag #haiku
    Then I got tweets which tagged with #haiku

  Scenario: Search for user mentions
    Given I am wagtail user
    When I search for @mashable
    Then I got tweets where @mashable was mentioned

