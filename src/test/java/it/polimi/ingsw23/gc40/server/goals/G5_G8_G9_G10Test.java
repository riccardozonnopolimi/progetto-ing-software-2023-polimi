package it.polimi.ingsw23.gc40.server.goals;

import it.polimi.ingsw23.gc40.server.Item;
import it.polimi.ingsw23.gc40.server.ItemTile;
import it.polimi.ingsw23.gc40.server.Player;
import junit.framework.TestCase;
import org.junit.Test;

public class G5_G8_G9_G10Test extends TestCase {
@Test
    public void testInstanceGoal5Case1() {
    Player p1 = new Player("Fil");
    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 0);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 0);

    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 1);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 1);

    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 2);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 2);

    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);
    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 3);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 3);

    p1.getBookshelf().insertIT(new ItemTile(Item.CAT, 2), 4);
    p1.getBookshelf().insertIT(new ItemTile(Item.GAME, 2), 4);
    p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
    p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
    p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);
    p1.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2), 4);

    G5_G8_G9_G10 goal = new G5_G8_G9_G10(3,3,true);
    assertTrue(goal.instanceGoal(p1));
    }
}