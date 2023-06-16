package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI extends View {
    private Scanner sc;
    public static String green = "\u001b[42m\u001b[30m";
    public static String red = "\u001B[41m\u001B[30m";
    public static String yellow = "\u001b[43m\u001b[30m";
    public static String reset = "\u001b[0m";
    public static String grey = "\u001b[47m\u001b[30m";



    public CLI(ConnectionController con) {
        super(con);
        sc = new Scanner(System.in);
    }

    public void init() throws RemoteException, Exception {

        //starting textual user interface
        System.out.println(green + "Text-based game interface started!" + reset);
        System.out.println("");
        super.nickname = inString("Insert player name: ");
        while (!con.connect(super.nickname)) {
            System.out.println(yellow + "Name already in use, retry..." + reset);
            super.nickname = inString("Insert player name: ");
        }

        this.matchId = con.reconnect(super.nickname);
        //If match id !=-1 player has disconnected, it will join the previous game
        if (matchId != -1)
            System.out.println("Welcome back"+super.nickname+"!\n"+ yellow + "Reconnecting to the previous game." + reset);
        else {
            //creating new player
            System.out.println(green + "Created player" + reset);
            System.out.println("");


            if (con.hasQueueMatch()) {
                //adding player to last match
                System.out.println(green + "You have been added to an existing match, waiting for players." + reset);
            } else {
                // no gaming exists, creating new game
                while (!con.newMatch(inInt("Creating new match, how many players would you like the game to have? "))) {
                    if (con.hasQueueMatch()) {
                        System.out.println(red + "Can't create match: there is another match waiting for players." + reset);
                        break;
                    } else {
                        System.out.println(red + "Insert player number between 2 and 4" + reset);
                    }
                }
                System.out.println(green + "Created match" + reset);
            }
            //adding player to the game
            super.matchId = con.addToLastMatch(super.nickname);

            System.out.println("JOINED MATCH N°" + super.matchId);


            System.out.println("");
            System.out.println(yellow + "MATCH WILL START AS SOON AS THE OTHER PLAYERS CONNECT" + reset);
        }
        //waiting notify when all player are connected
        con.addObserver(null, super.nickname, super.matchId);
        //waiting for match is ready
        while (!match_data.isPlayable()) {
            System.out.print("");
        }

        System.out.println("");
        System.out.println(green + "MATCH READY" + reset);
        System.out.println("");


        boolean play = match_data.isPlayable();
        boolean close = false;

        while (play && !close) {
            //separating current player from other players
            int choice;


            if (!match_data.getCurrent().equals(nickname)) {
                //isn't your turn, you can't take tiles from LRB
                System.out.println(CLI.yellow + "### IS " + match_data.getCurrent() + "'S TURN ###" + CLI.reset);
                choice = getChoice(new String[]{"Show LRB", "Show Personal Goal Card", "Show BookShelf", "Show Common Goals", "Show your score", "Exit"}, "Choose one of following action:", 1);
                if (!match_data.getCurrent().equals(nickname)) {
                    if (choice == 6) {
                        System.out.println("Wrong choice");
                        choice = 0;
                    }
                }
            } else {
                // is your turn, take tiles from LRB to make your move
                System.out.println(CLI.green + "### IS YOUR TURN " + nickname + " ###" + CLI.reset);
                choice = getChoice(new String[]{"Show LRB", "Show Personal Goal Card", "Show BookShelf", "Show Common Goals", "Show your score", "Exit", "Make your move, " + green + "is your turn!" + reset}, "Choose one of following action:");

            }


            if (match_data.isPlayable()) {
                //switch case for different move
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
                        printScores(player_data.getScores());
                        break;
                    case 5:
                        con.close(nickname);
                        close = true;
                        break;
                    case 6:
                        System.out.println("Make your move!");
                        int placeables = con.startRound(nickname, matchId);
                        System.out.println("Can draw " + placeables + " tiles...");
                        //handTiles represents chosen tiles
                        ItemTile[] handTiles;
                        printLRB(match_data.getLrb());


                        int y, x, quantity;
                        char direction;
                        do {
                            System.out.println("To select Tiles provide first Tile coordinates:");

                            x = inInt("X: ");

                            y = inInt("Y: ");


                            do {
                                //saving number of tiles that we want to take
                                quantity = inInt("How many tiles do you want to choose?");
                                if (quantity > placeables)
                                    //placeables returns max number of free cells in the column of the bookshelf with most free cells
                                    System.out.print("There isn't enough space in your bookshelf to place " + quantity + " tiles!\nRetry, how many Tiles do you want to take? ");
                            } while (quantity > placeables || quantity == 0);


                            do {
                                //choosing direction to take tiles
                                if (quantity == 1) direction = 'R';
                                else {
                                    System.out.println("in which direction you want to go for choice?   (U for Up - D for Down - L for Left - R for Right)");
                                    sc.reset();
                                    direction = sc.next().charAt(0);
                                }
                                if (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L')
                                    System.out.println(red + "Wrong choice, you can insert only U D L R directions" + reset);
                            } while (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L');

                            handTiles = con.chooseTiles(matchId, x, y, quantity, direction);
                            //taking items using direction and quantity
                            for (int i = 0; i < quantity; i++)
                                if (handTiles[i].getItem().equals(Item.EMPTY)) {
                                    handTiles = null;
                                    i = quantity;
                                }
                            //if an empty or "not reachable" item are taken, print wrong choice and allows you to take another choice
                            if (handTiles == null) {
                                System.out.println("Selected one or more not takeable Tiles, retry:");
                                printLRB(match_data.getLrb());
                            }
                        } while (handTiles == null);




                        //show bookshelf and ask in which column insert tile

                        int column;
                        printBS(player_data.getBookshelf());
                        boolean retry;

                        do {
                            retry = false;

                            column = inInt("In which column do you want to insert tile?");
                            if (column < 0 || column > 4) {
                                retry = true;
                                System.out.println("Choice an not existing column");
                            } else {
                                if (con.getAvailableSlots(nickname, column) < handTiles.length) {
                                    //if is chosen a column with free spaces < number of selected tiles
                                    System.out.println("Column hasn't enough space, retry:");
                                    retry = true;
                                }
                            }
                        } while (retry);



                        if (quantity > 1) {
                            System.out.println("Insert tile in the order you prefer.");

                            //show tiles, decide order and insert tiles
                            for (int i = 0; i < handTiles.length; i++) {
                                for (int j = 0; j < handTiles.length; j++) {
                                    if (handTiles[j] != null) System.out.println(j + ") " + handTiles[j].toString());
                                }
                                int selected;
                                do {
                                    retry = false;
                                    selected = inInt("Digit the number of tile you want to insert:");
                                    if (selected >= handTiles.length || selected < 0) {
                                        System.out.println("Retry:");
                                        retry = true;
                                    } else {
                                        if (handTiles[selected] == null) {
                                            System.out.println("Retry:");
                                            retry = true;
                                        }
                                    }
                                } while (retry);
                                con.insertTile(nickname, (handTiles[selected]), column);
                                handTiles[selected] = null;
                                sc.reset();
                            }
                        } else {
                            con.insertTile(nickname, (handTiles[0]), column);
                        }

                        //set next player as current player
                        con.nextRound(nickname, matchId);

                        //wainting for change of current player
                        while (match_data.getCurrent().equals(nickname))
                            System.out.print("");

                        /*if(attivi==1)
                            System.out.println("sei solo scemo ahhaha");

                        while(attivi==1) {
                            Thread.sleep(500);
                            System.out.print("...");
                        }
*/
                        //in caso di partita a due giocatori, ed uno si disconnette questo while è sempre true ed il player rimasto online resta fermo per sempre

                        break;
                    default:
                        System.out.println(red + "Wrong choice!" + reset);
                        break;
                }

            }
        }
        if (!close) {
            System.out.println("END!!!");
        } else {
            System.out.println("Exit from the game.");
        }

    }

    //todo description of this method
    public String inString(String question) {
        System.out.println(grey + question + reset);
        String line = sc.nextLine();
        sc.reset();
        return line;
    }

    //todo description of this method
    public int inInt(String question) {
        System.out.println(grey + question + reset);
        boolean retry = false;
        int n = -1;
        do {
            try {
                n = sc.nextInt();
                sc.reset();
                retry = false;
            } catch (InputMismatchException e) {
                System.out.println(red + "Insert wrong character, retry using an int!" + reset);
                sc.next();
                retry = true;
            }
        } while (retry);
        return n;
    }

    public static int getChoice(String[] choices, String question) {
        //choosing between possible moves
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(grey + question + reset);
            for (int i = 0; i < choices.length; i++)
                System.out.println(grey + i + ")" + reset + " " + choices[i]);
            System.out.println("Insert a number to make your choice: ");
            n = 0;
            boolean retry = false;
            do {
                try {
                    n = sc.nextInt();
                    sc.reset();
                    retry = false;
                } catch (InputMismatchException e) {
                    //if you don't put a number
                    System.out.println(red + "A character was insert, retry using an int!" + reset);
                    sc.next();
                    retry = true;
                }
            } while (retry);
            //if you choose a not existing or not available choice
            if (n < 0 || n >= choices.length) System.out.println(red + "Wrong choice, retry." + reset);
        } while (n < 0 || n >= choices.length);
        return n;
    }

    //used for k hidden answers ;)
    //todo comment this method
    public static int getChoice(String[] choices, String question, int k) {
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(grey + question + reset);
            for (int i = 0; i < choices.length; i++)
                System.out.println(grey + i + ")" + reset + " " + choices[i]);
            System.out.println("Insert a number to make your choice: ");
            n = 0;
            boolean retry = false;
            do {
                try {
                    n = sc.nextInt();
                    sc.reset();
                    retry = false;
                } catch (InputMismatchException e) {
                    System.out.println(red + "A character was insert, retry using an int!" + reset);
                    sc.next();
                    retry = true;
                }
            } while (retry);

            if (n < 0 || n >= choices.length + k) System.out.println(red + "Wrong choice, retry." + reset);
        } while (n < 0 || n >= choices.length + k);
        return n;
    }

    public void printLRB(ItemTile[][] map) {
        System.out.println(grey + "#|0|1|2|3|4|5|6|7|8" + reset + "|");
        for (int i = 0; i < 9; i++) {
            System.out.print(grey + i + "|" + reset);
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j].toString() + "|");
            }
            System.out.println();
        }
    }


    public void printPGC(int n) {
        System.out.println("Your personal Goal Card:");
        printBS(pgCards.get(n - 1));
    }

    public void printCGS(int[] cgs) {
        for (int i = 0; i < cgs.length; i++) {
            System.out.println(grey + (i + 1) + ")" + reset + " Common Goal Card n." + cgs[i] + ":");
            System.out.println(cgDescriptions[cgs[i] - 1]);
            System.out.println("");
        }
    }

    public void printScores(int[] scores) {
        System.out.println("1: PGscore " + scores[0]);
        System.out.println("2: CGscore " + scores[1]);
        System.out.println("3: ADJscore " + scores[2]);
        System.out.println("4: First Score " + scores[3]);
        System.out.println("");
        // scores[0] PGscore, scores[1] CGscores, scores[2] ADJscores, scores[3] firstScores
    }


    public void printBS(ItemTile[][] shelf) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                System.out.print(shelf[i][j].toString() + "|");

            }
            System.out.println();
        }
        System.out.println(grey + "0|1|2|3|4|" + reset);
    }

    @Override
    public void updatePublicData(PublicModelNetwork publicData) {
        //todo finish method description
        if (publicData.getIdMatch() == matchId || matchId == -1) {
            String temp = null;
            if (match_data != null)
                temp = match_data.getCurrent();

            super.updatePublicData(publicData);

            if (publicData.isPlayable())
                if (temp != null && match_data != null && this.nickname != null)
                    if ((match_data.getCurrent().equals(this.nickname)) && !temp.equals(nickname)) {
                        System.out.println(CLI.green + "### IS YOUR TURN " + nickname + " ###" + CLI.reset);
                        System.out.println(CLI.grey + "6)" + CLI.reset + " Make your move");
                    } else if (!match_data.getCurrent().equals(temp) && !match_data.getCurrent().equals(nickname) && !temp.equals(nickname))
                        System.out.println(CLI.yellow + "### IS " + match_data.getCurrent() + "'S TURN ###" + CLI.reset);

        }

    }

}