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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



public class CharacterCreationScreen implements Screen{

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
	private TextButton newGame;
	private Table root;
	private Texture backgroundTexture;
	private MainMenuScreen menuscreen;
	
	public CharacterCreationScreen(TerraRogueDemo game, MainMenuScreen menu) {
		this.game = game;
		cam = new OrthographicCamera();
		menuscreen = menu;
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.update();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		// background = new Texture("Background.png");
		title = "Character Selection";
		message = "Select your character to begin the game!";
		
		
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
		newGame = new TextButton("Start Game", skin);
		backgroundTexture = new Texture("Background.png");
		backgroundTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		regionBackground = new TextureRegion(backgroundTexture);
		regionBackground.setRegion(0, 0, backgroundTexture.getWidth(), backgroundTexture.getHeight());

		TextButton nateSpriteButton = new TextButton("Nate", skin);
		TextButton pyrrhaSpriteButton = new TextButton("Pyrrha", skin);
		TextButton brendanSpriteButton = new TextButton("Brendan", skin);
		TextButton charlaSpriteButton = new TextButton("Charla", skin);
		TextButton julianSpriteButton = new TextButton("Julian", skin);
		
		nateSpriteButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				String spriteSheetFileName = "nateSpriteSheet.png";
			
				game.setScreen(new GameScreen(game, spriteSheetFileName, menuscreen));
				dispose();
				textSound.dispose();
				selectSound.play();
				
				return answer;
			}
		});
		pyrrhaSpriteButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				String spriteSheetFileName = "pyrrhaSpriteSheet.png";
			
				game.setScreen(new GameScreen(game, spriteSheetFileName, menuscreen));
				dispose();
				textSound.dispose();
				selectSound.play();
				
				return answer;
			}
		});
		brendanSpriteButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				String spriteSheetFileName = "brendanSpriteSheet.png";
				
				game.setScreen(new GameScreen(game, spriteSheetFileName, menuscreen));
				dispose();
				textSound.dispose();
				selectSound.play();
				
				return answer;
			}
		});
		charlaSpriteButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				String spriteSheetFileName = "charlaSpriteSheet.png";
			
				game.setScreen(new GameScreen(game, spriteSheetFileName, menuscreen));
				dispose();
				textSound.dispose();
				selectSound.play();
				
				return answer;
			}
		});
		julianSpriteButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				boolean answer = true;
				String spriteSheetFileName = "julianSpriteSheet.png";
			
				game.setScreen(new GameScreen(game, spriteSheetFileName, menuscreen));
				dispose();
				textSound.dispose();
				selectSound.play();
				
				return answer;
			}
		});
		

		Gdx.input.setInputProcessor(stage);
		root.center();
		root.setFillParent(true);

		root.center().add(nateSpriteButton);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.center().add(pyrrhaSpriteButton);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.center().add(brendanSpriteButton);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.center().add(charlaSpriteButton);
		root.row().pad(Value.percentWidth(0.02f),Value.percentWidth(0),Value.percentWidth(0.02f), Value.percentWidth(0));
		root.center().add(julianSpriteButton);

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
