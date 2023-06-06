package com.example.mazeapp;

public class Mediator {

    private static Mediator INSTANCE;
    private Mediator() {}

    public static void resetInstance(){INSTANCE = null;}

    public static Mediator getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Mediator();
        }

        return INSTANCE;
    }
}
