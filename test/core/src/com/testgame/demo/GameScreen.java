package com.testgame.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.ScreenUtils;
import com.testgame.demo.world.TiledGameMap;

public class GameScreen implements Screen {

	
	public TiledGameMap gamemap;
	public TerraRogueDemo game;
	public OrthographicCamera cam;
	public Music bgm;
	private BoundingBox box;
	
	public GameScreen(TerraRogueDemo game) {
		this.game = game;
		gamemap = new TiledGameMap();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		try {
			bgm = Gdx.audio.newMusic(Gdx.files.internal(""));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 2f, true);
		
		//I can use camera position to set bounds on the map scrolling.
		
		this.gamemap.render(cam, this.game.batch);
		
		if(Gdx.input.isKeyJustPressed(Keys.I)) {
			game.setScreen(new InventoryScreen(game, cam, this));
			this.pause();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
			
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		this.gamemap.dispose();
		bgm.dispose();

	}

}

