package com.example.zy.opgldemo.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.zy.opgldemo.MainActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zy on 2015/8/28.
 */
public class BitmapUtil {

    private Bitmap bitmap;
    Context context;
    public  Bitmap getAsset(String filename) {

        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(filename);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bitmap;
    }
}
