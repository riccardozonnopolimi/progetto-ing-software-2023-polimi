package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.util.HashMap;

public class G5_G8_G9_G10 implements Goal {
    //number of different type
    int differentType;
    //number of blocks need to reach the goal
    int numBlocks;
    //boolean to chose if the goal search for column or not
    boolean c;

    public G5_G8_G9_G10(int DifferentType, int numBlocks, boolean c) {
        this.differentType = DifferentType;
        this.numBlocks = numBlocks;
        this.c = c;
    }

    @Override
    public boolean instanceGoal(Player p) {
        //TO DO
        //copy of the player's bookshelf
        ItemTile[][] s = p.getBookshelf().getShelf();
        //hashmap to count which types are present
        HashMap<Item, Boolean> count = new HashMap<>();
        //number of blocks that correspond to the goal
        int countValid = 0;
        //counter of how many types are present
        int counterBool = 0;
        //indexes of row and column
        int h = 0, k = 0;
        if (c) {
            h = 5;
            k = 6;
        } else {
            h = 6;
            k = 5;
        }
        for (int j = 0; j < h; j++) {
            //set the hashmap to false at every step of the cicle
            count.put(Item.CAT, false);
            count.put(Item.BOOK, false);
            count.put(Item.GAME, false);
            count.put(Item.FRAME, false);
            count.put(Item.TROPHY, false);
            count.put(Item.PLANT, false);
            for (int i = 0; i < k; i++) {
                //if need to check for column
                if (c) count.put(s[j][i].getItem(), true);
                    //if need to check for row
                else count.put(s[i][j].getItem(), true);
            }
            //count how many types there are
            if (count.get(Item.PLANT)) counterBool++;
            if (count.get(Item.FRAME)) counterBool++;
            if (count.get(Item.CAT)) counterBool++;
            if (count.get(Item.GAME)) counterBool++;
            if (count.get(Item.BOOK)) counterBool++;
            if (count.get(Item.TROPHY)) counterBool++;
            //if the condition is verified set that a new valid block is find
            if (((counterBool <= differentType) && (differentType == 3)) || (counterBool == differentType))
                countValid++;
            //if the number of blocks needed is reached return true and stop
            if (countValid == numBlocks) return true;
        }
        return false;
    }
}
