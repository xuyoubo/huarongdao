package com.xuyoubo.app;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by xuyoubo on 14-3-5.
 */
public class GameThread extends Thread {
    SurfaceHolder surfaceHolder;

    boolean run = true;
    Canvas c = null;

    @Override
    public void run() {
        int i = 0;
        while (run) {

            try {
                synchronized (surfaceHolder) {
                    c = surfaceHolder.lockCanvas();
                    c.drawARGB(255, 255, 255, 255);
                    c.drawText("" + i++, 100, 100, new Paint());
                    Thread.sleep(1000);
                }
            } catch (
                    Exception e
                    )

            {
                e.printStackTrace();
            } finally

            {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
