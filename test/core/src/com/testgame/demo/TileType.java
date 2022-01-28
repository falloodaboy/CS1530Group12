package com.testgame.demo;

import java.util.HashMap;

public enum TileType {

	/**
	 * Create an enum for each tile in the tileset being used in the "1 Tiles" folder.
	 */
	
	GRASS(0, true, "grass"),
	DIRT(1, true, "dirt"),
	SKY(2, false, "sky"),
	LAVA(3, true, "lava"),
	CLOUD(4, true, "cloud"),
	STONE(5, true, "stone");
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	private static HashMap<Integer, TileType> tilemap = new HashMap<>();
	
	static {
		for(TileType tile : TileType.values()) {
			tilemap.put(tile.id, tile);
		}
	}
	
	public static TileType getTileTypeByID(int id) {
		if(id >= 0)
			return tilemap.get(id);
		else
			return null;
	}
	

	public static final int TILE_SIZE = 16;
	private int id;
	private float damage;
	private String name;
	private boolean collidable;
	
	
	private TileType(int id, boolean collidable, String name) {
		this(id, collidable, name, 0);
	}
	
	private TileType(int id, boolean collidable, String name, float damage) {
		this.id = id;
		this.damage = damage;
		this.name = name;
		this.collidable = collidable;
	}
}
