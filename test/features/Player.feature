Feature: Player

  Scenario: The game has started and 2 Players have begun playing

    Given There are two players playing
    When 1 player finishes their turn
    Then The current players turn ends and the other player's turn begins

    Given The Player chose the found settlement action
    When A Player's turn ends
    Then The Player gains 1 point

    Given The Player chose the expand settlement action
    When A Player's turn ends
    Then The Player Receives the number of meeples squared points for each hex meeples are placed on

    Given Player chose the build totoro action
    When A Player’s turn ends
    And The Totoro was successfully placed
    Then Player receives 200 points

    Given Player chose the build tiger action
    And The chosen hex is on level 3 or greater
    When A Player’s turn ends
    And The Tiger was successfully placed
    Then Player receives 75 points

    Given The player cannot complete any turn option
    When It is one Player's turn
    Then The current player automatically loses

    Given That player has no meeples or totoros left
    When One player finishes their turn
    Then The game ends

    Given The players tie
    When One player who had played all their pieces
    Then The other player loses