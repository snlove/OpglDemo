package com.example.zy.opgldemo.shape;

import com.example.zy.opgldemo.Util.LogMes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/8/27.
 */
public class Square {
    // define out vertices
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f,  //top left
            -1.0f, -1.0f, 0.0f,// bottom left
            1.0f, -1.0f, 0.0f,//botttom right
            1.0f, 1.0f, 0.0f   //top right
    };
    // define the graph to matrix map common the basic graph is traingle
    private short[] indexs = {0, 1, 2, 0, 2, 3};
    // set the buffer
    private FloatBuffer verticesBuff;
    private ShortBuffer indexsBuff;

    public Square() {
        //attention the the byte differ
        /*
        * 进行缓存数据的写入,首先是顶点，然后是渲染投影对应关系,只能使direcebuff
        * */
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuff = vbb.asFloatBuffer();
        verticesBuff.put(vertices);
        verticesBuff.position(0);


        ByteBuffer ibb = ByteBuffer.allocateDirect(indexs.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexsBuff = ibb.asShortBuffer();
        indexsBuff.put(indexs);
        indexsBuff.position(0);
    }


    /*
    * 设置面的绘制方式尤其要注意面的前后面
    * */
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f); // 0x8080ffff

        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);



        /*
        * 设置使点在渲染时会被绘制,包括点，颜色，纹理等
        * */

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //参数依次为坐标系，顶点数据类型，顶点数据资源
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,verticesBuff);
        //根据资源在根据映射关系进行,在安卓只支持三角形绘画，
        gl.glDrawElements(GL10.GL_TRIANGLES, indexs.length, GL10.GL_UNSIGNED_SHORT, indexsBuff);

        /*
        * 最后将缓存擦除掉,跟上面对应
        * */
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
        LogMes.d("TAG", "=============平面:");
    }

}
