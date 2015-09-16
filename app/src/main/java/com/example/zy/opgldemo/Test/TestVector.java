package com.example.zy.opgldemo.Test;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.zy.opgldemo.MainActivity;
import com.example.zy.opgldemo.Game.Screen;
import com.example.zy.opgldemo.shape.CicleScreen;

/**
 * Created by zy on 2015/9/9.
 */
public class TestVector extends MainActivity {
    @Override
    public Screen getStartScreen() {
        return new RectAngle(this);
    }

    public Context getCurrentContext() {
       return  this.getBaseContext();
    }
}
