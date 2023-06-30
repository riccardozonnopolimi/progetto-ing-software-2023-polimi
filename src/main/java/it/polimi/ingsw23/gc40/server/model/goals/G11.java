package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;
public class G11 implements Goal {
    private final int id;

    /**
     * Common Goal Cards numbered following Rulebook in italian
     * from the first on the left to the bottom of the right
     * @param id Common Goal id
     */
    public G11(int id){
        this.id = id;
    }

    /**
     * @param p player that has already finished the round
     * @return true if the goal is achieved by the player, false if it is not.
     */
    @Override
    public boolean instanceGoal(Player p) {
        //TESTED
        //verify if there is a sequence of five ItemTile in the bookshelf that form a X and are of the same type
        ItemTile[][] s = p.getBookshelf().getShelf();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 4; j++) {
                if ((s[i][j].getItem().equals(s[i - 1][j - 1].getItem())) && (s[i][j].getItem().equals(s[i + 1][j - 1].getItem())) && (s[i][j].getItem().equals(s[i - 1][j + 1].getItem())) && (s[i][j].getItem().equals(s[i + 1][j + 1].getItem())) && !s[i][j].getItem().equals(Item.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }

    // usata solo nei test
    public void print() {
        System.out.println("G11");
    }

    /**
     * @return Common Goal id
     */
    @Override
    public int getId() {
        return this.id;
    }
}