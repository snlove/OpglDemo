package com.example.zy.opgldemo.shape;

import com.example.zy.opgldemo.BasicElement.Vector2f;
import com.example.zy.opgldemo.BasicElement.Vertices;
import com.example.zy.opgldemo.Game.GLGraphics;
import com.example.zy.opgldemo.Game.Game;
import com.example.zy.opgldemo.Game.Screen;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/9/15.
 */
public class CicleScreen extends Screen {

    private static final  float FRUM_WIDTH = 9.8f;
    private static final  float FRUM_HEIGHT = 9.8f;
    private Vector2f center = new Vector2f(0f, 0f);
    private int vertrticsCount =120;
    private float radius = 2.0f;
    Vertices vertices;
    private GLGraphics glGraphics;
    private float[] verticesCicle;



    public CicleScreen(Game game) {
        super(game);
        glGraphics = game.getGraphics();
        verticesCicle = new float[vertrticsCount];
        for (int i = 0,j=1; i <vertrticsCount && j<=60;j++) {
            verticesCicle[i] = (float) (radius * Math.cos(6*(j)));
            verticesCicle[i+1] = (float) (radius * Math.sin(6 * (j)));
            i= i+2;
        }
        vertices = new Vertices(glGraphics, vertrticsCount, 0, false, false);
        vertices.setVerticesbuff(verticesCicle,0,vertrticsCount);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
         GL10 gl = glGraphics.getGl();
        gl.glViewport(0,0,glGraphics.getWidth(),glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0,FRUM_WIDTH,0,FRUM_HEIGHT,1,-1);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(4.8f,4.3f,0);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLE_FAN,0,vertrticsCount);
        vertices.unBind();
        gl.glColor4f(1,0.5f,1.0f,1.0f);

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
