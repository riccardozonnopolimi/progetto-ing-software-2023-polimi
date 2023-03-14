package it.polimi.ingsw23.gc40.server.model;

public class PersonalGoalCard extends Card{
    private ItemTile[][] goal;
    public PersonalGoalCard(int num){
        goal = new ItemTile[6][5];
        //initialize all the ItemTile to EMPTY
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                goal[i][j]= new ItemTile(Item.EMPTY);
            }
        }
        switch (num) {
            case 1:
                goal[0][0] = new ItemTile(Item.CATS);
                goal[0][1] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);

            case 2:
                goal[0][0] = new ItemTile(Item.CATS);
                goal[0][1] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);

            case 3:
                goal[0][0] = new ItemTile(Item.CATS);
                goal[0][1] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][4] = new ItemTile(Item.PLANTS);
                break;
            case 4:
                goal[0][0] = new ItemTile(Item.CATS);
                goal[0][1] = new ItemTile(Item.BOOKS);
                goal[0][4] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
            case 5:
                goal[0][4] = new ItemTile(Item.CATS);
                goal[0][1] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
            case 6:
                goal[0][0] = new ItemTile(Item.CATS);
                goal[0][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
            break;
            case 7:
                goal[1][0] = new ItemTile(Item.CATS);
                goal[0][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
            case 8:
                goal[2][0] = new ItemTile(Item.CATS);
                goal[0][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
            case 9:
                goal[3][0] = new ItemTile(Item.CATS);
                goal[0][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
            case 10:
                goal[4][0] = new ItemTile(Item.CATS);
                goal[0][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
            case 11:
                goal[2][0] = new ItemTile(Item.CATS);
                goal[0][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[1][2] = new ItemTile(Item.PLANTS);
                break;
            case 12:
                goal[2][0] = new ItemTile(Item.CATS);
                goal[3][4] = new ItemTile(Item.BOOKS);
                goal[0][2] = new ItemTile(Item.GAMES);
                goal[0][3] = new ItemTile(Item.FRAMES);
                goal[0][4] = new ItemTile(Item.TROPHIES);
                goal[0][2] = new ItemTile(Item.PLANTS);
                break;
        }
    }
    @Override
    public void checkScore(Player p) {
        //TO DO
    }
}
