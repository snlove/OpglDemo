package com.example.zy.opgldemo.Test;

import android.util.FloatMath;

import com.example.zy.opgldemo.BasicElement.Vector2f;
import com.example.zy.opgldemo.BasicElement.Vertices;
import com.example.zy.opgldemo.Game.GLGraphics;
import com.example.zy.opgldemo.Game.Game;
import com.example.zy.opgldemo.Game.Input;
import com.example.zy.opgldemo.Game.Screen;
import com.example.zy.opgldemo.MainActivity;
import com.example.zy.opgldemo.Util.LogMes;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/9/9.
 */
public class CannonGravityScreen extends Screen {
    float FRUSTUM_WIDTH = 9.6f;
    float FRUSTUM_HEIGHT = 6.4f;
    GLGraphics glGraphics;
    Vertices cannonVertices;
    Vertices ballVertices;
    Vector2f cannonPos = new Vector2f();
    float cannonAngle = 0;
    Vector2f touchPos = new Vector2f();
    Vector2f ballPos = new Vector2f(0,0);
    Vector2f ballVelocity = new Vector2f(0,0);
    Vector2f gravity = new Vector2f(0,-10);

    public CannonGravityScreen(Game game) {
        super(game);
        glGraphics = ((MainActivity) game).getGraphics();
        cannonVertices = new Vertices(glGraphics, 3, 0, false, false);
        cannonVertices.setVerticesbuff(new float[]{-0.5f, -0.5f,
                0.5f, 0.0f,
                -0.5f, 0.5f}, 0, 6);
        ballVertices = new Vertices(glGraphics, 4, 6, false, false);
        ballVertices.setVerticesbuff(new float[]{-0.1f, -0.1f,
                0.1f, -0.1f,
                0.1f, 0.1f,
                -0.1f, 0.1f}, 0, 8);
        ballVertices.setIndexsBuff(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            touchPos.x = (event.x / (float) glGraphics.getWidth())
                    * FRUSTUM_WIDTH;
            touchPos.y = (1 - event.y / (float) glGraphics.getHeight())
                    * FRUSTUM_HEIGHT;
            cannonAngle = touchPos.sub(cannonPos).angle();

            if(event.type == Input.TouchEvent.TOUCH_UP) {
                float radians = cannonAngle * Vector2f.DegreeToRadius;
                float ballSpeed = touchPos.len();
                ballPos.setVector2f(cannonPos);
                ballVelocity.x = FloatMath.cos(radians) * ballSpeed;
                ballVelocity.y = FloatMath.sin(radians) * ballSpeed;
            }
        }

        ballVelocity.add(gravity.x * deltaTime, gravity.y * deltaTime);
        ballPos.add(ballVelocity.x * deltaTime, ballVelocity.y * deltaTime);
    }

    @Override
    public void present(float deltaTime) {

        GL10 gl = glGraphics.getGl();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, FRUSTUM_WIDTH, 0, FRUSTUM_HEIGHT, 1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
        gl.glTranslatef(cannonPos.x, cannonPos.y, 0);
        gl.glRotatef(cannonAngle, 0, 0, 1);
        gl.glColor4f(1, 1, 1, 1);
        cannonVertices.bind();
        cannonVertices.draw(GL10.GL_TRIANGLES, 0, 3);
        cannonVertices.unBind();

        gl.glLoadIdentity();
        gl.glTranslatef(ballPos.x, ballPos.y, 0);
        LogMes.d("Ball", "=======baoPos" + ballPos.x);
        gl.glColor4f(1,0,0,1);
        ballVertices.bind();
        ballVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        ballVertices.unBind();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

