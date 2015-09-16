package com.example.zy.opgldemo.shape;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/8/28.
 */
public class SimplePlane extends  Mesh {

    public SimplePlane() {
        this(1, 1);
    }

    /**
     * Create a plane.
     *
     * @param width
     *            the width of the plane.
     * @param height
     *            the height of the plane.
     */
    public SimplePlane(float width, float height) {
        // Mapping coordinates for the vertices
        float textureCoordinates[] = { 0.0f, 2.0f, //
                2.0f, 2.0f, //
                0.0f, 0.0f, //
                2.0f, 0.0f, //
        };

        short[] indices = new short[] { 0, 1, 2, 1, 3, 2 };

        float[] vertices = new float[] { -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                -0.5f,  0.5f, 0.0f,
                0.5f, 0.5f, 0.0f };

        setIndexsBuff(indices);
        setVertrixsBuff(vertices);
        setTextureBuff(textureCoordinates);
    }

    @Override
    public void drawTexture(GL10 gl) {

    }
}

