public class Player {

    //-----------
    // Attributes

    private int numberOfTotorosLeft;
    private int numberOfMeeplesLeft;

    //------------
    //Constructors

    public Player()
    {
        this.numberOfMeeplesLeft = NUMBER_OF_STARTING_MEEPLES;
        this.numberOfTotorosLeft = NUMBER_OF_STARTING_TOTOROS;
    }

    //--------
    // Methods

    void placeTotoro() {
        if (!isHasTotorosLeft()) {
            throw new RuntimeException("Can't place totoro. Player doesn't have enough"); // TODO: 3/19/2017 Replace with logging
        }

        numberOfTotorosLeft--;
    }

    void placeMeeples(final int numberOfMeeplesToPlace) {
        if ( !isHasEnoughMeeples(numberOfMeeplesToPlace) ) {
            throw new RuntimeException("Can't place meeples. Player doesn't have enough"); // TODO: 3/19/2017 Replace with logging
        }

        numberOfMeeplesLeft -= numberOfMeeplesToPlace;
    }

    boolean isHasTotorosLeft() {
        return numberOfTotorosLeft != 0;
    }

    boolean isHasEnoughMeeples(final int numberOfMeeples) {
        return numberOfMeeplesLeft >= numberOfMeeples;
    }

    //-----------
    // Getters

    public int getNumberOfTotorosLeft() {
        return numberOfTotorosLeft;
    }

    public int getNumberOfMeeplesLeft() {
        return numberOfMeeplesLeft;
    }

    //----------
    // Constants

    private static final int NUMBER_OF_STARTING_MEEPLES = 10;
    private static final int NUMBER_OF_STARTING_TOTOROS = 2;
}
