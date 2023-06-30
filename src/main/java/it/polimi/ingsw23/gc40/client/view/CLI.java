package it.polimi.ingsw23.gc40.client.view;

import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI extends View {
    private final Scanner sc;
    public static String green = "\u001b[42m\u001b[30m";
    public static String red = "\u001B[41m\u001B[30m";
    public static String yellow = "\u001b[43m\u001b[30m";
    public static String resetStdColor = "\u001b[0m";
    public static String grey = "\u001b[47m\u001b[30m";

    /**
     * CLI constructor create the textual view
     *
     * @param con used for take data from server to create interface
     */
    public CLI(ConnectionController con) {
        super(con);
        sc = new Scanner(System.in);
    }


    /**
     * Starts CLI, asks for player name.
     * Checks if player was disconnected, or if he is a new player.
     * In first case it reconnects him to right match.
     * Otherwise, checks if the name is already in use or not.
     * If there is an existing match, adds player to that match, if there isn't an existing match creates a new on and adds player to it.
     * In case, check if the name is already used or not.
     * When match is started, checks the correct sequence of turns and allows players to make their moves.
     * Prints END when game is ended.
     *
     * @throws Exception :throws RemoteException when there is a connection problem
     */
    public void init() throws Exception {

        //starting textual user interface
        System.out.println(green + "Command Line Interface has started!" + resetStdColor);
        System.out.println();
        super.nickname = askString("Insert player name: ");
        while (!con.connect(super.nickname)) {
            System.out.println(yellow + "Name already in use, retry..." + resetStdColor);
            super.nickname = askString("Insert player name: ");
        }

        this.matchId = con.reconnect(super.nickname);
        //If match id !=-1 player has disconnected, it will join the previous game
        if (matchId != -1)
            System.out.println("Welcome back " + super.nickname + "!\n" + yellow + "Reconnecting to the previous game." + resetStdColor);
        else {
            //creating new player
            System.out.println(green + "Created player" + resetStdColor);
            System.out.println();


            if (con.hasQueueMatch()) {
                //adding player to last match
                System.out.println(green + "You are being added to the pending match" + resetStdColor);
            } else {
                // no match exists, creating new game
                boolean createdmatch = con.newMatch(askInt("Creating new match, how many players would you like the game to have? "));

                if (createdmatch)
                    System.out.println(green + "You successfully created a match" + resetStdColor);

                while (!createdmatch) {
                    //if while condition is false the match is not created, there are 2 possible reasons
                    if (con.hasQueueMatch()) {
                        //in the meantime the player was chosing the number of players another client created a match
                        System.out.println(red + "Can't create match: there is another match waiting for players." + resetStdColor);
                        System.out.println("This error occurs only if while you were choosing the number of players some other player connected on the server and started a game faster than you");
                        System.out.println();
                        System.out.println(green + "You are being added to the pending match" + resetStdColor);
                        break;
                    } else {
                        //the player wrote a wrong number
                        System.out.println(red + "Insert player number between 2 and 4" + resetStdColor);
                    }
                    createdmatch = con.newMatch(askInt("Creating new match, how many players would you like the game to have? "));
                }
            }
            //adding player to the game
            super.matchId = con.addToLastMatch(super.nickname);

            System.out.println();
            System.out.println(green + "You are in the match N°" + super.matchId + resetStdColor);


            System.out.println();
            System.out.println(yellow + "MATCH WILL START AS SOON AS THE OTHER PLAYERS CONNECT" + resetStdColor);
        }

        super.turnNumber = 0;


        //waiting notify when all player are connected
        con.addObserver(null, super.nickname, super.matchId);
        //waiting for match is ready
        while (!match_data.isPlayable()) {
            System.out.print("");
        }

        System.out.println();
        System.out.println(green + "MATCH READY" + resetStdColor);
        System.out.println();


        boolean play = match_data.isPlayable();
        boolean close = false;

        while (play && !close) {
            //separating current player from other players
            int choice;


            if (!match_data.getCurrent().equals(nickname)) {
                //isn't your turn, you can't take tiles from LRB
                if (match_data.getCurrent().equals("###gamepaused###")) {
                    System.out.println(yellow + "You remained the only player online in this match, the game will resume as soon as at least one other player reconnects." + resetStdColor);
                } else {
                    System.out.println(CLI.yellow + "### IS " + match_data.getCurrent() + "'S TURN ###" + CLI.resetStdColor);
                }
                choice = getChoice(new String[]{"Show LRB", "Show Personal Goal Card", "Show BookShelf", "Show Common Goals", "Show your score", "Exit"}, "Choose one of following action:", 1);

            } else {
                // is your turn, take tiles from LRB to make your move
                System.out.println(CLI.green + "### IS YOUR TURN " + nickname + " ###" + CLI.resetStdColor);
                choice = getChoice(new String[]{"Show LRB", "Show Personal Goal Card", "Show BookShelf", "Show Common Goals", "Show your score", "Exit", "Make your move, " + green + "is your turn!" + resetStdColor}, "Choose one of following action:");

            }
            if (!match_data.getCurrent().equals(nickname)) {
                if (choice == 6) {
                    System.out.println("Wrong choice");
                    choice = 0;
                }
            }


            if (match_data.isPlayable()) {


                super.turnNumber = match_data.getTurnNumber();
                //System.out.println("TURNO NUMERO: "+turnNumber);
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
                        makemove(super.turnNumber);

                        break;
                    default:
                        System.out.println(red + "Wrong choice!" + resetStdColor);
                        break;
                }

            }
            play = match_data.isPlayable();
        }
        if (!close) {
            System.out.println("The winner is... " + match_data.getWinner());
            System.out.println("The total scores of each player is:");
            for (int i = 0; i < match_data.getPlayersNick().length; i++) {
                System.out.println("-" + match_data.getPlayersNick()[i] + ": " + match_data.getPoints()[i]);
            }
        } else {
            System.out.println("Exit from the game.");
        }

    }

    /**
     * permits current player to take tiles from LRB
     *
     * @param turnN allow to differentiate the current player from the others
     * @throws RemoteException if remote communication falis
     */
    private void makemove(int turnN) throws RemoteException {
        System.out.println("Make your move!");
        int placeables = con.startRound(nickname, matchId);
        System.out.println("You can take " + placeables + " tiles...");
        //handTiles represents chosen tiles
        ItemTile[] handTiles;
        printLRB(match_data.getLrb());


        int y, x, quantity;
        char direction;
        do {
            System.out.println("To select Tiles provide first Tile coordinates:");

            x = askInt("X: ");
            if (!match_data.getCurrent().equals(nickname)) return;

            y = askInt("Y: ");
            if (!match_data.getCurrent().equals(nickname)) return;


            do {

                //saving number of tiles that we want to take
                quantity = askInt("How many tiles do you want to choose?");
                if (!match_data.getCurrent().equals(nickname)) return;

                if (quantity > placeables) {
                    //placeables returns max number of free cells in the column of the bookshelf with most free cells
                    System.out.println(red + "There isn't enough space in your bookshelf to place " + quantity + " tiles!" + resetStdColor);
                    System.out.print(red+"Retry"+resetStdColor);
                }
                if (quantity < 1) {
                    //placeables returns max number of free cells in the column of the bookshelf with most free cells
                    System.out.println(red + "You have to select at least 1 Tile" + quantity + " tiles!" + resetStdColor);
                    System.out.print(red+"Retry"+resetStdColor);
                }

            } while (quantity > placeables || quantity < 1);

            do {
                if (!match_data.getCurrent().equals(nickname)) return;

                //choosing direction to take tiles
                if (quantity == 1) direction = 'R';
                else {
                    System.out.println("in which direction you want to go for choice?   (U for Up - D for Down - L for Left - R for Right)");
                    sc.reset();
                    direction = sc.next().charAt(0);
                    if (!match_data.getCurrent().equals(nickname)) return;

                }
                if (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L')
                    System.out.println(red + "Wrong choice, you can insert only U D L R directions" + resetStdColor);
            } while (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L');

            handTiles = con.chooseTiles(nickname, matchId, x, y, quantity, direction);
            if (handTiles == null) {
                System.out.println(red + "Error:" + resetStdColor + "\n There is a problem taking tiles from map, probably is not your turn or you are alone in the match.");
                break;
            } else {
                //taking items using direction and quantity
                for (int i = 0; i < quantity; i++)
                    if (handTiles[i].getItem().equals(Item.EMPTY)) {
                        handTiles = null;
                        i = quantity;
                    }
                //if an empty or "not reachable" item are taken, print wrong choice and allows you to take another choice
                if (handTiles == null) {
                    System.out.println(red + "Selected one or more un-takeable Tiles, retry:" + resetStdColor);
                    printLRB(match_data.getLrb());
                }
            }
        } while (handTiles == null);

        if (handTiles != null) {
            insertTile(handTiles, quantity, turnN);
        }

    }

    /**
     * Puts item taken from LRB into bookshelf and change current player to next player
     *
     * @param handTiles  represents the item taken from LRB
     * @param quantity   represents how many items has been taken
     * @param turnN to check if the current player is making the move
     * @throws RemoteException if remote communication fails
     */
    private void insertTile(ItemTile[] handTiles, int quantity, int turnN) throws RemoteException {
        //show bookshelf and ask in which column insert tile

        int column;
        printBS(player_data.getBookshelf());
        boolean retry;

        do {
            retry = false;

            column = askInt("In which column do you want to insert tile?");
            if (column < 0 || column > 4) {
                retry = true;
                System.out.println(red + "You have selected an invalid column" + resetStdColor);
            } else {
                if (con.getAvailableSlots(nickname, column) < handTiles.length) {
                    //if is chosen a column with free spaces < number of selected tiles
                    System.out.println(red + "Column hasn't enough space, retry:" + resetStdColor);
                    retry = true;
                }
            }
        } while (retry);

        boolean success = false;

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
                    selected = askInt("Digit the number of tile you want to insert:");
                    if (!match_data.getCurrent().equals(nickname)) return;

                    if (selected >= handTiles.length || selected < 0) {
                        System.out.println("Retry:");
                        retry = true;
                    } else {
                        if (handTiles[selected] == null) {
                            System.out.println(red + "Retry:" + resetStdColor);
                            retry = true;
                        }
                    }
                } while (retry);
                success = con.insertTile(nickname, (handTiles[selected]), column);
                handTiles[selected] = null;
                sc.reset();
            }
        } else {
            success = con.insertTile(nickname, (handTiles[0]), column);
        }
        if (!success) {
            System.out.println(red + "Error:" + resetStdColor + "\n There is a problem inserting tiles in your bookshelf, probably is not your turn or you are alone in the match.");
            return;
        }
        //set next player as current player
        con.nextRound(nickname, matchId);

        while (super.turnNumber == match_data.getTurnNumber()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        if (match_data.getWinner() == null) {
            //waiting for change of current player
            while ((super.turnNumber == match_data.getTurnNumber() - 1) && (match_data.getCurrent().equals(nickname))) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            int niente = askInt("");
        }
    }

    /**
     * Used just for asking string from user
     * @param question printed
     * @return string written by the client (answer)
     */
    public String askString(String question) {
        System.out.println(grey + question + resetStdColor);

        String line = "";

        do {
            line = sc.nextLine();
            sc.reset();

            if (line.equals("Gianpy")){
                System.out.println(green+"HA VINTO GIANPAOLO!"+resetStdColor);
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Scherzetto... siamo dei burloni :D");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            if ((line.isBlank() || line.isEmpty() || line.equals("")))
                System.out.println(red + "Insert a valid character" + resetStdColor);

        } while (line.isBlank() || line.isEmpty() || line.equals(""));


        return line;
    }

    /**
     * Used just for asking an int from user
     * @param question printed
     * @return int written by the client (answer)
     */
    public int askInt(String question) {
        System.out.println(grey + question + resetStdColor);
        boolean retry = false;
        int n = -1;
        do {
            try {
                n = sc.nextInt();
                sc.reset();
                retry = false;
            } catch (InputMismatchException e) {
                System.out.println(red + "Insert wrong character, retry using an int!" + resetStdColor);
                sc.next();
                retry = true;
            }
        } while (retry);
        return n;
    }

    /**
     * this method prints a question, the possible choices and ask user to input a number to make a choice, it
     * returns the int entered by the user after checking if it is in the range ollowed
     * @param choices possible choices to select
     * @param question printed
     * @return int written by the client (answer)
     */
    public static int getChoice(String[] choices, String question) {

        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(grey + question + resetStdColor);
            for (int i = 0; i < choices.length; i++)
                System.out.println(grey + i + ")" + resetStdColor + " " + choices[i]);
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
                    System.out.println(red + "A character was insert, retry using an int!" + resetStdColor);
                    sc.next();
                    retry = true;
                }
            } while (retry);
            //if you choose a not existing or not available choice
            if (n < 0 || n >= choices.length) System.out.println(red + "Wrong choice, retry." + resetStdColor);
        } while (n < 0 || n >= choices.length);
        return n;
    }

    /**
     *
     * @param choices
     * @param question
     * @param k used to allow a k number of "hidden" answers
     * @return
     */

    public static int getChoice(String[] choices, String question, int k) {
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println(grey + question + resetStdColor);
            for (int i = 0; i < choices.length; i++)
                System.out.println(grey + i + ")" + resetStdColor + " " + choices[i]);
            System.out.println("Insert a number to make your choice: ");
            n = 0;
            boolean retry = false;
            do {
                try {
                    n = sc.nextInt();
                    sc.reset();
                    retry = false;
                } catch (InputMismatchException e) {
                    System.out.println(red + "A character was insert, retry using an int!" + resetStdColor);
                    sc.next();
                    retry = true;
                }
            } while (retry);

            if (n < 0 || n >= choices.length + k) System.out.println(red + "Wrong choice, retry." + resetStdColor);
        } while (n < 0 || n >= choices.length + k);
        return n;
    }

    /**
     * Prints LivingRoomBoard
     *
     * @param map to print LRB
     */
    public void printLRB(ItemTile[][] map) {
        System.out.println(grey + "#|0|1|2|3|4|5|6|7|8" + resetStdColor + "|");
        for (int i = 0; i < 9; i++) {
            System.out.print(grey + i + "|" + resetStdColor);
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j].toString() + "|");
            }
            System.out.println();
        }
    }


    /**
     * Prints PersonalGoalCard
     *
     * @param n to choose the assigned PGC
     */
    public void printPGC(int n) {
        System.out.println("Your personal Goal Card:");
        printBS(pgCards.get(n - 1));
    }

    /**
     * Prints CommonGoalCard
     *
     * @param cgs contains the CGS of the match
     */
    public void printCGS(int[] cgs) {
        for (int i = 0; i < cgs.length; i++) {
            System.out.println(grey + (i + 1) + ")" + resetStdColor + " Common Goal Card n." + cgs[i] + ":");
            System.out.println(cgDescriptions[cgs[i] - 1]);
            System.out.println();
        }
    }

    /**
     * Prints scores
     *
     * @param scores contains scores of all type
     */
    public void printScores(int[] scores) {
        System.out.println("1: Personal Goal Card score " + scores[0]);
        System.out.println("2: Common Goal Cards score " + scores[1]);
        System.out.println("3: Adjacenses score " + scores[2]);
        System.out.println("4: First finished score " + scores[3]);
        System.out.println("#: TOT scores " + (scores[0] + scores[1] + scores[2] + scores[3]));
        System.out.println();
    }


    /**
     * print Bookshelf
     *
     * @param shelf is the Bookshelf to print
     */
    public void printBS(ItemTile[][] shelf) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].toString() + "|");
            }
            System.out.println();
        }
        System.out.println(grey + "0|1|2|3|4|" + resetStdColor);
    }

    /**
     * @param publicData updating PublicModelNetwork
     */
    @Override
    public void updatePublicData(PublicModelNetwork publicData) {


        if (publicData.getIdMatch() == matchId || matchId == -1) {
            String precedente = null;
            String corrente = null;

            if (match_data != null) {
                precedente = match_data.getCurrent();
            }

            String giocatore = this.nickname;

            super.updatePublicData(publicData);
            if (match_data != null) {
                corrente = match_data.getCurrent();
            }

            //Condition necessary to ensure that the following instructions are executed only if the match is ongoing
            if (publicData.isPlayable()) {

                //if necessary to avoid NullPointerException beforehand
                if (precedente != null && match_data != null && giocatore != null && corrente != null) {

                    //if it's my turn and before wasn't (turn is changed)
                    if ((corrente.equals(giocatore)) && !precedente.equals(giocatore)) {

                        //if it wasn't my turn because the match was on pause
                        if (precedente.equals("###gamepaused###"))
                            System.out.println(green + "The match started again" + resetStdColor + "\n");

                        //I notify the player that it's his turn.
                        System.out.println(CLI.green + "### IS YOUR TURN " + giocatore + " ###" + CLI.resetStdColor);

                        //I inform him about move number 6, which is now selectable.
                        System.out.println(CLI.grey + "6)" + CLI.resetStdColor + " Make your move");

                        //otherwise if turn is changed and it is not my turn
                    } else if (!corrente.equals(precedente) && !corrente.equals(giocatore) && !precedente.equals(giocatore)) {

                        //if game is on pause
                        if (corrente.equals("###gamepaused###")) {
                            System.out.println("\n" + red + "ATTENTION:" + resetStdColor + "\nYou remained the only player online in this match, the game will resume as soon as at least one other player reconnects...\n\n");

                            //otherwise if it isn't on pause
                        } else {
                            //if it was on pause, notify that it has resumed se era in pausa avviso che è ripartito
                            if (precedente.equals("###gamepaused###"))
                                System.out.println(green + "The match started again" + resetStdColor + "\n");

                            //In any case, I notify that the turn has changed.
                            System.out.println(CLI.yellow + "### IS " + corrente + "'S TURN ###" + CLI.resetStdColor);
                        }

                    } else {
                        //In this 'else' statement, I enter and notify regardless of whether the turn is changing from one player to another

                        //So, if the game wasn't on pause but enters a pause, I notify.
                        if (!precedente.equals("###gamepaused###") && corrente.equals("###gamepaused###")) {
                            System.out.println("\n" + red + "ATTENTION:" + resetStdColor + "\nYou remained the only player online in this match, the game will resume as soon as at least one other player reconnects...\n\n");
                        }

                    }

                }
            } else {
                if (match_data.getPoints() != null) {
                    System.out.println(green + "MATCH COMPLETATO!" + resetStdColor + " Digita 0 per scoprire chi ha vinto");
                }
            }
        }

    }

}