package it.polimi.ingsw23.gc40.server.model;

public class ItemTile {
    private final Item type;
    private int x;
    private int y;

    public ItemTile(Item t) {
        type = t;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    // public boolean controlSides(LRB map){};
    //TO DO
    Item getItem() {
        return type;
    }
    public boolean equals(ItemTile it) {
        return (this.type == it.type);
    }
    public ItemTile rightTile (Bookshelf b){
      return  b.getItemTile(this.getX(),this.getY() + 1);
    }
    public ItemTile belowTile (Bookshelf b){
        return  b.getItemTile(this.getX() +1,this.getY());
    }
    public ItemTile diagonalRightDownTile(Bookshelf b){
        return  b.getItemTile(this.getX() +1,this.getY()+1);
    }
    public ItemTile diagonalRightUpTile(Bookshelf b){
        return  b.getItemTile(this.getX() -1,this.getY()+1);
    }
    public ItemTile diagonalLeftDownTile(Bookshelf b){
        return  b.getItemTile(this.getX() +1,this.getY()-1);
    }
    public ItemTile diagonalLeftUpTile(Bookshelf b){
        return  b.getItemTile(this.getX() -1,this.getY()-1);
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