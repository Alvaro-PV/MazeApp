package com.example.mazeapp.MazeGenerator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MazeGeneratorView extends View{

    String TAG = MazeGeneratorView.class.getSimpleName();
    Context context;

    int[][] cellMatrix;
    static int cWidth, cHeight;

    int[] cellColors = {  //REDO WITH STYLES IN MIND !!!!!!!!!!!!!!!!!!!!
        Color.BLACK, //WALL
        Color.WHITE, //PATH
        Color.GREEN, //ORIGIN
        Color.BLUE    //ACTIVE
    };

    private OnClickListener listener;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(listener != null) listener.onClick(this);
        }
        return super.dispatchTouchEvent(event);
    }

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
        Log.e(TAG, "setCellMatrix()");
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
        if(cellMatrix != null) drawCellArray(canvas);
    }

    private void drawCellArray(Canvas canvas){
        int cellSize, longOffset = 0, shortOffset = 0;
        int dLongEdge  = getWidth() > getHeight() ? getWidth() : getHeight();
        int dShortEdge = getWidth() < getHeight() ? getWidth() : getHeight();
        int cLongEdge  = cWidth > cHeight ? cWidth : cHeight;
        int cShortEdge = cWidth < cHeight ? cWidth : cHeight;

        Log.d(TAG, "dW: " + getWidth() + ", dH: " + getHeight());
        Log.d(TAG, "cW: " + cWidth + ", cH: " + cHeight);
        Log.d(TAG, "dL: " + dLongEdge + ", dS: " + dShortEdge);
        Log.d(TAG, "cL: " + cLongEdge + ", cS: " + cShortEdge);

        if(dLongEdge / cLongEdge < dShortEdge / cShortEdge){
            cellSize = dLongEdge / cLongEdge;
            shortOffset = (dShortEdge - cellSize * cShortEdge) / 2; //Center the maze vertically
        }else{
            cellSize = dShortEdge / cShortEdge;
            longOffset = (dLongEdge - cellSize * cLongEdge) / 2; //Center the maze horizontally
        }

        int xOffset = getWidth()   == dLongEdge ? longOffset : shortOffset;
        int yOffset = getHeight()  == dLongEdge ? longOffset : shortOffset;
        boolean matchCoordinates = (cWidth == cLongEdge) == (getWidth()  == dLongEdge); //matches the long edge of the Grid with the long edge of the View

        for(int i = 0; i < cWidth; i++)
            for(int j = 0; j < cHeight; j++) {
                int x = matchCoordinates ?  i : j;
                int y = !matchCoordinates ? i : j;

                if(cellMatrix[j][i] < cellColors.length){
                    Paint cellPaint = new Paint();
                    cellPaint.setColor(cellColors[cellMatrix[j][i]]);
                    canvas.drawRect(new Rect(x      * cellSize + xOffset,     y      * cellSize + yOffset,
                                           (x + 1) * cellSize + xOffset, (y + 1) * cellSize + yOffset), cellPaint);
                }
            }
    }
}
