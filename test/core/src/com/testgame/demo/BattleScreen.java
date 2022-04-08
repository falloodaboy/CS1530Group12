package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;




import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;




/**
 *  BattleScreen class for when the player engages the enemy.
 *  The battle screen depends on the player state data and enemy state data as input
 *  It will simulate the battle and update the player and enemy state data accordingly
 *  
 *  Tasks Left To Do:
 *  1. Properly position all of the elements on the BattleScreen based on the design layout
 *  2. Setup list UI for the options for the player.
 *  3. Test separate skin for options and combine it into the TDS atlas afterwards
 *  4. Setup Turn-based RPG logic
 *  5. Get/Set the player and enemy states
 *  6. Add enemy attack logic
 */
public class BattleScreen implements Screen {
	//class elements
	private Option curOption; //the current option that the player has selected
	private boolean turnLock = true;
	float time = 0;
	float actionDelay = 2;
	private String[] tst;
	
	//UI elements
	private Stage stage;
	private Table root;
	private TextField topBox;
	private Table optionsBox, sceneBox;
	private List options;
	private Image playerSprite;
	private Image enemySprite;
	private Label optionsGreeter;
	
	
	
	//Miscellaneous elements
	private Skin skin;
	private Game game;
	private GameScreen gameScreen;
	private Music bgm;
	
	public BattleScreen(Game game, GameScreen gameScreen) {
		this.game = game;
		this.gameScreen = gameScreen;
		try {
			bgm = Gdx.audio.newMusic(Gdx.files.internal("aurora.mp3"));
			bgm.setVolume(0.06f);
			bgm.setLooping(true);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		tst = new String[2];
		tst[0] = "Milk";
		tst[1] = "Eggs";
	}
	
	
	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("TDS.json"));
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		root = new Table();
		topBox = new TextField("Battle Start", skin);
		optionsBox = new Table();
		sceneBox = new Table();
		options = new List(skin);
		optionsGreeter = new Label("Select an Option Below: ", skin, "holo-light");
		options.setItems(tst);
		playerSprite = new Image(new Texture(Gdx.files.internal("nate_back.png")));
		enemySprite = new Image(new Texture(Gdx.files.internal("dirt.png")));
		topBox.setTouchable(null);
		topBox.setAlignment(1);
		optionsBox.setBackground(new TextureRegionDrawable(new Texture(Gdx.files.internal("battleoptionsbg.png"))));
		root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("old_castle_bg.jpg")))));
		root.setFillParent(true);
		
		Gdx.input.setInputProcessor(stage);
		
		
		root.top();
		root.add(topBox).prefWidth(Value.percentWidth(0.7f, root));
		root.row();
		Cell<Table> sceneCell = root.add(sceneBox).expand().bottom();
		Cell<Image> playerCell = sceneBox.add(playerSprite);
		playerCell.padRight(Value.percentWidth(0.5f, root));
		sceneBox.add(enemySprite).padLeft(Value.percentWidth(-0.6f, root));
		root.row();
		Cell<Table> optionsCell = root.add(optionsBox);
		optionsBox.left().top();
		optionsBox.add(optionsGreeter).padLeft(40);
		optionsBox.row();
		optionsBox.add(options).padLeft(-115);
		optionsCell.maxHeight(Value.percentHeight(0.3f, root));
		optionsCell.minHeight(Value.percentHeight(0.1f, root));
		optionsCell.prefWidth(Value.percentWidth(1f, root));
		
		stage.addActor(root);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0, 0.1f);
		bgm.play();
		
		stage.act(delta);
		stage.draw();
		
		if(Gdx.input.isKeyJustPressed(Keys.B)) {
			Gdx.input.setInputProcessor(null);
			this.game.setScreen(gameScreen);
			this.dispose();
		}
		
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && turnLock == true) {
			turnLock = false;
		}
		
		this.executeTurn(delta);
		
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
		bgm.dispose();

	}

	private class Option {
		
		String name;
		String description;
		
		public Option(String name, String desc) {
			this.name = name;
			this.description = desc;
		}
	}
	
	/**
	 * Executes the current RPG turn where the player does an action and then the enemy does an action
	 */
	private void executeTurn(float delta) {
		if(turnLock == false) {
			
			time += delta;
			
			if(time > actionDelay) {
				time -= actionDelay;
				turnLock = true;
				optionsBox.setVisible(true);
			}
			else {
				optionsBox.setVisible(false);
			}
		}
		
	}
}
