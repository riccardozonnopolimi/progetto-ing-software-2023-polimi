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

    //soluzione 1: 3 matrici di booleani che fanno 1) copia tutte le colonne senza conflitto e si attribuisce punti
    //                                             2) copia tutte le righe senza conflitto e si attribuiscono punti
    //                                             3) righe e colonne con conflitto, crei due interi che contano i conflitti
    //                                             sulle colonne e sulle righe, se scorrendo i e j di quel conflitto,
    //                                             se hai piu conflitti nella riga cancelli la riga
    //                                             altrimenti cancelli la colonna,
    //                                             e cosi con tutti i confilitti rimanenti
    //                                             e conti i punti

    //soluzione 2: conti tutte le colonne, tutte le righe con e senza conflitti in 4 int diversi.
    // Se righe_tot > colonne_tot allora num finale è righe_tot + colonne_senza
    // Se righe_tot < colonne_tot allora num finale è colonne_tot + righe_senza
    // caso da risolvere: se in una colonna/riga della matrice c'è più di una riga/colonna di tile
    //soluzione 3: 3 matrici di booleani cosi: 1) copia tutte le righe
    //                                         2) copia tutte le colonne
    //                                         3) calcola le celle[i][j] che provocano conflitto
    //      a questo punto si contano tutte le righe e colonne senza conflitto, si cancellano dalle relative matrici
    //      e dovremmo aver eliminato i conflitti in modo da poter calcolare il totale
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