package it.polimi.ingsw23.gc40.server.model;

import java.io.*;

public class ItemTile implements Serializable {
    private final Item type;
    private final int num;

    private static final long serialVersionUID = 7829136421241571165L;

    private boolean checkedADJ = false;


    public ItemTile(Item t) {
        super();
        type = t;
        num = 0;
    }

    public ItemTile(Item t, int n) {
        //todo
        super();
        type = t;
        num = n % 3;
    }


    public Item getItem() {
        return type;
    }

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

    public boolean getCheckedADJ() {
        return checkedADJ;
    }

    public void setCheckedADJ(boolean check) {
        this.checkedADJ = check;
    }

    public boolean equals(ItemTile it) {
        return (this.type == it.type);
    }

}