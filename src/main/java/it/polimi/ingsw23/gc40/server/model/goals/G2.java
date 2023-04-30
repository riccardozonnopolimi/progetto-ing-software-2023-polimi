package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
public class G2 implements Goal {
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
        ItemTile[][] s = p.getBookshelf().getShelf();
        //control if the superior angles
        // and the inferior angles are both equals
        // and if the superior left angle is equal to the inferior left
        return ((s[0][0].equals(s[0][4]))
                && (s[0][0].getItem() != Item.EMPTY)
                && (s[5][0].equals(s[5][4]))
                && (s[0][0].equals(s[5][0])));
    }

    @Override
    public void print() {
        System.out.println("G2");
    }
}