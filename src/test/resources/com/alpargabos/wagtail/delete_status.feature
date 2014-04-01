Feature: Update status
  As a registered user on Twitter
  I want to delete a tweet
  So my mum cannot find my tweets written in drunk state

  Scenario: Delete one of my tweets
    Given I am wagtail user
    When I delete one of my tweet
    Then the tweet disappears from my time line

  Scenario: I cannot delete others tweets
    Given I am wagtail user
    When I try to delete one of my friends tweet
    Then nothing happens with that tweet