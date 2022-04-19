package com.testgame.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.testgame.demo.entities.EntitiesType;
import com.testgame.demo.entities.Player;
import com.testgame.demo.world.Settings;
import com.testgame.demo.world.TiledGameMap;

public class GameScreen implements Screen {

	
	public TiledGameMap gamemap;
	public TerraRogueDemo game;
	public OrthographicCamera cam;
	private Music bgm;
	private MainMenuScreen menuscreen;
	
	private Option[] choices;
    private Stage stage;
    private Table root;
    private TextField topBox;
    private Table optionsBox;
    private List<Object> options;
    private Skin skin;
    private int playerIndex;
    private Label optionsGreeter;
	
	public GameScreen(TerraRogueDemo game, String spriteSheetFileName, MainMenuScreen mainmenu) {
		this.game = game;
		gamemap = new TiledGameMap(spriteSheetFileName);
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menuscreen = mainmenu;
		cam.update();
		this.createOptions();
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
		skin = new Skin(Gdx.files.internal("TDS.json"));
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() ));
        root = new Table();
        optionsBox = new Table();
        options = new List<Object>(skin);    
        optionsGreeter = new Label("Player Stats: ", skin, "holo-light");
        optionsBox.setBackground(new TextureRegionDrawable(new Texture(Gdx.files.internal("battleoptionsbg.png"))));
        root.setFillParent(true);
        options.setItems(this.getArrayChoices());

        Gdx.input.setInputProcessor(stage);
        root.bottom();

        Cell<Table> optionsCell = root.add(optionsBox);
        optionsBox.left().top();
        optionsBox.add(optionsGreeter).padLeft(40);
        optionsBox.padBottom(20);
        optionsBox.row();
        optionsBox.add(options).padLeft(0);
        options.setSelectedIndex(-1);
        options.setTouchable(null);
        optionsCell.maxHeight(Value.percentHeight(0.1f, root));
        optionsCell.minHeight(Value.percentHeight(0.05f, root));
        optionsCell.prefWidth(Value.percentWidth(0.25f, root));
        stage.addActor(root);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 2f, true);
		bgm.play();
		
		//I can use camera position to set bounds on the map scrolling.
		
		this.gamemap.render(cam, this.game.batch);
		stage.act(delta);
		stage.draw();
		
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
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			game.setScreen(new EndGameScreen(game, false));
		}
		
		
		if(this.gamemap.checkEnemyEngage(gamemap.player)) {
			//System.out.println("Looking at enemy");
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
	private class Option {

        String name;
        String description;

        public Option(String name, String desc) {
            this.name = name;
            this.description = desc;
        }

        public String getName() {
            return name;
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
        choices = new Option[2];
        
        for (int i=0; i < Settings.entities.size(); i++) {
    		if(Settings.entities.get(i).getType().equals(EntitiesType.PLAYER)) {
    			((Player) Settings.entities.get(i)).setHealth(100);
    			playerIndex = i;
    		}
    	}

        choices[0] = new Option( ((Player) Settings.entities.get(playerIndex)).getHealth() + " HP", "Health Points");
        choices[1] = new Option( ((Player) Settings.entities.get(playerIndex)).getMana() + " MP", "Mana Points");

    }

}

