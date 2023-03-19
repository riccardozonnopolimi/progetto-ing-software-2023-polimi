package it.polimi.ingsw23.gc40.server.model;

public class CG7 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST

        //control if there is a diagonal of 5 in the bookshelf that is all the same type
        if(p.getBookshelf().getItemTile(0,0).equals(p.getBookshelf().getItemTile(1,1))
        && p.getBookshelf().getItemTile(1,1).equals(p.getBookshelf().getItemTile(2,2))
        && p.getBookshelf().getItemTile(2,2).equals(p.getBookshelf().getItemTile(3,3))
        && p.getBookshelf().getItemTile(3,3).equals(p.getBookshelf().getItemTile(4,4)))
            return true;
        if(p.getBookshelf().getItemTile(1,0).equals(p.getBookshelf().getItemTile(2,1))
                && p.getBookshelf().getItemTile(2,1).equals(p.getBookshelf().getItemTile(3,2))
                && p.getBookshelf().getItemTile(3,2).equals(p.getBookshelf().getItemTile(4,3))
                && p.getBookshelf().getItemTile(4,3).equals(p.getBookshelf().getItemTile(5,4)))
            return true;
        if(p.getBookshelf().getItemTile(0,4).equals(p.getBookshelf().getItemTile(1,3))
                && p.getBookshelf().getItemTile(1,3).equals(p.getBookshelf().getItemTile(2,2))
                && p.getBookshelf().getItemTile(2,2).equals(p.getBookshelf().getItemTile(3,1))
                && p.getBookshelf().getItemTile(3,1).equals(p.getBookshelf().getItemTile(4,0)))
            return true;
        return p.getBookshelf().getItemTile(1, 4).equals(p.getBookshelf().getItemTile(2, 3))
                && p.getBookshelf().getItemTile(2, 3).equals(p.getBookshelf().getItemTile(3, 2))
                && p.getBookshelf().getItemTile(3, 2).equals(p.getBookshelf().getItemTile(4, 1))
                && p.getBookshelf().getItemTile(4, 1).equals(p.getBookshelf().getItemTile(5, 0));
    }
}
