package com.testgame.demo.entities;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation {

	
	public TextureAtlas patlas;
	public Animation<TextureRegion> walkNorth;
	public Animation<TextureRegion> walkSouth;
	public Animation<TextureRegion> walkEast;
	public Animation<TextureRegion> walkWest;
	
	private Texture spriteSheet;

	
	
	public PlayerAnimation(Texture sheet) {
		this.spriteSheet = sheet;
		
		
	}
	
	
	public void playSouth() {
		
	}
	
	public void playNorth() {
		
	}
	
	public void playEast() {
		
	}
	
	public void playWest() {
		
	}
}
