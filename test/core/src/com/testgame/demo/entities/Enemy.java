package com.testgame.demo.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.testgame.demo.world.GameMap;
import com.testgame.demo.world.TiledGameMap;

public class Enemy extends Entity {

	
	private Texture image;
	private double points;
	private EnemyAnimation eanim;
	public int facing;
	
	public Enemy(float x, float y, EntitiesType type, TiledGameMap map, Texture image, int facing) {
		super(x, y, type, map);
		points = 100;
		this.image = image;
		this.facing = facing;
		
	}

	@Override
	public void render(OrthographicCamera cam, SpriteBatch batch) {
		Vector3 enmcoords = cam.project(new Vector3(getX(), getY(), 0));
		batch.draw(image, enmcoords.x, enmcoords.y);
	}

	@Override
	public void dispose() {
		image.dispose();
		//eanim.dispose();
		
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
