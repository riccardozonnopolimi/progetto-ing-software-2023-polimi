package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
public class G2_G7_G12 implements Goal {
    private int n;
    public G2_G7_G12(int n) {
        this.n = n;
    }
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
        ItemTile[][] s = p.getBookshelf().getShelf();
        switch (n) {
            case 7:
                //control if there is a diagonal of 5 in the bookshelf that is all the same type
                return ((s[0][0].equals(s[1][1])
                        && s[1][1].equals(s[2][2])
                        && s[2][2].equals(s[3][3])
                        && s[3][3].equals(s[4][4])
                        && s[3][3].getItem() != Item.EMPTY)
                        || (s[1][0].equals(s[2][1])
                        && s[2][1].equals(s[3][2])
                        && s[3][2].equals(s[4][3])
                        && s[4][3].equals(s[5][4])
                        && s[4][3].getItem() != Item.EMPTY)
                        || (s[0][4].equals(s[1][3])
                        && s[1][3].equals(s[2][2])
                        && s[2][2].equals(s[3][1])
                        && s[3][1].equals(s[4][0])
                        && s[3][1].getItem() != Item.EMPTY)
                        || (s[1][4].equals(s[2][3])
                        && s[2][3].equals(s[3][2])
                        && s[3][2].equals(s[4][1])
                        && s[4][1].equals(s[5][0])
                        && (s[4][1].getItem() != Item.EMPTY)));
            case 2:
                //control if the superior angles
                // and the inferior angles are both equals
                // and if the superior left angle is equal to the inferior left
                return ((s[0][0].equals(s[0][4])) && (s[0][0].getItem() != Item.EMPTY)
                        && (s[5][0].equals(s[5][4]))
                        && (s[0][0].equals(s[5][0])));
            case 12:
                //control if there is a diagonal of 5 in the bookshelf that is all fill and the space at right is empty
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
            default://need to say invalid instance
                return false;
        }
    }
}