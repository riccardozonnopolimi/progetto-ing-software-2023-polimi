package it.polimi.ingsw23.gc40.server.model;

public abstract class Token {
    private final int value;


    public Token(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }

}
