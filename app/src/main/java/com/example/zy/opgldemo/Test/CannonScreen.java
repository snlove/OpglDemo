package com.example.zy.opgldemo.Test;

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
public class CannonScreen extends Screen {

    private  float FrumViewWidth = 4.8f;
    private  float FrumViewHeight = 3.2f;
    Vector2f cannon = new Vector2f(2.4f, 0.5f);
    Vector2f touchPos = new Vector2f();
    float cannAngle = 0;
    GLGraphics glGraphics;
    Vertices vertices;



    public CannonScreen(Game game) {
        super(game);
        glGraphics = ((MainActivity)game).getGraphics();
        vertices = new Vertices(glGraphics, 3, 0, false, false);
        vertices.setVerticesbuff(new float[]{-0.5f, -0.5f, 0.5f, 0.0f, -0.5f, 0.5f}, 0, 6);
    }

    //得到旋转的角度
    @Override
    public void update(float deltaTime) {

            List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
            game.getInput().getKeyEvents();
            LogMes.d("Cannon", "==================" + touchEvents.size());
            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                Input.TouchEvent event = touchEvents.get(i);
                //世界坐标转换
                touchPos.x = (event.x / (float)glGraphics.getWidth()) * FrumViewWidth;
                touchPos.y = (1 - event.y /(float) glGraphics.getHeight()) * FrumViewHeight;
                cannAngle = touchPos.sub(cannon).angle();
                LogMes.d("Cannon","================="+ cannAngle);
            }


    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGl();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, FrumViewWidth, 0, FrumViewHeight, 1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(cannon.x, cannon.y, 0);
        gl.glRotatef(cannAngle,0,0,1);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLES,0,3);
        vertices.unBind();
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
