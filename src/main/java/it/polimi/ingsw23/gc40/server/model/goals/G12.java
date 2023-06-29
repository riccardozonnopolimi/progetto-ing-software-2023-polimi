package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G12 implements Goal {
    private final int id;

    /**
     * Common Goal Cards numbered following Rulebook in italian
     * from the first on the left to the bottom of the right
     * @param id Common Goal id
     */
    public G12(int id) {
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
        //control if there is a diagonal of 5 in the bookshelf that is all fill and the space at right is empty
        //todo verficare se si deve contare anche colonna vuota come possibile colonna
        return (((s[0][0].getItem().equals(Item.EMPTY))
                && (s[1][1].getItem().equals(Item.EMPTY))
                && (s[2][2].getItem().equals(Item.EMPTY))
                && (s[3][3].getItem().equals(Item.EMPTY))
                && (s[4][4].getItem().equals(Item.EMPTY))
                && !(s[1][0].getItem().equals(Item.EMPTY))
                && !(s[2][1].getItem().equals(Item.EMPTY))
                && !(s[3][2].getItem().equals(Item.EMPTY))
                && !(s[4][3].getItem().equals(Item.EMPTY))
                && !(s[5][4].getItem().equals(Item.EMPTY)))
                || ((s[0][1].getItem().equals(Item.EMPTY))
                && (s[1][2].getItem().equals(Item.EMPTY))
                && (s[2][3].getItem().equals(Item.EMPTY))
                && (s[3][4].getItem().equals(Item.EMPTY))
                && !(s[0][0].getItem().equals(Item.EMPTY))
                && !(s[1][1].getItem().equals(Item.EMPTY))
                && !(s[2][2].getItem().equals(Item.EMPTY))
                && !(s[3][3].getItem().equals(Item.EMPTY))
                && !(s[4][4].getItem().equals(Item.EMPTY)))
                || ((s[0][3].getItem().equals(Item.EMPTY))
                && (s[1][2].getItem().equals(Item.EMPTY))
                && (s[2][1].getItem().equals(Item.EMPTY))
                && (s[3][0].getItem().equals(Item.EMPTY))
                && !(s[0][4].getItem().equals(Item.EMPTY))
                && !(s[1][3].getItem().equals(Item.EMPTY))
                && !(s[2][2].getItem().equals(Item.EMPTY))
                && !(s[3][1].getItem().equals(Item.EMPTY))
                && !(s[4][0].getItem().equals(Item.EMPTY)))
                || ((s[0][4].getItem().equals(Item.EMPTY))
                && (s[1][3].getItem().equals(Item.EMPTY))
                && (s[2][2].getItem().equals(Item.EMPTY))
                && (s[3][1].getItem().equals(Item.EMPTY))
                && (s[4][0].getItem().equals(Item.EMPTY))
                && !(s[1][4].getItem().equals(Item.EMPTY))
                && !(s[2][3].getItem().equals(Item.EMPTY))
                && !(s[3][2].getItem().equals(Item.EMPTY))
                && !(s[4][1].getItem().equals(Item.EMPTY))
                && !(s[5][0].getItem().equals(Item.EMPTY))));
    }

   //todo usato solo nei test
    public void print() {
        System.out.println("G12");
    }

    /**
     * @return Common Goal id
     */
    @Override
    public int getId() {
        return this.id;
    }
}