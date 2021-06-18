package com.nezusera.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AquariumGame extends ApplicationAdapter {
	SpriteBatch batch;
	Random r;
	Grid grid;



	@Override
	public void create () {
		batch = new SpriteBatch();
		r = new Random();
		grid = new Grid(r);
	}

	@Override
	public void render () {
		//Update
		grid.update();

		//Clear Screen
		ScreenUtils.clear(0, 0, 1, 1);
		batch.begin();

		//Draw Grid
		grid.draw(batch);

		//End Batch
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();

		grid.dispose();
	}
}
