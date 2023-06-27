package it.polimi.ingsw23.gc40.server.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
//todo javadoc
public class Score {
    //todo ???
    LocalDateTime date;
    ArrayList<Player> players;
    Player winner;
    ArrayList<Integer> scores;

    public Score(ArrayList<Player> players, Player winner, ArrayList<Integer> scores) {
        this.players = players;
        this.winner = winner;
        this.scores = scores;
        this.date = LocalDateTime.now();
    }

}
