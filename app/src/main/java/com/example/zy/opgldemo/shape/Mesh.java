package com.example.zy.opgldemo.shape;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.example.zy.opgldemo.Util.LogMes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/8/27.
 */
public abstract class Mesh {

    private FloatBuffer vertrixsBuff = null;
    private ShortBuffer indexsBuff = null;
    private FloatBuffer colorsBuff = null;
    private FloatBuffer textureBuff = null;
    int texttureId = -1;
    Bitmap bitmap;
    private  int numberofIndexs = -1;
    private  float x=0,y=0,z=0;
    private  float rx=0,ry=0,rz=0;
    private  final float[] rgba = new float[]{ 1.0f, 0.5f, 1.0f, 1.0f };

    private boolean checkMove = false;
    private  boolean checkColor = true;
    private  boolean checkTexture = false;



    public  void setVertrixsBuff(float[] vertrixs) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertrixs.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertrixsBuff = vbb.asFloatBuffer();
        vertrixsBuff.put(vertrixs);
        vertrixsBuff.position(0);
    }

    public void setIndexsBuff(short[] indexs) {
        ByteBuffer ibb = ByteBuffer.allocateDirect(indexs.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexsBuff = ibb.asShortBuffer();
        indexsBuff.put(indexs);
        indexsBuff.position(0);

    }
    public void setColor(float red , float green, float blue, float alpha){
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
        LogMes.d("TAG", "===========color is set default");
    }

    public void setColorsBuff(float[] colors) {
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorsBuff = cbb.asFloatBuffer();
        colorsBuff.put(colors);
        colorsBuff.position(0);
        LogMes.d("TAG", "==========color free is set");
        checkColor = false;
    }

    public void setTextureBuff(float[] texture) {
        checkTexture = true;
        ByteBuffer tbb = ByteBuffer.allocateDirect(texture.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        textureBuff = tbb.asFloatBuffer();
        textureBuff.put(texture);
        textureBuff.position(0);

    }

    public void setTexBitmap(Bitmap bitmap) {
        LogMes.d("TAG","=======setTexBitmap()");
        this.bitmap = bitmap;
    }
//    public void loadTexture(GL10 gl) {
//
//        if(bitmap != null) {
//            int[] textureIds = new int[1];
//            gl.glGenTextures(1, textureIds, 0);
//            texttureId = textureIds[0];
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, texttureId);
//            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
//
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
//
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
//                    GL10.GL_CLAMP_TO_EDGE);
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
//                    GL10.GL_REPEAT);
//            LogMes.d("TAG","===========The bitmap is load success");
//
//        } else {
//            LogMes.d("TAG","===========the bitmap is null");
//        }
//    }

//    private int loadTexture(GL10 gl, Bitmap bmap) {
//        return loadTexture(gl, bmap, false);
//    }

    public int loadTexture(GL10 gl, Bitmap bmap, boolean reverseRGB) {
        ByteBuffer bb = ByteBuffer.allocateDirect(bmap.getHeight()*bmap.getWidth()*4);
        bb.order(ByteOrder.BIG_ENDIAN);
        IntBuffer ib = bb.asIntBuffer();

        for (int y=bmap.getHeight()-1;y>-1;y--)
            for (int x=0;x<bmap.getWidth();x++) {
                int pix = bmap.getPixel(x,bmap.getHeight()-y-1);
                // Convert ARGB -> RGBA
                @SuppressWarnings("unused")
                byte alpha = (byte)((pix >> 24)&0xFF);
                byte red = (byte)((pix >> 16)&0xFF);
                byte green = (byte)((pix >> 8)&0xFF);
                byte blue = (byte)((pix)&0xFF);

                // It seems like alpha is currently broken in Android...
                ib.put(red << 24 | green << 16 | blue << 8 | 0xFF);//255-alpha);
            }
        ib.position(0);
        bb.position(0);

        int[] tmp_tex = new int[1];

        gl.glGenTextures(1, tmp_tex, 0);
        int tex = tmp_tex[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex);
//        gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, bitmap.getWidth(), bitmap.getHeight(), 0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, bb);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0,bmap,0);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        return tex;
    }


    public void setTranslater(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        LogMes.d("TAG","========translate");
        checkMove = true;
    }

    public void setRoate(float rx, float ry, float rz) {
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        LogMes.d("TAG","========roate");
    }


    public void draw(GL10 gl) {

        LogMes.d("TAG","============THE method is staring ");
        //设置前后投影属性
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        //设置矩阵投影关系
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertrixsBuff);

        if (colorsBuff != null){
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorsBuff);

        } else {
            gl.glColor4f(rgba[0],rgba[1],rgba[2],rgba[3]);
            LogMes.d("TAG", "===========color is default");
        }

        //渲染纹理
        if (checkTexture) {
            //texttureId = loadTexture(gl,bitmap);
            LogMes.d("TAG","===========load texture");
            checkTexture = false;
        }
        if (texttureId != -1 && textureBuff != null) {
            gl.glEnableClientState(GL10.GL_TEXTURE_2D);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuff);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texttureId);

        }


        // 平移必须在此之前进行平移旋转
        if(checkMove) {
            gl.glTranslatef(x, y, z);
            checkMove = false;
            LogMes.d("TAG","========Translate");
        }

        /**
        * 旋转
        * */
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);

        //设置投影方式，大之六种，
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 36, GL10.GL_UNSIGNED_SHORT, indexsBuff);

        //设置的所有enable不可能
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        if (colorsBuff != null) {
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        }

        if (texttureId != -1 && textureBuff != null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
        gl.glDisable(GL10.GL_CULL_FACE);
        rx += 1.0f;
        ry += 0.5f;
    }

     public  void draw3d(GL10 gl){
         gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

//         gl.glMatrixMode(GL10.GL_MODELVIEW);
//         gl.glLoadIdentity();
//         GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);

         gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertrixsBuff);
         gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);



         gl.glRotatef(rx, 1, 0, 0);
         gl.glRotatef(ry, 0, 1, 0);

         //gl.glColor4f(1.0f, 0, 0, 1.0f);
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

         gl.glColor4f(0, 1.0f, 0, 1.0f);
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

         gl.glColor4f(0, 0, 1.0f, 1.0f);
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
         gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
         rx += 1.0f;
         ry += 0.5f;
     }



    public void drawSecond(GL10 gl) {


        //设置矩阵投影关系
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertrixsBuff);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuff);


        LogMes.d("Boolean", "==========" + checkTexture);
//        if (checkTexture) {
//            texttureId = loadTexture(gl,bitmap);
//            LogMes.d("TAG","===========load texture success" + "    " + texttureId);
//            //checkTexture = false;
//        }
//        if (texttureId != -1 && textureBuff != null) {
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, texttureId);
//            LogMes.d("TAG","=========the textureZD is set");
//        }
        loadTexture(gl,bitmap,false);
        drawTexture(gl);

    }
  public  abstract  void  drawTexture(GL10 gl);


}
