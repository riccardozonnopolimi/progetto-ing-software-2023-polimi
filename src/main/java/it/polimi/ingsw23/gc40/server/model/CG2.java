package it.polimi.ingsw23.gc40.server.model;

public class CG2 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //control if the superior angles
        // and the inferior angles are both equals
        // and if the superior left angle is equal to the inferior left
        return (p.getShelf().getItemTile(0,0).equals(p.getShelf().getItemTile(0,4))
                && p.getShelf().getItemTile(5,0).equals(p.getShelf().getItemTile(5,4))
                && p.getShelf().getItemTile(0,0).equals(p.getShelf().getItemTile(5,0)));
    }
}
