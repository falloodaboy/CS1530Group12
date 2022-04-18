package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.testgame.demo.entities.*;

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
	private boolean turnLock = true;
	float time = 0;
	float actionDelay = 2;
	private Option[] choices;
	
	//UI elements
	private Stage stage;
	private Table root;
	private TextField topBox;
	private Table optionsBox, sceneBox;
	private ProgressBar playerHealth, enemyHealth;
	private List<Object> options;
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
		
		this.createOptions();
	}
	
	
	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("TDS.json"));
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		root = new Table();
		topBox = new TextField("Battle Start", skin);
		optionsBox = new Table();
		sceneBox = new Table();
		options = new List<Object>(skin);
		optionsGreeter = new Label("Select an Option Below: ", skin, "holo-light");
		playerHealth = new ProgressBar(0f, 100f, 1f, false, skin);
		playerHealth.setValue(100);
		enemyHealth = new ProgressBar(0f, 100f, 1f, false, skin);
		enemyHealth.setValue(100);
		
		
		options.setItems(this.getArrayChoices());
		playerSprite = new Image(new Texture(Gdx.files.internal("nate_back.png")));
		enemySprite = new Image(new Texture(Gdx.files.internal("test_monster.png")));
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
		sceneBox.add(enemySprite).padLeft(Value.percentWidth(-0.6f, root)).padTop(Value.percentHeight(0.1f, root));
		playerHealth.setPosition(100, 0);
		playerHealth.setWidth(300);
		sceneBox.addActor(playerHealth);
		root.row();
		Cell<Table> optionsCell = root.add(optionsBox);
		optionsBox.left().top();
		optionsBox.add(optionsGreeter).padLeft(40);
		optionsBox.row();
		optionsBox.add(options).padLeft(0);
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
			sceneBox.getChild(1).addAction(new DamageAction(0.2f, 0.7f, 5f));
			
		}
		
		this.executeTurn(delta);
		
	}

	@Override
	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height);
		this.stage.getViewport().apply();
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

	//To be used with the Options. RNG damage model for each option
	private class Option {
		
		String name;
		String description;
		float damage;
		
		public Option(String name, String desc) {
			this.name = name;
			this.description = desc;
		}
		
		public String getName() {
			return name;
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
				topBox.setText("Battle Start");
				optionsBox.setVisible(true);
			}
			else {
				topBox.setText("Announcement");
				optionsBox.setVisible(false);
			}
		}
		
	}
	
	private String[] getArrayChoices() {
		String[] cho = new String[choices.length];
		for(int i=0; i < choices.length; i++) {
			cho[i] = choices[i].getName();
		}
		
		return cho;
	}
	
	private void createOptions() {
		choices = new Option[4];
		
		choices[0] = new Option("Fireball          30MP", "Throws a fireball at the enemy");
		choices[1] = new Option("Heal               12MP", "Recovers your health by 10 points");
		choices[2] = new Option("Charm            50MP", "50% chance that the enemy does nothing");
		choices[3] = new Option("Icicle              40MP", "Attack the enemy with an icicle");	
	}
}
