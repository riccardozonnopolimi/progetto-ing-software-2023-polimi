package it.polimi.ingsw23.gc40.server.model;

public class CG11 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //TO DO
        ItemTile it;
        int count = 0;
        ItemTile[] temp = new ItemTile[5];

        for (int i = 1; i < 5; i++){
            for (int j = 1; j < 4; j++) {
                it = p.getBookshelf().getItemTile(i, j);
                ItemTile diagonalRightDown = it.diagonalRightDownTile(p.getBookshelf());
                ItemTile diagonalRightUp = it.diagonalRightUpTile(p.getBookshelf());
                ItemTile diagonalLeftDown = it.diagonalLeftDownTile(p.getBookshelf());
                ItemTile diagonalLeftUp = it.diagonalLeftUpTile(p.getBookshelf());

                temp[0] = it;
                temp[1] = diagonalRightDown;
                temp[2] = diagonalRightUp;
                temp[3] = diagonalLeftDown;
                temp[4] = diagonalLeftUp;
                if (it.equalsToN(5, temp) == 5) {
                    return true;
                }
            }
        }
        return false;
    }
}
