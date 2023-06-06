package com.example.mazeapp.MazeGenerator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;

public class MazeGeneratorView extends View {

    Context context;
    static int dWidth, dHeight;
    Rect background;
    Paint wallColor, pathColor, activeCellColor;
    ArrayList<Rect> cellList;

    int[] cellColors = {
        Color.BLACK, //WALL
        Color.WHITE, //PATH
        Color.GREEN, //ORIGIN
        Color.RED    //ACTIVE
    };

    public MazeGeneratorView(Context context) {
        super(context);
        this.context = context;


        //Get Display Sizes
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        background = new Rect(0, 0, dWidth, dHeight);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint ex = new Paint();
        ex.setColor(Color.DKGRAY);
        canvas.drawRect(background, ex);
        int[][] cells =
                {{0, 0, 0, 0, 0},
                 {0, 1, 1, 1, 0},
                 {0, 0, 2, 1, 3},
                 {0, 1, 1, 1, 0},
                 {0, 0, 0, 0, 0}};
        drawCellArray(canvas, cells, 5, 5);
    }

    public void drawCellArray(Canvas canvas, int[][] cells, int cWidth, int cHeight){
        int cellSize = dWidth/cWidth < dHeight/cHeight ? dWidth/cWidth : dHeight/cHeight;
            for(int x = 0; x < cWidth; x++)
                for(int y = 0; y < cHeight; y++) {
                    if(cells[y][x] < cellColors.length){
                        Paint cellPaint = new Paint();
                        cellPaint.setColor(cellColors[cells[y][x]]);
                        canvas.drawRect(new Rect(x * cellSize, y * cellSize, (x + 1) * cellSize, (y + 1) * cellSize), cellPaint);
                    }
                }
    }
}
