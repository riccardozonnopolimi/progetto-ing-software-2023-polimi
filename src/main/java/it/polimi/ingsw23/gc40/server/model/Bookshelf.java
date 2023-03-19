package it.polimi.ingsw23.gc40.server.model;

import java.util.HashMap;

public class Bookshelf {
    private ItemTile[][] shelf;
    private boolean full = false;


    public Bookshelf(){
        shelf = new ItemTile[6][5];
        //TO TEST
        //initialize all the ItemTile to EMPTY
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                shelf[i][j]= new ItemTile(Item.EMPTY);
            }
        }
    }

    public void setFull(boolean full) {
        this.full = full;
    }
    public ItemTile[][] getShelf() {
        return shelf;
    }

    public void insertIT(ItemTile it, int column){
        //TO DO
        for(int i=5; i>=0; i--){
            if(shelf[i][column].getItem().equals(Item.EMPTY)){
                shelf[i][column] = it;
                break;
            }
        }
    }

    public ItemTile getItemTile(int x, int y){
        return shelf[x][y];
    }

    //return the max number of ItemTiles that we can take from the LRB checking empty slots in bookshelf
    public int placeables(){
        //TO TEST
        int maxSlotAvailable=0;
        //checking in order:
        //      if there are at least 3 free cell return directly 3 (we can take max 3 ItemTiles)
        //      else if maxSlotAvailable is updated it check the next column
        for(int j=0; j<=5; j++){
            for(int i=2; i>=0; i--){
                if(shelf[i][j].getItem().equals(Item.EMPTY)){
                    if(i==2) return i+1;
                    else if (maxSlotAvailable<i+1) {
                        maxSlotAvailable=i+1;
                        break;
                    }
                }
            }
        }
        return maxSlotAvailable;
    }

    //returning the number of free slots for a column, if there aren't free slots
    public int getAvailableSlots(int n){
        //TO TEST
        for(int i=2; i>=0; i--){
            if(shelf[i][n].getItem().equals(Item.EMPTY))    return i+1;
        }
        return 0;
    }

    public int checkAdjObjects(){
        //TO DO
        //return the sum of the max adjacent same-type cells, for each type
        HashMap<Item, Integer> sommeParziali = new HashMap();
        int sum=0;

        return 0;
    }

}
