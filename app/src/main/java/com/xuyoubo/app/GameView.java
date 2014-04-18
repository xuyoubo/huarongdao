package com.xuyoubo.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by xuyoubo on 14-3-1.
 */
public abstract class GameView extends SurfaceView implements Drawable.Callback{
    GameThread gameThread;

    GestureDetector gestureDetector ;

    public GameView(Context context) {
        super(context);

        setFocusable(true);
        setClickable(true);
        setFocusableInTouchMode(true);
        gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public abstract void onBack();

    public abstract void onReset();

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        LayerManager.paint(canvas);
    }
}

