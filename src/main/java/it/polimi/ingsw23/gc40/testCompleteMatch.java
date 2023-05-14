package it.polimi.ingsw23.gc40;

import it.polimi.ingsw23.gc40.server.controller.GameController;
import it.polimi.ingsw23.gc40.server.model.CommonGoalCard;
import it.polimi.ingsw23.gc40.server.model.Player;
import it.polimi.ingsw23.gc40.server.model.goals.G6;
import it.polimi.ingsw23.gc40.server.model.goals.G7;

import java.util.ArrayList;

public class testCompleteMatch {
    public static void main(String[] args) {


        GameController gameController = new GameController(2,1);
        gameController.addPlayer(new Player("Fil"));
        //match.addPlayer(new Player("Rick"));
        gameController.addPlayer(new Player("Gio"));
        gameController.setNext(gameController.getPlayers().get(0));

        CommonGoalCard[] cgs = gameController.getCommonGoals();
        cgs[0] = new CommonGoalCard(new G6(6));
        cgs[1] = new CommonGoalCard(new G7(7));
        gameController.setCommonGoals(cgs);

        cgs[0].getGoal().print();
        cgs[1].getGoal().print();

        while(gameController.playable()){
            System.out.println("E' il turno di "+ gameController.getNext().getNickName());
            gameController.round(gameController.getNext());
        }

        //server.endMatch(match);

        for (int i = 0; i< gameController.getNumPlayers(); i++){
            System.out.println("Punteggio di "+ gameController.getPlayers().get(i).getNickName()+": "+ gameController.getPlayers().get(i).totScore());
        }

        System.out.println("THE WINNER IS..... "+ gameController.winner().getNickName());
    }

}