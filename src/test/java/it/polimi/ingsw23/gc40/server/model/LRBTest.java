package it.polimi.ingsw23.gc40.server.model;

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
        assertEquals(lrb.isTakeable(4,4),false); //check initialized map works
        ItemTile it = new ItemTile(Item.CAT);
      //  for(int i=0;i<9; i++) {
        //     for(int j=0;j<9; j++) {
       //         if(lrb.getTileInMap(i, j).equals(Item.EMPTY)) {
       //             lrb.setTileInMap(it, i, j);
        //        }
         //   }
        //}
        lrb.setTileInMap(it,1, 3);

       // assertTrue(lrb.isTakeable(1,3));


        //assertEquals(lrb.isTakeable(4, 4), "false");

    }


    public void testEveryTileCompletelyFree() {
    LRB lrb = new LRB(2);
        for(int i=0; i<9; i++){
                for(int j=0;j<9; j++) {
                  if(lrb.getTileInMap(i,j).getItem().equals(Item.EMPTY)){
                       lrb.setTileInMap(new ItemTile(Item.PLANT), i ,j);
                    }
                }
           }
            assertFalse(lrb.everyTileCompletelyFree());
          // assertEquals(mappa[0][0].getItem(), Item.CAT);
    }

    public void testTakeables() {
    }
}