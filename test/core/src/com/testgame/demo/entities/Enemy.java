package com.testgame.demo.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgame.demo.world.GameMap;
import com.testgame.demo.world.TiledGameMap;

public class Enemy extends Entity {

	
	private Texture image;
	private double points;
	private EnemyAnimation eanim;
	
	public Enemy(float x, float y, EntitiesType type, TiledGameMap map, Texture image) {
		super(x, y, type, map);
		points = 0;
	}

	@Override
	public void render(OrthographicCamera cam, SpriteBatch batch) {
		batch.draw(image, getX(), getY());
	}

	@Override
	public void dispose() {
		image.dispose();
		eanim.dispose();
		
	}

	@Override
	public void update(OrthographicCamera cam, float deltaTime) {
		//define the movements of the enemy.
		
	}

	@Override
	public void moveX(float amount, int direction) {
		
		
	}

	@Override
	public void moveY(float amount, int direction) {
		
		
	}

	
	
	
}
