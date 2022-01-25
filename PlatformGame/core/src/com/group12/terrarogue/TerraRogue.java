package com.group12.terrarogue;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.group12.terrarogue.world.CustomGameMap;
import com.group12.terrarogue.world.GameMap;
import com.group12.terrarogue.world.TileType;

public class TerraRogue extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameMap gamemap;
	OrthographicCamera cam;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		gamemap = new CustomGameMap();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		
		
		if(Gdx.input.isTouched()) {
			cam.translate(-Gdx.input.getDeltaX(), -Gdx.input.getDeltaY());
			cam.update();
		}
		
		if(Gdx.input.justTouched()) {
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			TileType type = gamemap.getTileTypeByLocation(1, pos.x, pos.y);
		}
		
		gamemap.render(cam);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
