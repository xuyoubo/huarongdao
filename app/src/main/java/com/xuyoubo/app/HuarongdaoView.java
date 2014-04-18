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
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by xuyoubo on 14-3-2.
 */
public class HuarongdaoView extends GameView{
    Sprite[] sprites = new Sprite[10];
    Sprite sprite = null;
    int stepX = 0,stepY = 0;
    Bitmap b1,b2;
    int min_distance = 40;
    final static int BORDER_WIDTH = 18;
    List steps = new ArrayList();
    Logger logger = Logger.getLogger("GameView");
    Paint infoPaint = new Paint();
    Paint infoBackPaint = new Paint();
    int hiScore = 0;
    int[][][] levelDatas = {
            {{0,0}, {1,0}, {3,0},{0,2},{1,2},{3,2},{1,3},{2,3},{1,4},{2,4}},
            {{0,0},{1,0}, {3,0},{0,3},{1,2},{3,3},{1,3},{2,3},{0,2},{3,2}},
            {{0,0},{1,0}, {1,2},{0,2},{1,4},{2,2},{3,0},{3,1},{3,2},{3,3}},
            {{1,3},{1,0}, {2,3},{0,2},{1,2},{3,2},{0,0},{0,1},{3,0},{3,1}}
    };
    LevelData[] levels = {
            new LevelData("横刀立马",levelDatas[0]),
            new LevelData("层峦叠嶂",levelDatas[1]),
            new LevelData("一路进军",levelDatas[2]),
            new LevelData("四面楚歌",levelDatas[3]),
    };
    private int level = 0;

    public HuarongdaoView(Context context){
        super(context);

        Resources res = context.getResources();
        int[] resourceIds = {
            R.drawable.huangzhong,
            R.drawable.caocao,
            R.drawable.zhaoyun,
            R.drawable.zhangfei,
            R.drawable.guanyu,
            R.drawable.macao,
            R.drawable.zu,
            R.drawable.zu,
            R.drawable.zu,
            R.drawable.zu,
        };

        for(int i = 0;i < resourceIds.length;i++){
            Bitmap bitmap = BitmapFactory.decodeResource(res,
                    resourceIds[i]);
            sprites[i] = new Sprite(bitmap);
            LayerManager.addLayer(sprites[i]);
        }

        sprites[0].setPosition(BORDER_WIDTH,BORDER_WIDTH);
        sprites[1].setPosition(BORDER_WIDTH + stepX,BORDER_WIDTH);
        sprites[2].setPosition(BORDER_WIDTH + 3 * stepX,BORDER_WIDTH);
        sprites[3].setPosition(BORDER_WIDTH, BORDER_WIDTH + 2 * stepY);
        sprites[4].setPosition(BORDER_WIDTH + stepX,BORDER_WIDTH + 2 * stepY);
        sprites[5].setPosition(BORDER_WIDTH + 3 * stepX, BORDER_WIDTH + 2 * stepY);
        sprites[6].setPosition(BORDER_WIDTH + stepX,BORDER_WIDTH + 3 * stepY);
        sprites[7].setPosition(BORDER_WIDTH + 2 * stepX, BORDER_WIDTH + 3 * stepY);
        sprites[8].setPosition(BORDER_WIDTH + stepX,BORDER_WIDTH + 4 * stepY);
        sprites[9].setPosition(BORDER_WIDTH + 2 * stepX, BORDER_WIDTH + 4 * stepY);

        b1 = BitmapFactory.decodeResource(res,R.drawable.border_1);
        b2 = BitmapFactory.decodeResource(res,R.drawable.border_2);

        infoPaint.setColor(Color.GREEN);
        infoPaint.setTextSize(20);

        infoBackPaint = new Paint();
        infoBackPaint.setColor(Color.BLACK);
        infoBackPaint.setAlpha(125);
    }

    public CharSequence[] getLevelNames(){
        CharSequence[] levelNames = new String[levels.length];
        for(int i = 0;i < levels.length;i++){
            levelNames[i] = levels[i].getName();
        }
        return  levelNames;
    }

    public void reset() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (stepX == 0 || stepY == 0) {
            stepX = (w - 2 * BORDER_WIDTH) / 4;
            stepY = (h - 2 * BORDER_WIDTH) / 5;
            min_distance = stepX / 2;
            reset();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(b1,null,new Rect(0,0,BORDER_WIDTH,getHeight()),new Paint());
        canvas.drawBitmap(b1,null,new Rect(getWidth() - BORDER_WIDTH,0,getWidth(),getHeight()),new Paint());
        canvas.drawBitmap(b2,null,new Rect(BORDER_WIDTH,0,getWidth() - BORDER_WIDTH,BORDER_WIDTH),new Paint());
        canvas.drawBitmap(b2,null,new Rect(BORDER_WIDTH,getHeight() - BORDER_WIDTH,BORDER_WIDTH + (getWidth() - BORDER_WIDTH) / 4,getHeight()),new Paint());
        canvas.drawBitmap(b2,null,new Rect(getWidth() - (getWidth() - BORDER_WIDTH)/4,getHeight() - BORDER_WIDTH,getWidth() - BORDER_WIDTH,getHeight()),new Paint());
        for (int i = 0;i < sprites.length;i++){
            sprites[i].paint(canvas);
        }

        canvas.drawRoundRect(new RectF(getWidth() - 160,0,getWidth(),100),5,5,infoBackPaint);
        canvas.drawText(levels[this.level].getName(),getWidth() - 150,20,infoPaint);
        canvas.drawText("步：" + steps.size(),getWidth() - 150,50,infoPaint);
        canvas.drawText("最高记录：" + hiScore,getWidth() - 150,80,infoPaint);
    }

    @Override
    public void onBack(){
        Step step = (Step)steps.remove(steps.size() - 1);
        step.back();
        postInvalidate();
    }

    @Override
    public void onReset() {
        SharedPreferences sharedata = getContext().getSharedPreferences("data", 0);
        hiScore = sharedata.getInt("hiscore", 0);

        for (int i = 0; i < 10; i++) {
            sprites[i].setPosition(BORDER_WIDTH + levels[this.level].getX(i) * stepX, BORDER_WIDTH + levels[this.level].getY(i) * stepY);
        }

        sprites[0].setSize(stepX, stepY * 2);
        sprites[1].setSize(2 * stepX, 2 * stepY);
        sprites[2].setSize(stepX, 2 * stepY);
        sprites[3].setSize(stepX, 2 * stepY);
        sprites[4].setSize(2 * stepX, stepY);
        sprites[5].setSize(stepX, 2 * stepY);
        sprites[6].setSize(stepX, stepY);
        sprites[7].setSize(stepX, stepY);
        sprites[8].setSize(stepX, stepY);
        sprites[9].setSize(stepX, stepY);

        steps.clear();
        postInvalidate();
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v2) {
        if (sprite == null){
            return false;
        }

        logger.log(Level.INFO, "## [" + e1.getX() + "," + e1.getY() + "],[" + e2.getX() + "," + e2.getY() + "]");
        if (e1.getX() - e2.getX() > min_distance) {
            moveSprite(sprite, sprite.x - stepX, sprite.y);
        }
        else  if (e1.getX() - e2.getX() < -min_distance) {
            moveSprite(sprite,sprite.x + stepX,sprite.y);
        }
        else  if (e1.getY() - e2.getY() > min_distance) {
            moveSprite(sprite, sprite.x, sprite.y - stepY);
        }
        else  if (e1.getY() - e2.getY() < -min_distance) {
            moveSprite(sprite, sprite.x, sprite.y + stepY);
        }

        postInvalidate();
        return false;
    }

    private void moveSprite(Sprite sprite,int x,int y){
        logger.log(Level.INFO,"** " + sprite.getBounds().toString());
        if(x < 0 || x + sprite.getWidth() > getWidth() || y < 0 || y + sprite.getHeight() > getHeight()){
            logger.log(Level.INFO,"collides with bounds");
            logger.log(Level.INFO, "[" + x + "," + y + "," + sprite.getWidth() + "," + sprite.getHeight() + "]"+ ",[" + getWidth() + "," + getHeight() + "]");
            return;
        }
        int oldX = sprite.getX();
        int oldY = sprite.getY();

        sprite.setPosition(x,y);
        for(int i = 0;i < sprites.length;i++){
            if(sprites[i] != sprite){
                if(sprite.collidesWith(sprites[i])){
                    sprite.setPosition(oldX,oldY);
                    logger.log(Level.INFO,"collides with other sprite + [" + i + "]");
                    logger.log(Level.INFO,sprite.getBounds().toString() + "," + sprites[i].getBounds().toString());
                    return;
                }
            }
        }

        steps.add(new Step(sprite,oldX,oldY));

        if(sprites[1].getX() == BORDER_WIDTH + stepX && sprites[1].getY() == BORDER_WIDTH + 3 * stepY){
            gameWin();
        }
    }

    private void gameWin(){
        SharedPreferences.Editor sharedata = getContext().getSharedPreferences("data", 0).edit();
        sharedata.putInt("hiscore", steps.size());
        sharedata.commit();

        final AlertDialog dlg = new AlertDialog.Builder(getContext()).create();
        dlg.setTitle("提示");
        dlg.setMessage("小样，竟敢放走曹操！！！");
        dlg.setButton(AlertDialog.BUTTON_NEUTRAL,"剁手",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reset();
            }
        });
        dlg.show();
    }

    protected Sprite getSpriteAt(int x,int y){
        for(int i = 0;i < sprites.length; i++){
            if(sprites[i].getBounds().contains(x,y)){
                logger.log(Level.INFO,"current sprite is [" + i + "]");
                return sprites[i];
            }
        }

        return null;
    }

    public void selectLevel(int which) {
        this.level = which;
        reset();
    }
}
