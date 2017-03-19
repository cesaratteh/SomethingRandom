# SomethingRandom


LOG:


Moving away from linked structure to a matrix map
-------------------------------------------------
Using a linked structure makes it almost impossible to detect a "full circle of hexagons".
We would end up making an illegal move by placing tiles in the same spot without knowing.
Also, would can always do a matrix map as the base data structure, and then add links to hexagons if needed. Although there are better ways of doing this.