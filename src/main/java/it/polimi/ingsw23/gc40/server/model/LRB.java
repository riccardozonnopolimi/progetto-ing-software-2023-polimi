package it.polimi.ingsw23.gc40.server.model;

public class LRB {
    private ItemTile[][] map;

    public LRB(int nump) {
        this.map = new ItemTile[9][9];
        if (nump == 2) {
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
        if (nump == 3) {
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
        if (nump == 4) {
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
    public boolean isTakeable(ItemTile it) {
        //TO TEST
        int coordX = it.getX();
        int coordY = it.getY();
        if(coordX==0)
        {
            if (
                    this.map[coordX + 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX][coordY + 1].equals(Item.EMPTY) ||
                    this.map[coordX][coordY - 1].equals(Item.EMPTY))
            {
            return true;
            }
            else if (
                    this.map[coordX + 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY + 1].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY - 1].equals(Item.NOTREACHABLE))
            {
            return true;
            }
        }
        else if(coordX == 8)
        {
            if (
                    this.map[coordX - 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX][coordY + 1].equals(Item.EMPTY) ||
                    this.map[coordX][coordY - 1].equals(Item.EMPTY))
            {
            return true;
            }
            else if (
                    this.map[coordX - 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY + 1].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY - 1].equals(Item.NOTREACHABLE))
            {
            return true;
            }
        }
        else if (coordY == 0)
        {
            if (
                    this.map[coordX + 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX - 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX][coordY + 1].equals(Item.EMPTY))
            {
                return true;
            }
            else if (
                    this.map[coordX + 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX - 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY + 1].equals(Item.NOTREACHABLE))
            {
                return true;

            }
        }
        else if (coordY==8)
        {
            if (
                    this.map[coordX + 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX - 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX][coordY - 1].equals(Item.EMPTY))
            {
                return true;
            }
            else if (
                    this.map[coordX + 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX - 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY - 1].equals(Item.NOTREACHABLE))
            {
                return true;

            }
        }
        else
        {
            if (
                    this.map[coordX + 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX - 1][coordY].equals(Item.EMPTY) ||
                    this.map[coordX][coordY + 1].equals(Item.EMPTY) ||
                    this.map[coordX][coordY - 1].equals(Item.EMPTY))
            {
                return true;
            }
            else if (
                    this.map[coordX + 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX - 1][coordY].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY + 1].equals(Item.NOTREACHABLE) ||
                    this.map[coordX][coordY - 1].equals(Item.NOTREACHABLE))
            {
                return true;
            }
        }
        return false;
    }

    public ItemTile[][] getMap() {
        return map;
    }

    public void setMap(ItemTile[][] map) {
        this.map = map;
    }

    public boolean isTakable(ItemTile it)
    {
        //TO DO
        return false;
    }

    public boolean everyTileCompletelyFree() {
        //to test
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(((map[i][j].getItem()!=Item.EMPTY)&&(map[i][j].getItem()!=Item.NOTREACHABLE))
                        &&(((map[i+1][j].getItem()!=Item.EMPTY)&&(map[i+1][j].getItem()!=Item.NOTREACHABLE))
                        ||((map[i][j+1].getItem()!=Item.EMPTY)&&(map[i][j+1].getItem()!=Item.NOTREACHABLE))))
                    return false;

            }
        }
        return true;
    }

    public void takeables() {
        //TO DO
    }
}
