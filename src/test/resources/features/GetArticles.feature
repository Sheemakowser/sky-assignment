Feature: Get Articles

  Background:
    Given I have a valid endpoint

  Scenario: Get a list of articles using valid endpoint
    When I attempt to get a list of articles
    Then the received response status code is 200
    And the response format is JSON
    And the response contains the list of articles

  Scenario Outline: Get the article <id> using valid endpoint
    When I attempt to get an article with id <id>
    Then the received response status code is 200
    And the response format is JSON
    And the response contain an article <id> with "<title>"

    Examples:
      | id            | title                                   |
      | 1             | title 1                                 |
      | 2             | title 2                                 |
      | 3             | £31 million pounds shortfall in economy |
      | 4656364867443 | title 4                                 |
      | 5             | title 5                                 |

  Scenario: Get a particular article which is not in the list of articles
    When I attempt to get an article with id 6
    Then the received response status code is 404
    And the response body has value "Not found"

  Scenario: Get articles from an invalid path
    When I attempt to get articles from an invalid path
    Then the received response status code is 404
    And the response body has value "Not found"

  Scenario Outline: Perform a POST action using valid endpoint - <path>
    When I attempt to perform a POST action using valid path "<path>"
    Then the received response status code is 404
    And the response body has value "Endpoint disabled"
    Examples:
      | path                  |
      | /tech-test/articles   |
      | /tech-test/articles/2 |

  Scenario Outline: Perform a PUT action using valid endpoint - <path>
    When I attempt to perform a PUT action using valid path "<path>"
    Then the received response status code is 404
    And the response body has value "Endpoint disabled"
    Examples:
      | path                  |
      | /tech-test/articles   |
      | /tech-test/articles/2 |

  Scenario Outline: Perform a DELETE action using valid endpoint - <path>
    When I attempt to perform a DELETE action using valid path "<path>"
    Then the received response status code is 404
    And the response body has value "Endpoint disabled"
    Examples:
      | path                  |
      | /tech-test/articles   |
      | /tech-test/articles/2 |
