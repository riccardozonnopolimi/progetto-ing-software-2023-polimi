package it.polimi.ingsw23.gc40.server.model;

public class ItemTile {
    private final Item type;


    public ItemTile(Item t){
        type = t;
    }
    // public boolean controlSides(LRB map){}; add unimplemented exception
     Item getItem() {
         return type;
     }

}
