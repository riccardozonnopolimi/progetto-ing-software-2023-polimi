package it.polimi.ingsw23.gc40.server.model;

public class CG7 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //control if there is a diagonal of 5 in the bookshelf that is all the same type
        if(p.getShelf().getItemTile(0,0).equals(p.getShelf().getItemTile(1,1))
        && p.getShelf().getItemTile(1,1).equals(p.getShelf().getItemTile(2,2))
        && p.getShelf().getItemTile(2,2).equals(p.getShelf().getItemTile(3,3))
        && p.getShelf().getItemTile(3,3).equals(p.getShelf().getItemTile(4,4)))
            return true;
        if(p.getShelf().getItemTile(1,0).equals(p.getShelf().getItemTile(2,1))
                && p.getShelf().getItemTile(2,1).equals(p.getShelf().getItemTile(3,2))
                && p.getShelf().getItemTile(3,2).equals(p.getShelf().getItemTile(4,3))
                && p.getShelf().getItemTile(4,3).equals(p.getShelf().getItemTile(5,4)))
            return true;
        if(p.getShelf().getItemTile(0,4).equals(p.getShelf().getItemTile(1,3))
                && p.getShelf().getItemTile(1,3).equals(p.getShelf().getItemTile(2,2))
                && p.getShelf().getItemTile(2,2).equals(p.getShelf().getItemTile(3,1))
                && p.getShelf().getItemTile(3,1).equals(p.getShelf().getItemTile(4,0)))
            return true;
        return p.getShelf().getItemTile(1, 4).equals(p.getShelf().getItemTile(2, 3))
                && p.getShelf().getItemTile(2, 3).equals(p.getShelf().getItemTile(3, 2))
                && p.getShelf().getItemTile(3, 2).equals(p.getShelf().getItemTile(4, 1))
                && p.getShelf().getItemTile(4, 1).equals(p.getShelf().getItemTile(5, 0));
    }
}
