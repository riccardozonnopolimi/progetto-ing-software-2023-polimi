package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G7 implements Goal{
    private final int id;

    /**
     * Common Goal Cards numbered following Rulebook in italian
     * from the first on the left to the bottom of the right
     * @param id Common Goal id
     */
    public G7(int id){
        this.id = id;
    }

    /**
     * @param p player that has already finished the round
     * @return true if the goal is achieved by the player, false if it is not.
     */
    @Override
    public boolean instanceGoal(Player p) {
        //TESTED
        ItemTile[][] s = p.getBookshelf().getShelf();
        //control if there is a diagonal of 5 ItemTiles in the bookshelf that is all the same type
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
    }
   //todo usato solo nei test
    public void print() {
        System.out.println("G7");
    }

    /**
     * @return Common Goal id
     */
    @Override
    public int getId() {
        return this.id;
    }
}
