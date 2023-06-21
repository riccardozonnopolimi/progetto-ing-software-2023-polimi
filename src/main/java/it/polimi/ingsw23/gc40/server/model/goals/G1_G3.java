package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G1_G3 implements Goal {
    private final int blockSize;
    private final int numOfBlocks;
    private final int id;

    public G1_G3(int blockSize, int numOfBlocks, int id) {
        this.blockSize = blockSize;
        this.numOfBlocks = numOfBlocks;
        this.id = id;
    }

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
        //check if there is a block long like grandezzaBlocco of the same type in a column
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

    @Override
    public int getId() {
        return this.id;
    }

    public void print(){
        System.out.println("G1_G3");
    }
}