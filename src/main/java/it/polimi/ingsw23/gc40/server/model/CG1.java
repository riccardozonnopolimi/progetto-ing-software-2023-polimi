package it.polimi.ingsw23.gc40.server.model;

public class CG1 implements Goal {
    private final int grandezzaBlocco;
    private final int numeroBlocchi;

    public CG1(int grandezzaBlocco, int numeroBlocchi) {
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
    //soluzione 3: 3 matrici di booleani cosi: 1) copia tutte le righe
    //                                         2) copia tutte le colonne
    //                                         3) calcola le celle[i][j] che provocano conflitto
    //      a questo punto si contano tutte le righe e colonne senza conflitto, si cancellano dalle relative matrici
    //      e dovremmo aver eliminato i conflitti in modo da poter calcolare il totale
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
/*
        //dovrebbe funzionare sia per cg1 che per cg3 ma farei qualche test prima
        //favoreggia Tile dello stesso tipo sulla riga e non sulla colonna
        int n = 0;

        ItemTile[] right = new ItemTile[grandezzaBlocco-1];
        ItemTile[] below = new ItemTile[grandezzaBlocco-1];
        ItemTile[][] s = p.getBookshelf().getShelf();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                it = p.getBookshelf().getItemTile(i,j);
                //controllo sulle colonne
                if (j<5-grandezzaBlocco){
                    below[0] = it.belowTile(p.getBookshelf());
                    for (int k = 1; k < grandezzaBlocco-1; k++){
                        below[k] = below[k-1].belowTile(p.getBookshelf());
                //controllo sulle colonne
                if (j<5-grandezzaBlocco){
                    for (int h = i; h<grandezzaBlocco-1; h++){
                        for (int k = 0; k < grandezzaBlocco-1; k++){
                        below[k] = s[h+1][j];
                        }
                    }
                    if (s[i][j].equalsToN(grandezzaBlocco, below) == grandezzaBlocco){
                        n++;
                        j++;
                    }
                }


                it = p.getBookshelf().getItemTile(i,j); //questo non serve piu




                //controllo sulle righe
                else if (i<6-grandezzaBlocco){
                    right[0] = it.rightTile(p.getBookshelf());
                    for (int k = 1; k < grandezzaBlocco-1; k++){
                        right[k] = right[k-1].rightTile(p.getBookshelf());
                    }
                    if (it.equalsToN(grandezzaBlocco, below) == grandezzaBlocco){
                        n++;
                        i++;
                    }
                }
            }
        }
        return (n >= numeroBlocchi);
    }*/
        return false;
    }
}