Feature: GameBoard

  Scenario: A game has been started

    Given There are no Tiles
    When A starting tile is placed
    Then The Tile starts a new board in the center
    And The Tile is on level one

    Given There are no Tiles
    When A regular tile is placed
    Then The move is illegal

    Given There is an existing board
    When placing a Tile adjacent to another Tile
    Then The Tile is placed on level one in the correct spot