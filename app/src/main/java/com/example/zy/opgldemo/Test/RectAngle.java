package com.example.zy.opgldemo.Test;

import android.util.Log;

import com.example.zy.opgldemo.BasicElement.Texture;
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
public class RectAngle extends Screen {


    private float FrumViewWidth = 6.0f;
    private float FrumViewHeight = 6.0f;
    Vector2f cannon = new Vector2f(2.4f, 0.5f);
    Vector2f touchPos = new Vector2f();
    GLGraphics glGraphics;
    Vertices vertices;
    private boolean startAnimation = false;
    private Texture texture;
    private int texId;


    public RectAngle(Game game) {
        super(game);
        glGraphics = ((MainActivity) game).getGraphics();
        vertices = new Vertices(glGraphics, 4, 6, false, true);
        vertices.setVerticesbuff(new float[]{
                -1.0f, -1.0f, 0.0f, 1.0f,
                1.0f, -1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 0.0f,
                -1.0f, 1.0f, 0.0f, 0.0f}, 0, 16);
        vertices.setIndexsBuff(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);
        texture = new Texture(game, "bobargb8888.png");
        texId = texture.loadTexture();
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
            touchPos.x = (event.x / (float) glGraphics.getWidth()) * FrumViewWidth;
            touchPos.y = (1 - event.y / (float) glGraphics.getHeight()) * FrumViewHeight;

        }
        if (len > 0) {
            startAnimation = true;
        }


    }

    int j = 1;
    int k = 1;
    int stepScale = 200;
    float stepXsmalle = 0.0f;
    private static final int STOP_SCALE = 300;
    private static final float Scale_FACTOR = 3.0f;

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGl();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, FrumViewWidth, 0, FrumViewHeight, 1, -1);


        if (startAnimation == true) {
            //startAnimation = false;
            float x = FrumViewWidth / 2 - cannon.x;
            float y = FrumViewHeight / 2 - cannon.y;
            // 移动到指定位置
            if (j <= 21) {
                gl.glPushMatrix();
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef(cannon.x + x / 20 * j, cannon.y + y / 20 * j, 0.0f);
                vertices.bind();
                vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getnumberSize());
                vertices.unBind();
                j++;
            }
            //在给定位置进行打开动作
            if (j == 22) {
                LogMes.d("OPEN", "==========Open=========");
                if (stepScale <= STOP_SCALE) {
                    gl.glPushMatrix();
                    gl.glMatrixMode(GL10.GL_MODELVIEW);
                    gl.glLoadIdentity();
                    gl.glTranslatef(FrumViewWidth / 2, FrumViewHeight / 2, 0.0f);
                    gl.glScalef(Scale_FACTOR / STOP_SCALE * stepScale, Scale_FACTOR / STOP_SCALE * stepScale, Scale_FACTOR / STOP_SCALE * stepScale);
                    vertices.bind();
                    vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getnumberSize());
                    vertices.unBind();
                    stepScale += 2;
                    if (stepScale >= 280){
                        if (stepXsmalle < 90.0f) {
                            gl.glPushMatrix();
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.5f, 3.0f, 0.0f);
                            gl.glScalef(6.0f, 3.0f, 3.0f);
                            gl.glRotatef(-stepXsmalle, 0, 1, 0);
                            vertices.bind();
                            vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getnumberSize());
                            vertices.unBind();
                            stepXsmalle += 8.8f;
                        }
                }
                }

                if (stepScale > STOP_SCALE) {
                    if (stepXsmalle>=75.0f) {
                        gl.glPushMatrix();
                        gl.glMatrixMode(GL10.GL_MODELVIEW);
                        gl.glLoadIdentity();
                        gl.glTranslatef(FrumViewWidth / 2, FrumViewHeight / 2, 0.0f);
                        gl.glScalef(Scale_FACTOR, Scale_FACTOR, Scale_FACTOR);
                        vertices.bind();
                        vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getnumberSize());
                        vertices.unBind();
                    }
                }
            }
            gl.glPopMatrix();
            LogMes.d("Scale", "========let the squre scale");
        } else {
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(cannon.x, cannon.y, 0);
            texture.bindTexture();
            vertices.bind();
            vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getnumberSize());
            vertices.unBind();
        }
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
