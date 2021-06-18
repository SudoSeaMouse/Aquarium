package com.nezusera.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Food extends GridObject{
    boolean isEaten = false;
    public Food (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        if (this.img == null) img = new Texture("food.png");
    }

    public void eaten () {
        isEaten = true;
    }

    @Override
    public void draw (SpriteBatch sb) {
        sb.draw(img, (xPos * 50) + xOffset, (yPos * 50) + yOffset);
    }
}
