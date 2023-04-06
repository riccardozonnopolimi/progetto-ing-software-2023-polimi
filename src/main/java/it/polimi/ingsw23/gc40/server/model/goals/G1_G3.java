package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G1_G3 implements Goal {
    private final int grandezzaBlocco;
    private final int numeroBlocchi;

    public G1_G3(int grandezzaBlocco, int numeroBlocchi) {
        this.grandezzaBlocco = grandezzaBlocco;
        this.numeroBlocchi = numeroBlocchi;
    }

    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
        //value to update when a column or a line is found

        int ADJCounter;
        int validBlock = numeroBlocchi;
        ItemTile[][] s = p.getBookshelf().getShelf();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (!s[i][j].getCheckedADJ() && !s[i][j].getItem().equals(Item.EMPTY)) {
                    ADJCounter = 0;
                    p.getBookshelf().checker(i, j, s[i][j].getItem());
                    //method called by CG1||CG3
                    if (ADJCounter > grandezzaBlocco) {
                        validBlock--;
                    }
                    if (validBlock == 0) {
                        //goal achieved
                        return true;
                    }
                }

            }
        }
        //goal not achieved
        return false;
    }
}