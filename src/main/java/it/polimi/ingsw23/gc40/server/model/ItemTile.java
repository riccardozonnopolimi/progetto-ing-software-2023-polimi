package it.polimi.ingsw23.gc40.server.model;

public class ItemTile {
    private final Item type;
    //x, y are ItemTile's coordinates when it is placed on LRB

    private boolean checkedADJ = false;


    public ItemTile(Item t) {
        type = t;
    }

    // public boolean controlSides(LRB map){};
    //TO DO
    public Item getItem() {
        return type;
    }

    public boolean getCheckedADJ(){
        return checkedADJ;
    }
    public void setCheckedADJ(boolean check){
        this.checkedADJ =check;
    }
    public boolean equals(ItemTile it) {
        return (this.type == it.type);
    }
    public int equalsToN(int n, ItemTile[] i) {
        int equals = 0;
        for (int j = 0; j<n-2; j++){
            if (i[j].equals(i[j+1]))
                equals++;
        }
        return equals;
    }
}