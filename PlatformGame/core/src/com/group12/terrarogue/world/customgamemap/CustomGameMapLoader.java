package com.group12.terrarogue.world.customgamemap;


import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.group12.terrarogue.world.TileType;

public class CustomGameMapLoader {
	
	private static Json json = new Json();
	private static final int SIZE = 100;
	private static Random random = new Random();
	
	
	public static CustomGameMapData loadMap(String id, String name) {
		Gdx.files.local("maps/").file().mkdirs();
		FileHandle file = Gdx.files.local("maps/" + id + " .map");
		
		if(file.exists()) {
			CustomGameMapData data = json.fromJson(CustomGameMapData.class, file.readString());
			return data;
		}
		else {
			CustomGameMapData data =  generateMapData(id, name);
			saveMap(data.id, data.name, data.map);
			return data;
		}
	}
	
	public static void saveMap(String id, String name, int[][][] map) {
		CustomGameMapData data = new CustomGameMapData();
		data.id = id;
		data.name = name;
		data.map = map;
		
		Gdx.files.local("maps/").file().mkdirs();
		FileHandle file = Gdx.files.local("maps/" + id + " .map");
		file.writeString(json.prettyPrint(data), false);
	}
	
	public static CustomGameMapData generateMapData(String id, String name) {
		CustomGameMapData data = new CustomGameMapData();
		data.id = id;
		data.name = name;
		data.map = new int[2][SIZE][SIZE];
		
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				data.map[0][row][col] = TileType.SKY.getId();
				
				if(row > SIZE - 3 - 1) {
					data.map[1][row][col] = TileType.LAVA.getId();
				}
				else if(row > SIZE - 20) {
					data.map[1][row][col] = TileType.STONE.getId();
				}
				else if(row > SIZE - 25) {
					data.map[1][row][col] = TileType.DIRT.getId();
				}
				else if(row > SIZE - 26) {
					data.map[1][row][col] = TileType.GRASS.getId();
				}
				else {
					if(random.nextInt(50) == 0) {
						data.map[1][row][col] = TileType.CLOUD.getId();
					}
				}
			}
		}
		
		return data;
	}

}
