Feature: Nuke

  Scenario: A game is in progress

    Given There are Hexagons that are on the same level
    And The Hexagons are part of at least 2 different Tiles
    And They do not contain a Totoro
    And They do not contain a Tiger
    When The Hexagons are Nuked
    Then The new Tile is placed on top of the Hexagons
    And The population of the Hexagons are removed

    Given There are Hexagons that are on the same level
    And  The Hexagons are part of at least 2 different Tiles
    When The Hexagons are Nuked
    And The volcano Hexagons of each layer match up
    Then The Tile is placed on top of the Hexagons
    And The population of the Hexagons are removed
