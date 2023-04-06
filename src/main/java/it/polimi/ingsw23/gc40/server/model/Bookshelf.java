package it.polimi.ingsw23.gc40.server.model;

import java.util.HashMap;

public class Bookshelf {
    private ItemTile[][] shelf;
    private boolean full = false;

    private int ADJCounter;


    public Bookshelf() {
        shelf = new ItemTile[6][5];
        ADJCounter = 0;
        //TO TEST
        //initialize all the ItemTile to EMPTY
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                shelf[i][j] = new ItemTile(Item.EMPTY);
            }
        }
    }

    public void setFull(boolean full) {
        this.full = full;
    }
    public boolean getFull(){ return this.full;}

    public ItemTile[][] getShelf() {
        return shelf;
    }

    public void insertIT(ItemTile it, int column) {
        //TO TEST
        for (int i = 5; i >= 0; i--) {
            if (shelf[i][column].getItem().equals(Item.EMPTY)) {
                shelf[i][column] = it;
                break;
            }
        }
    }

    public void print() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].toString() + "|");
            }
            System.out.println();

        }
    }

    public ItemTile getItemTile(int x, int y) {
        return shelf[x][y];
    }

    //return the max number of ItemTiles that we can take from the LRB checking empty slots in bookshelf
    public int placeables() {
        //TO TEST
        int maxSlotAvailable = 0;
        //checking in order:
        //      if there are at least 3 free cell return directly 3 (we can take max 3 ItemTiles)
        //      else if maxSlotAvailable is updated it check the next column
        for (int j = 0; j <= 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if (shelf[i][j].getItem().equals(Item.EMPTY)) {
                    if (i == 2) return i + 1;
                    else if (maxSlotAvailable < i + 1) {
                        maxSlotAvailable = i + 1;
                        break;
                    }
                }
            }
        }
        return maxSlotAvailable;
    }

    //returning the number of free slots for a column, if there aren't free slots return 0
    public int getAvailableSlots(int n) {
        //TO TEST
        for (int i = 2; i >= 0; i--) {
            if (shelf[i][n].getItem().equals(Item.EMPTY)) return i + 1;
        }
        return 0;
    }

    public void checker(int i, int j, Item type) {
        //TO TEST
        if (!shelf[i][j].getCheckedADJ() && type.equals(shelf[i][j].getItem())) {
            ADJCounter++;
            shelf[i][j].setCheckedADJ(true);

            if (j < 4) checker(i, j + 1, type);
            if (i < 5) checker(i + 1, j, type);
            if (j > 0) checker(i, j - 1, type);
            if (i > 0) checker(i - 1, j, type);
        }

    }

    public int ADJScore() {
        //TO TEST
        //return the sum of the max adjacent same-type cells, for each type

        //setting false all ItemTile getChecked
        for(int i=0; i<=5; i++){
            for(int j=0; j<=4; j++){
                getShelf()[i][j].setCheckedADJ(false);
            }
        }
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (!shelf[i][j].getCheckedADJ() && !shelf[i][j].getItem().equals(Item.EMPTY)) {
                    ADJCounter = 0;
                    checker(i, j, shelf[i][j].getItem());
                    //checker returns number of item tile in a single group, put it into the switch to update sum with the corresponding score
                    switch (ADJCounter) {
                        //unreachable state could be removed
                        case 0:
                            sum = sum;
                            break;
                        case 1:
                            sum = sum;
                            break;
                        case 2:
                            sum = sum;
                            break;
                        case 3:
                            sum = sum + 2;
                            break;
                        case 4:
                            sum = sum + 3;
                            break;
                        case 5:
                            sum = sum + 5;
                            break;
                        default:
                            sum = sum + 8;
                    }

                }
            }
        }
        return sum;
    }

    //Creating two methods to set/get ADJCounter to test this class
    public int getADJCounter() {
        return ADJCounter;
    }
    public void setADJCounter(int x) {
        ADJCounter = x;
    }




}


