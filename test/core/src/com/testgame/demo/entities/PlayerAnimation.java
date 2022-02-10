package com.testgame.demo.entities;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation {

	
	private TextureAtlas patlas;
	private Animation<TextureRegion> walkNorth;
	private Animation<TextureRegion> walkSouth;
	private Animation<TextureRegion> walkEast;
	private Animation<TextureRegion> walkWest;
	
	private Animation<TextureRegion> currentSprite;
	private int stateTime;
	private Texture spriteSheet;

	
	
	public PlayerAnimation(Texture sheet) {
		this.spriteSheet = sheet;
		stateTime = 0;
		
	}
	
	
	public void playSouth() {
		
	}
	
	public void playNorth() {
		
	}
	
	public void playEast() {
		
	}
	
	public void playWest() {
		
	}
	
	public void faceSouth() {
		
	}
	
	public void faceNorth() {
		
	}
	
	public void faceEast() {
		
	}
	
	public void faceWest() {
		
	}
	
	public void render(SpriteBatch batch) {
		
	}
}
