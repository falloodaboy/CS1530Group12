package com.testgame.demo.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgame.demo.entities.Entity;
public abstract class GameMap {

	public abstract void render(OrthographicCamera camera, SpriteBatch batch);
	public abstract void update(OrthographicCamera camera, float deltaTime);
	public List<Entity> entities;
	
	
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE) , (int) (y / TileType.TILE_SIZE));
	}
	
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	public abstract void dispose();
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
	public float getPixelWidth() {
		return this.getWidth() * TileType.TILE_SIZE;
	}
	
	public float getPixelHeight() {
		return this.getHeight() * TileType.TILE_SIZE;
	}
	
	public boolean isCollidingWithMap(float x, float y, int width, int height) {
		boolean answer = false;
		
		if(x < 0 || x + width > getPixelWidth() || y < 0 || y + height > getPixelHeight()) {
			answer = true;
		}
		
		for(int row = (int) y / TileType.TILE_SIZE; row  < Math.ceil(y + height) / TileType.TILE_SIZE; row++) {
			for(int col = (int) x / TileType.TILE_SIZE; col  < Math.ceil(x + width) / TileType.TILE_SIZE; col++) {
				for(int layer = 0; layer < getLayers(); layer++) {
					TileType type = this.getTileTypeByCoordinate(layer, col, row);
					
					
					if(type != null && type.isCollidable()) {
						answer = true;
					}
				}
			}
		}
		
		return answer;
	}
}
