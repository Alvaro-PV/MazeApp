package com.example.mazeapp.App;

import java.util.ArrayList;

public class MazeGeneratorState {
    final public int frameIndex;
    final public ArrayList<int[][]> generationFrames;
    final public MazeState mazeState;
    public MazeGeneratorState(MazeState mazeState, int frameIndex, ArrayList<int[][]> generationFrames) {
        this.mazeState = mazeState;
        this.frameIndex = frameIndex;
        this.generationFrames = generationFrames;
    }
}
