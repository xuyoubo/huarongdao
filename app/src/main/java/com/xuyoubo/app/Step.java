package com.xuyoubo.app;

/**
 * Created by xuyoubo on 14-3-2.
 */
public class Step {
    private Sprite sprite;
    private int x,y;

    public Step(Sprite sprite, int oldX, int oldY) {
        this.sprite = sprite;
        x = oldX;
        y = oldY;
    }

    public void back(){
        sprite.setPosition(x,y);
    }
}