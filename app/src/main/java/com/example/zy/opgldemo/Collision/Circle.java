package com.example.zy.opgldemo.Collision;

import com.example.zy.opgldemo.BasicElement.Vector2f;

/**
 * Created by zy on 2015/9/10.
 */
public class Circle {
    private Vector2f center ;
    private  float radius;

    public Circle(float x,float y, float radius) {
        center = new Vector2f();
        this.center.x = x;
        this.center.y = y;
        this.radius = radius;
    }


    public Vector2f getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}
