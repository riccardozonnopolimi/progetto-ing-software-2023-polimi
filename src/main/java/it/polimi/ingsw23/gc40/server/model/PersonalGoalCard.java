package it.polimi.ingsw23.gc40.server.model;

public class PersonalGoalCard {
    private ItemTile[][] goal;
    public int id;

    public PersonalGoalCard(ItemTile[][] goal, int id) {
        this.goal = goal;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ItemTile[][] getGoal() {
        return goal;
    }

    public void checkScore(Player p) {
        //TO TEST
        int count = 0;
        ItemTile[][] s = p.getBookshelf().getShelf();
        //calculate how many tiles are in the Bookshelf of Player p
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++)
                if (!(goal[i][j].getItem().equals(Item.EMPTY)) && (goal[i][j].getItem().equals(s[i][j].getItem())))
                    count++;
        //assign the personal goal card points reached
        switch (count) {
            case 1:
                p.setPGScore(1);
                break;
            case 2:
                p.setPGScore(2);
                break;
            case 3:
                p.setPGScore(4);
                break;
            case 4:
                p.setPGScore(6);
                break;
            case 5:
                p.setPGScore(9);
                break;
            case 6:
                p.setPGScore(12);
                break;
            default:
                p.setPGScore(0);
                break;
        }

    }
}
