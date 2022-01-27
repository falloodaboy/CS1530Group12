package com.group12.terrarogue.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
		return null;
	}

	@Override
	public void dispose() {
		tileMap.dispose();
		maprenderer.dispose();
	}

	@Override
	public int getWidth() {
		return (int) maprenderer.getViewBounds().width;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getLayers() {
		return 0;
	}

}
