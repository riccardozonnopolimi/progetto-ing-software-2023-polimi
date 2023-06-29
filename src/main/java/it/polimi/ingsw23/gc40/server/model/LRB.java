package it.polimi.ingsw23.gc40.server.model;

public class LRB {

    private ItemTile[][] map;

    /**
     * Lrb constructor instantiates map with empty && notReachable elements when the match starts.
     * @param numPlayer tells for how many players LRB has to be set 2/3/4
     */
    public LRB(int numPlayer)  {
        this.map = new ItemTile[9][9];
        if (numPlayer == 2) {
            //initialize all the ItemTile to EMPTY except notReachable items
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == 0) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 1 && (j < 3 || j > 4)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 2 && (j < 3 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 3 && (j < 2 || j > 7)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 4 && (j < 1 || j > 7)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 5 && (j < 1 || j > 6)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 6 && (j < 3 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 7 && (j < 4 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 8) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else {
                        map[i][j] = new ItemTile(Item.EMPTY);
                    }
                }

            }
        }
        if (numPlayer == 3) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == 0 && j != 3) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 1 && (j < 3 || j > 4)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 2 && (j < 2 || j > 6)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 3 && j < 2) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 4 && (j < 1 || j > 7)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 5 && j > 6) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 6 && (j < 2 || j > 6)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 7 && (j < 4 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 8 && j != 5) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else {
                        map[i][j] = new ItemTile(Item.EMPTY);
                    }
                }

            }
        }
        if (numPlayer == 4) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == 0 && (j < 3 || j > 4)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 1 && (j < 3 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 2 && (j < 2 || j > 6)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 3 && j == 0) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 5 && j == 8) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 6 && (j < 2 || j > 6)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 7 && (j < 3 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else if (i == 8 && (j < 4 || j > 5)) {
                        map[i][j] = new ItemTile(Item.NOTREACHABLE);
                    } else {
                        map[i][j] = new ItemTile(Item.EMPTY);
                    }
                }

            }
        }
    }

    /**
     * @param coordX of chosen tile
     * @param coordY of chosen tile
     * @return true if player can take the item chosen in the map using coordX && coordY
     */
    public boolean isTakeable(int coordX, int coordY) {
        //verify if a single tile is takeable from the LRB
        //if you are choosing a type tile Empty or NotReachable
    if((this.map[coordX][coordY].getItem().equals(Item.NOTREACHABLE)) || (this.map[coordX][coordY].getItem().equals(Item.EMPTY))){
        return false;
    }
        //if you are choosing a tile in this corner case
        // you need to check if on right left and bottom there is at least one tile NR or Empty
    if (coordX == 0) {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;
            }
        //if you are choosing a tile in this corner case
        // you need to check if on right, left and up there is at least one tile NR or Empty
    } else if (coordX == 8) {
            if (this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;
            }
        //if you are choosing a tile in this corner case
        // you need to check if on right, bottom and up there is at least one tile NR or Empty
    } else if (coordY == 0) {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;

            }
        //if you are choosing a tile in this corner case
        // you need to check if on bottom, left and up there is at least one tile NR or Empty
    } else if (coordY == 8) {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;

            }
            // you are not in corner case and you need to check if there's at least one tile NR or empty in every direction
    } else {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return map in the match
     */
    public ItemTile[][] getMap() {
        return map;
    }

    /**
     * @param coordX axis of a tile in map
     * @param coordY allies of a tile in map
     * @return the tile in position coordX coordY
     */
    public ItemTile getTileInMap(int coordX, int coordY) {
        return this.map[coordX][coordY];
    }

    /**
     * Used in gamecontroller tu fill the map with empty when a client take an item in the map
     * @param it type of item tile you put in map
     * @param coordX axis where the it will be put
     * @param coordY allies where the it will be put
     */
    public void setTileInMap(ItemTile it, int coordX, int coordY)
    {
        this.map[coordX][coordY] = it;
    }

    /**
     * @param map map is the new refilled map that is put into the game
     */
    public void setMap(ItemTile[][] map) {
        this.map = map;
    }

    /**
     * @return true if the map is complitely EMPTY/NR or if every tile in the map has no adjiacences with other tiles
     */
    public boolean needRefill() {
        //if there is a tile that is adjacent to another the method return false
        // else it returns true because the lrb need to be refilled
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (((!map[i][j].getItem().equals(Item.EMPTY)) &&
                        (!map[i][j].getItem().equals(Item.NOTREACHABLE)))) {
                    if (((!map[i + 1][j].getItem().equals(Item.EMPTY)) && (!map[i + 1][j].getItem().equals(Item.NOTREACHABLE)))
                            || ((!map[i][j + 1].getItem().equals(Item.EMPTY)) &&(!map[i][j + 1].getItem().equals(Item.NOTREACHABLE))))
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * @return a copy of the map in which there are just item tiles you can take
     */
    public ItemTile[][] takeables() {
        //populate a new matrix like lrb whit only the takeables Item Tiles
        ItemTile[][] matrixTakeables = new ItemTile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isTakeable(i, j)) {
                    matrixTakeables[i][j] = new ItemTile(this.map[i][j].getItem());
                } else {
                    matrixTakeables[i][j] = new ItemTile(Item.EMPTY);
                }
            }
        }
        return matrixTakeables;
    }

    public void print(){
        //todo vedere usage e nel caso non necessario
        // eliminare metodo perche'e'nel posto sbagliato
        System.out.println("#|0|1|2|3|4|5|6|7|8|");
        for (int i = 0; i < 9; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j].toString()+"|");
            }
            System.out.println();

        }
    }

    public void print(ItemTile[][] map){
        //todo questo ha 0 usage
        // e non dovrebbe neanche stare qui
        System.out.println("#|0|1|2|3|4|5|6|7|8|");
        for (int i = 0; i < 9; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j].toString()+"|");
            }
            System.out.println();
        }
    }
}