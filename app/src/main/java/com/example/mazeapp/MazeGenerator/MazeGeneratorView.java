package com.example.mazeapp.MazeGenerator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MazeGeneratorView extends View{

    Context context;

    int[][] cellMatrix;
    static int cWidth, cHeight;

    int[] cellColors = {
        Color.BLACK, //WALL
        Color.WHITE, //PATH
        Color.GREEN, //ORIGIN
        Color.RED    //ACTIVE
    };

/*----------------------------------------------------------Constructors------------------------------------------------------*/
    public MazeGeneratorView(Context context) {
        super(context);
        this.context = context;
        init(null, 0, 0);
    }
    public MazeGeneratorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0, 0);
    }
    public MazeGeneratorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr, 0);
    }
    public MazeGeneratorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init(attrs, defStyleAttr, defStyleRes);
    }
/*----------------------------------------------------------------------------------------------------------------------------*/

    private void init(@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes){

    }


    public void setCellMatrix(int[][] cellMatrix, int cWidth, int cHeight){
        this.cellMatrix = cellMatrix;
        this.cWidth = cWidth;
        this.cHeight = cHeight;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint ex = new Paint();
        ex.setColor(Color.RED);
        Rect background = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(background, ex);

    }

    private void drawCellArray(Canvas canvas){
        int dWidth = getWidth(), dHeight = getHeight();
        int cellSize = 0, xOffset = 0, yOffset = 0;

        if(dWidth / cWidth < dHeight / cHeight){
            cellSize = dWidth / cWidth;
            yOffset = (dHeight - cellSize * cHeight) / 2; //Center the maze vertically
        }else{
            cellSize = dHeight / cHeight;
            xOffset = (dWidth - cellSize * cWidth) / 2; //Center the maze horizontally
        }

        for(int x = 0; x < cWidth; x++)
            for(int y = 0; y < cHeight; y++) {
                if(cellMatrix[y][x] < cellColors.length){
                    Paint cellPaint = new Paint();
                    cellPaint.setColor(cellColors[cellMatrix[y][x]]);
                    canvas.drawRect(new Rect(x      * cellSize + xOffset,     y      * cellSize + yOffset,
                                           (x + 1) * cellSize + xOffset, (y + 1) * cellSize + yOffset), cellPaint);
                }
            }
    }
}
