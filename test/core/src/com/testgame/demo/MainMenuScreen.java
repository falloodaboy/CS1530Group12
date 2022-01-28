package com.testgame.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MainMenuScreen implements Screen {

	final TerraRogueDemo game;
	public OrthographicCamera cam;
	public BitmapFont font;
	public Texture background;
	private GlyphLayout layout, layout2;
	String title, message;
	
	public MainMenuScreen(TerraRogueDemo game) {
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.setProjectionMatrix(cam.combined);
		cam.update();
		font = new BitmapFont();
		font.setColor(Color.GRAY);
		background = new Texture("Background.png");
		title = "Welcome to Terra Rogue";
		message = "Click on the Screen to start the game.";
		layout = new GlyphLayout(font, title);
		layout2 = new GlyphLayout(font, message);
		
	}
	
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		
		
		if(Gdx.input.isTouched()) {
			this.game.setScreen(new GameScreen(this.game));
			this.dispose();
		}
		
		game.batch.begin();
		
		//Draw menu here
		game.batch.draw(background, 0, 0, cam.viewportWidth, cam.viewportHeight);
		font.draw(game.batch, layout , (cam.viewportWidth)/2 - layout.width/2, cam.viewportHeight);
		font.draw(game.batch, layout2, cam.viewportWidth/2 - layout2.width/2, cam.viewportHeight - layout.height*2);
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

	}

}
