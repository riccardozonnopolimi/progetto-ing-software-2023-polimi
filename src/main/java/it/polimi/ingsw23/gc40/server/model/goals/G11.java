package it.polimi.ingsw23.gc40.server.model.goals;

import it.polimi.ingsw23.gc40.server.model.Item;
import it.polimi.ingsw23.gc40.server.model.ItemTile;
import it.polimi.ingsw23.gc40.server.model.Player;

public class G11 implements Goal {
    @Override
    public boolean instanceGoal(Player p) {
        //TO TEST
        ItemTile it;
        int count = 0;
        ItemTile[][] s = p.getBookshelf().getShelf();

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 4; j++) {

                if ((s[i][j].getItem() == s[i - 1][j - 1].getItem()) && (s[i][j].getItem() == s[i + 1][j - 1].getItem()) && (s[i][j].getItem() == s[i - 1][j + 1].getItem()) && (s[i][j].getItem() == s[i + 1][j + 1].getItem()) && s[i][j].getItem() != Item.EMPTY) {
                    return true;
                }

            }
        }
        return false;
    }
}
