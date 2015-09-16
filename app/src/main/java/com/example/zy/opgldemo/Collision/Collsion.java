package com.example.zy.opgldemo.Collision;

import com.example.zy.opgldemo.BasicElement.Vector2f;

/**
 * Created by zy on 2015/9/10.
 */
public class Collsion {

    public boolean cicleCollision(Circle c1 , Circle c2) {
        float dis =  c1.getCenter().disSqure(c2.getCenter());
        float radSqu = (c1.getRadius() + c2.getRadius()) * (c1.getRadius() + c2.getRadius());
        return  dis <= radSqu;
    }

    public boolean rectCollision(Rectangle move, Rectangle aimRect) {
        if (move.getLeftConer().x < aimRect.getLeftConer().x && move.getLeftConer().x + move.getWidth() > aimRect.getLeftConer().x + aimRect.getWidth()
                && move.getLeftConer().y > aimRect.getLeftConer().y && move.getLeftConer().y + move.getHeight() < aimRect.getLeftConer().y + aimRect.getHeight()
                ) {
            return  true;
        } else {
            return  false;
        }
    }

    public boolean rectCicleCollision(Rectangle move, Circle circle) {
        float closetX = circle.getCenter().x;
        float closetY = circle.getCenter().y;
        if (closetX <= move.getLeftConer().x) {
            closetX = move.getLeftConer().x;
        }

        if (closetX > move.getLeftConer().x + move.getWidth()) {
            closetX = move.getLeftConer().x + move.getWidth();
        }

        if (closetY <= move.getLeftConer().y) {
            closetY = move.getLeftConer().y;
        }

        if (closetY > move.getLeftConer().y + move.getHeight()) {
            closetY = move.getLeftConer().y + move.getHeight();
        }
        float dis = circle.getCenter().dis(closetX,closetY);
        if ((dis <= circle.getRadius() * circle.getRadius())) {
            return true;
        } else {
            return  false;
        }
    }

    public boolean poinCicCollsion(Circle circle, Vector2f point) {
          if( circle.getCenter().disSqure(point) <=  circle.getRadius() * circle.getRadius()){
              return true;
          }
        else  return false;
    }

    public boolean poinCicCollsion(Circle circle, float x, float y) {
        return circle.getCenter().disSqure(x,y) <=  circle.getRadius() * circle.getRadius();
    }

    public boolean poinRectCollsion(Rectangle rectangle, Vector2f point) {
        if (point.x >= rectangle.getLeftConer().x &&  point.x <= rectangle.getLeftConer().x + rectangle.getWidth()
            && point.y >= rectangle.getLeftConer().y &&  point.y <= rectangle.getLeftConer().y + rectangle.getHeight()
                ) {
            return  true;
        }
        else  {
            return  false;
        }
    }
}
