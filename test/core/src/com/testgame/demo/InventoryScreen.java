package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;

public class InventoryScreen implements Screen {

	//trying out scene2d.ui to make this inventory screen.
	
	//should we create a new instance of this screen every time the player presses pause?
	//and delete this instance whenever the screen is hidden again?
	
	//
	
	private Stage stage;
	private Skin skin;
	private Game game; //Game object which sets the screens
	private OrthographicCamera cam; //Camera looking into the world.
	private GameScreen gamescreen; //game screen to return to after player is done doing inventory stuff.
	
	
	public InventoryScreen(Game game, OrthographicCamera cam, GameScreen gamescreen) {
		this.game = game;
		this.cam = cam;
		this.gamescreen = gamescreen;
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1, true);
		
		
		
		
		if(Gdx.input.isKeyJustPressed(Keys.I)) {
			game.setScreen(gamescreen);
			this.dispose();
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
		
	}

}
