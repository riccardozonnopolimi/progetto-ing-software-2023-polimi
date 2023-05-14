package it.polimi.ingsw23.gc40.server.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.goals.G11;
import org.junit.Test;
import static junit.framework.TestCase.*;
public class G11Test {
@Test
    public void testInstanceGoal1() {
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

        G11 goal = new G11(11);
        assertTrue(goal.instanceGoal(p1));
    }
    @Test
    public void testInstanceGoal2() {
    Player p2 = new Player("Fil");

    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),0);
    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),0);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),0);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),0);
    p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2),0);
    p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2),0);

    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),1);
    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),1);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),1);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),1);
    p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2),1);
    p2.getBookshelf().insertIT(new ItemTile(Item.PLANT, 2),1);

    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),2);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),2);
    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),2);
    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),2);
    p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2),2);
    p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2),2);

    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),3);
    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),3);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),3);
    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),3);
    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),3);
    p2.getBookshelf().insertIT(new ItemTile(Item.BOOK, 2),3);

    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),4);
    p2.getBookshelf().insertIT(new ItemTile(Item.GAME, 2),4);
    p2.getBookshelf().insertIT(new ItemTile(Item.CAT, 2),4);
    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),4);
    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),4);
    p2.getBookshelf().insertIT(new ItemTile(Item.TROPHY, 2),4);

    G11 goal = new G11(11);
    assertFalse(goal.instanceGoal(p2));
    }
}