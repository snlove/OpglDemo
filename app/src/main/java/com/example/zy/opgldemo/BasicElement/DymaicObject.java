package com.example.zy.opgldemo.BasicElement;

import com.example.zy.opgldemo.Collision.Rectangle;

/**
 * Created by zy on 2015/9/10.
 */
public class DymaicObject extends BasicObject {
    private Vector2f postion;
    private Rectangle bound;
    private Vector2f accle;
    private Vector2f vellocity;


    public DymaicObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        accle = new Vector2f();
        vellocity = new Vector2f();
    }
}
