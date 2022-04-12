package com.testgame.demo.world;
import java.util.ArrayList;

import com.testgame.demo.entities.Entity;

public class Settings {

	public static final int TILE_SIZE = 32;
	public static final float SCALE = 2f;	
	public static final float SCALED_TILE_SIZE = SCALE * TILE_SIZE;
	public static final int PLAYER_WIDTH = 30;
	public static final int PLAYER_HEIGHT = 30;
	private static float ID = 0;
	
	public static ArrayList<Entity> entities = new ArrayList<>();
	
	
	public static float getNextID() {
		return ID++;
	}
}
