package com.example.mazeapp.MazeGenerator;

import com.example.mazeapp.App.MazeSetupState;
import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.Data.RepositoryContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MazeGeneratorModel implements MazeGeneratorContract.Model {

    private RepositoryContract repository;
    private Random random = new Random();
    private int[] cellValues;
    private String[] methods;
    private boolean mazeGenerated = false;
    private MazeSetupState activeMaze;

    private ArrayList<int[][]> generationFrames = new ArrayList<>();
    private int frameIndex = -1;

    private boolean isFavorite = false;

    public MazeGeneratorModel(RepositoryContract repository, int[] cellValues, String[] methods) {
        this.methods = methods;
        this.cellValues = cellValues;
        this.repository = repository;
    }

    @Override
    public void setupMaze(MazeSetupState mazeSetupState){
        activeMaze = mazeSetupState;
        mazeGenerated = activeMaze.loadingFromSave;

        if(!activeMaze.loadingFromSave)
            for (int id = 0; id < methods.length; id++)
                if (methods[id].equals(activeMaze.method))
                    switch (id) {
                        case 0:
                            randomizedDepthFirstSearchAlgorithm();
                            break;
                        default:
                            break;
                    }
        generationFrames.add(activeMaze.cellMatrix);
    }

    @Override
    public int getWidth() {
        return activeMaze.width;
    }
    @Override
    public int getHeight() {
        return activeMaze.height;
    }

    @Override
    public int[][] getNextFrame() {
        if(frameIndex + 1 < generationFrames.size()) frameIndex++;
        return generationFrames.get(frameIndex);
    }

    @Override
    public int[][] getPreviousFrame() {
        if(frameIndex - 1 > 0) frameIndex--;
        return generationFrames.get(frameIndex);
    }

    @Override
    public void saveCurrentMaze(MazeGeneratorContract.Presenter.SaveCurrentMazeCallback callback) {
        repository.getUnusedMazeListItemId(id -> {
            String cellString = "";
            for(int y = 0; y < activeMaze.height; y++) for(int x = 0; x < activeMaze.width; x++)
                cellString += Integer.toString(activeMaze.cellMatrix[y][x]);

            activeMaze.id = id;
            activeMaze.cellString = cellString;
            repository.addMazeListItem(activeMaze, () -> {
                if(callback != null) callback.onSavedCurrentMaze();
            });
        });
    }

    private void randomizedDepthFirstSearchAlgorithm(){
        if(mazeGenerated) return;
        mazeGenerated = true;

        for(int x = 0; x < activeMaze.width; x++) for(int y = 0; y < activeMaze.height; y++)
            activeMaze.cellMatrix[y][x] = ((x + 1) % 2) == 0 && ((y + 1) % 2) == 0 ? cellValues[2] : cellValues[0];

        int activeX = random.nextInt((activeMaze.width - 1) / 2) * 2 + 1, activeY = random.nextInt((activeMaze.height - 1) / 2) * 2 + 1;
        randomizedDepthFirstSearchAlgorithm(activeX, activeY);
    }
    private void randomizedDepthFirstSearchAlgorithm(int activeX, int activeY){
        activeMaze.cellMatrix[activeY][activeX] = cellValues[3];

        ArrayList<int[]> directions = new ArrayList<>();
        for(int dir = 0; dir < 4; dir++) directions.add(new int[]{((-dir + 2) % 2) * 2, ((-dir + 1) % 2) * 2});
        Collections.shuffle(directions);

        for (int[] offset : directions){
            try {
                if(activeMaze.cellMatrix[activeY + offset[1]][activeX + offset[0]] == cellValues[2]){
                    if(activeMaze.showSteps) generationFrames.add(deepCopy(activeMaze.cellMatrix));
                    activeMaze.cellMatrix[activeY + offset[1] / 2][activeX + offset[0] / 2] = cellValues[3];


                    randomizedDepthFirstSearchAlgorithm(activeX + offset[0], activeY + offset[1]);
                    activeMaze.cellMatrix[activeY + offset[1] / 2][activeX + offset[0] / 2] = cellValues[1];
                }
            }catch (Exception e){}
        }
        activeMaze.cellMatrix[activeY][activeX] = cellValues[1];
        if(activeMaze.showSteps) generationFrames.add(deepCopy(activeMaze.cellMatrix));
    }

    private static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}

