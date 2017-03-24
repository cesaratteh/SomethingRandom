Feature: GameBoard

  Scenario: A game has been started

    Given There are no Tiles
    When A Tile is placed
    Then The Tile starts a new board
    And The Tile is on level one

    Given There is an existing board
    When placing a Tile adjacent to another Tile
    Then The Tile is placed on level one in the correct spot