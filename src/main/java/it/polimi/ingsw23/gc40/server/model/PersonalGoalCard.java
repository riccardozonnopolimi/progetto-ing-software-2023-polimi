package it.polimi.ingsw23.gc40.server.model;

public class PersonalGoalCard extends Card{
    private ItemTile[][] goal = new ItemTile[6][5];
    public PersonalGoalCard(int num){
      /*  switch (num) {
            case 1:
                goal[0][0] = new ItemTile("c");
                goal[0][1] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");

            case 2:
                goal[0][0] = new ItemTile("c");
                goal[0][1] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");

            case 3:
                goal[0][0] = new ItemTile("c");
                goal[0][1] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                break;
            case 4:
                goal[0][0] = new ItemTile("c");
                goal[0][1] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 5:
                goal[0][4] = new ItemTile("c");
                goal[0][1] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 6:
                goal[0][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
            break;
            case 7:
                goal[1][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 8:
                goal[2][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 9:
                goal[3][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 10:
                goal[4][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 11:
                goal[5][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            case 12:
                goal[6][0] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][2] = new ItemTile("c");
                goal[0][3] = new ItemTile("c");
                goal[0][4] = new ItemTile("c");
                goal[0][5] = new ItemTile("c");
                break;
            default:

        }

       */
    }
    @Override
    public void checkScore(Player p) {

    }
}
