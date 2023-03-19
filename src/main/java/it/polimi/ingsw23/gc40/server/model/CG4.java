package it.polimi.ingsw23.gc40.server.model;

public class CG4 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //TO DO
        ItemTile it;
        int count = 0;
        ItemTile[] temp = new ItemTile[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                it = p.getBookshelf().getItemTile(i, j);
                ItemTile right = it.rightTile(p.getBookshelf());
                ItemTile below = it.belowTile(p.getBookshelf());
                ItemTile diagonal = it.diagonalRightDownTile(p.getBookshelf());
                temp[0] = it;
                temp[1] = right;
                temp[2] = below;
                temp[3] = diagonal;
                if (it.equalsToN(4, temp) == 4) {
                    count++;
                    i++;
                }
                if (count == 2)
                    return true;

            }
        }
        return false;
    }
}
