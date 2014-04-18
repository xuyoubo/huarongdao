package com.xuyoubo.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuyoubo on 14-3-3.
 */
public class ImageLayer extends Layer {
    private Map<Bitmap,Rect> images = new HashMap<Bitmap,Rect>();

    ImageLayer(int width, int height) {
        super(width, height);
    }

    public void addImage(Bitmap bitmap,Rect rect){
        images.put(bitmap,rect);
    }

    @Override
    public void paint(Canvas c) {
        Set<Bitmap> bitmaps = images.keySet();
        Iterator it = bitmaps.iterator();
        while (it.hasNext()){
            Bitmap bitmap = (Bitmap) it.next();
            c.drawBitmap(bitmap,null,images.get(bitmap),new Paint());
        }
    }
}
