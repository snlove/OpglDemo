package com.example.zy.opgldemo;

import com.example.zy.opgldemo.Game.GLGraphics;
import com.example.zy.opgldemo.Game.Game;
import com.example.zy.opgldemo.Game.Screen;
import com.example.zy.opgldemo.Util.LogMes;
import com.example.zy.opgldemo.BasicElement.Texture;
import com.example.zy.opgldemo.BasicElement.Vertices;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/9/1.
 */
public class BlendingScreen extends Screen {
    private Vertices vertices;
    private GLGraphics glGraphics;
    Texture texturea;
    Texture textureb;

    public BlendingScreen(Game game) {
        super(game);
        glGraphics = game.getGraphics();

        texturea = new Texture(game, "bobrgb888.png");
        textureb = new Texture(game, "borargb888.png");

        vertices = new Vertices(glGraphics, 8, 12, true, true);

        float[] rects = new float[]{
                100, 100, 1, 1, 1, 0.5f, 0, 1,
                228, 100, 1, 1, 1, 0.5f, 1, 1,
                228, 228, 1, 1, 1, 0.5f, 1, 0,
                100, 228, 1, 1, 1, 0.5f, 0, 0,

                100, 300, 1, 1, 1, 1, 0, 1,
                228, 300, 1, 1, 1, 1, 1, 1,
                228, 428, 1, 1, 1, 1, 1, 0,
                100, 428, 1, 1, 1, 1, 0, 0
        };
        vertices.setVerticesbuff(rects, 0, rects.length);
        vertices.setIndexsBuff(new short[]{0, 1, 2, 2, 3, 0,
                4, 5, 6, 6, 7, 4}, 0, 12);
    }

    @Override
    public void update(float deltaTime) {
        game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
    }

    @Override
    public void present(float deltaTime) {
         GL10 gl = glGraphics.getGl();
        // gl.glClearColor(1,0,1, 1);
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
//        gl.glMatrixMode(GL10.GL_MODELVIEW);
//        gl.glLoadIdentity();
//        gl.glOrthof(0, 320, 0, 480, 1, -1);
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        //透明混合渲染
        gl.glEnable(GL10.GL_BLEND);
        //设置混合的计算公式
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        texturea.bindTexture();
        vertices.draw(GL10.GL_TRIANGLES, 0, 6);
        textureb.bindTexture();
        vertices.draw(GL10.GL_TRIANGLES, 0, 6);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glScalef(0.5f, 0.5f, 0.5f);
        LogMes.d("TAG", "----------渲染方法");


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
