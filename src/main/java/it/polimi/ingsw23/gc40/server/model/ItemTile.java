package it.polimi.ingsw23.gc40.server.model;

import java.io.*;

public class ItemTile implements Serializable {
    private Item type;
    private int num;

    private static final long serialVersionUID = 7829136421241571165L;

    private boolean checkedADJ = false;


    public ItemTile(Item t) {
        //TO TEST
        super();
        type = t;
        num = 0;
    }

    public ItemTile(Item t, int n) {
        //TO TEST
        super();
        type = t;
        num = n % 3;
    }

    // public boolean controlSides(LRB map){};
    //TO DO
    public Item getItem() {
        return type;
    }

    @Override
    public String toString() {
        switch (this.type) {
            case CAT -> {
                return "C";
            }
            case BOOK -> {
                return "B";
            }
            case GAME -> {
                return "G";
            }
            case FRAME -> {
                return "F";
            }
            case TROPHY -> {
                return "T";
            }
            case PLANT -> {
                return "P";
            }
            case EMPTY -> {
                return " ";
            }
            default -> {
                return "#";
            }
        }
    }

    public boolean getCheckedADJ() {
        return checkedADJ;
    }

    public void setCheckedADJ(boolean check) {
        this.checkedADJ = check;
    }

    public boolean equals(ItemTile it) {
        return (this.type == it.type);
    }


    //TO TEST
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        switch (aInputStream.readUTF()) {
//TO TEST
            case "C":
                type = Item.CAT;
                break;
            case "B":
                type = Item.BOOK;
                break;
            case "G":
                type = Item.GAME;
                break;
            case "F":
                type = Item.FRAME;
                break;
            case "T":
                type = Item.TROPHY;
                break;
            case "P":
                type = Item.PLANT;
                break;
            case "E":
                type = Item.EMPTY;
                break;
            default:
                break;
        }
        num = aInputStream.readInt();
    }
    //TO TEST
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        switch (type) {
            case CAT:
                aOutputStream.writeUTF("C");
                break;
            case BOOK:
                aOutputStream.writeUTF("B");
                break;
            case PLANT:
                aOutputStream.writeUTF("P");
                break;
            case TROPHY:
                aOutputStream.writeUTF("T");
                break;
            case FRAME:
                aOutputStream.writeUTF("F");
                break;
            case GAME:
                aOutputStream.writeUTF("G");
                break;
            case EMPTY:
                aOutputStream.writeUTF("E");
                break;
            default:
                aOutputStream.writeUTF("*");
                break;


        }
        aOutputStream.writeInt(num);
    }

}