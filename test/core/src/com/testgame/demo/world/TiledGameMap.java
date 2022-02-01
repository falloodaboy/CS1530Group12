package com.testgame.demo.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

	
	TiledMap tileMap;
	OrthogonalTiledMapRenderer maprenderer;
	
	
	public TiledGameMap() {
		tileMap = new TmxMapLoader().load("basemap.tmx");
		maprenderer = new OrthogonalTiledMapRenderer(tileMap);
		entities = new ArrayList<>();
		
		entities.add(new Player(320, 240, this));
	}
	
	
	@Override
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		this.update(camera, Gdx.graphics.getDeltaTime());
		maprenderer.setView(camera);
		maprenderer.render();
		batch.begin();
		for(Entity entry : entities) {
			entry.render(camera, batch);
		}
		batch.end();
	}

	@Override
	public void update(OrthographicCamera camera, float deltaTime) {
		for(Entity entry: entities) {
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
		
		for(Entity entry: entities) {
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

}
