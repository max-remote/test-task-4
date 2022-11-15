package com.maks.maxtask4.ui;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class CustomClockView extends View {

    private int height, width, radius;
    private Paint paint;
    private boolean isInit;
    private Calendar calendar;


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
        paint = new Paint();
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            init();
        }
        drawRound(canvas);
        drawHourMarkers(canvas);
        drawHands(canvas);
        invalidate();
    }

    private void drawRound(Canvas canvas) {
        paint.setStrokeWidth(22);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width, height, radius, paint);
    }

    private void drawHourMarkers(Canvas canvas) {
        for (int i = 1; i <= 12; i++) {
            double angle = (Math.PI / 2 + i * (Math.PI / 6));
            canvas.drawLine(
                    (float) ((radius * Math.cos(angle)) + width),
                    (float) ((radius * Math.sin(angle)) + height),
                    (float) (((radius * 0.85) * Math.cos(angle)) + width),
                    (float) (((radius * 0.85) * Math.sin(angle)) + height),
                    paint);
        }
    }

    private void drawHands(Canvas canvas) {
        calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;

        drawHourHand(canvas, (hour + calendar.get(Calendar.MINUTE) / 60.0) * 5f);
        drawMinuteHand(canvas, calendar.get(Calendar.MINUTE));
        drawSecondHand(canvas, calendar.get(Calendar.SECOND));
    }

    private void drawHourHand(Canvas canvas, double loc) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(25);
        canvas.drawLine(
                (float) (width - Math.cos(angle) * radius / 3),
                (float) (height - Math.sin(angle) * radius / 3),
                (float) (width + Math.cos(angle) * radius / 1.6),
                (float) (height + Math.sin(angle) * radius / 1.6),
                paint);
    }

    private void drawMinuteHand(Canvas canvas, double loc) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        canvas.drawLine(
                (float) (width - Math.cos(angle) * radius / 3.5),
                (float) (height - Math.sin(angle) * radius / 3.5),
                (float) (width + Math.cos(angle) * radius / 1.9),
                (float) (height + Math.sin(angle) * radius / 1.9),
                paint);
    }

    private void drawSecondHand(Canvas canvas, double loc) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(15);
        canvas.drawLine(
                (float) (width - Math.cos(angle) * radius / 8),
                (float) (height - Math.sin(angle) * radius / 8),
                (float) (width + Math.cos(angle) * radius / 3),
                (float) (height + Math.sin(angle) * radius / 3),
                paint);
    }
}
