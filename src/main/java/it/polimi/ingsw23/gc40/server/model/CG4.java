package it.polimi.ingsw23.gc40.server.model;

import java.util.HashMap;

public class CG4 implements Goal{
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
        ItemTile it;
        HashMap<Item,Integer> count = new HashMap<>();
        ItemTile[][] s = p.getBookshelf().getShelf();
        HashMap<Item,Integer> temp_j =  new HashMap<>();
        HashMap<Item,Integer> temp_i = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if(i==temp_i.get(s[i][j].getItem()) &&
                        ((j==temp_j.get(s[i][j].getItem())-1)||
                        (j==temp_j.get(s[i][j].getItem())-2)||
                        (j==temp_j.get(s[i][j].getItem()))))
                {} else{
                    if ((s[i][j].getItem() == s[i][j + 1].getItem())
                            && (s[i][j].getItem() == s[i + 1][j].getItem())
                            && (s[i][j].getItem() == s[i + 1][j + 1].getItem())) {

                        count.put(s[i][j].getItem(),count.get(s[i][j].getItem())+1);
                        temp_j.put(s[i][j].getItem(),j+1);
                        temp_i.put(s[i][j].getItem(),i+1);

                        j++;
                    }
                }

                if (count.get(s[i][j].getItem()) == 2)
                    return true;
            }

        }
        return false;
    }
}
