package com.maks.maxtask4.ui;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class CustomClockView extends View {

    private int height, width, radius;
    private Paint paint;
    private boolean isInit;

    public CustomClockView(Context context) {
        super(context);
    }

    public CustomClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        height = getHeight() / 2;
        width = getWidth() / 2;
        radius = (int) (Math.min(width, height) * 0.8);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            init();
        }
        drawRound(canvas);
        drawHourMarkers(canvas);
    }

    private void drawRound(Canvas canvas) {
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE, 16);
        canvas.drawCircle( width, height, radius, paint);
    }

    private void drawHourMarkers(Canvas canvas) {
        for (int i = 1; i <= 12; i++) {
            double angle = (Math.PI / 2 + i * (Math.PI / 6));
            canvas.drawLine(
                    (float) ((radius * Math.cos(angle)) + width),
                    (float) ((radius * Math.sin(angle))+ height),
                    (float) (((radius * 0.85) * Math.cos(angle))+ width),
                    (float) (((radius * 0.85) * Math.sin(angle))+ height),
                    paint
            );
        }
    }

    private void setPaintAttributes(int colour, Paint.Style stroke, int strokeWidth) {
       paint.reset();
       paint.setColor(colour);
       paint.setStyle(stroke);
       paint.setStrokeWidth(strokeWidth);
    }
}
