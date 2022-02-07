package com.testgame.demo.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.testgame.demo.world.GameMap;
import com.testgame.demo.world.TileType;

public abstract class Entity {

	
	protected Vector2 pos;
	protected EntitiesType type;
	protected GameMap map;
	
	
	public Entity(float x, float y, EntitiesType type, GameMap map) {
		this.pos = new Vector2(x, y);
		this.type = type;
		this.map = map;
	}
	
	public abstract void render(OrthographicCamera cam, SpriteBatch batch);
	public abstract void dispose();
	public abstract void update(OrthographicCamera cam, float deltaTime);
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public void setX(float x) {
		pos.x = x;
	}
	
	public void setY(float y) {
		pos.y = y;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public void setMap(GameMap map) {
		this.map = map;
	}
	
	public EntitiesType getType() {
		return type;
	}
	
	public void setType(EntitiesType type) {
		this.type = type;
	}
	
	//Do collision detection for x-axis here
	public void moveX (float amount) {
		//add new x position
		float newX = pos.x + amount;
//		pos.x = newX;
		if(true) {
			pos.x = newX;
		}
		else {
			//System.out.println("Colliding with map objects");
		}
	}
	
	
	
	//Do collision detection for y-axis here
	public void moveY (float amount) {
		float newY = pos.y + amount;
//		pos.y = newY;
		if(true) {
			pos.y = newY;
		}
		else {
			//System.out.println("Colliding with map objects");
		}
	}
}

//!map.isCollidingWithMap(pos.x, newY,TileType.TILE_SIZE, TileType.TILE_SIZE)
