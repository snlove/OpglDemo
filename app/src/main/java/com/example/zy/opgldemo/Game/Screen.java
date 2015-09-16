package com.example.zy.opgldemo.Game;

/**
 * Created by zy on 2015/9/1.
 */
public abstract  class Screen {
  protected final  Game game;
    public Screen(Game game) {
        this.game = game;
    }



    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
