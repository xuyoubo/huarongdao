package com.xuyoubo.app;

import android.graphics.Canvas;

/**
 * Created by xuyoubo on 14-3-1.
 */

public abstract class Layer {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    boolean visible = true;

    Layer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

	public void setSize(int width,int height){
		this.width = width;
		this.height = height;
	}

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public final boolean isVisible() {
        return visible;
    }

    public abstract void paint(Canvas c);
}

