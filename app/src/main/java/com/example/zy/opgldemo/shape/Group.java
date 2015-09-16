package com.example.zy.opgldemo.shape;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zy on 2015/8/27.
 */
public class Group extends Mesh {
    Vector<Mesh> child = new Vector<Mesh>();

    public void addMesh(int location, Mesh object) {
        child.add(location, object);
    }

    public boolean addMesh(Mesh object) {
        return child.add(object);
    }

    public boolean removeMesh(Mesh object) {
        return child.remove(object);
    }

    public int getSize() {
        return child.size();
    }

    public Mesh getMesh(int location) {
        return child.get(location);
    }

    public void clear() {
        child.clear();
    }

    public void remove(int location) {
        child.remove(location);
    }

    @Override
    public void draw(GL10 gl) {
        for (int i = 0; i < child.size(); i++) {
            child.get(i).draw(gl);
        }
    }

    @Override
    public void drawTexture(GL10 gl) {

    }


}
