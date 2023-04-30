package it.polimi.ingsw23.gc40.server.goals;

import it.polimi.ingsw23.gc40.server.Item;
import it.polimi.ingsw23.gc40.server.ItemTile;
import it.polimi.ingsw23.gc40.server.Player;

public class G1_G3 implements Goal {
    private final int grandezzaBlocco;
    private final int numeroBlocchi;

    public G1_G3(int grandezzaBlocco, int numeroBlocchi) {
        this.grandezzaBlocco = grandezzaBlocco;
        this.numeroBlocchi = numeroBlocchi;
    }

    @Override
    public boolean instanceGoal(Player p) {
        //Tested
        //setting false all ItemTile getChecked
        for(int i=0; i<=5; i++){
            for(int j=0; j<=4; j++){
                p.getBookshelf().getShelf()[i][j].setCheckedADJ(false);
            }
        }
        //value to update when a column or a line is found

        int validBlock = numeroBlocchi;
        ItemTile[][] s = p.getBookshelf().getShelf();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (!s[i][j].getCheckedADJ() && !s[i][j].getItem().equals(Item.EMPTY)) {
                    p.getBookshelf().setADJCounter(0);

                    p.getBookshelf().checker(i, j, s[i][j].getItem());

                    if (p.getBookshelf().getADJCounter() >= grandezzaBlocco) {
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
    public void print(){
        System.out.println("G1_G3");
    }
}