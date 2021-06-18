package com.nezusera.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Bug extends GridObject {
    static Texture bugUp;
    static Texture bugDown;
    static Texture bugLeft;
    static Texture bugRight;
    static float bugCooldown = .5f;
    float bugTimer = 0f;
    Random r;
    long seed;
    Grid parentGrid;
    AI ai;


    public Bug (Grid g, int x, int y, Facing f, Random r) {
        //Set textures if need be
        if (bugUp == null) {
            setTextures();
        }


        this.xPos = x;
        this.yPos = y;
        this.facing = f;
        parentGrid = g;
        this.r = r;
        ai = new RandomAI();
        ai.parent = this;
        updateFacing();
    }


    @Override
    public void update() {
        //Runs once per draw

        //Clean Timer
        if (bugTimer > 0) {
            bugTimer -= Gdx.graphics.getDeltaTime();
        }

        //AI Update
        ai.AIUpdate(bugTimer, r);
    }

    @Override
    public void draw (SpriteBatch sb) {
        sb.draw(img, (xPos * 50) + xOffset, (yPos * 50) + yOffset);
    }

    void setTextures () {
        bugUp = new Texture("bug.png");
        bugDown = new Texture("bugDown.png");
        bugLeft = new Texture("bugLeft.png");
        bugRight = new Texture("bugRight.png");
    }

    void updateFacing () {
        switch (facing) {
            case Up:
                img = bugUp;
                break;
            case Down:
                img = bugDown;
                break;
            case Left:
                img = bugLeft;
                break;
            case Right:
                img = bugRight;
                break;
        }
    }

    public void RotateLeft() {
        switch (facing) {
            case Up:
                facing = Facing.Left;
                break;
            case Left:
                facing = Facing.Down;
                break;
            case Down:
                facing = Facing.Right;
                break;
            case Right:
                facing = Facing.Up;
        }
        updateFacing();
        bugTimer = bugCooldown/2;
    }

    public void RotateRight() {
        switch (facing) {
            case Up:
                facing = Facing.Right;
                break;
            case Left:
                facing = Facing.Up;
                break;
            case Down:
                facing = Facing.Left;
                break;
            case Right:
                facing = Facing.Down;
                break;
        }
        updateFacing();
        bugTimer = bugCooldown/2;
    }

    public void Wait() {
        bugTimer += r.nextFloat();
    }

    public void Forward() {
        int movingToX = xPos;
        int movingToY = yPos;

        switch (facing) {
            case Up:
                movingToY++;
                break;
            case Down:
                movingToY--;
                break;
            case Left:
                movingToX--;
                break;
            case Right:
                movingToX++;
                break;
        }

        if (canMove(movingToX,movingToY)) {
            if (parentGrid.isFoodAt(movingToX,movingToY)) {
                Food f = (Food) parentGrid.getObjectAt(movingToX,movingToY);
                f.eaten();
            }
            xPos = movingToX;
            yPos = movingToY;
            bugTimer = bugCooldown;
        } else {
            bugTimer = bugCooldown/4;
        }
    }

    boolean canMove(int x, int y) {
        if ((parentGrid.isInGrid(x, y)) && (parentGrid.isBugAt(x,y) == false)) {
            return true;
        }

        return false;
    }
}
