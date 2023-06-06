package it.polimi.ingsw23.gc40.server.model.goals;
import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

import java.util.HashMap;
public class G4 implements Goal {
    private final int id;
    public G4(int id){
        this.id = id;
    }
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
        HashMap<Item, Integer> count = new HashMap<>();
        ItemTile[][] s = p.getBookshelf().getShelf();
        HashMap<Item, Integer> temp_j = new HashMap<>();
        HashMap<Item, Integer> temp_i = new HashMap<>();

        count.put(Item.CAT, 0);
        count.put(Item.BOOK, 0);
        count.put(Item.GAME, 0);
        count.put(Item.FRAME, 0);
        count.put(Item.TROPHY, 0);
        count.put(Item.PLANT, 0);
        count.put(Item.EMPTY, 0);

        temp_i.put(Item.CAT, -1);
        temp_i.put(Item.BOOK, -1);
        temp_i.put(Item.GAME, -1);
        temp_i.put(Item.FRAME, -1);
        temp_i.put(Item.TROPHY, -1);
        temp_i.put(Item.PLANT, -1);
        temp_i.put(Item.EMPTY, -1);


        temp_j.put(Item.CAT, -1);
        temp_j.put(Item.BOOK, -1);
        temp_j.put(Item.GAME, -1);
        temp_j.put(Item.FRAME, -1);
        temp_j.put(Item.TROPHY, -1);
        temp_j.put(Item.PLANT, -1);
        temp_j.put(Item.EMPTY, -1);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {

                if (i == temp_i.get(s[i][j].getItem())
                        && ((j == temp_j.get(s[i][j].getItem()) - 1)
                        || (j == temp_j.get(s[i][j].getItem()) - 2)
                        || (j == temp_j.get(s[i][j].getItem())))) {
                    ;
                }
               else {
                    if ((s[i][j].getItem() == s[i][j + 1].getItem())
                            && (s[i][j].getItem() == s[i + 1][j].getItem())
                            && (s[i][j].getItem() == s[i + 1][j + 1].getItem())
                            && (s[i][j].getItem() != Item.EMPTY)) {
                        count.put(s[i][j].getItem(), count.get(s[i][j].getItem()) + 1);
                        temp_j.put(s[i][j].getItem(), j + 1);
                        temp_i.put(s[i][j].getItem(), i + 1);
                        j++;
                    }
                }
                if (count.get(s[i][j].getItem()) == 2 && 
                        (s[i][j].getItem() != Item.EMPTY)) return true;
            }
        }
        return false;
    }
    public void print() {
        System.out.println("G4");
    }
    @Override
    public int getId() {
        return this.id;
    }
}