package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *  BattleScreen class for when the player engages the enemy.
 *  The battle screen depends on the player state data and enemy state data as input
 *  It will simulate the battle and update the player and enemy state data accordingly
 */



import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class BattleScreen implements Screen {
	
	private Stage stage;
	private Table root;
	private Label intro;
	private Skin skin;
	private Game game;
	private GameScreen gameScreen;
	
	public BattleScreen(Game game, GameScreen gameScreen) {
		this.game = game;
		this.gameScreen = gameScreen;
	}
	
	
	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("TDS.json"));
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		root = new Table();
		root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("city_back.png")))));
		intro = new Label("This is the battle screen", skin);
		root.setFillParent(true);
		root.top();
		Gdx.input.setInputProcessor(stage);
		
		
		root.add(intro);
		stage.addActor(root);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0, 0.1f);
		
		stage.act(delta);
		stage.draw();
		
		if(Gdx.input.isKeyJustPressed(Keys.B)) {
			Gdx.input.setInputProcessor(null);
			this.game.setScreen(gameScreen);
			this.dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height);
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
		stage.dispose();
		skin.dispose();

	}

}
