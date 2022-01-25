package com.group12.terrarogue.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.group12.terrarogue.world.customgamemap.CustomGameMapData;
import com.group12.terrarogue.world.customgamemap.CustomGameMapLoader;

public class CustomGameMap extends GameMap {

	
	String id;
	String name;
	int[][][] map;
	
	private SpriteBatch sb;
	private TextureRegion[][] tiles;
	
	public CustomGameMap() {
		CustomGameMapData data = CustomGameMapLoader.loadMap("basic", "My Grass Lands");
		this.id = data.id;
		this.name = data.name;
		this.map = data.map;
		
		sb = new SpriteBatch();
		tiles = TextureRegion.split(new Texture("tiles.png"), TileType.TILE_SIZE, TileType.TILE_SIZE);
	}
	
	
	
	
	
	
	@Override
	public void render(OrthographicCamera camera) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		
		for(int layer = 0; layer < getLayers(); layer++) {
			for(int row = 0; row < getHeight(); row++) {
				for(int col = 0; col < getWidth(); col++) {
					TileType type = this.getTileTypeByCoordinate(layer, col, row);
					
					if(type != null) {
						sb.draw(tiles[0][type.getId()], col * TileType.TILE_SIZE, row * TileType.TILE_SIZE);
					}
				}	
			}
		}
		
		sb.end();
	}

	@Override
	public void update(float deltaTime) {
		
		
	}

	@Override
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		// TODO Auto-generated method stub
		return getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), getHeight() - (int) (y / TileType.TILE_SIZE) - 1);
	}
	
	@Override
	public TileType getTileTypeByCoordinate(int layer, int col, int row) {
		if(col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
			return null;
		}
		return TileType.getTileTypeByID(map[layer][getHeight() - row - 1][col]);
	}

	@Override
	public void dispose() {
		sb.dispose();
	}

	@Override
	public int getWidth() {
		return map[0][0].length;
	}

	@Override
	public int getHeight() {
		return map[0].length;
	}

	@Override
	public int getLayers() {
		return map.length;
	}

}
