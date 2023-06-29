package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.Player;
import junit.framework.TestCase;

public class GameProxyControllerTest extends TestCase {

    //verify that the initially next is null
    public void testGetNext() {
        GameController gameController = new GameController(3,1);
        assertNull(gameController.getNext());
    }
   /*
    //testing playable with four players
    public void testPlayable4Player() {
        Player Gioggio = new Player("Gioggio");
        Player Peppe = new Player("Peppe");
        Player Fil = new Player("Fil");
        Player Rick = new Player("Rick");
        GameController gameController = new GameController(4,1);
        //adding three player -> playable should return false
        gameController.addPlayer(Gioggio);
        gameController.addPlayer(Peppe);
        gameController.addPlayer(Fil);
        assertFalse(gameController.playable());
        //adding last player playable should return true
        gameController.addPlayer(Rick);
        assertTrue(gameController.playable());
    }

    //testing playable with 3 players
public void testPlayable3Player() {
        Player Gioggio = new Player("Gioggio");
        Player Peppe = new Player("Peppe");
        Player Fil = new Player("Fil");
        GameController gameController = new GameController(3,1);
        //adding two player -> playable should return false
        gameController.addPlayer(Gioggio);
        gameController.addPlayer(Peppe);
        assertFalse(gameController.playable());
        //adding last player -> playable should return true
        gameController.addPlayer(Fil);
        assertTrue(gameController.playable());
    }

    //testing playable with two people
    public void testPlayable2Player() {
        Player Gioggio = new Player("Gioggio");
        Player Peppe = new Player("Peppe");
        GameController gameController = new GameController(2,1);
        //adding two player -> playable should return false
        gameController.addPlayer(Gioggio);
        assertFalse(gameController.playable());
        //adding last player -> playable should return true
        gameController.addPlayer(Peppe);
        assertTrue(gameController.playable());
    }
*/


    public void testWinner() {
    }

    public void testTakeItem() {
    }

    public void testSetPlayersOrder() {
    }

    public void testAddPlayer() {
    }

    public void testRefill() {
    }

    public void testRound() {
    }
}