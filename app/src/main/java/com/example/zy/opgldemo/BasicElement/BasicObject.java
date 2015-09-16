package com.example.zy.opgldemo.BasicElement;

import com.example.zy.opgldemo.Collision.Rectangle;

/**
 * Created by zy on 2015/9/10.
 */
public class BasicObject {
    private Vector2f postion;
    private Rectangle bound;

    public BasicObject(float x, float y, float width, float height) {
        postion.x = x;
        postion.y = y;
        bound = new Rectangle(new Vector2f(x - width / 2, y - height / 2), height, width);
    }

    public Vector2f getPostion() {
        return postion;
    }

    public void setPostion(Vector2f postion) {
        this.postion = postion;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
}
