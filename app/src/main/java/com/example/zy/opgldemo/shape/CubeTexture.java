package com.example.zy.opgldemo.shape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.zy.opgldemo.R;
import com.example.zy.opgldemo.Util.LogMes;

;import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/9/2.
 */
public class CubeTexture  extends  Mesh {
    float lightAmbient[] = new float[] { 0.2f, 0.2f, 0.2f, 1.0f };
    float lightDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };
    float[] lightPos = new float[] {0,0,3,1};

    float matAmbient[] = new float[] { 1f, 1f, 1f, 1.0f };
    float matDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };

    int tex;
    Bitmap bmp;

    private  Context context;
    public  CubeTexture(Context context) {
        this.context = context;
        float box[] = new float[] {
                // FRONT
                -0.5f, -0.5f,  0.5f,
                0.5f, -0.5f,  0.5f,
                -0.5f,  0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                // BACK
                -0.5f, -0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f,  0.5f, -0.5f,
                // LEFT
                -0.5f, -0.5f,  0.5f,
                -0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f,
                // RIGHT
                0.5f, -0.5f, -0.5f,
                0.5f,  0.5f, -0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                // TOP
                -0.5f,  0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                -0.5f,  0.5f, -0.5f,
                0.5f,  0.5f, -0.5f,
                // BOTTOM
                -0.5f, -0.5f,  0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f, -0.5f, -0.5f,
        };

        float texCoords[] = new float[] {
                // FRONT
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                // BACK
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                0.0f, 1.0f,
                // LEFT
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                0.0f, 1.0f,
                // RIGHT
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                0.0f, 1.0f,
                // TOP
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                // BOTTOM
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                0.0f, 1.0f
        };

        setVertrixsBuff(box);
        setTextureBuff(texCoords);
        bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
        setTexBitmap(bmp);
    }


    float xrot = 0.0f;
    float yrot = 0.0f;
    @Override
    public void drawTexture(GL10 gl) {

       LogMes.d("TAG", "========CubeTexture draw is starting");

        gl.glRotatef(xrot, 1, 0, 0);
        gl.glRotatef(yrot, 0, 1, 0);
        gl.glColor4f(1, 1.0f, 1, 1.0f);
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        gl.glColor4f(1, 1, 1.0f, 1.0f);
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
        xrot += 1.0f;
        yrot += 0.5f;
    }
}
