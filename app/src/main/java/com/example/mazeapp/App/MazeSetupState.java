package com.example.mazeapp.App;

public class MazeSetupState {
    public int width, height;
    public int[][] cellMatrix;
    public String method;
    public boolean showSteps;

    public MazeSetupState(int width, int height, String method, boolean showSteps) {
        this.width = width;
        this.height = height;
        this.method = method;
        this.showSteps = showSteps;
    }

    public MazeSetupState(int width, int height, int[][] cellMatrix, String method) {
        this.width = width;
        this.height = height;
        this.cellMatrix = cellMatrix;
        this.method = method;
        showSteps = false;
    }
}
