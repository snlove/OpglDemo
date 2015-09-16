package com.example.zy.opgldemo.Collision;

import com.example.zy.opgldemo.BasicElement.Vector2f;

/**
 * Created by zy on 2015/9/10.
 */
public class Rectangle {

    private Vector2f leftConer;
    private float height;
    private float width;

    public Rectangle(float x, float y, float height, float width) {
        leftConer = new Vector2f();
        leftConer.setVector2f(x, y);
        this.height = height;
        this.width = width;
    }

    public boolean checkCollision(Rectangle aimRect) {
        if (this.leftConer.x < aimRect.leftConer.x && this.leftConer.x + this.width > aimRect.leftConer.x + aimRect.width
                && this.leftConer.y > aimRect.leftConer.y && this.leftConer.y + this.height < aimRect.leftConer.y + aimRect.height
                ) {
              return  true;
        } else {
            return  false;
        }
    }

    public boolean overAllCollision(Circle circle) {
        float closetX = circle.getCenter().x;
        float closetY = circle.getCenter().y;
        if (closetX <= this.leftConer.x) {
            closetX = this.leftConer.x;
        }

        if (closetX > this.leftConer.x + width) {
            closetX = this.leftConer.x + width;
        }

        if (closetY <= this.leftConer.y) {
            closetY = this.leftConer.y;
        }

        if (closetY > this.leftConer.y + height) {
            closetY = this.leftConer.y + height;
        }
        float dis = circle.getCenter().dis(closetX,closetY);
        if ((dis <= circle.getRadius() * circle.getRadius())) {
            return true;
        } else {
            return  false;
        }
    }

    public Rectangle(Vector2f leftConer, float height, float width) {
        this.leftConer = leftConer;
        this.height = height;
        this.width = width;
    }

    public Vector2f getLeftConer() {
        return leftConer;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
