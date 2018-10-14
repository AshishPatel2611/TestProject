package com.example.ashishpatel.myapplication.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CircleSurface extends SurfaceView {
    int dimension = 600;
    private Path clipPath;

    public CircleSurface(Context context) {
        super(context);
        init();
    }

    public CircleSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(dimension, dimension);

    }

    private void init() {
        clipPath = new Path();
        clipPath.addCircle(dimension / 2, dimension / 2, dimension / 2, Path.Direction.CW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.clipPath(clipPath);
        super.dispatchDraw(canvas);
    }
}
