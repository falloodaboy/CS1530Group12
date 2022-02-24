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

public class MainMenuScreen implements Screen {

	final TerraRogueDemo game;
	public OrthographicCamera cam;
	public BitmapFont font;
	public BitmapFont mill;
	public Texture background;
	private GlyphLayout layout, layout2;
	
	private Music bgm;
	
	String title, message;
	
	private final float letterSpawnTime = 0.03f;
	private float timer = 0;

	private String drawTitle = "";
	private String drawMessage = "";
	private int stringIndexTitle = 0;
	private int stringIndexMessage = 0;
	
	public MainMenuScreen(TerraRogueDemo game) {
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.update();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		background = new Texture("Background.png");
		title = "Welcome to Terra Rogue";
		message = "Click on the Screen to start the game.";
		
		
		layout2 = new GlyphLayout(font, message);
		try {
			
			mill = new BitmapFont(Gdx.files.internal("FutureMillennium.fnt"));
			mill.setColor(Color.WHITE);
			layout = new GlyphLayout(mill, title);
			bgm = Gdx.audio.newMusic(Gdx.files.internal("DragonBall-Z Tapion Melody (Royalty Free Anime Music).mp3"));
			bgm.setVolume(0.3f);
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

		
		bgm.play();
		
			if(Gdx.input.isTouched()) {
				this.game.setScreen(new GameScreen(this.game));
				this.dispose();
			}
			
			//Gdx.graphics.getDeltaTime();
			game.batch.begin();
			
			// Test code
			timer += delta;
			
			// Render title
			if (timer >= letterSpawnTime && stringIndexTitle != title.length()) {
				drawTitle = drawTitle + title.charAt(stringIndexTitle);
				stringIndexTitle++;
				timer -= letterSpawnTime;        
			}
			
			// Render message
			if (timer >= letterSpawnTime && stringIndexMessage != message.length()) {
				drawMessage = drawMessage + message.charAt(stringIndexMessage);
				stringIndexMessage++;
				timer -= letterSpawnTime;        
			}	

		
			//Draw menu here
			game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		background.dispose();
		bgm.dispose();
	}

}
