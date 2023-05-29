package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CLI extends View {
    private Scanner sc;

    public CLI(ConnectionController con) {
        super(con);
        sc = new Scanner(System.in);
    }

    public void init() throws RemoteException, Exception {
        System.out.println("#metodo start della view CLI");
        super.nickname = inString("Inserisci nome giocatore: ");
        while (!con.addPlayer(super.nickname)) {
            System.out.println("Nome utente già in uso, riprova...");
            super.nickname = inString("Inserisci nome giocatore: ");
        }

        if (con.hasQueueMatch()) {
            System.out.println("Sei stato aggiunto ad un match pre-esistente in attesa di giocatori.");
        } else {
            while (!con.newMatch(inInt("Stai creando un nuovo match, di quanti giocatori vuoi che sia la partita? ")))
                System.out.println("Match non creato, riprova...  (Ricorda che le partite possono avere da 2 a 4 giocatori)");
        }
        super.matchId = con.addToLastMatch(super.nickname);

        System.out.println("---------");

        while (!con.playable(matchId)) {
            while (inInt("Digita 0 per uscire - 1 per visualizzare i dettagli") == 1) {
                System.out.println("Match ID: " + super.match_data.idMatch);
                System.out.println("Nickname: " + super.player_data.getNickname());
                System.out.println("PGC: " + super.player_data.getPgc());
            }

        }

        if (con.playable(matchId))
            System.out.println("##############puoi iniziare a giocare###################");

        while (inInt("Digita 0 per giocare") != 0) {
            System.out.println("ok");
        }


        while (con.playable(matchId)) {
            int choice;
            if (!match_data.current.equals(nickname)) {
                System.out.println("E' il turno di " + match_data.current);
                choice = getChoice(new String[]{"Mostra LRB", "Mostra Personal Goal Card", "Mostra BookShelf", "Visualizza Common Goals"}, "Scegli una delle seguenti azioni:");
            } else {
                System.out.println("Ora tocca a te!");
                choice = getChoice(new String[]{"Mostra LRB", "Mostra Personal Goal Card", "Mostra BookShelf", "Visualizza Common Goals", "Fai la tua mossa, tocca a te!"}, "Scegli una delle seguenti azioni:");
            }
            switch (choice) {
                case 0:
                    printLRB(match_data.lrb);
                    break;
                case 1:
                    printPGC(player_data.getPgc());
                    break;
                case 2:
                    printBS(player_data.getBookshelf());
                    break;
                case 3:
                    printCGS(match_data.cgs);
                    break;
                case 4:

                    System.out.println("Fai la tua mossa!");
                    int placeables = con.startRound(nickname, matchId);
                    System.out.println("Puoi pescare " + placeables + " tiles...");
                    ItemTile[] handTiles;
                    printLRB(match_data.lrb);


                    int y, x, quantity;
                    char direction;
                    do {
                        System.out.println("Per selezionare le Tiles inserisci le coordinate della prima Tile nella riga che vuoi prendere:");
                        System.out.print("X: ");
                        x = sc.nextInt();
                        System.out.print("Y: ");
                        sc.reset();
                        y = sc.nextInt();
                        System.out.print("Quante Tiles vuoi prendere? ");
                        sc.reset();


                        do {
                            quantity = sc.nextInt();
                            if (quantity > placeables)
                                System.out.print("Non hai spazio sufficiente nella tua bookshelf per " + quantity + " tiles!\nRiprova, quante Tiles vuoi prendere? ");
                        } while (quantity > placeables);
                        handTiles = new ItemTile[quantity];

                        System.out.println("Verso dove vuoi prendere?   (U per Up - D per Down - L per Left - R per Right)");
                        sc.reset();

                        direction = sc.next().charAt(0);
                        handTiles = con.chooseTiles(matchId, x, y, quantity, direction);
                        if (handTiles == null) {
                            System.out.println("Hai selezionato una o più Tiles non prendibili, riprova:");
                            printLRB(match_data.lrb);
                        }

                    } while (handTiles == null);

                    System.out.println("olee!");








                        /*





                            for (int i = 0; i < quantity; i++) {

                            }

                            //mostro la bookshelf e chiedo colonna in cui vuole inserire
                            int column;
                            p.getBookshelf().print();
                            boolean retry;
                            do {
                                retry = false;
                                System.out.println("In quale colonna vuoi inserire le tiles?");
                                sc.reset();
                                column = sc.nextInt();
                                if (column < 0 || column > 4) {
                                    retry = true;
                                    System.out.println("Hai scelto una colonna inesistente");
                                }
                                if (p.getBookshelf().getAvailableSlots(column) < handTiles.length) {
                                    System.out.println("La colonna non ha sufficiente spazio, riprova:");
                                    retry = true;
                                }
                            } while (retry);

                            //mostra tiles in mano
                            System.out.println("Hai selezionato i seguenti Tiles:");
                            for (int i = 0; i < handTiles.length; i++) {
                                System.out.print(i + ": " + handTiles[i].toString() + "|");
                            }

                            //inserisci le tiles che hai in mano
                            for (int i = 0; i < handTiles.length; i++) {
                                System.out.println("Scegli una tile da inserire:");
                                int selected;
                                do {
                                    retry = false;
                                    selected = sc.nextInt();
                                    if (selected >= handTiles.length || selected < 0) {
                                        System.out.println("Riprova:");
                                        retry = true;
                                    } else {
                                        if (handTiles[selected] == null) {
                                            System.out.println("Riprova:");
                                            retry = true;
                                        }
                                    }
                                } while (retry);
                                p.getBookshelf().insertIT(handTiles[selected], column);
                                handTiles[selected] = null;
                                sc.reset();
                            }

                            //check if there is space in the player bookshelf
                            if (p.getBookshelf().getFull()) {
                                //p.getBookshelf().setFull(true);
                                p.setFirstScore(1);
                                this.ending = true;
                            }

                            //refresh scores
                            p.setADJScore(p.getBookshelf().ADJScore());
                            if (commonGoals[0].getGoal().instanceGoal(p))
                                p.setCGScore(p.getCGScore() + CG1Scores.get(CG1Scores.size()) - 1);
                            if (commonGoals[1].getGoal().instanceGoal(p))
                                p.setCGScore(p.getCGScore() + CG2Scores.get(CG2Scores.size()) - 1);
                            p.getPersonalGoal().checkScore(p);

                            //set turn to next player
                            int num;
                            if (players.indexOf(p) == players.size() - 1)
                                num = 0;
                            else
                                num = players.indexOf(p) + 1;
                            next = players.get(num);


                        */


                    break;
                default:
                    System.out.println("Scelta errata!");
                    break;
            }

        }


    }


    public String inString(String question) {
        System.out.println(question);
        String line = sc.nextLine();
        sc.reset();
        return line;
    }

    public int inInt(String question) {
        System.out.println(question);
        int n = sc.nextInt();
        sc.reset();
        return n;
    }

    public static int getChoice(String[] choices, String question) {
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(question);
            for (int i = 0; i < choices.length; i++)
                System.out.println(i + ": " + choices[i]);
            System.out.println("Digitare un numero per effettuare la scelta: ");
            n = 0;
            sc.reset();
            n = sc.nextInt();
            if (n < 0 || n >= choices.length)
                System.out.println("Scelta errata, riprova.");
        } while (n < 0 || n >= choices.length);
        return n;
    }

    public void printLRB(ItemTile[][] map) {
        System.out.println("#|0|1|2|3|4|5|6|7|8|");
        for (int i = 0; i < 9; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j].toString() + "|");
            }
            System.out.println();
        }
    }


    public void printPGC(int n) {
        System.out.println("La tua personal Goal Card:");
        printBS(pgCards.get(n - 1));
    }

    public void printCGS(int[] cgs) {
        for (int i = 0; i < cgs.length; i++) {
            System.out.println((i + 1) + ") Common Goal Card n." + cgs[i] + ":");
            System.out.println(cgDescriptions[cgs[i] - 1]);
            System.out.println("");
        }
    }


    public void printBS(ItemTile[][] shelf) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].toString() + "|");
            }
            System.out.println();
        }
    }

}