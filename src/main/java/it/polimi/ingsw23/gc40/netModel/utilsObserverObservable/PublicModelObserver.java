package it.polimi.ingsw23.gc40.netModel.utilsObserverObservable;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.ItemTile;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PublicModelObserver implements Observer {
    private PublicModelNetwork data;
    public ItemTile[][] lrb;
    public int[] cgs;
    public int idMatch;
    public Boolean playable;
    public String winner;
    public String current;
    private GameController match;
    private ArrayList<String> downloaded = new ArrayList<>();


    public PublicModelObserver(GameController gameController) {
        gameController.addObserver(this);
        // todo implement remove listener or destructor
        match = gameController;
        playable=false;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ItemTile[][]) {
            lrb = (ItemTile[][]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current,playable, null);
        }

        if (arg instanceof int[]) {
            cgs = (int[]) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current,playable, null);
        }
        if (arg instanceof Integer) {
            idMatch = (int) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current,playable,null);
        }
        if (arg instanceof String) {
            winner = (String) arg;
            current = (String) arg;
            data = new PublicModelNetwork(lrb, cgs, idMatch, current,playable,null);
        }
        if (arg instanceof Boolean) {
            playable = (Boolean) arg;
            if (playable)
                data = new PublicModelNetwork(lrb, cgs, idMatch, current,playable,null);
            else
                data = new PublicModelNetwork(lrb, cgs, idMatch, current,playable,winner);

        }

        try {

            System.out.println("public update");
            for (int i = 0; i < match.getPlayers().size(); i++) {
                if (match.getPlayers().get(i).getPrivateModelObserver().getSH() != null) {
                    System.out.println("public update in socket: " + match.getPlayers().get(i).getNickName());
                    match.getPlayers().get(i).getPrivateModelObserver().getSH().sendObj("#public_object", data);
                } else {
                    downloaded.add(match.getPlayers().get(i).getNickName());
                    System.out.println("public update in rmi: " + match.getPlayers().get(i).getNickName());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public ArrayList<String> getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(ArrayList<String> downloaded) {
        this.downloaded = downloaded;
    }

    public Object getData() {
        return data;
    }

}
