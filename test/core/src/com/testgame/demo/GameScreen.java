package com.testgame.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

	
	TiledGameMap gamemap;
	TerraRogueDemo game;
	OrthographicCamera cam;
	
	public GameScreen(TerraRogueDemo game) {
		this.game = game;
		gamemap = new TiledGameMap();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.update();
	}
	
	
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 2f, 2f, true);
		
		if(Gdx.input.isTouched()) {
			cam.translate(-Gdx.input.getDeltaX(),  Gdx.input.getDeltaY());
			cam.update();
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(5, 0);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 5);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -5);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-5, 0);
		}
		cam.update();
		
		this.gamemap.render(cam);
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

	}

}
