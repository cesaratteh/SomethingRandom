package models;

public class Player {

    //-----------
    // Attributes

    private int numberOfMeeplesLeft;
    private int numberOfTotorosLeft;
    private int numberOfTigersLeft;
    private final Team team;

    //------------
    //Constructors

    public Player(Team team)
    {
        this.numberOfMeeplesLeft = NUMBER_OF_STARTING_MEEPLES;
        this.numberOfTotorosLeft = NUMBER_OF_STARTING_TOTOROS;
        this.numberOfTigersLeft = NUMBER_OF_STARTING_TIGERS;
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

    boolean isHasTigersLeft() {
        return numberOfTigersLeft > 0;
    }

    //-----------
    // Getters


    int getNumberOfTotorosLeft() {
        return numberOfTotorosLeft;
    }

    int getNumberOfMeeplesLeft() {
        return numberOfMeeplesLeft;
    }

    int getNumberOfTigersLeft() {
        return numberOfTigersLeft;
    }

    public Team getTeam() {
        return team;
    }

    //----------
    // Constants

    private static final int NUMBER_OF_STARTING_MEEPLES = 20;
    private static final int NUMBER_OF_STARTING_TOTOROS = 3;
    private static final int NUMBER_OF_STARTING_TIGERS = 2;

}
