package it.polimi.ingsw23.gc40.server.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Score {
    LocalDateTime date;
    ArrayList<Player> players;
    Player winner;
    ArrayList<Integer> scores;

    /**
     * Class hypothetically designed to save scores, winning players, and match date.
     * @param players
     * @param winner
     * @param scores
     */
    public Score(ArrayList<Player> players, Player winner, ArrayList<Integer> scores) {
        this.players = players;
        this.winner = winner;
        this.scores = scores;
        this.date = LocalDateTime.now();
    }

}
