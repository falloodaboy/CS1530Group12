package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;

public class TestScreen implements Screen {
	
	//test screen for the skin in json, can be repurposed later
	
	private Game game;
	private GameScreen gameScreen;
	private OrthographicCamera cam;
	private Skin skin;
	private Stage stage;
	private Window window;
	private Table root;
	
	public TestScreen(Game game, OrthographicCamera cam, GameScreen gameScreen) {
		this.game = game;
		this.cam = cam;
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void show() {
		
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("TDS.json"));
		root = new Table(skin);
		root.center();
		root.setFillParent(true);
		window = new Window("", skin);
		window.add(new Label("This is a label", skin));
		root.add(window);
		
		stage.addActor(root);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1, true);
		
		stage.draw();
		stage.act();
		
		
		if(Gdx.input.isKeyJustPressed(Keys.T)) {
			Gdx.input.setInputProcessor(null);
			game.setScreen(gameScreen);
			this.dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		skin.dispose();
		stage.dispose();

	}

}
