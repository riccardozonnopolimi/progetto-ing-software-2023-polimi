package it.polimi.ingsw23.gc40.server.model;

public class CG6 implements Goal{
    @Override
    public boolean instanceGoal(Player a) {
        //control all the shelf of player and
        // count how many item of same type are in shelf
        int c = 0, b = 0, g = 0, f = 0, t = 0, p = 0;
              for (int i = 0; i<6; i++){
                  for (int j = 0; j<5; j++){
                      if (a.getShelf().getItemTile(i,j).getItem().equals(Item.CATS))
                          c++;
                      if (a.getShelf().getItemTile(i,j).getItem().equals(Item.BOOKS))
                          b++;
                      if (a.getShelf().getItemTile(i,j).getItem().equals(Item.GAMES))
                          g++;
                      if (a.getShelf().getItemTile(i,j).getItem().equals(Item.FRAMES))
                          f++;
                      if (a.getShelf().getItemTile(i,j).getItem().equals(Item.TROPHIES))
                          t++;
                      if (a.getShelf().getItemTile(i,j).getItem().equals(Item.PLANTS))
                          p++;
                  }
              }
              //if once of item type is almost eight return true, else false
              return (c >= 8 || b >= 8 || g >= 8 || f >= 8 || t >= 8 || p >= 8);
    }
}
