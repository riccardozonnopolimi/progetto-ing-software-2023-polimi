package it.polimi.ingsw23.gc40.server.goals;
import it.polimi.ingsw23.gc40.server.Item;
import it.polimi.ingsw23.gc40.server.ItemTile;
import it.polimi.ingsw23.gc40.server.Player;

public class G12 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        ItemTile[][] s = p.getBookshelf().getShelf();
        //control if there is a diagonal of 5 in the bookshelf that is all fill and the space at right is empty
        // verficare che si deve contare anche colonna vuota come possibile colonna
        return (((s[0][0].getItem() == Item.EMPTY)
                && (s[1][1].getItem() == Item.EMPTY)
                && (s[2][2].getItem() == Item.EMPTY)
                && (s[3][3].getItem() == Item.EMPTY)
                && (s[4][4].getItem() == Item.EMPTY)
                && !(s[1][0].getItem() == Item.EMPTY)
                && !(s[2][1].getItem() == Item.EMPTY)
                && !(s[3][2].getItem() == Item.EMPTY)
                && !(s[4][3].getItem() == Item.EMPTY)
                && !(s[5][4].getItem() == Item.EMPTY))
                || ((s[0][1].getItem() == Item.EMPTY)
                && (s[1][2].getItem() == Item.EMPTY)
                && (s[2][3].getItem() == Item.EMPTY)
                && (s[3][4].getItem() == Item.EMPTY)
                && !(s[0][0].getItem() == Item.EMPTY)
                && !(s[1][1].getItem() == Item.EMPTY)
                && !(s[2][2].getItem() == Item.EMPTY)
                && !(s[3][3].getItem() == Item.EMPTY)
                && !(s[4][4].getItem() == Item.EMPTY))
                || ((s[0][3].getItem() == Item.EMPTY)
                && (s[1][2].getItem() == Item.EMPTY)
                && (s[2][1].getItem() == Item.EMPTY)
                && (s[3][0].getItem() == Item.EMPTY)
                && !(s[0][4].getItem() == Item.EMPTY)
                && !(s[1][3].getItem() == Item.EMPTY)
                && !(s[2][2].getItem() == Item.EMPTY)
                && !(s[3][1].getItem() == Item.EMPTY)
                && !(s[4][0].getItem() == Item.EMPTY))
                || ((s[0][4].getItem() == Item.EMPTY)
                && (s[1][3].getItem() == Item.EMPTY)
                && (s[2][2].getItem() == Item.EMPTY)
                && (s[3][1].getItem() == Item.EMPTY)
                && (s[4][0].getItem() == Item.EMPTY)
                && !(s[1][4].getItem() == Item.EMPTY)
                && !(s[2][3].getItem() == Item.EMPTY)
                && !(s[3][2].getItem() == Item.EMPTY)
                && !(s[4][1].getItem() == Item.EMPTY)
                && !(s[5][0].getItem() == Item.EMPTY)));
    }
    public void print() {
        System.out.println("G12");
    }
}