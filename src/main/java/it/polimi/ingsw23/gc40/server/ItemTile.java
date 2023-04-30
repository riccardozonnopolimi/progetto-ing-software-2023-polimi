package it.polimi.ingsw23.gc40.server;

import java.io.Serializable;

public class ItemTile implements Serializable {
    private final Item type;
    private final int num;

    private boolean checkedADJ = false;


    public ItemTile(Item t) {
        type = t;
        num = 0;
    }

    public ItemTile(Item t, int n) {
        type = t;
        num = n % 3;
    }

    // public boolean controlSides(LRB map){};
    //TO DO
    public Item getItem() {
        return type;
    }

    @Override
    public String toString(){
        switch(this.type){
            case CAT -> {
                return "C";
            }
            case BOOK -> {
                return "B";
            }
            case GAME -> {
                return "G";
            }
            case FRAME -> {
                return "F";
            }
            case TROPHY -> {
                return "T";
            }
            case PLANT -> {
                return "P";
            }
            case EMPTY -> {
                return " ";
            }
            default -> {
                return "#";
            }
        }
    }

    public boolean getCheckedADJ() {
        return checkedADJ;
    }

    public void setCheckedADJ(boolean check) {
        this.checkedADJ = check;
    }

    public boolean equals(ItemTile it) {
        return (this.type == it.type);
    }

    public int equalsToN(int n, ItemTile[] i) {
        int equals = 0;
        for (int j = 0; j < n - 2; j++) {
            if (i[j].equals(i[j + 1])) equals++;
        }
        return equals;
    }
}