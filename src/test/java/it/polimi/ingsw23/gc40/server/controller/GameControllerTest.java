package it.polimi.ingsw23.gc40.server.controller;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
import junit.framework.TestCase;

import java.util.ArrayList;

public class GameControllerTest extends TestCase {

    public void testChooseTile() {
        //testing tiles taken correctly, and printing to check if has taken tiles not takeables()
        var gc = new GameController(2,1);
        var p = new Player("Giosi");
        var p1 = new Player("Rickz");
        var p2 = new Player("FIl");
        gc.addPlayer(p);
        gc.addPlayer(p1);
        gc.addPlayer(p2);
        ItemTile[] handt;
        gc.addObserver(gc.getPublicModelObserver());
        int quantity = 2;
        handt = gc.chooseTiles(1,3,quantity, 'R');
        assertEquals(handt.length, quantity);
        assertNotNull(handt);
        quantity=3;
        handt = gc.chooseTiles(2,3,quantity, 'R');
        assertEquals(handt.length, quantity);
        assertNotNull(handt);
        handt = gc.chooseTiles(3,2,quantity, 'R');
        assertEquals(handt.length, quantity);
        assertNotNull(handt);
        //it does not take tile correctly
        handt = gc.chooseTiles(2,5,quantity, 'U');
        //it does not take tile correctly
        handt = gc.chooseTiles(1,3,quantity, 'D');
        //it does not take tile correctly
        handt = gc.chooseTiles(1,3,quantity, 'L');
        gc.getMap().print();
    }

    public void testWinner() {
       //bookshelfs full, calling winner to test if it works correctly. At first with assertionNull
        var gc = new GameController(3,1);
        var p = new Player("Giosi");
        var p1 = new Player("Rickz");
        var p2 = new Player("FIl");
        assertNull(gc.getPublicModelObserver().winner);
        gc.addPlayer(p);
        gc.addPlayer(p1);
        gc.addPlayer(p2);
        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 0);
        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 0);

        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 1);
        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 1);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 1);

        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 2);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 2);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 2);

        p.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);

        p.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 4);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);


        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);

        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.FRAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);

        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.FRAME, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.FRAME, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);





        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 0);
        p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 0);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 0);

        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 1);
        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 1);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 1);

        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 2);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 2);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 2);

        p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 3);

        p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 4);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        p2.getBookshelf().insertIT(new ItemTile(Item.EMPTY, 2), 4);
        gc.winner();
        assertNotNull(gc.getPublicModelObserver().winner);

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


    }


    public void testSetPlayersOrder() {
        //testing if players order effectively changes in list after calling setPlayersOrder
        ArrayList<Player> p = new ArrayList<>();
        var gc = new GameController(4, 1);
        var gc1 = new GameController(3,2);
        var p1 = new Player("Rickz");
        var p2 = new Player("FIl");
        var p3 = new Player("Giosi");
        var p4 = new Player("Peppo");
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        gc.getPlayers().addAll(p);
        gc1.getPlayers().add(p1);
        gc1.getPlayers().add(p2);
        gc1.getPlayers().add(p3);
        for (int i = 0; i < gc.getPlayers().size(); i++) {
            System.out.println(("gamecontroller " + i + gc.getPlayers().get(i).getNickName()));
//            assertEquals(p.get(i).getNickName(),gc.getPlayers().get(i).getNickName());
        }
        for (int i = 0; i < gc1.getPlayers().size(); i++) {
            System.out.println(("gamecontroller " + i + gc1.getPlayers().get(i).getNickName()));
//            assertEquals(p.get(i).getNickName(),gc.getPlayers().get(i).getNickName());
        }
        gc.setPlayersOrder();
        gc1.setPlayersOrder();

        for (int j = 0; j < gc.getPlayers().size(); j++) {
            System.out.println(("gamecontroller " + j + gc.getPlayers().get(j).getNickName()));

        }


        for (int k = 0; k < gc1.getPlayers().size(); k++) {
            System.out.println(("gamecontroller " + k + gc1.getPlayers().get(k).getNickName()));
        }
    }

    public void testRefill() {
       //removing tiles from LRB, checking if refills correctly leaving old item tile in board when needRefill
        //turns true
        var gc = new GameController(3,1);
        var p = new Player("Giosi");
        var p1 = new Player("Rickz");
        var p2 = new Player("FIl");
        gc.addPlayer(p);
        gc.addPlayer(p1);
        gc.addPlayer(p2);
        gc.chooseTiles(1,3,2, 'R');
        gc.chooseTiles(2,3,3, 'R');
        gc.chooseTiles(3,2,3, 'R');
        gc.chooseTiles(4,1,3, 'R');
        gc.chooseTiles(5,2,3, 'L');
        gc.chooseTiles(5,6,2, 'D');
        gc.chooseTiles(5,5,3, 'D');
        gc.chooseTiles(4,5,3, 'R');
        gc.chooseTiles(3,6,3, 'R');
        gc.chooseTiles(6,2,2, 'R');
        gc.chooseTiles(5,3,2, 'R');
        gc.chooseTiles(6,4,2, 'D');
        assertTrue(gc.getMap().needRefill());
        gc.getMap().print();
                if(gc.getMap().needRefill())
                    gc.refill();

        gc.getMap().print();


    }


    public void testCheckScores() {
        var gc = new GameController(3,1);
        var p = new Player("Giosi");
        var p1 = new Player("Rickz");
        var p2 = new Player("FIl");
        gc.addPlayer(p);
        gc.addPlayer(p1);
        gc.addPlayer(p2);
    }



}