Feature: Update status
  As a registered user on Twitter
  I want to write a new tweet
  So my followers can see what is happening with me

  Scenario: Write a new tweet
    Given I am wagtail user
    When I update my status to: "I am on ACCU!"
    Then the following will appear on my time line
    """

    """

  Scenario: Write a longer tweet than 140 characters
    Given I am wagtail user
    When I update my status to: "This is a too long tweet, which means it is invalid. Therefore wont appear on my time line! Ho ho ho ho ho hi hi hi hi hi hi hi grrr grgrrrgrrr"
    Then nothing happens on my time line