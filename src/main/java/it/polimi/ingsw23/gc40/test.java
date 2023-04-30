package it.polimi.ingsw23.gc40;

import it.polimi.ingsw23.gc40.server.controller.MatchRMI;
import it.polimi.ingsw23.gc40.server.model.CommonGoalCard;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.goals.G4;
import it.polimi.ingsw23.gc40.server.model.goals.G6;
import it.polimi.ingsw23.gc40.server.model.goals.G7;

import java.util.ArrayList;

public class test{
    public static void main(String[] args) {


        MatchRMI match = new MatchRMI(2);
        match.addPlayer(new Player("Fil"));
        //match.addPlayer(new Player("Rick"));
        match.addPlayer(new Player("Gio"));
        match.setNext(match.getPlayers().get(0));

        ArrayList<CommonGoalCard> cgs = match.getCommonGoals();
        cgs.remove(1);
        cgs.remove(0);
        cgs.add(new CommonGoalCard(new G6()));
        cgs.add(new CommonGoalCard(new G7()));
        match.setCommonGoals(cgs);

        match.getCommonGoals().get(0).getGoal().print();
        match.getCommonGoals().get(1).getGoal().print();

        while(match.playable()){
            System.out.println("E' il turno di "+match.getNext().getNickName());
            match.round(match.getNext());
        }

        for (int i=0; i< match.getNumPlayers(); i++){
            System.out.println("Punteggio di "+match.getPlayers().get(i).getNickName()+": "+match.getPlayers().get(i).totScore());
        }

        System.out.println("THE WINNER IS..... "+match.winner().getNickName());
    }

}