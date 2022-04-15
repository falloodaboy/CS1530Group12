package com.testgame.demo.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.testgame.demo.entities.Entity;
import com.testgame.demo.entities.Player;

public class TiledGameMap extends GameMap {

	
	private TiledMap tileMap;
	private TiledMapTileLayer collisionLayer;
	private OrthogonalTiledMapRenderer maprenderer;
	private Texture background;
	
	public TiledGameMap(String spriteSheetFileName) {
		tileMap = new TmxMapLoader().load("basemap.tmx");
		maprenderer = new OrthogonalTiledMapRenderer(tileMap);
		background = new Texture("Background.png");
		
		
		
		Settings.entities.add(new Player(-10, 0, this, spriteSheetFileName));

	}
	
	
	@Override
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		this.update(camera, Gdx.graphics.getDeltaTime());
		maprenderer.setView(camera);
		maprenderer.render();
		batch.begin();
		for(Entity entry : Settings.entities) {
			entry.render(camera, batch);
		}
		batch.end();
	}

	@Override
	public void update(OrthographicCamera camera, float deltaTime) {
		for(Entity entry: Settings.entities) {
			entry.update(camera, deltaTime);
		}
	}

	@Override
	public TileType getTileTypeByCoordinate(int layer, int col, int row) {
		Cell cell = ((TiledMapTileLayer)tileMap.getLayers().get(layer)).getCell(col, row);
		int id = -1;
		if(cell != null) {
			TiledMapTile tile = cell.getTile();
			
			if(tile != null) {
				id = tile.getId();
			}
		}
		
		return TileType.getTileTypeByID(id);
	}

	@Override
	public void dispose() {
		tileMap.dispose();
		maprenderer.dispose();
		background.dispose();
		for(Entity entry: Settings.entities) {
			entry.dispose();
		}
	}

	@Override
	public int getWidth() {
		return  ((TiledMapTileLayer) tileMap.getLayers().get(0)).getWidth();
	}

	@Override
	public int getHeight() {
		return  ((TiledMapTileLayer) tileMap.getLayers().get(0)).getHeight();
	}

	@Override
	public int getLayers() {
		return tileMap.getLayers().getCount();
	}
	
	
	
	/**
	 * to get the center of the square, take the current coordinates, add tilesize/2 to x, add tilesize/2 to y
	 */
	
	public boolean isCollidingWithMap(float x, float y, int layer, int direction) {
		boolean answer = false;
		
		x += Settings.TILE_SIZE/2;
		
		collisionLayer = (TiledMapTileLayer) tileMap.getLayers().get(layer);
		int xval = ( ((int)x + Gdx.graphics.getWidth()/2) / Settings.TILE_SIZE);
		int yval = ( ((int)y + Gdx.graphics.getHeight()/2) / Settings.TILE_SIZE);
		int xup = ((int) x + Settings.PLAYER_WIDTH + Gdx.graphics.getWidth()/2) / Settings.TILE_SIZE;
		int yup = ( ((int)y + Settings.PLAYER_HEIGHT + Gdx.graphics.getHeight()/2) / Settings.TILE_SIZE);
		
		Corner leftdown = new Corner(xval, yval);
		Corner leftup = new Corner(xval, yup);
		Corner rightdown = new Corner(xup, yval);
		Corner rightup = new Corner(xup, yup);
		
		switch(direction) {
		case 0: //up direction
			if(collisionLayer.getCell(leftup.x, leftup.y) != null || collisionLayer.getCell(rightup.x, rightup.y) != null) {
				answer = true;
			}
			else
				answer = false;
		break;

		case 1: //right direction
			if(collisionLayer.getCell(rightdown.x, rightdown.y) != null || collisionLayer.getCell(rightup.x, rightup.y) != null) {
				answer = true;
			}
			else
				answer = false;
		break;
		case 2: //down direction
			if(collisionLayer.getCell(leftdown.x, leftdown.y) != null || collisionLayer.getCell(rightdown.x, rightdown.y) != null) {
				answer = true;
			}
			else
				answer = false;
			
		break;
		case 3: //left direction
			if(collisionLayer.getCell(leftdown.x, leftdown.y) != null || collisionLayer.getCell(leftup.x, leftup.y) != null) {
				answer = true;
			}
			else
				answer = false;
		break;
		}	
		
		return answer;
	}
	
	
	public void saveGame() {
		
	}
	
	
	private class Corner {
		
		public int x;
		public int y;
		
		public Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
