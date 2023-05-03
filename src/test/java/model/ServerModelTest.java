package model;

import it.polimi.ingsw23.gc40.server.model.Server;
import junit.framework.TestCase;

import java.rmi.RemoteException;

public class ServerModelTest extends TestCase {

    public void testAddPlayer() {

    }

    public void testCreateNewMatch() {
    }

    public void testAddToLastMatch() {
    }

    public void testCheckExistingNickName() throws RemoteException {
        var SM = new Server();
        SM.addPlayer("Giorgio");
        assertTrue(SM.checkExistingNickName("Giorgio"));
        SM.addPlayer("Filiberto");
        SM.addPlayer("Giuseppe");
        SM.addPlayer("Riccardo");
        assertTrue(SM.checkExistingNickName("Giuseppe"));
        assertFalse(SM.checkExistingNickName("GianpaoloCugola"));

    }

    public void testEndMatch() {
    }

    public void testHasQueueMatch() {
    }

 }