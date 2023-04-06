package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
import junit.framework.TestCase;

public class G1_G3Test extends TestCase {

    public void testInstanceGoal2True() {
        Player p1 = new Player("Fil");
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

        p1.getBookshelf().print();
        G1_G3 goal = new G1_G3(2,6);
        assertTrue(goal.instanceGoal(p1));
    }


    public void testInstanceGoal2False() {
        Player p1 = new Player("Fil");
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);

        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);

        G1_G3 goal = new G1_G3(2,6);
        assertTrue(goal.instanceGoal(p1));
    }

    public void testInstanceGoal4True() {
        Player p1 = new Player("Fil");
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);

        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);

        G1_G3 goal = new G1_G3(4,4);
        assertTrue(goal.instanceGoal(p1));
    }

    public void testInstanceGoal4False() {
        Player p1 = new Player("Fil");
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);

        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);

        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);

        G1_G3 goal = new G1_G3(4,4);
        assertTrue(goal.instanceGoal(p1));
    }

}
