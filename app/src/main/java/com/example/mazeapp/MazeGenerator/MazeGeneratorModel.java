package com.example.mazeapp.MazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MazeGeneratorModel implements MazeGeneratorContract.Model {

    private Random random = new Random();
    private int[] cellValues;
    private String generationMethod;
    private String[] methods;

    private int cWidth, cHeight;
    private int mWidth, mHeight;
    private int[][] cellMatrix;
    private boolean mazeGenerated = false;

    private boolean showSteps;
    private ArrayList<int[][]> generationFrames = new ArrayList<>();
    private int frameIndex = 0;

    public MazeGeneratorModel(int[] cellValues, String[] methods) {
        this.methods = methods;
        this.cellValues = cellValues;
    }

    @Override
    public void setInitialParameters(int width, int height, String generationMethod, boolean showSteps){
        this.generationMethod = generationMethod;
        this.showSteps = showSteps;
        this.mWidth = width;
        this.mHeight = height;
        this.cWidth = width * 2 + 1;
        this.cHeight = height * 2 + 1;
        cellMatrix = new int[cHeight][cWidth];

        for(int id = 0; id < methods.length; id++) if(methods[id].equals(generationMethod)){
            switch (id){
                case 0:
                    randomizedDepthFirstSearchAlgorithm();
                    break;
                default:
                    break;
            }
            break;
        }
    }

    @Override
    public int getcWidth() {
        return cWidth;
    }
    @Override
    public int getcHeight() {
        return cHeight;
    }
    @Override
    public int[][] getCellMatrix() {
        return cellMatrix;
    }

    @Override
    public int[][] getNextFrame() {
        if(frameIndex >= generationFrames.size()) frameIndex = 0;
        return generationFrames.get(frameIndex++);
    }

    private void randomizedDepthFirstSearchAlgorithm(){
        if(mazeGenerated) return;
        mazeGenerated = true;

        for(int x = 0; x < cWidth; x++) for(int y = 0; y < cHeight; y++)
            cellMatrix[y][x] = ((x + 1) % 2) == 0 && ((y + 1) % 2) == 0 ? cellValues[2] : cellValues[0];

        int activeX = random.nextInt(mWidth) * 2 + 1, activeY = random.nextInt(mHeight) * 2 + 1;
        randomizedDepthFirstSearchAlgorithm(activeX, activeY);
    }
    private void randomizedDepthFirstSearchAlgorithm(int activeX, int activeY){
        cellMatrix[activeY][activeX] = cellValues[3];

        ArrayList<int[]> directions = new ArrayList<>();
        for(int dir = 0; dir < 4; dir++) directions.add(new int[]{((-dir + 2) % 2) * 2, ((-dir + 1) % 2) * 2});
        Collections.shuffle(directions);

        for (int[] offset : directions){
            try {
                if(cellMatrix[activeY + offset[1]][activeX + offset[0]] == cellValues[2]){
                    if(showSteps) generationFrames.add(deepCopy(cellMatrix));
                    cellMatrix[activeY + offset[1] / 2][activeX + offset[0] / 2] = cellValues[3];


                    randomizedDepthFirstSearchAlgorithm(activeX + offset[0], activeY + offset[1]);
                    cellMatrix[activeY + offset[1] / 2][activeX + offset[0] / 2] = cellValues[1];
                }
            }catch (Exception e){}
        }
        cellMatrix[activeY][activeX] = cellValues[1];
        if(showSteps) generationFrames.add(deepCopy(cellMatrix));
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

