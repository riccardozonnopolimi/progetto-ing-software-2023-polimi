package it.polimi.ingsw23.gc40.server.model;

public class CG7 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //control if there is a diagonal of 5 in the bookshelf that is all the same type
        if(p.getShelf().getCell(0,0).equals(p.getShelf().getCell(1,1))
        && p.getShelf().getCell(1,1).equals(p.getShelf().getCell(2,2))
        && p.getShelf().getCell(2,2).equals(p.getShelf().getCell(3,3))
        && p.getShelf().getCell(3,3).equals(p.getShelf().getCell(4,4)))
            return true;
        if(p.getShelf().getCell(1,0).equals(p.getShelf().getCell(2,1))
                && p.getShelf().getCell(2,1).equals(p.getShelf().getCell(3,2))
                && p.getShelf().getCell(3,2).equals(p.getShelf().getCell(4,3))
                && p.getShelf().getCell(4,3).equals(p.getShelf().getCell(5,4)))
            return true;
        if(p.getShelf().getCell(0,4).equals(p.getShelf().getCell(1,3))
                && p.getShelf().getCell(1,3).equals(p.getShelf().getCell(2,2))
                && p.getShelf().getCell(2,2).equals(p.getShelf().getCell(3,1))
                && p.getShelf().getCell(3,1).equals(p.getShelf().getCell(4,0)))
            return true;
        return p.getShelf().getCell(1, 4).equals(p.getShelf().getCell(2, 3))
                && p.getShelf().getCell(2, 3).equals(p.getShelf().getCell(3, 2))
                && p.getShelf().getCell(3, 2).equals(p.getShelf().getCell(4, 1))
                && p.getShelf().getCell(4, 1).equals(p.getShelf().getCell(5, 0));
    }
}
