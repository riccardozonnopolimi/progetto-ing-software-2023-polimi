package it.polimi.ingsw23.gc40.server.model;

public class Bookshelf {
    private ItemTile[][] shelf = new ItemTile[5][6];
    private boolean full;


    public Bookshelf(){
        //initialize all the ItemTile to EMPTY
        for(int i=0; i>0 && i<=5; i++){
            for(int j=0; j>0 && j<=6; j++){
                shelf[i][j]= new ItemTile(Item.EMPTY);
            }
        }
    }

    public void insertIT(ItemTile it, int column){
    }

    public ItemTile getCell(int x, int y){
        return shelf[x][y];
    }

    public int checkAdjObjects(){
        //return the number of adjacencies
        return 0;
    }

}
