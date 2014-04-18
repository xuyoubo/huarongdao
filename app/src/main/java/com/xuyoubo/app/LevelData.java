package com.xuyoubo.app;

/**
 * Created by xuyoubo on 14-3-2.
 */
public class LevelData {
    private String name;
    int[][] lvlData;

    public LevelData(String name,int[][] data){
        this.name = name;
        this.lvlData = data;
    }

    public String getName() {
        return name;
    }

    public int getX(int i){
        return this.lvlData[i][0];
    }

    public int getY(int i){
        return this.lvlData[i][1];
    }
}
