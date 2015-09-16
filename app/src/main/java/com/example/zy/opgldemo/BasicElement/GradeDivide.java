package com.example.zy.opgldemo.BasicElement;

import android.util.FloatMath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2015/9/10.
 */
public class GradeDivide {
    List<BasicObject>[] dymaicObject;
    List<BasicObject>[] staticObject;//为了提高内存效率，分两个列表
    List<BasicObject> foundObject;
    int[] cellIds = new int[4];//最多占四个单元格
    int cellPerRow;
    int cellPerColum;
    float cellSize;

    public GradeDivide(float frumWidth, float frumHeight, float cellSize) {
        this.cellSize = cellSize;
        cellPerRow = (int) Math.ceil(frumWidth / cellSize);
        cellPerColum = (int) Math.ceil(frumHeight / cellSize);
        int cellId = -1;
        int numCell = cellPerColum * cellPerRow;
        for (int i = 0; i < numCell; i++) {
            dymaicObject[i] = new ArrayList<BasicObject>(10);//一个单元格最多存十个对象
            staticObject[i] = new ArrayList<BasicObject>(10);
        }
        foundObject = new ArrayList<BasicObject>(10);
    }

    public void insertStatic(BasicObject object) {
        int[] cellIds = getCellIds(object);
        int i = 0;
        int cellId = -1;
        while (i < 3 && ((cellId = cellIds[i++]) != -1)) {
            staticObject[cellId].add(object);
        }
    }

    public void insertDymaic(BasicObject object) {
        int[] cellIds = getCellIds(object);
        int i = 0;
        int cellId = -1;
        while (i < 3 && ((cellId = cellIds[i++]) != -1)) {
            dymaicObject[cellId].add(object);
        }
    }



    public void removeObject(BasicObject object) {
        int[] cellIds = getCellIds(object);
        int i = 0;
        int cellId = -1;
        while (i < 3 && ((cellId = cellIds[i++]) != -1)) {
            dymaicObject[cellId].remove(object);
            staticObject[cellId].remove(object);
        }
    }

    public void clearDymaicObject(BasicObject object) {
        int[] cellIds = getCellIds(object);
        int i = 0;
        int cellId = -1;
        while (i < 3 && ((cellId = cellIds[i++]) != -1)) {
            dymaicObject[cellId].remove(object);
        }
    }

    public void getPointerList(BasicObject object) {
        foundObject.clear();
        int[] cellIds = getCellIds(object);
        int i = 0;
        int cellId = -1;
        while (i < 3 && ((cellId = cellIds[i++]) != -1)) {
            int len = dymaicObject[cellId].size();
            for (int j = 0; j < len; j++) {
                BasicObject pointObject = dymaicObject[cellId].get(j);
                if (!foundObject.contains(pointObject)){
                    foundObject.add(pointObject);
                }
            }

            int size = staticObject[cellId].size();
            for (int j = 0; j < size; j++) {
                BasicObject pointObject = staticObject[cellId].get(j);
                if (!foundObject.contains(pointObject)){
                    foundObject.add(pointObject);
                }
            }
        }
    }


    public int[] getCellIds(BasicObject basicObject) {
        Vector2f bound = basicObject.getBound().getLeftConer();
        float boudWidth = basicObject.getBound().getWidth();
        float boundHeight = basicObject.getBound().getHeight();
        //计算得到对象边界点所在的单元格
        int x1 = (int) Math.ceil(bound.x / cellSize);
        int x2 = (int) Math.ceil((bound.x + boudWidth) / cellSize);
        int y1 = (int) Math.ceil(bound.y / cellSize);
        int y2 = (int) Math.ceil((bound.y + boundHeight) / cellSize);

        //一个单元格
        if (x1 == x2 && y1 == y2) {
            if (x1 >= 0 && x1 <= cellPerRow) {
                if (y1 >= 0 && y1 <= cellPerColum) {
                    cellIds[0] = x1 + y1 * cellPerRow;
                } else {
                    cellIds[0] = -1;
                    cellIds[1] = -1;
                    cellIds[2] = -1;
                    cellIds[3] = -1;
                }
            }
        }

        //x方向占两个单元格
        else if (y1 == y2) {
            int i = 0;
            if (y1 >= 0 && y1 <= cellPerColum) {
                if (x1 >= 0 && x1 <= cellPerRow) {
                    cellIds[i++] = x1 + y1 * cellPerRow;
                }
                if (x2 >= 0 && x2 <= cellPerRow) {
                    cellIds[i++] = x2 + y1 * cellPerRow;
                }
                while (i < 3) {
                    cellIds[i++] = -1;
                }
            }
        }

        //y方向占两个单元格
        else if (x1 == x2) {
            int i = 0;
            if (x1 >= 0 && x1 <= cellPerRow) {
                if (y1 >= 0 && y1 <= cellPerColum) {
                    cellIds[i++] = x1 + y1 * cellPerRow;
                }
                if ((y2 >= 0 && y2 <= cellPerColum)) {
                    cellIds[i++] = x2 + y2 * cellPerColum;
                }
                while (i < 3) {
                    cellIds[i++] = -1;
                }
            }
        } else {
            int i = 0;
            if (x1 >= 0 && x1 <= cellPerRow && y1 >= 0 && y1 <= cellPerColum) {
                cellIds[i++] = x1 + y1 * cellPerRow;
            }
            if (x2 >= 0 && x2 <= cellPerRow && y1 >= 0 && y1 <= cellPerColum) {
                cellIds[i++] = x2 + y1 * cellPerRow;
            }
            if (x1 >= 0 && x1 <= cellPerRow && y2 >= 0 && y2 <= cellPerColum) {
                cellIds[i++] = x1 + y2 * cellPerRow;
            }
            if (x2 >= 0 && x2 <= cellPerRow && y2 >= 0 && y2 <= cellPerColum) {
                cellIds[i++] = x2 + y2 * cellPerRow;
            }
            while (i < 3) {
                cellIds[i++] = -1;
            }
        }
        return cellIds;
    }
}
