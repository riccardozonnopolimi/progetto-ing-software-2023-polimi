package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.util.HashMap;

public class G5_G8_G9_G10 implements Goal {
    //number of maximum different type
    private final int differentType;
    //number of blocks need to reach the goal
    private final int numOfBlocks;
    //boolean to choose if the goal search for column or not
    private final boolean c;
    private final int id;

    /**
     * Common Goal Cards numbered following Rulebook in italian
     *from the first on the left to the bottom of the right
     * @param differentType number of maximum different type in tthe same column/row, it could be 1,2 or 3
     * @param numOfBlocks number of blocks achieved at the moment, value to update when a column or line is found
     * @param c if true it checks the columns, if not checks the row
     * @param id Common Goal Card id
     */
    public G5_G8_G9_G10(int differentType, int numOfBlocks, boolean c, int id) {
        this.differentType = differentType;
        this.numOfBlocks = numOfBlocks;
        this.c = c;
        this.id = id;
    }

    /**
     * @param p player that has already finished the round
     * @return true if the goal is achieved by the player, false if it is not.
     */
    @Override
    public boolean instanceGoal(Player p) {
        //TESTED
        //copy of the player's bookshelf
        ItemTile[][] s = p.getBookshelf().getShelf();
        //hashmap to count which types are present
        HashMap<Item, Boolean> count = new HashMap<>();
        //number of blocks that correspond to the goal
        int countValid = 0;
        //counter of how many types are present
        int counterBool;
        //indexes of row and column
        int h, k;
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
            count.put(Item.EMPTY, false);
            count.put(Item.NOTREACHABLE, false);
            counterBool = 0;
            for (int i = 0; i < k; i++) {
                //if need to check for column
                if (c) {
                    if (!s[i][j].getItem().equals(Item.EMPTY))
                        count.put(s[i][j].getItem(), true);
                }
                //if need to check for row
                else {
                    if (!s[j][i].getItem().equals(Item.EMPTY))
                        count.put(s[j][i].getItem(), true);
                }
            }
            //count how many types there are
            if (count.get(Item.PLANT)) counterBool++;
            if (count.get(Item.FRAME)) counterBool++;
            if (count.get(Item.CAT)) counterBool++;
            if (count.get(Item.GAME)) counterBool++;
            if (count.get(Item.BOOK)) counterBool++;
            if (count.get(Item.TROPHY)) counterBool++;
            //if the condition is verified set that a new valid block is find
            if (differentType == 3) {
                if ((counterBool <= differentType) && (!count.get(Item.EMPTY)) && (counterBool > 0))
                    countValid++;
            }
            else {
                if (counterBool == differentType)
                    countValid++;
            }
            //if the number of blocks needed is reached return true and stop
            if (countValid == numOfBlocks) return true;
        }
        return false;
    }


    //usata solo nei test
    public void print() {
        System.out.println("G5_G8_G9_G10");
    }

    /**
     * @return common goal id
     */
    @Override
    public int getId() {
        return this.id;
    }
}
