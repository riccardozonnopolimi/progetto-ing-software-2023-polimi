package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelObserver;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

public class CLI extends View {
    private Scanner sc;
    static public String verde = "\u001b[42m\u001b[30m";
    static String rosso = "\u001B[41m\u001B[30m";
    static String giallo = "\u001b[43m\u001b[30m";
    static public String reset = "\u001b[0m";
    static public String grigio = "\u001b[47m\u001b[30m";
    private PublicModelObserver publicObserver;
    private PrivateModelObserver privateObserver;

    public CLI(ConnectionController con) {
        super(con);
        sc = new Scanner(System.in);
    }

    public void init() throws RemoteException, Exception {
        System.out.println(verde + "Interfaccia di gioco testuale avviata!" + reset);
        System.out.println("");
        super.nickname = inString("Inserisci nome giocatore: ");
        while (!con.addPlayer(super.nickname)) {
            System.out.println(giallo + "Nome utente già in uso, riprova..." + reset);
            super.nickname = inString("Inserisci nome giocatore: ");
        }
        System.out.println(verde + "Giocatore creato!" + reset);
        System.out.println("");


        if (con.hasQueueMatch()) {
            System.out.println(verde + "Sei stato aggiunto ad un match pre-esistente in attesa di giocatori." + reset);
        } else {
            while (!con.newMatch(inInt("Stai creando un nuovo match, di quanti giocatori vuoi che sia la partita? ")))
                System.out.println(rosso + "Match non creato, riprova...  (Ricorda che le partite possono avere da 2 a 4 giocatori)" + reset);
        }

        System.out.println(verde + "Match creato" + reset);
        super.matchId = con.addToLastMatch(super.nickname);


       /* publicObserver = con.getPublicObserver(matchId);
        publicObserver.addObserver(this);
        privateObserver = con.getPrivateObserver(nickname);
        privateObserver.addObserver(this);*/

        /*System.out.println("---------");

        while (!con.playable(matchId)) {
            while (inInt("Digita 0 per uscire - 1 per visualizzare i dettagli") == 1) {

                if (super.match_data!=null)
                    System.out.println("Match ID: " + super.match_data.getIdMatch());


                if (super.player_data != null) {
                    System.out.println("Nickname: " + super.player_data.getNickname());
                    System.out.println("PGC: " + super.player_data.getPgc());
                }
            }

        }*/
        System.out.println("");
        System.out.println(giallo + "IL MATCH PARTIRA' NON APPENA SI COLLEGERANNO GLI ALTRI GIOCATORI" + reset);

        while (!match_data.isPlayable()) {
            System.out.print("");
        }

        System.out.println("");
        System.out.println(verde + "MATCH PRONTO" + reset);


        while (match_data.isPlayable()) {
            int choice;

            if (!match_data.getCurrent().equals(nickname)) {
                System.out.println(giallo + "E' il turno di " + match_data.getCurrent() + reset);
                choice = getChoice(new String[]{"Mostra LRB", "Mostra Personal Goal Card", "Mostra BookShelf", "Visualizza Common Goals"}, "Scegli una delle seguenti azioni:", 1);
                if (!match_data.getCurrent().equals(nickname)) {
                    if (choice == 4) {
                        System.out.println("Scelta errata");
                        choice = 0;
                    }
                }
            } else {
                System.out.println(verde + "Ora tocca a te!" + reset);
                choice = getChoice(new String[]{"Mostra LRB", "Mostra Personal Goal Card", "Mostra BookShelf", "Visualizza Common Goals", "Fai la tua mossa, tocca a te!"}, "Scegli una delle seguenti azioni:");

            }

            switch (choice) {
                case 0:
                    printLRB(match_data.getLrb());
                    break;
                case 1:
                    printPGC(player_data.getPgc());
                    break;
                case 2:
                    printBS(player_data.getBookshelf());
                    break;
                case 3:
                    printCGS(match_data.getCgs());
                    break;
                case 4:

                    System.out.println("Fai la tua mossa!");
                    int placeables = con.startRound(nickname, matchId);
                    System.out.println("Puoi pescare " + placeables + " tiles...");
                    ItemTile[] handTiles;
                    printLRB(match_data.getLrb());


                    int y, x, quantity;
                    char direction;
                    do {
                        System.out.println("Per selezionare le Tiles inserisci le coordinate della prima Tile nella riga che vuoi prendere:");

                        x = inInt("X: ");

                        y = inInt("Y: ");


                        do {
                            quantity = inInt("Quante tiles vuoi prendere?");
                            if (quantity > placeables)
                                System.out.print("Non hai spazio sufficiente nella tua bookshelf per " + quantity + " tiles!\nRiprova, quante Tiles vuoi prendere? ");
                        } while (quantity > placeables);

                        do {
                            if (quantity == 1) direction = 'R';
                            else {
                                System.out.println("Verso dove vuoi prendere?   (U per Up - D per Down - L per Left - R per Right)");
                                sc.reset();
                                direction = sc.next().charAt(0);
                            }
                            if (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L')
                                System.out.println(rosso + "Scelta errata, puoi inserire solo le direzioni U D L R" + reset);
                        } while (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L');

                        handTiles = con.chooseTiles(matchId, x, y, quantity, direction);
                        for (int i = 0; i < quantity; i++)
                            if (handTiles[i].getItem() == Item.EMPTY) {
                                handTiles = null;
                                i = quantity;
                            }
                        if (handTiles == null) {
                            System.out.println("Hai selezionato una o più Tiles non prendibili, riprova:");
                            printLRB(match_data.getLrb());
                        }
                    } while (handTiles == null);


                    //mostro la bookshelf e chiedo colonna in cui vuole inserire
                    int column;

                    printBS(player_data.getBookshelf());

                    boolean retry;
                    do {
                        retry = false;

                        column = inInt("In quale colonna vuoi inserire le tiles?");
                        if (column < 0 || column > 4) {
                            retry = true;
                            System.out.println("Hai scelto una colonna inesistente");
                        } else {
                            if (con.getAvailableSlots(nickname, column) < handTiles.length) {
                                System.out.println("La colonna non ha sufficiente spazio, riprova:");
                                retry = true;
                            }
                        }
                    } while (retry);

                    //mostra tiles in mano

                    System.out.println("Inserisci le tiles nell'ordine che preferisci.");


                    //inserisci le tiles che hai in mano
                    for (int i = 0; i < handTiles.length; i++) {
                        for (int j = 0; j < handTiles.length; j++) {
                            if (handTiles[j] != null) System.out.println(j + ") " + handTiles[j].toString());
                        }
                        int selected;
                        do {
                            retry = false;
                            selected = inInt("Digita il numero della Tile da inserire:");
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
                        con.insertTile(nickname, (handTiles[selected]), column);
                        handTiles[selected] = null;
                        sc.reset();
                    }
                    con.nextRound(nickname, matchId);

                    while (match_data.getCurrent().equals(nickname)) System.out.print("");

                    break;
                default:
                    System.out.println(rosso + "Scelta errata!" + reset);
                    break;
            }

        }


    }


    public String inString(String question) {
        System.out.println(grigio + question + reset);
        String line = sc.nextLine();
        sc.reset();
        return line;
    }

    public int inInt(String question) {
        System.out.println(grigio + question + reset);
        boolean retry = false;
        int n = -1;
        do {
            try {
                n = sc.nextInt();
                sc.reset();
                retry = false;
            } catch (InputMismatchException e) {
                System.out.println(rosso + "Hai inserito un carattere, riprova inserendo un numero intero!" + reset);
                sc.next();
                retry = true;
            }
        } while (retry);
        return n;
    }

    public static int getChoice(String[] choices, String question) {
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(grigio + question + reset);
            for (int i = 0; i < choices.length; i++)
                System.out.println(grigio + i + ")" + reset + " " + choices[i]);
            System.out.println("Digitare un numero per effettuare la scelta: ");
            n = 0;
            boolean retry = false;
            do {
                try {
                    n = sc.nextInt();
                    sc.reset();
                    retry = false;
                } catch (InputMismatchException e) {
                    System.out.println(rosso + "Hai inserito un carattere, riprova inserendo un numero intero!" + reset);
                    sc.next();
                    retry = true;
                }
            } while (retry);

            if (n < 0 || n >= choices.length) System.out.println(rosso + "Scelta errata, riprova." + reset);
        } while (n < 0 || n >= choices.length);
        return n;
    }

    //used for k hidden answers ;)
    public static int getChoice(String[] choices, String question, int k) {
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(grigio + question + reset);
            for (int i = 0; i < choices.length; i++)
                System.out.println(grigio + i + ")" + reset + " " + choices[i]);
            System.out.println("Digitare un numero per effettuare la scelta: ");
            n = 0;
            boolean retry = false;
            do {
                try {
                    n = sc.nextInt();
                    sc.reset();
                    retry = false;
                } catch (InputMismatchException e) {
                    System.out.println(rosso + "Hai inserito un carattere, riprova inserendo un numero intero!" + reset);
                    sc.next();
                    retry = true;
                }
            } while (retry);

            if (n < 0 || n >= choices.length + k) System.out.println(rosso + "Scelta errata, riprova." + reset);
        } while (n < 0 || n >= choices.length + k);
        return n;
    }

    public void printLRB(ItemTile[][] map) {
        System.out.println(grigio + "#|0|1|2|3|4|5|6|7|8" + reset + "|");
        for (int i = 0; i < 9; i++) {
            System.out.print(grigio + i + "|" + reset);
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
            System.out.println(grigio + (i + 1) + ")" + reset + " Common Goal Card n." + cgs[i] + ":");
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
        System.out.println(grigio + "0|1|2|3|4|" + reset);
    }

}