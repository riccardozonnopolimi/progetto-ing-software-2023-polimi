package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Player;
import junit.framework.TestCase;

public class GameControllerTest extends TestCase {

    public void testWinner() {

    }

    public void testAddPlayer() {
        /*
        this.nickName = nickName;
        this.CGScore = 0;
        this.CG1Reached = false;
        this.personalGoal = null;
        this.CG2Reached = null;
        this.first = false;
        this.PGScore = 0;
        this.ADJScore = 0;
        this.bookshelf = new Bookshelf();
        */
        var gc = new GameController(3,1);
        var p = new Player("Giosi");
        var p1 = new Player("Rickz");
        var p2 = new Player("FIl");
        gc.addPlayer(p);
        assertEquals(gc.getPlayers().size(), 1);
        assertFalse(gc.isPlayable());
        gc.addPlayer(p1);
        assertEquals(gc.getPlayers().size(), 2);
        assertFalse(gc.isPlayable());
        gc.addPlayer(p2);
        assertEquals(gc.getPlayers().size(), 3);
        assertTrue(gc.isPlayable());
        gc.getMap().print();


    }
    public void chooseTileTest(){

        var gc = new gamecontroll
    }

    public void testGetPlayers() {
    }
}