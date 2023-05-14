package it.polimi.ingsw23.gc40.server.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.goals.G2;
import junit.framework.TestCase;
import org.junit.Test;

public class G2Test extends TestCase {
    @Test
    public void testInstanceGoalTrue() {
        Player p1 = new Player("Fil");
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);

        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);

        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 2);

        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);

        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 4);

        p1.getBookshelf().print();
        G2 goal = new G2(2);
        assertTrue(goal.instanceGoal(p1));
    }
@Test
    public void testInstanceGoalFalse() {
        Player p1 = new Player("Fil");
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 0);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 0);

        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 1);

        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 2);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 2);

        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 3);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);

        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
        p1.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2), 4);

        p1.getBookshelf().print();
        G2 goal = new G2(2);
        assertFalse(goal.instanceGoal(p1));
    }
}