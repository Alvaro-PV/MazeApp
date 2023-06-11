package com.example.mazeapp.App;

import com.example.mazeapp.Data.UserItem;

public class Mediator {

    private MazeSetupState mazeSetupState;

    private UserItem activeUser;
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

    public UserItem getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(UserItem activeUser) {
        this.activeUser = activeUser;
    }
}
