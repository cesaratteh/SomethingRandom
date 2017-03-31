package models;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    final Random random = new Random();

    @Test
    public void initialNumberOfMeeplesTest() {
        final Player player = new Player();

        assertEquals(20, player.getNumberOfMeeplesLeft());
    }

    @Test
    public void initialNumberOfTotorosTest() {
        final Player player = new Player();

        assertEquals(3, player.getNumberOfTotorosLeft());
    }

    @Test
    public void initialNumberOfTigersTest() {
        final Player player = new Player();

        assertEquals(2, player.getNumberOfTigersLeft());
    }

    @Test
    public void isHasTotorosLeft() {
        final Player player = new Player();

        assertEquals(true, player.isHasTotorosLeft());

        while(player.getNumberOfTotorosLeft() > 0) {
            player.takeATotoroFromPlayer();
        }

        assertEquals(false, player.isHasTotorosLeft());
    }

    @Test
    public void isHasTigersLeft() {
        final Player player = new Player();

        assertEquals(true, player.isHasTigersLeft());

        while(player.getNumberOfTigersLeft() > 0) {
            player.takeATigerFromPlayer();
        }

        assertEquals(false, player.isHasTigersLeft());
    }

    @Test
    public void isHasEnoughMeeplesTest() {
        final Player player = new Player();

        assertEquals(true,
                player.isHasEnoughMeeples(player.getNumberOfMeeplesLeft()));
        assertEquals(false,
                player.isHasEnoughMeeples( 1 + player.getNumberOfMeeplesLeft()));
    }

    @Test
    public void takeXMeeplesFromPlayerTest() {
        final Player player = new Player();

        final int initialNumberOfMeeples = player.getNumberOfMeeplesLeft();
        final int numberOfMeeplesToTakeAway = random.nextInt(initialNumberOfMeeples);

        player.takeXMeeplesFromPlayer(numberOfMeeplesToTakeAway);
        new Player();
        assertEquals(initialNumberOfMeeples - numberOfMeeplesToTakeAway, player.getNumberOfMeeplesLeft());
    }
}
