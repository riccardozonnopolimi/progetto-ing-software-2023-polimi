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
        //System.out.println("qui siiiiiiiiium");
        while (!con.addPlayer(super.nickname)) {
            System.out.println("Nome utente già in uso, riprova...");
            super.nickname = inString("Inserisci nome giocatore: ");
        }
        // System.out.println("ciao fil sono qui");
        //entro nel match / creo il match
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

            if (!match_data.current.equals(nickname)) {
                System.out.println("E' il turno di " + match_data.current);
                switch(getChoice(new String[]{"Mostra LRB", "Mostra Personal Goal Card", "Mostra BookShelf", "Visualizza Common Goals"}, "Scegli una delle seguenti azioni:")){
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
                    default:
                        System.out.println("Scelta errata!");
                        break;
                }

            }else {
                System.out.println("Ora tocca a te!");
            }
        }
        /*
        for (int i = 0; i < match.getNumPlayers(); i++) {
            System.out.println("Punteggio di " + match.getPlayers().get(i).getNickName() + ": " + match.getPlayers().get(i).totScore());
        }*/
        //System.out.println("THE WINNER IS..... " + match.winner().getNickName());



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

    public void printLRB(ItemTile[][] map){
        System.out.println("#|0|1|2|3|4|5|6|7|8|");
        for (int i = 0; i < 9; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j].toString()+"|");
            }
            System.out.println();
        }
    }


    public void printPGC(int n){
        System.out.println("La tua personal Goal Card:");
        printBS(pgCards.get(n-1));
    }

    public void printCGS(int[] cgs){
        for(int i=0; i< cgs.length; i++)
        {
            System.out.println((i+1)+") Common Goal Card n."+cgs[i]+":");
            System.out.println(cgDescriptions[cgs[i]-1]);
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