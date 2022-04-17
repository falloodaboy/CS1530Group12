package com.testgame.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;




public class MainMenuScreen implements Screen {

	final TerraRogueDemo game;
	public OrthographicCamera cam;
	public BitmapFont font;
	public BitmapFont mill;
	public Texture background;
	private GlyphLayout layout, layout2;
	
	private Music bgm, textSound, selectSound;
	
	String title, message;
	
	private final float letterSpawnTime = 0.03f;
	private float timer = 0;

	private String drawTitle = "";
	private String drawMessage = "";
	private int stringIndexTitle = 0;
	private int stringIndexMessage = 0;
	private Skin skin;
	private Stage stage;
	private TextureRegion regionBackground;
	private TextButton newGame, loadGame;
	private Table root;
	private Texture backgroundTexture;
	
	public MainMenuScreen(TerraRogueDemo game) {
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.update();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		// background = new Texture("Background.png");
		title = "Welcome to Terra Rogue";
		message = "Click on the Screen to start the game.";
		
		
		layout2 = new GlyphLayout(font, message);
		try {
			
			mill = new BitmapFont(Gdx.files.internal("FutureMillennium.fnt"));
			mill.setColor(Color.WHITE);
			layout = new GlyphLayout(mill, title);
			bgm = Gdx.audio.newMusic(Gdx.files.internal("deathnote.mp3"));
			bgm.setVolume(0.1f);
			bgm.setLooping(true);
			
			textSound = Gdx.audio.newMusic(Gdx.files.internal("textSound.wav"));
			textSound.setVolume(0.8f);
			
			selectSound = Gdx.audio.newMusic(Gdx.files.internal("selectSound.mp3"));
			selectSound.setVolume(0.4f);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public void show() {
		// Main menu buttons
		skin = new Skin(Gdx.files.internal("TDS.json"));
		root = new Table(skin);
		// TODO: double check this
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		newGame = new TextButton("New Game", skin);
		backgroundTexture = new Texture("Background.png");
		backgroundTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		regionBackground = new TextureRegion(backgroundTexture);
		regionBackground.setRegion(0, 0, backgroundTexture.getWidth(), backgroundTexture.getHeight());
		loadGame = new TextButton("Load Game", skin);
		
		newGame.pad(0, 50, 0, 50);
		loadGame.pad(0, 43, 0, 43);
		newGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				
				//insert code
				//game.setScreen(new GameScreen(game));
				game.setScreen(new CharacterCreationScreen(game));
				dispose();
				textSound.dispose();
				selectSound.play();
				
				return answer;
			}
		});
		
		// TODO: Game load functionality
		newGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				//loadGame();
				
				return answer;
			}
		});
		
		
		
		Gdx.input.setInputProcessor(stage);
		root.center();
		root.setFillParent(true);
		
		root.add(newGame);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.add(loadGame);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.setBackground(new TextureRegionDrawable(regionBackground));
		stage.addActor(root);
	}

	@Override
	public void render(float delta) {

		
		bgm.play();
		stage.draw();
		stage.act(delta);
			
		game.batch.begin();
			
		timer += delta;
			
			// Render title
		if (timer >= letterSpawnTime && stringIndexTitle != title.length()) {
			drawTitle = drawTitle + title.charAt(stringIndexTitle);
			stringIndexTitle++;
			timer -= letterSpawnTime;        
			textSound.play();
		} else if(stringIndexTitle == title.length()) {
				textSound.stop();
		}
			
		// Render message
		if (timer >= letterSpawnTime && stringIndexMessage != message.length()) {
			drawMessage = drawMessage + message.charAt(stringIndexMessage);
			stringIndexMessage++;
			timer -= letterSpawnTime;   
			textSound.play();
		} else if (stringIndexMessage == message.length()) {
			textSound.stop();
		}
		
	

	
		//Draw menu here
		mill.draw(game.batch, drawTitle, (cam.viewportWidth)/2 - layout.width/2, cam.viewportHeight - 50);
		font.draw(game.batch, drawMessage, cam.viewportWidth/2 - layout2.width/2, cam.viewportHeight - layout.height*2 - 50);
		
		game.batch.end();
			
		
		

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
		font.dispose();
		mill.dispose();
		bgm.dispose();
		textSound.dispose();
		selectSound.dispose();
		skin.dispose();
		stage.dispose();
	}

}
