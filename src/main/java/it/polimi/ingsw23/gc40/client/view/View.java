package it.polimi.ingsw23.gc40.client.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw23.gc40.netModel.ConnectionController;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PrivateModelNetwork;
import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelNetwork;
import it.polimi.ingsw23.gc40.server.model.ItemTile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Scanner;

public abstract class View {
    ConnectionController con;
    public int matchId;
    public String nickname;
    public PrivateModelNetwork player_data;
    public PublicModelNetwork match_data;
    public String[] cgDescriptions;
    public ArrayList<ItemTile[][]> pgCards = new ArrayList<>();

    public abstract void init() throws RemoteException, Exception;

    public View(ConnectionController connection) {
        this.con = connection;
        this.match_data = new PublicModelNetwork(null,null,-1,null,false,null);
        this.player_data = null;
        this.matchId = -1;
        this.nickname = "###notdefined###";
        this.cgDescriptions = new String[]{"Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.", "Quattro tessere dello stesso tipo ai quattro angoli della Libreria.", "Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.", "Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo.", "Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti. Colonne diverse possono avere combinazioni diverse di tipi di tessere.", "Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere.", "Cinque tessere dello stesso tipo che formano una diagonale.", "Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere.", "Due colonne formate ciascuna da 6 diversi tipi di tessere.", "Due righe formate ciascuna da 5 diversi tipi di tessere.", "Cinque tessere dello stesso tipo che formano una X.", "Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. Le tessere possono essere di qualsiasi tipo."};

        //import all existing personal goals from json
        Gson gson = new Gson();
        Type personalGoalsType = new TypeToken<ArrayList<ItemTile[][]>>() {
        }.getType();
        try {
            pgCards = gson.fromJson(new FileReader("personalGoals.json"), personalGoalsType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void addPlayer(String name) throws RemoteException, Exception {
        con.addPlayer(name);
    }

    public void updatePrivateData(PrivateModelNetwork privateData) {
        if (privateData.getNickname().equals(nickname)||nickname.equals("###notdefined###"))
            this.player_data = privateData;

        //System.out.println("@@@@@@@@@@@@@@ deserializzazione private completata @@@@@@@@@@@@@@@@@@");
    }

    public void updatePublicData(PublicModelNetwork publicData) {
        if (publicData.getIdMatch()==matchId ||matchId==-1)
        {
            String temp = null;
            if (match_data != null)
                temp = match_data.getCurrent();

            this.match_data = publicData;
            //System.out.println("###############   deserializzazione public completata #################");

            if (temp != null && match_data != null && this.nickname != null)
                if ((match_data.getCurrent().equals(this.nickname)) && !temp.equals(nickname)) {
                    System.out.println(CLI.verde+"### E' IL TUO TURNO ###"+CLI.reset);
                    System.out.println(CLI.grigio + "4)" + CLI.reset + " Fai la tua mossa");
                }
        }
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
            if (n < 0 || n >= choices.length) System.out.println("Scelta errata, riprova.");
        } while (n < 0 || n >= choices.length);
        return n;
    }
}