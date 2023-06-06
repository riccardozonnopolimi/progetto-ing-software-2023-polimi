package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G1_G3 implements Goal {
    private final int grandezzaBlocco;
    private final int numeroBlocchi;
    private final int id;

    public G1_G3(int grandezzaBlocco, int numeroBlocchi, int id) {
        this.grandezzaBlocco = grandezzaBlocco;
        this.numeroBlocchi = numeroBlocchi;
        this.id = id;

    }

    @Override
    public boolean instanceGoal(Player p) {
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

    @Override
    public int getId() {
        return this.id;
    }

    public void print(){
        System.out.println("G1_G3");
    }
}