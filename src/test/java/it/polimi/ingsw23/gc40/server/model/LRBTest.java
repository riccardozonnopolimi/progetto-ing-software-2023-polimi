package it.polimi.ingsw23.gc40.server.model;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;

public class LRBTest extends TestCase {

@Test
    protected TestResult createResult() {
        return super.createResult();
    }
    public ItemTile[][] mapT = new ItemTile[9][9];
    LRB lrb = new LRB(2);
    public void testIsTakeable() {
        
        lrb.setMap(mapT);

        //assertEquals(lrb.isTakeable(4, 4), "false");

    }


    public void testEveryTileCompletelyFree() {
    }

    public void testTakeables() {
    }
}