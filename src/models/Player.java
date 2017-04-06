package models;

public class Player {

    //-----------
    // Attributes

    private int numberOfTotorosLeft;
    private int numberOfMeeplesLeft;
    private int numberOfTigersLeft;
    private int score;
    private final Team team;

    //------------
    //Constructors

    public Player(Team team)
    {
        this.numberOfMeeplesLeft = NUMBER_OF_STARTING_MEEPLES;
        this.numberOfTotorosLeft = NUMBER_OF_STARTING_TOTOROS;
        this.numberOfTigersLeft = NUMBER_OF_STARTING_TIGERS;
        this.score = 0;
        this.team = team;
    }

    //--------
    // Methods

    void takeATotoroFromPlayer() {
        if (!isHasTotorosLeft()) {
            throw new RuntimeException("Can't place totoro. models.Player doesn't have enough"); // TODO: 3/19/2017 Replace with logging
        }

        numberOfTotorosLeft--;
    }

    void takeXMeeplesFromPlayer(final int numberOfMeeplesToPlace) {
        if ( !isHasEnoughMeeples(numberOfMeeplesToPlace) ) {
            throw new RuntimeException("Can't place meeples. models.Player doesn't have enough"); // TODO: 3/19/2017 Replace with logging
        }
        numberOfMeeplesLeft -= numberOfMeeplesToPlace;
    }
    void takeATigerFromPlayer() {
        if (!isHasTigersLeft()) {
            throw new RuntimeException("Can't place tiger. models.Player doesnt have enough");
        }

        numberOfTigersLeft--;
    }


    boolean isHasTotorosLeft() {
        return numberOfTotorosLeft > 0;
    }

    boolean isHasEnoughMeeples(final int numberOfMeeples) {
        return numberOfMeeplesLeft >= numberOfMeeples;
    }

    boolean isHasTigersLeft() {return numberOfTigersLeft > 0; }
    //-----------
    // Getters

    public int getNumberOfTotorosLeft() {
        return numberOfTotorosLeft;
    }

    public int getNumberOfMeeplesLeft() {
        return numberOfMeeplesLeft;
    }

    public int getNumberOfTigersLeft() {return numberOfTigersLeft;}

    //----------
    // Constants

    private static final int NUMBER_OF_STARTING_MEEPLES = 20;
    private static final int NUMBER_OF_STARTING_TOTOROS = 3;
    private static final int NUMBER_OF_STARTING_TIGERS = 2;

    public Team getTeam() {
        return team;
    }

    public int getScore() {
        return score;
    }
}
