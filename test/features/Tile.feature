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
