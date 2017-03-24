Feature: Tile

  Scenario: A game is currently in progress

    Given A Player draws a Tile
    When The Tile is chosen from the set of remaining Tiles
    Then The Tile has all 3 appropriately attached Hexagons
    And The Tile has 1 volcano and 2 other Terrain types

    Given A Player draws a Tile
    When The Player looks at a Hexagon contained in the Tile
    Then The Hexagon has a Terrain
    And The Hexagon has an empty space for Meeples or Totoros

    Given There are Tiles on level 1 or higher on the board
    When The Player wants to nuke some Hexagons with a new Tile
    And At least 2 of the Hexagons are on different Tiles
    And All of the Hexagons are on the same level
    Then The Player nukes the Hexagons
    And  Places the Tile

    Given There are Tiles on level 1 or higher on the board
    When The Player wants to nuke some Hexagons with a new Tile
    And All of the Hexagons belong to the same Tile
    And All of the Hexagons are on the same level
    Then The Player nukes the Hexagons
    And  Places the Tile

