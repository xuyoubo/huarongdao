package com.xuyoubo.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by xuyoubo on 14-3-1.
 */
public class Sprite extends Layer {
    Bitmap image;

    public  Sprite(Bitmap image) {
      this(image,16,16);
    }

    public  Sprite(Bitmap image,int width,int height){
        super(width, height);
        this.image = image;
    }

    public Rect getBounds(){
        return new Rect(this.x,this.y,this.x + this.width,this.y + this.height);
    }

    public boolean collidesWith(Sprite sprite){
        return getBounds().intersect(sprite.getBounds());
    }

    @Override
    public void paint(Canvas c) {
        c.drawBitmap(image,null,new Rect(this.x,this.y,this.x + this.width,this.y + this.height),new Paint());
    }
}
