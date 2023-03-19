package it.polimi.ingsw23.gc40.server.model;

import java.util.HashMap;

public class CG6 implements Goal {
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST

        ItemTile[][] s = p.getBookshelf().getShelf();
        //control all the shelf of player and
        // count how many item of same type are in shelf
        HashMap<Item, Integer> count = new HashMap<>();
        count.put(Item.CATS, 0);
        count.put(Item.BOOKS, 0);
        count.put(Item.GAMES, 0);
        count.put(Item.FRAMES, 0);
        count.put(Item.TROPHIES, 0);
        count.put(Item.PLANTS, 0);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                count.put(s[i][j].getItem(), count.get(s[i][j].getItem()) + 1);
            }
        }

        //if once of item type is almost eight return true, else false
        return (count.get(Item.CATS) >= 8 ||
                count.get(Item.BOOKS) >= 8 ||
                count.get(Item.GAMES) >= 8 ||
                count.get(Item.FRAMES) >= 8 ||
                count.get(Item.TROPHIES) >= 8 ||
                count.get(Item.PLANTS) >= 8);
    }
}
