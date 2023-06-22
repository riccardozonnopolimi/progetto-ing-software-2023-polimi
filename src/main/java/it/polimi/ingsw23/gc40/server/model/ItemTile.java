package it.polimi.ingsw23.gc40.server.model;

import java.io.*;

public class ItemTile implements Serializable {
    private static final long serialVersionUID = 7829136421241571165L;
    private final Item type;
    private final int num;
    private boolean checkedADJ = false;

    public ItemTile(Item t) {
        //todo verificare se abbiamo ancora bisogno
        super();
        type = t;
        num = 0;
    }

    /**
     * Constructore of ItemTile
     * @param t item type
     * @param n used to assign to Item the right icon
     */
    public ItemTile(Item t, int n) {
        super();
        type = t;
        num = n % 3;
    }

    //todo verificare se serve questo metodo senza usage
    public int getNum() {
        return num;
    }

    /**
     * @return item type
     */
    public Item getItem() {
        return type;
    }

    /**
     * @param check set checkedADJ to true if item tile was checked by checker,
     *              set checkedADJ to false before starting another check for the adjacencies
     */
    public void setCheckedADJ(boolean check) {
        this.checkedADJ = check;
    }

    /**
     * @return checkedADJ value
     */
    public boolean getCheckedADJ() {
        return checkedADJ;
    }

    /**
     * //assign a color after looking his type
     * @return return the string corresponding to the text style
     */
    @Override
    public String toString() {
        switch (this.type) {
            case CAT -> {
                return "\u001b[42m\u001b[30mC\u001b[0m";
            }
            case BOOK -> {
                return "\u001b[47m\u001b[30mB\u001b[0m";
            }
            case GAME -> {
                return "\u001b[43m\u001b[30mG\u001b[0m";
            }
            case FRAME -> {
                return "\u001b[44m\u001b[30mF\u001b[0m";
            }
            case TROPHY -> {
                return "\u001b[46m\u001b[30mT\u001b[0m";
            }
            case PLANT -> {
                return "\u001b[45m\u001b[30mP\u001b[0m";
            }
            case EMPTY -> {
                return " ";
            }
            default -> {
                return "\u001b[41m\u001b[30m \u001b[0m";
            }
        }
    }

    /**
     * confronts types of two items
     * @param it
     * @return true if items are equal, false otherwise
     */
    public boolean equals(ItemTile it) {
        return (this.type == it.type);
    }

}