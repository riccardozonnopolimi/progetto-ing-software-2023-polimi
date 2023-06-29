package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G1_G3 implements Goal {
    private final int blockSize;
    private final int numOfBlocks;
    private final int id;

    /**
     * Common Goal Cards numbered following Rulebook in italian from the first on the left to the bottom of the right
     * @param blockSize is the size of the block, it could be 2 or 4.
     * @param numOfBlocks number of blocks achieved at the moment, value to update when a column or line is found
     * @param id Common Goal Card id
     */
    public G1_G3(int blockSize, int numOfBlocks, int id) {
        this.blockSize = blockSize;
        this.numOfBlocks = numOfBlocks;
        this.id = id;
    }

    /**
     * @param p player that has already finished the round
     * @return true if the goal is achieved by the player, false if it is not.
     */
    @Override
    public boolean instanceGoal(Player p) {
        //setting false all ItemTile getChecked
        for(int i=0; i<=5; i++){
            for(int j=0; j<=4; j++){
                p.getBookshelf().getShelf()[i][j].setCheckedADJ(false);
            }
        }
        //value to update when a column or a line is found
        int validBlock = numOfBlocks;
        ItemTile[][] s = p.getBookshelf().getShelf();
        //check if there is a block long like blockSize of the same type in a column
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (!s[i][j].getCheckedADJ() && !s[i][j].getItem().equals(Item.EMPTY)) {
                    p.getBookshelf().setADJCounter(0);
                    p.getBookshelf().checker(i, j, s[i][j].getItem());
                    if (p.getBookshelf().getADJCounter() >= blockSize) {
                        validBlock--;
                    }
                    if (validBlock == 0) {
                        //goal achieved
                        return true;
                    }
                }
            }
        }
        //goal not achieved
        return false;
    }

    /**
     * @return Common Goal id
     */
    @Override
    public int getId() {
        return this.id;
    }

   //todo utilizzata solo nelle classi di test, da cancellare?
    public void print(){
        System.out.println("G1_G3");
    }
}