Feature: Validating place Api

  @AddPlace
  Scenario Outline: User is able to add place successfully using addplace api
    Given add place payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "post" http req
    Then api call is successfull with status code "200"
    And "status" response body "OK"
    And "scope" response body "APP"
    And verify placeid created maps to "<name>" using "getplaceapi"

    Examples: 
      | name | language | address |
      | aa   | eng      | pune    |
      | BB   | Russian  | Russia  |

  @deleteplace
  Scenario: User is able to delete place successfully using addplace api
    Given deleteplace  payload
    When user calls "delplaceapi" with "post" http req
    Then api call is successfull with status code "200"
    And "status" response body "OK"
