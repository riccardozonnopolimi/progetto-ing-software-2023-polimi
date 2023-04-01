package it.polimi.ingsw23.gc40.server.model;

import junit.framework.TestCase;

public class ServerModelTest extends TestCase {

    public void testAddPlayer() {

    }

    public void testCreateNewMatch() {
    }

    public void testAddToLastMatch() {
    }

    public void testCheckExistingNickName() {
        var SM = new ServerModel();
        SM.addPlayer("Giorgio");
        assertTrue(SM.checkExistingNickName("Giorgio"));

    }

    public void testEndMatch() {
    }

    public void testHasQueueMatch() {
    }

    public void testGetPlayer() {
    }
}