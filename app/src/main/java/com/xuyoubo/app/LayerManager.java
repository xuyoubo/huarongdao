package com.xuyoubo.app;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyoubo on 14-3-3.
 */
public class LayerManager {
    public List<Layer> layerList = new ArrayList<Layer>();

    /**
     * 添加一个组件的方法
     *
     * @param layer
     */
    public synchronized void addLayer(Layer layer) {
        layerList.add(layer);
    }


    /**
     * 删除一个组件的方法
     *
     * @param layer
     */
    public synchronized void deleteLayer(Layer layer) {
        layerList.remove(layer);// 在Vector对象中删除此组件
    }

    /**
     * 在before指定的位置插入layer，原来对象以及此后的对象依次往后顺延。
     *
     * @param layer
     * @param before
     */
    public void insert(Layer layer, Layer before) {
        for (int i = 0; i < layerList.size(); i++) {
            int idx = layerList.indexOf(before);
            if (idx >= 0) {
                layerList.add(idx, layer);
            }
        }
    }

    public Layer getLayerAt(int i){
        return layerList.get(i);
    }


    public void paint(Canvas canvas) {
        for (int i = 0; i < layerList.size(); i++) {
            layerList.get(i).paint(canvas);
        }
    }
}
