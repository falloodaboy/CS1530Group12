package com.testgame.demo.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.testgame.demo.world.GameMap;
import com.testgame.demo.world.TileType;
import com.testgame.demo.world.TiledGameMap;

public abstract class Entity {

	
	protected Vector2 pos;
	protected EntitiesType type;
	protected TiledGameMap map;
	
	
	public Entity(float x, float y, EntitiesType type, TiledGameMap map) {
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
	
	public TiledGameMap getMap() {
		return map;
	}
	
	public void setMap(TiledGameMap map) {
		this.map = map;
	}
	
	public EntitiesType getType() {
		return type;
	}
	
	public void setType(EntitiesType type) {
		this.type = type;
	}
	
	//Do collision detection for x-axis here
	public abstract void moveX (float amount, int direction);
	
	
	
	//Do collision detection for y-axis here
	public abstract void moveY (float amount, int direction);
}

//!map.isCollidingWithMap(pos.x, newY,TileType.TILE_SIZE, TileType.TILE_SIZE)
