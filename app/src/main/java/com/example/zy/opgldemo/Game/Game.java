package com.example.zy.opgldemo.Game;

import android.content.Context;

/**
 * Created by zy on 2015/9/1.
 */
public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public GLGraphics getGraphics();



    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();

    public Context getContext();
}
