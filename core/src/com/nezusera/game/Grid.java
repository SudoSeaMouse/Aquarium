package com.nezusera.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class Grid {

    List<GridObject> objList = new ArrayList<>();
    static Texture tile;
    public int width = 12;
    public int height = 9;
    int xOffset = 20;
    int yOffset = 16;
    Random r;

    public Grid (Random r) {
        tile = new Texture("tile.png");
        this.r = r;
        //Make Bugs
        for (int i = 0; i < 5; i++) {
            int randomX = r.nextInt(width-1);
            int randomY = r.nextInt(height-1);
            if (isTileEmpty(randomX,randomY)) {
                objList.add(new Bug(this,randomX,randomY, Facing.Up, r));
            }
        }
        //Make Food
        for (int i = 0; i < 30; i++) {
            int randomX = r.nextInt(width-1);
            int randomY = r.nextInt(height-1);
            if (isTileEmpty(randomX,randomY)) {
                objList.add(new Food(randomX,randomY));
            }
        }
    }

    public void update () {
        //Remove food that was eaten
        List<GridObject> temp = objList;
        for (int i = 0; i < temp.size(); i++) {
            GridObject t = temp.get(i);
            if (t instanceof Food) {
                if (((Food) t).isEaten) {
                    objList.remove(t);
                }
            }
        }
        List<GridObject> tempBugList = objList;
        Collections.shuffle(tempBugList);
        for (int i = 0; i<tempBugList.size(); i++) {
            tempBugList.get(i).update();
        }

    }

    public void draw (SpriteBatch sb) {
        //Draw Tile
        for (int i = 0; i<width; i++) {
            for (int j = 0; j<height; j++) {
                sb.draw(tile, (i * 50) + xOffset, (j * 50) + yOffset);
            }
        }

        //Draw Bug
        for (int i = 0; i < objList.size(); i++) {
            objList.get(i).draw(sb);
        }
    }

    boolean isInGrid (int x, int y) {
        if (x < 0) return false;
        if (y < 0) return false;
        if (x > width-1) return false;
        if (y > height-1) return false;
        return true;
    }

    boolean isBugAt (int x, int y) {
        for (int i = 0; i < objList.size(); i++) {
            GridObject o = objList.get(i);
            if (o instanceof Bug) {
                if ((o.xPos == x) && (o.yPos == y)) return true;
            }
        }
        return false;
    }

    boolean isFoodAt (int x, int y) {
        for (int i = 0; i < objList.size(); i++) {
            GridObject o = objList.get(i);
            if (o instanceof Food) {
                if ((o.xPos == x) && (o.yPos == y)) return true;
            }
        }
        return false;
    }

    GridObject getObjectAt (int x, int y) {
        for (int i = 0; i < objList.size(); i++) {
            GridObject o = objList.get(i);
            if ((o.xPos == x) && (o.yPos == y)) return o;
        }
        return null;
    }

    public boolean canBugMoveTo (int x, int y) {
        if ((isInGrid(x, y)) && (isBugAt(x,y) == false)) {
            return true;
        }
        return false;
    }

    boolean isTileEmpty (int x, int y) {
        if ((isInGrid(x,y)) && (isBugAt(x,y) == false) && (isFoodAt(x,y) == false)) {
            return true;
        }
        return false;
    }

    public void dispose() {
        tile.dispose();

        for (int i = 0; i < objList.size(); i++) {
            objList.get(i).img.dispose();
        }
    }
}
