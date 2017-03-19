package models;

import models.Tile;

public class Map {
    private Tile mapSeedTile;

    Map(Tile seedTile){
        mapSeedTile = seedTile;
        mapSeedTile.setLevel(1);

    }

    public Tile getSeedTile(){
        return mapSeedTile;
    }

    public void placeNewTile(Tile tileToBePlaced){

    }
}
