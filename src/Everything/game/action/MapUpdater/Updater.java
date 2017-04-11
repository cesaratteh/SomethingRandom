package Everything.game.action.MapUpdater;


import Everything.game.action.scanners.settlemenet.expanding.ExpansionToSpecificTerrainScanner;
import Everything.models.*;
import Everything.game.action.scanners.*;


import java.util.ArrayList;

import static Everything.models.Team.ENEMY;


public class Updater {

    private Player player;
    private Map map;
    public Updater(Map map){

        this.map = map;
    }




    public void SetFirstTile(){


        MapSpot mapspot = new MapSpot(0,0,0);
        Hexagon hex1 = new Hexagon(Terrain.VOLCANO,1,0);
        map.setHexagon(mapspot, hex1);

        hex1 = new Hexagon(Terrain.JUNGLE,1,0);
        map.setHexagon(mapspot.topLeft(),hex1);

        hex1 = new Hexagon(Terrain.LAKE,1,0);
        map.setHexagon(mapspot.topRight(),hex1);

        hex1 = new Hexagon(Terrain.ROCKY,1,0);
        map.setHexagon(mapspot.bottomLeft(),hex1);

        hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        map.setHexagon(mapspot.bottomRight(),hex1);



    }



    //Does Not cover expand will be a different method
    //Move Number 1=Found Settlement 2=Expand 3=build Totoro 4=build tiger
    //int MoveNumber, Terrain A, Terrain B, MapSpot TileSpot, int Orientation, int TurnChoice, MapSpot ExpandLocation
    //Order above, if doing Expand action use other method below


    public void EnemyMove(int MoveNumber, Terrain A, Terrain B, MapSpot TileSpot, int Orientation, int TurnChoice, MapSpot ExpandLocation){
        Player Team = new Player(ENEMY);

        int TileLevel = 0;

        MapSpot hexSpot2 = new MapSpot(0,0,0);
        MapSpot hexSpot3 = new MapSpot(0,0,0);


        switch(Orientation) {
            case 1: hexSpot2 = TileSpot.topLeft(); hexSpot3 = TileSpot.topRight();
            break;
            case 2: hexSpot2 = TileSpot.topRight(); hexSpot3 = TileSpot.right();
            break;
            case 3: hexSpot2 = TileSpot.right(); hexSpot3 = TileSpot.bottomRight();
            break;
            case 4: hexSpot2 = TileSpot.bottomRight(); hexSpot3 = TileSpot.bottomLeft();
            break;
            case 5: hexSpot2 = TileSpot.bottomLeft(); hexSpot3 = TileSpot.left();
            break;
            case 6: hexSpot2 = TileSpot.left(); hexSpot3 = TileSpot.topLeft();
            break;

        }
        if(this.map.getHexagon(TileSpot) == null){

            TileLevel = 1;
        }
        else{
            TileLevel = this.map.getHexagon(TileSpot).getLevel() +1;
        }



        //Places Tiles
        Hexagon hex1 = new Hexagon(Terrain.VOLCANO, TileLevel,MoveNumber);
        this.map.setHexagon(TileSpot, hex1);
        Hexagon hex2 = new Hexagon(A,TileLevel,MoveNumber);
        this.map.setHexagon(hexSpot2, hex2);
        Hexagon hex3 = new Hexagon(B,TileLevel,MoveNumber);
        this.map.setHexagon(hexSpot3, hex3);




        switch(TurnChoice){
            case 1: this.map.getHexagon(ExpandLocation).addMeeplesAccordingToLevel(Team.getTeam());
            break;
            case 3: this.map.getHexagon(ExpandLocation).addTotoro(Team.getTeam());
            break;
            case 4: this.map.getHexagon(ExpandLocation).addTiger(Team.getTeam());
            break;
        }



    }


    public void EnemyMoveExpand(int MoveNumber, Terrain A, Terrain B, MapSpot TileSpot, int Orientation, int TurnChoice, MapSpot ExpandLocation, Terrain ExpandTerrain) throws NoValidActionException {
        Player Team = new Player(ENEMY);


        MapSpot hexSpot2 = new MapSpot(0,0,0);
        MapSpot hexSpot3 = new MapSpot(0,0,0);

        int TileLevel = 0;


        switch(Orientation) {
            case 1: hexSpot2 = TileSpot.topLeft(); hexSpot3 = TileSpot.topRight();
                break;
            case 2: hexSpot2 = TileSpot.topRight(); hexSpot3 = TileSpot.right();
                break;
            case 3: hexSpot2 = TileSpot.right(); hexSpot3 = TileSpot.bottomRight();
                break;
            case 4: hexSpot2 = TileSpot.bottomRight(); hexSpot3 = TileSpot.bottomLeft();
                break;
            case 5: hexSpot2 = TileSpot.bottomLeft(); hexSpot3 = TileSpot.left();
                break;
            case 6: hexSpot2 = TileSpot.left(); hexSpot3 = TileSpot.topLeft();
                break;

        }

        if(this.map.getHexagon(TileSpot) == null){

            TileLevel = 1;
        }
        else{
            TileLevel = this.map.getHexagon(TileSpot).getLevel() +1;
        }



        //Places Tiles
        Hexagon hex1 = new Hexagon(Terrain.VOLCANO, TileLevel,MoveNumber);
        this.map.setHexagon(TileSpot, hex1);
        Hexagon hex2 = new Hexagon(A,TileLevel,MoveNumber);
        this.map.setHexagon(hexSpot2, hex2);
        Hexagon hex3 = new Hexagon(B,TileLevel,MoveNumber);
        this.map.setHexagon(hexSpot3, hex3);
        //



        Settlement EnemySettlements = new Settlement(Team.getTeam());

        SettlementsFactory Settlementfinder = new SettlementsFactory();

        ArrayList<Settlement> ListOfSettlements = Settlementfinder.generateSettlements(map, Team.getTeam());


        ExpansionToSpecificTerrainScanner Scanner = new ExpansionToSpecificTerrainScanner();


        for(int i =0; i < ListOfSettlements.get(0).getMapSpots().size(); i++){




            if(ListOfSettlements.get(0).getMapSpots().get(i).isEqual(ExpandLocation)){
                EnemySettlements = ListOfSettlements.get(i);


            }
            else{

            }
        }

        ArrayList<MapSpot> ExpansionSpots =  Scanner.scan(EnemySettlements, this.map, ExpandTerrain);

        for(int i = 0; i < ExpansionSpots.size(); i++){
            this.map.getHexagon(ExpansionSpots.get(i)).addMeeplesAccordingToLevel(Team.getTeam());
        }






    }






}
