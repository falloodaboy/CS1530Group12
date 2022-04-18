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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.testgame.demo.world.TiledGameMap;

public class GameScreen implements Screen {

	
	public TiledGameMap gamemap;
	public TerraRogueDemo game;
	public OrthographicCamera cam;
	private Music bgm;
	private MainMenuScreen menuscreen;
	
	public GameScreen(TerraRogueDemo game, String spriteSheetFileName, MainMenuScreen mainmenu) {
		this.game = game;
		gamemap = new TiledGameMap(spriteSheetFileName);
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menuscreen = mainmenu;
		cam.update();
		try {
			bgm = Gdx.audio.newMusic(Gdx.files.internal("kiliansWake.mp3"));
			bgm.setVolume(0.1f);
			bgm.setLooping(true);
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
		bgm.play();
		
		
		//I can use camera position to set bounds on the map scrolling.
		
		this.gamemap.render(cam, this.game.batch);
		
		if(Gdx.input.isKeyJustPressed(Keys.I)) {
			game.setScreen(new InventoryScreen(game, cam, this));
			this.pause();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.P)) {
			game.setScreen(new PauseScreen(game, cam, this, menuscreen));
			this.pause();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.T)) {
			game.setScreen(new TestScreen(game, cam, this));
			this.pause();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.B)) {
			game.setScreen(new BattleScreen(game, this));
			bgm.pause();
			this.pause();
		}
	}


	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		bgm.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		this.gamemap.dispose();
		bgm.dispose();

	}

	
	public void saveGame() {
		gamemap.saveGame();
	}
	
	
	
	@Override
	public void resize(int width, int height) {
		
	}

}

