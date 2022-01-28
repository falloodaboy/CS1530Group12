package com.testgame.demo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap{

	
	TiledMap tileMap;
	OrthogonalTiledMapRenderer maprenderer;
	
	
	public TiledGameMap() {
		tileMap = new TmxMapLoader().load("basemap.tmx");
		maprenderer = new OrthogonalTiledMapRenderer(tileMap);
	}
	
	
	@Override
	public void render(OrthographicCamera camera) {
		maprenderer.setView(camera);
		maprenderer.render();
	}

	@Override
	public void update(float deltaTime) {
		
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
