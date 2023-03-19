package it.polimi.ingsw23.gc40.server.model;

public class CG12 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        ItemTile empty = new ItemTile(Item.EMPTY);
        //control if there is a diagonal of 5 in the bookshelf that is all fill and the space at right is empty
        if(p.getBookshelf().getItemTile(0,0).equals(empty)
                && p.getBookshelf().getItemTile(1,1).equals(empty)
                && p.getBookshelf().getItemTile(2,2).equals(empty)
                && p.getBookshelf().getItemTile(3,3).equals(empty)
                && p.getBookshelf().getItemTile(4,4).equals(empty)
                && !p.getBookshelf().getItemTile(1,0).equals(empty)
                && !p.getBookshelf().getItemTile(2,1).equals(empty)
                && !p.getBookshelf().getItemTile(3,2).equals(empty)
                && !p.getBookshelf().getItemTile(4,3).equals(empty)
                && !p.getBookshelf().getItemTile(5,4).equals(empty)) {
            return true;
        }

        if(!p.getBookshelf().getItemTile(0,0).equals(empty)
                && !p.getBookshelf().getItemTile(1,1).equals(empty)
                && !p.getBookshelf().getItemTile(2,2).equals(empty)
                && !p.getBookshelf().getItemTile(3,3).equals(empty)
                && !p.getBookshelf().getItemTile(4,4).equals(empty)
                && p.getBookshelf().getItemTile(0,1).equals(empty)
                && p.getBookshelf().getItemTile(1,2).equals(empty)
                && p.getBookshelf().getItemTile(2,3).equals(empty)
                && p.getBookshelf().getItemTile(3,4).equals(empty)) {
            return true;
        }
        if(p.getBookshelf().getItemTile(0,3).equals(empty)
                && p.getBookshelf().getItemTile(1,2).equals(empty)
                && p.getBookshelf().getItemTile(2,1).equals(empty)
                && p.getBookshelf().getItemTile(3,0).equals(empty)
                && !p.getBookshelf().getItemTile(0,4).equals(empty)
                && !p.getBookshelf().getItemTile(1,3).equals(empty)
                && !p.getBookshelf().getItemTile(2,2).equals(empty)
                && !p.getBookshelf().getItemTile(3,1).equals(empty)
                && !p.getBookshelf().getItemTile(4,0).equals(empty)) {
            return true;
        }

        if(!p.getBookshelf().getItemTile(1,4).equals(empty)
                && !p.getBookshelf().getItemTile(2,3).equals(empty)
                && !p.getBookshelf().getItemTile(3,2).equals(empty)
                && !p.getBookshelf().getItemTile(4,1).equals(empty)
                && !p.getBookshelf().getItemTile(5,0).equals(empty)
                && p.getBookshelf().getItemTile(0,4).equals(empty)
                && p.getBookshelf().getItemTile(1,3).equals(empty)
                && p.getBookshelf().getItemTile(2,2).equals(empty)
                && p.getBookshelf().getItemTile(3,1).equals(empty)
                && p.getBookshelf().getItemTile(4,0).equals(empty)) {
            return true;
    }
        return false;
    }
}
