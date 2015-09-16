package com.example.zy.opgldemo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.zy.opgldemo.Game.GLGraphics;
import com.example.zy.opgldemo.Game.Game;
import com.example.zy.opgldemo.shape.ColorSqure;

import com.example.zy.opgldemo.shape.Cube;
import com.example.zy.opgldemo.shape.CubeTexture;
import com.example.zy.opgldemo.Util.LogMes;
import com.example.zy.opgldemo.shape.Mesh;
import com.example.zy.opgldemo.shape.SimplePlane;
import com.example.zy.opgldemo.shape.SmoothColoredSquare;
import com.example.zy.opgldemo.shape.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/8/26.
 */
public class MyRender implements GLSurfaceView.Renderer {
    private Square mySquare;
    private float angle;
    private ColorSqure floatSqure;
    private SmoothColoredSquare smoothColoredSquare;
    private Mesh root;
    SimplePlane sp;
    Cube cube;
    private  Context context;

    public MyRender() {
        floatSqure = new ColorSqure();
        smoothColoredSquare = new SmoothColoredSquare();
    }

    public MyRender(float angle) {
        mySquare = new Square();
        this.angle = angle;
    }

    public MyRender(float rx, float ry, float rz,Context context) {
        //Group group = new Group();
        cube = new Cube(0,0,0);


    }
    CubeTexture cubeTexture;
    public MyRender(Context context) {
        this.context = context;
        cubeTexture = new CubeTexture(context);
    }
    BlendingScreen bc;
    Game game;
    GLGraphics glGraphics ;

    public MyRender(Game game) {
        this.game = game;
        glGraphics = game.getGraphics();
        bc = new BlendingScreen(game);
    }

    float lightAmbient[] = new float[] { 0.2f, 0.2f, 0.2f, 1.0f };
    float lightDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };
    float[] lightPos = new float[] {0,0,3,1};

    float matAmbient[] = new float[] { 1f, 1f, 1f, 1.0f };
    float matDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // set the backgroud color to black
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient, 0);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse, 0);

        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);

//        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
        gl.glDisable(GL10.GL_DEPTH_TEST);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepthf(1.0f);

//        gl.glEnable(GL10.GL_CULL_FACE);
//        gl.glFrontFace(GL10.GL_FRONT);
//        gl.glFrontFace(GL10.GL_BACK);
        gl.glShadeModel(GL10.GL_SMOOTH);



//        gl.glEnable(GL10.GL_CULL_FACE);
//        gl.glShadeModel(GL10.GL_SMOOTH);
//        //set the shade model
//        gl.glShadeModel(GL10.GL_SMOOTH);
//        gl.glEnable(GL10.GL_CULL_FACE);
//        gl.glClearDepthf(1.0f);
//        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glDepthFunc(GL10.GL_LEQUAL);
        //gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        LogMes.d("TAG", "========SurfaceCreae");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, width,height);
        GLU.gluPerspective(gl, 45.0f, ((float) width) / height, 1f, 100f);
        LogMes.d("TAG","=========Surface is changed");

        // make adjustments for screen ratio

//        float ratio = (float) width / height;
//        gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
//        gl.glLoadIdentity();                        // reset the matrix to its default state
//        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);  // apply the projection matrix
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);

        //复制以前的矩阵进行旋转
//        gl.glPushMatrix();
//        gl.glRotatef(angle, 0, 0, 1);
//        mySquare.draw(gl);
//        gl.glPopMatrix();

//        gl.glPushMatrix();
//        //gl.glRotatef(-angle, 0, 0, 1);
//        gl.glTranslatef(0, 3.0f, -7);
//        gl.glScalef(.5f, .5f, .5f);
//        //gl.glRotatef(angle * 10, 0, 0, 1);
//        mySquare.draw(gl);
//        //gl.glPopMatrix();
//        //angle++;
//        LogMes.d("TAG","========" + angle);
//        gl.glLoadIdentity();
//        gl.glTranslatef(0, 1.5f, -7);
//        floatSqure.draw(gl);
          //floatSqure.draw(gl);
//        glGraphics.setGl(gl);
//        bc.present(10);
        cubeTexture.drawSecond(gl);
        LogMes.d("TAG", "---------Initlize the drawing method ");;


    }

}
