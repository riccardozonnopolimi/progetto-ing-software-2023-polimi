package it.polimi.ingsw23.gc40.server.model;

public class LRB {
    private ItemTile[][] map;

    public LRB ()
    {
        this.map = new ItemTile[9][9];
        //initialize all the ItemTile to EMPTY
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                map[i][j]= new ItemTile(Item.NOTREACHABLE);
            }
        }

    }


    public void refill()
    {
        //TO DO
    }

    public boolean isTakable(ItemTile it)
    {
        //TO DO
        return false;
    }
    public boolean everyTileCompletelyFree()
    {
        //TO DO
        return false;
    }

    public void takeables() {
        //TO DO
    }
}
