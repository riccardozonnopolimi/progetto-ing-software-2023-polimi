package it.polimi.ingsw23.gc40.server.model;

public class PersonalGoalCard extends Card {
    private ItemTile[][] goal;

    public PersonalGoalCard() {

    }

    @Override
    public void checkScore(Player p) {
        //TO TEST
        int count = 0;
        ItemTile[][] s = p.getBookshelf().getShelf();

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++)
                if ((goal[i][j].getItem() != Item.EMPTY) && (goal[i][j].getItem() == s[i][j].getItem())) count++;

        switch (count) {
            case 1:
                p.setPGScore(1);
            case 2:
                p.setPGScore(2);
            case 3:
                p.setPGScore(4);
            case 4:
                p.setPGScore(6);
            case 5:
                p.setPGScore(9);
            case 6:
                p.setPGScore(12);
            default:
                p.setPGScore(0);
        }

    }
}
