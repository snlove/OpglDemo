package com.example.zy.opgldemo.shape;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/8/27.
 * 平面有几个不同的三角形构成的正方形组成，segments代表小正方形数
 */
public class Plane extends Mesh {

    public Plane() {
        this(1, 1, 1, 1);
    }

    public Plane(float wdith, float height) {
        this(wdith, height, 1, 1);
    }

    public Plane(float width, float height, int widthSegments, int heightSegments) {
        float[] vertrixs = new float[(int) ((widthSegments + 1) * (heightSegments + 1) * 3)];
        short[] indexs = new short[(widthSegments + 1) * (heightSegments + 1) * 6];
        float offsetx = width / -2;
        float offsety = height / -2;
        float xwidth = width / (widthSegments + 1);
        float yheigh = height / (heightSegments + 1);
        int currentVertrix = 0;
        int currentIndex = 0;
        short w = (short) (widthSegments + 1);
        for(int y= 0; y<heightSegments +1; y++) {
            for(int x =0; x<widthSegments +1; x++) {
                vertrixs[currentVertrix] = offsetx + xwidth *x;
                vertrixs[currentVertrix+1] = offsety + yheigh *y;
                vertrixs[currentVertrix+2] = 0;
                currentVertrix +=3;
                int n = y * (widthSegments + 1) + x;
                if (y < heightSegments && x < widthSegments) {
                    // Face one
                    indexs[currentIndex] = (short) n;
                    indexs[currentIndex + 1] = (short) (n + 1);
                    indexs[currentIndex + 2] = (short) (n + w);
                    // Face two
                    indexs[currentIndex + 3] = (short) (n + 1);
                    indexs[currentIndex + 4] = (short) (n + 1 + w);
                    indexs[currentIndex + 5] = (short) (n + 1 + w - 1);

                    currentIndex += 6;
                }
            }
        }
        this.setVertrixsBuff(vertrixs);
        this.setIndexsBuff(indexs);
    }

    @Override
    public void drawTexture(GL10 gl) {

    }
}
