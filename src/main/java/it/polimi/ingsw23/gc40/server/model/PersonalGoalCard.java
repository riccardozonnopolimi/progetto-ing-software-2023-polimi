package it.polimi.ingsw23.gc40.server.model;

public class PersonalGoalCard {
    private final ItemTile[][] goal;
    public int id;

    /**
     * @param goal initializes the matrix 6x5 with the personal goal
     * @param id of the personal goal
     */
    public PersonalGoalCard(ItemTile[][] goal, int id) {
        this.goal = goal;
        this.id = id;
    }

    /**
     * @return personal goal id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the 6x5 matrix of player personal goal
     */
    public ItemTile[][] getGoal() {
        return goal;
    }

    /**
     * called every end of player round, checks if the player scored points in his updated bookshelf
     * @param p player who ended round
     */
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
            case 1 -> p.setPGScore(1);
            case 2 -> p.setPGScore(2);
            case 3 -> p.setPGScore(4);
            case 4 -> p.setPGScore(6);
            case 5 -> p.setPGScore(9);
            case 6 -> p.setPGScore(12);
            default -> p.setPGScore(0);
        }

    }
}
