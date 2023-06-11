package com.example.mazeapp.App;

import com.example.mazeapp.Data.UserItem;

public class Mediator {

    private MazeState mazeState;
    private boolean loadingFavoritesList = false;
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

    public MazeState getMazeSetupState() {
        return mazeState;
    }

    public void setMazeSetupState(MazeState mazeState) {
        this.mazeState = mazeState;
    }

    public UserItem getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(UserItem activeUser) {
        this.activeUser = activeUser;
    }

    public boolean isLoadingFavoritesList() {
        return loadingFavoritesList;
    }

    public void setLoadingFavoritesList(boolean loadingFavoritesList) {
        this.loadingFavoritesList = loadingFavoritesList;
    }
}
