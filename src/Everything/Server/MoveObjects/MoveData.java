package Everything.Server.MoveObjects;

public class MoveData {

    public boolean gameOver = false;
    public Move move;
    public Consumer consumer;

    public MoveData(boolean gameOver, Move move, Consumer consumer) {
        this.gameOver = gameOver;
        this.move = move;
        this.consumer = consumer;
    }

    public enum Consumer {
        CLIENT,
        THREAD;
    }
}
