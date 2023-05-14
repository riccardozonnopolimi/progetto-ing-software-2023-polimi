package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.util.HashMap;
public class G6 implements Goal {
    private final int id;
    public G6(int id){
        this.id = id;
    }
    @Override
    public boolean instanceGoal(Player p) {
        //TESTED
        ItemTile[][] s = p.getBookshelf().getShelf();
        //control all the shelf of player and
        // count how many item of same type are in shelf
        HashMap<Item, Integer> count = new HashMap<>();
        count.put(Item.EMPTY, 0);
        count.put(Item.CAT, 0);
        count.put(Item.BOOK, 0);
        count.put(Item.GAME, 0);
        count.put(Item.FRAME, 0);
        count.put(Item.TROPHY, 0);
        count.put(Item.PLANT, 0);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                count.put(s[i][j].getItem(), count.get(s[i][j].getItem()) + 1);
            }
        }
        //if once of item type is almost eight return true, else false
        return (count.get(Item.CAT) >= 8 || count.get(Item.BOOK) >= 8 || count.get(Item.GAME) >= 8 || count.get(Item.FRAME) >= 8 || count.get(Item.TROPHY) >= 8 || count.get(Item.PLANT) >= 8);
    }
    public void print() {
        System.out.println("G6");
    }
    @Override
    public int getId() {
        return this.id;
    }
}