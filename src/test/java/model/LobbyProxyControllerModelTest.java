package model;

import it.polimi.ingsw23.gc40.server.controller.LobbyController;
import junit.framework.TestCase;

import java.rmi.RemoteException;

public class LobbyProxyControllerModelTest extends TestCase {

    public void testAddPlayer() {

    }

    public void testCreateNewMatch() {
    }

    public void testAddToLastMatch() {
    }

    public void testCheckExistingNickName() throws RemoteException {
        var SM = new LobbyController();
        SM.addPlayer("Giorgio");
        assertTrue(SM.checkOnlinePlayers("Giorgio"));
        SM.addPlayer("Filiberto");
        SM.addPlayer("Giuseppe");
        SM.addPlayer("Riccardo");
        assertTrue(SM.checkOnlinePlayers("Giuseppe"));
        assertFalse(SM.checkOnlinePlayers("GianpaoloCugola"));

    }

    public void testEndMatch() {
    }

    public void testHasQueueMatch() {
    }

 }