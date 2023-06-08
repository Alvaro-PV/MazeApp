package com.example.mazeapp.App;

public class Mediator {

    private MazeSetupState mazeSetupState;
    private static Mediator INSTANCE;
    private Mediator() {}

    public static void resetInstance(){INSTANCE = null;}

    public static Mediator getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Mediator();
        }

        return INSTANCE;
    }

    public MazeSetupState getMazeSetupState() {
        return mazeSetupState;
    }

    public void setMazeSetupState(MazeSetupState mazeSetupState) {
        this.mazeSetupState = mazeSetupState;
    }
}
