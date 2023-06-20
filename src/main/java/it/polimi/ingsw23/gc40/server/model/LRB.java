package it.polimi.ingsw23.gc40.server.model;

import it.polimi.ingsw23.gc40.netModel.utilsObserverObservable.PublicModelObserver;

public class LRB {

    private ItemTile[][] map;

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

    public boolean isTakeable(int coordX, int coordY) {
        //verify if a single tile is takeable from the LRB
    if((this.map[coordX][coordY].getItem().equals(Item.NOTREACHABLE)) || (this.map[coordX][coordY].getItem().equals(Item.EMPTY))){
        return false;
    }
    if (coordX == 0) {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;
            }
    } else if (coordX == 8) {
            if (this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;
            }
    } else if (coordY == 0) {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;

            }
    } else if (coordY == 8) {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;

            }
    } else {
            if (this.map[coordX + 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX - 1][coordY].getItem().equals(Item.EMPTY) || this.map[coordX][coordY + 1].getItem().equals(Item.EMPTY) || this.map[coordX][coordY - 1].getItem().equals(Item.EMPTY)) {
                return true;
            } else if (this.map[coordX + 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX - 1][coordY].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY + 1].getItem().equals(Item.NOTREACHABLE) || this.map[coordX][coordY - 1].getItem().equals(Item.NOTREACHABLE)) {
                return true;
            }
        }
        return false;
    }

    public ItemTile[][] getMap() {
        return map;
    }

    public ItemTile getTileInMap(int coordX, int coordY) {
        return this.map[coordX][coordY];
    }

    public void setTileInMap(ItemTile it, int coordX, int coordY)
    {
        this.map[coordX][coordY] = it;
    }

    public void setMap(ItemTile[][] map) {
        this.map = map;
    }

    public boolean needRefill() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (((map[i][j].getItem() != Item.EMPTY) &&
                        (map[i][j].getItem() != Item.NOTREACHABLE))) {
                    if (((map[i + 1][j].getItem() != Item.EMPTY) && (map[i + 1][j].getItem() != Item.NOTREACHABLE))
                            || ((map[i][j + 1].getItem() != Item.EMPTY) &&(map[i][j + 1].getItem() != Item.NOTREACHABLE)))
                        return false;
                }
            }
        }
        return true;
    }

    public ItemTile[][] takeables() {
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
