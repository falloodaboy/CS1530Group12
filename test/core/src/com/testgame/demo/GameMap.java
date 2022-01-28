package com.testgame.demo;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {

	public abstract void render(OrthographicCamera camera);
	public abstract void update(float deltaTime);
	
	
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE) , (int) (y / TileType.TILE_SIZE));
	}
	
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	public abstract void dispose();
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
}
