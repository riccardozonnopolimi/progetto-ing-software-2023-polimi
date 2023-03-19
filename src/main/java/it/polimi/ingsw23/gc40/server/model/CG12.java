package it.polimi.ingsw23.gc40.server.model;

public class CG12 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST

        ItemTile[][] s = p.getBookshelf().getShelf();;
        //control if there is a diagonal of 5 in the bookshelf that is all fill and the space at right is empty

        if((s[0][0].getItem()==Item.EMPTY)
            && (s[1][1].getItem()==Item.EMPTY)
            && (s[2][2].getItem()==Item.EMPTY)
            && (s[3][3].getItem()==Item.EMPTY)
            && (s[4][4].getItem()==Item.EMPTY)
            && !(s[1][0].getItem()==Item.EMPTY)
            && !(s[2][1].getItem()==Item.EMPTY)
            && !(s[3][2].getItem()==Item.EMPTY)
            && !(s[4][3].getItem()==Item.EMPTY)
            && !(s[5][4].getItem()==Item.EMPTY)) {
            return true;
        }

        if((s[0][1].getItem()==Item.EMPTY)
                && (s[1][2].getItem()==Item.EMPTY)
                && (s[2][3].getItem()==Item.EMPTY)
                && (s[3][4].getItem()==Item.EMPTY)
                && !(s[0][0].getItem()==Item.EMPTY)
                && !(s[1][1].getItem()==Item.EMPTY)
                && !(s[2][2].getItem()==Item.EMPTY)
                && !(s[3][3].getItem()==Item.EMPTY)
                && !(s[4][4].getItem()==Item.EMPTY)) {
            return true;
        }


        if((s[0][3].getItem()==Item.EMPTY)
                && (s[1][2].getItem()==Item.EMPTY)
                && (s[2][1].getItem()==Item.EMPTY)
                && (s[3][0].getItem()==Item.EMPTY)
                && !(s[0][4].getItem()==Item.EMPTY)
                && !(s[1][3].getItem()==Item.EMPTY)
                && !(s[2][2].getItem()==Item.EMPTY)
                && !(s[3][1].getItem()==Item.EMPTY)
                && !(s[4][0].getItem()==Item.EMPTY)) {
            return true;
        }



        if((s[0][4].getItem()==Item.EMPTY)
                && (s[1][3].getItem()==Item.EMPTY)
                && (s[2][2].getItem()==Item.EMPTY)
                && (s[3][1].getItem()==Item.EMPTY)
                && (s[4][0].getItem()==Item.EMPTY)
                && !(s[1][4].getItem()==Item.EMPTY)
                && !(s[2][3].getItem()==Item.EMPTY)
                && !(s[3][2].getItem()==Item.EMPTY)
                && !(s[4][1].getItem()==Item.EMPTY)
                && !(s[5][0].getItem()==Item.EMPTY)) {
            return true;
        }

        return false;
    }
}
