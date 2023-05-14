package model;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.LRB;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;

public class LRBTest extends TestCase {

    @Test
    protected TestResult createResult() {
        return super.createResult();
    }


    public void testIsTakeable() {
        var lrb = new LRB(2);
        assertEquals(lrb.isTakeable(4, 4), false); //check initialized map works
        ItemTile it = new ItemTile(Item.CAT);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (lrb.getTileInMap(i, j).getItem().equals(Item.EMPTY)) {
                    lrb.setTileInMap(it, i, j);
                }
            }
        }


        assertTrue(lrb.isTakeable(7, 5));
        assertTrue(lrb.isTakeable(1, 3));
        assertFalse(lrb.isTakeable(4, 4));
        assertFalse(lrb.isTakeable(0, 3));


    }


    public void testEveryTileCompletelyFree() {
        LRB lrb = new LRB(2);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (lrb.getTileInMap(i, j).getItem().equals(Item.EMPTY)) {
                    lrb.setTileInMap(new ItemTile(Item.PLANT), i, j);
                }
            }
        }
        assertFalse(lrb.needRefill());
        // assertEquals(mappa[0][0].getItem(), Item.CAT);
    }

    public void testTakeables() {
        var lrb = new LRB(4);
        ItemTile[][] mappaTemp = new ItemTile[9][9];
        for (int j = 3; j < 7; j++) {
            lrb.setTileInMap(new ItemTile(Item.CAT), 3, j);
        }
        for (int i = 2; i < 6; i++) {
            lrb.setTileInMap(new ItemTile(Item.BOOK), i, 4);
        }
        for (int i = 2; i < 6; i++) {
            lrb.setTileInMap(new ItemTile(Item.BOOK), i, 3);
        }
        for (int i = 2; i < 6; i++) {
            lrb.setTileInMap(new ItemTile(Item.BOOK), i, 5);
        }
        lrb.print();
        mappaTemp = lrb.takeables();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(mappaTemp[i][j].toString() + "|");
            }
            System.out.println();

        }


    }
}