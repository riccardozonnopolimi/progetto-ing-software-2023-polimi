package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Bookshelf;
import it.polimi.ingsw23.gc40.server.model.Item;
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
        //TO DO
        //value to update when a column or a line is found
        int numberRC = 0;
        numberRC += updateRC(p.getBookshelf(), Item.CAT);
        if (numberRC >= numeroBlocchi) {
            return true;
        } else numberRC += updateRC(p.getBookshelf(), Item.BOOK);
        if (numberRC >= numeroBlocchi) {
            return true;
        } else numberRC += updateRC(p.getBookshelf(), Item.FRAME);
        if (numberRC >= numeroBlocchi) {
            return true;
        } else numberRC += updateRC(p.getBookshelf(), Item.GAME);
        if (numberRC >= numeroBlocchi) {
            return true;
        } else numberRC += updateRC(p.getBookshelf(), Item.PLANT);
        if (numberRC >= numeroBlocchi) {
            return true;
        } else numberRC += updateRC(p.getBookshelf(), Item.TROPHY);
        if (numberRC >= numeroBlocchi) {
            return true;
        } else return false;

    }

    int updateRC(Bookshelf shelf, Item type) {
        //TO DO
        //inner class to create a

        boolean matColumn[][] = new boolean[6][5];
        boolean matLine[][] = new boolean[6][5];
        boolean counted;
        int numColumn = 0;
        int numLine = 0;


        //checking column of the same type
        for (int j = 0; j <= 4; j++) {
            counted = false;
            for (int i = 0; i <= 2; i++) {
                if (shelf.getItemTile(i, j).getItem().equals(type) && shelf.getItemTile(i + 1, j).getItem().equals(type) && shelf.getItemTile(i + 2, j).getItem().equals(type) && shelf.getItemTile(i + 3, j).getItem().equals(type)) {
                    matColumn[i][j] = true;
                    matColumn[i + 1][j] = true;
                    matColumn[i + 2][j] = true;
                    matColumn[i + 3][j] = true;
                    if (!counted) {
                        numColumn++;
                        counted = true;
                    }
                }
            }
        }
        //checking for line of the same type
        for (int i = 0; i <= 5; i++) {
            counted = false;
            for (int j = 0; j <= 1; j++) {
                if (shelf.getItemTile(i, j).getItem().equals(type) && shelf.getItemTile(i, j + 1).getItem().equals(type) && shelf.getItemTile(i, j + 2).getItem().equals(type) && shelf.getItemTile(i, j + 3).getItem().equals(type)) {
                    matColumn[i][j] = true;
                    matColumn[i][j + 1] = true;
                    matColumn[i][j + 2] = true;
                    matColumn[i][j + 3] = true;
                    if (!counted) {
                        numLine++;
                        counted = true;
                    }
                }
            }
        }
        //checking presence of column/line without conflicts for multicolumn or multiline in the same column/line
        for (int j = 0; j <= 4; j++) {
            for (int i = 0; i <= 2; i++) {
                if (matColumn[i][j] && !matLine[i][j] && matColumn[i + 1][j] && !matLine[i + 1][j] && matColumn[i + 2][j] && !matLine[i + 2][j] && matColumn[i + 3][j] && !matLine[i + 3][j]) {
                    if (i == 0) {
                        matColumn[4][j] = false;
                        matColumn[5][j] = false;
                    } else if (i == 1) {
                        matColumn[0][j] = false;
                        matColumn[5][j] = false;
                    } else if (i == 2) {
                        matColumn[0][j] = false;
                        matColumn[1][j] = false;
                    }
                }
            }
        }
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 1; j++) {
                if (matLine[i][j] && !matColumn[i][j] && matLine[i][j + 1] && !matColumn[i][j + 1] && matLine[i][j + 2] && !matColumn[i][j + 2] && matLine[i][j + 3] && !matColumn[i][j + 3]) {
                    if (j == 0) {

                    }
                }
            }


            return 0;
        }
        return 0;
    }


}