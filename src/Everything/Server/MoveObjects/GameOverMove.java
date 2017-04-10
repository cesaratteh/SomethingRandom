package Everything.Server.MoveObjects;


public class GameOverMove implements Move{

    private String gameID;

    public GameOverMove(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
