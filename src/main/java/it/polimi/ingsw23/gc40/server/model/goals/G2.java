package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
public class G2 implements Goal {
    private final int id;

    /**
     * Common Goal Cards numbered following Rulebook in italian
     * from the first on the left to the bottom of the right
     * @param id the common goal id passed by parameter
     */
    public G2(int id) {
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
        //control if the superior angles
        // and the inferior angles are both equals
        // and if the superior left angle is equal to the inferior left
        // and if the superior left angle is different to empty
        return ((s[0][0].equals(s[0][4]))
                && (s[0][0].getItem() != Item.EMPTY)
                && (s[5][0].equals(s[5][4]))
                && (s[0][0].equals(s[5][0])));
    }

    @Override
    // usata solo nei test
    public void print() {
        System.out.println("G2");
    }

    /**
     * @return Common Goal id.
     */
    @Override
    public int getId() {
        return this.id;
    }
}