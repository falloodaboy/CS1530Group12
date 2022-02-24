package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TerraRogueDemo extends Game{

	public SpriteBatch batch;
	
	
	public TerraRogueDemo() {
		
	}
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
