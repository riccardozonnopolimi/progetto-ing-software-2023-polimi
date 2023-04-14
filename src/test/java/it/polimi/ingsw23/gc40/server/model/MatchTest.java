package it.polimi.ingsw23.gc40.server.model;

import junit.framework.TestCase;

public class MatchTest extends TestCase {

    //verify that the initially next is null
    public void testGetNext() {
        Match match = new Match(3);
        assertNull(match.getNext());
    }

    //testing playable with four players
    public void testPlayable4Player() {
        Player Gioggio = new Player("Gioggio");
        Player Peppe = new Player("Peppe");
        Player Fil = new Player("Fil");
        Player Rick = new Player("Rick");
        Match match = new Match(4);
        //adding three player -> playable should return false
        match.addPlayer(Gioggio);
        match.addPlayer(Peppe);
        match.addPlayer(Fil);
        assertFalse(match.playable());
        //adding last player playable should return true
        match.addPlayer(Rick);
        assertTrue(match.playable());
    }

    //testing playable with 3 players
    public void testPlayable3Player() {
        Player Gioggio = new Player("Gioggio");
        Player Peppe = new Player("Peppe");
        Player Fil = new Player("Fil");
        Match match = new Match(3);
        //adding two player -> playable should return false
        match.addPlayer(Gioggio);
        match.addPlayer(Peppe);
        assertFalse(match.playable());
        //adding last player -> playable should return true
        match.addPlayer(Fil);
        assertTrue(match.playable());
    }

    //testing playable with two people
    public void testPlayable2Player() {
        Player Gioggio = new Player("Gioggio");
        Player Peppe = new Player("Peppe");
        Match match = new Match(2);
        //adding two player -> playable should return false
        match.addPlayer(Gioggio);
        assertFalse(match.playable());
        //adding last player -> playable should return true
        match.addPlayer(Peppe);
        assertTrue(match.playable());
    }



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