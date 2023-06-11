package com.example.mazeapp.App;

import com.example.mazeapp.Data.MazeListItem;

public class MazeState extends MazeListItem {
    public int[][] cellMatrix;
    public boolean showSteps;
    public boolean loadingFromSave;

    public MazeState(int width, int height, String author, String method, boolean showSteps) {
        super(-1, author, method, width, height, null);
        this.showSteps = showSteps;
        loadingFromSave = false;
        cellMatrix = new int[height][width];
    }

    public MazeState(MazeListItem mazeListItem) throws IllegalStateException {
        super(mazeListItem);
        showSteps = false;
        loadingFromSave = true;

        this.cellMatrix = new int[height][width];
        char[] rawMatrix = cellString.toCharArray();
        if(width * height != rawMatrix.length) {
            throw new IllegalStateException();
        }
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                this.cellMatrix[y][x] = Character.getNumericValue(rawMatrix[y * width + x]);
    }
}
