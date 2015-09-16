package com.example.zy.opgldemo.Test;

import com.example.zy.opgldemo.BasicElement.BasicObject;

/**
 * Created by zy on 2015/9/10.
 */
public class Cannon extends BasicObject {
    private  float angle;
    public Cannon(float x, float y, float width, float height) {
        super(x, y, width, height);
        angle = 0;
    }
}
