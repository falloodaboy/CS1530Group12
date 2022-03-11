package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PauseScreen implements Screen {

	private Game game;
	private OrthographicCamera cam;
	private Stage stage;
	private Table root;
	private TextureRegion regionBackground;
	private Texture backgroundTexture;
	private Label title;
	private TextButton save, resume, exit;
	private Skin skin;
	private GameScreen gameScreen;
	private Window window;
	private Music pauseSound;
	
	public PauseScreen(Game game, OrthographicCamera cam, GameScreen gameScreen) {
		
		this.game = game;
		this.cam = cam;
		this.gameScreen = gameScreen;
		try {
			pauseSound = Gdx.audio.newMusic(Gdx.files.internal("selectSound.mp3"));
			pauseSound.setVolume(0.4f);
		} catch(Exception e) {
			System.out.print(e);
		}
		
	}
	
	
	@Override
	public void show() {
		pauseSound.play();
		
		skin = new Skin(Gdx.files.internal("craftacular-ui.json"));
		title = new Label("Paused Game", skin, "title");
		root = new Table(skin);
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		backgroundTexture = new Texture("dirt.png");
		backgroundTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		regionBackground = new TextureRegion(backgroundTexture);
		regionBackground.setRegion(0, 0, backgroundTexture.getWidth()*10, backgroundTexture.getHeight()*10);
		
		save = new TextButton("Save Game", skin);
		resume = new TextButton("Resume", skin);
		exit = new TextButton("Exit", skin);
		
		
		
		exit.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					boolean answer = true;
					exitGame();
					
					return answer;
				}
		});
		
		save.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					boolean answer = true;
					
					saveGame();
					
					return answer;
				}
		});
		
		resume.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				
				game.setScreen(gameScreen);
				dispose();
				
				return answer;
			}
	});
		
		Gdx.input.setInputProcessor(stage);
		root.center();
		root.setFillParent(true);
		title.setFontScale(0.60f);
		
		root.add(title);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.add(save);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.add(resume);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.add(exit);
		root.setBackground(new TextureRegionDrawable(regionBackground));
		stage.addActor(root);

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1, true);
		
		stage.draw();
		stage.act(delta);
			
			
			if(Gdx.input.isKeyJustPressed(Keys.P)) {
				Gdx.input.setInputProcessor(null);
				game.setScreen(gameScreen);
				this.dispose();
				pauseSound.play();
			}
	}

	@Override
	public void resize(int width, int height) {
			//should I recreate every element based on resize?
			stage.getViewport().update(width, height, true);
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
		pauseSound.dispose();

	}
	
	
	/*
	 * Save the Player's game data in a separate file. This includes: 
	 * the player score, 
	 * the items the player has, 
	 * the player's location in the world, 
	 * and the list of enemies currently in the game.
	 */
	public void saveGame() {
		gameScreen.saveGame();
	}
	
	
	public void exitGame() {
		Gdx.app.exit();
	}

}
