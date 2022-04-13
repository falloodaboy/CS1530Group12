package com.testgame.demo.world;

public class Settings {

	public static final int TILE_SIZE = 32;
	public static final float SCALE = 2f;	
	public static final float SCALED_TILE_SIZE = SCALE * TILE_SIZE;
	public static final int PLAYER_WIDTH = 30;
	public static final int PLAYER_HEIGHT = 30;
	public enum BattleState { PLAYERCHOOSE, PLAYEREXECUTE, ENEMYEXECUTE; }
}
