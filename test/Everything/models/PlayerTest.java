package Everything.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    final Random random = new Random();
    private Player player;
    
    @Before
    public void initializePlayer(){
        player = new Player(Team.FRIENDLY);
    }

    @Test
    public void initialNumberOfMeeplesTest() {
        assertEquals(20, player.getNumberOfMeeplesLeft());
    }

    @Test
    public void initialNumberOfTotorosTest() {
        

        assertEquals(3, player.getNumberOfTotorosLeft());
    }

    @Test
    public void initialNumberOfTigersTest() {


        assertEquals(2, player.getNumberOfTigersLeft());
    }

    @Test
    public void isHasTotorosLeft() {


        assertEquals(true, player.isHasTotorosLeft());

        while(player.getNumberOfTotorosLeft() > 0) {
            player.takeATotoroFromPlayer();
        }

        assertEquals(false, player.isHasTotorosLeft());
    }

    @Test
    public void isHasTigersLeft() {


        assertEquals(true, player.isHasTigersLeft());

        while(player.getNumberOfTigersLeft() > 0) {
            player.takeATigerFromPlayer();
        }

        assertEquals(false, player.isHasTigersLeft());
    }

    @Test
    public void isHasEnoughMeeplesTest() {


        assertEquals(true,
                player.isHasEnoughMeeples(player.getNumberOfMeeplesLeft()));
        assertEquals(false,
                player.isHasEnoughMeeples( 1 + player.getNumberOfMeeplesLeft()));
    }

    @Test
    public void takeXMeeplesFromPlayerTest() {


        final int initialNumberOfMeeples = player.getNumberOfMeeplesLeft();
        final int numberOfMeeplesToTakeAway = random.nextInt(initialNumberOfMeeples);

        player.takeXMeeplesFromPlayer(numberOfMeeplesToTakeAway);
        assertEquals(initialNumberOfMeeples - numberOfMeeplesToTakeAway, player.getNumberOfMeeplesLeft());
    }
}
