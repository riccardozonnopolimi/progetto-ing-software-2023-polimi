package it.polimi.ingsw23.gc40.server.model;

public class Bookshelf {
    private ItemTile[][] shelf;
    private boolean full;


    public Bookshelf(){
        shelf = new ItemTile[6][5];
        //initialize all the ItemTile to EMPTY
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                shelf[i][j]= new ItemTile(Item.EMPTY);
            }
        }
    }

    public void insertIT(ItemTile it, int column){
        //TO DO
    }

    public ItemTile getCell(int x, int y){
        return shelf[x][y];
    }

    public int checkAdjObjects(){
        //return the number of adjacencies
        //TO DO
        return 0;
    }

    public int placeables() {
        //TO DO
        return 0;
    }
}
