package com.example.mazeapp.App;

public class MazeSetupState {
    public int width, height;
    public String method;
    public boolean showSteps;

    public MazeSetupState(int width, int height, String method, boolean showSteps) {
        this.width = width;
        this.height = height;
        this.method = method;
        this.showSteps = showSteps;
    }
}
