package com.testgame.demo.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerAnimation {

	private Texture playerTexture;
	private Animation<TextureRegion> walkNorth;
	private Animation<TextureRegion> walkSouth;
	private Animation<TextureRegion> walkEast;
	private Animation<TextureRegion> walkWest;
	
	private Animation<TextureRegion> faceNorth;
	private Animation<TextureRegion> faceSouth;
	private Animation<TextureRegion> faceEast;
	private Animation<TextureRegion> faceWest;
	
	
	private Animation<TextureRegion> currentSprite;
	private float stateTime;
	private Texture spriteSheet;

	
	
	public PlayerAnimation(Texture sheet, int pixelWidth, int pixelHeight) {
		this.spriteSheet = sheet;
		stateTime = 0f;
		playerTexture = sheet;
		
		TextureRegion[][] frames = TextureRegion.split(playerTexture, pixelWidth, pixelHeight);
//		System.out.println(frames.length + " " + frames[0].length);
		
		TextureRegion[] northFrames = new TextureRegion[4];
		TextureRegion[] southFrames = new TextureRegion[4];
		TextureRegion[] eastFrames = new TextureRegion[4];
		TextureRegion[] westFrames = new TextureRegion[4];
		int index = 0;
		
		for(int i=0; i < 4; i++) {
			northFrames[index++] = frames[3][i];
		}
		
		index = 0;
		for(int i=0; i < 4; i++) {
			southFrames[index++] = frames[0][i];
		}
		
		index = 0;
		for(int i=0; i< 4; i++) {
			eastFrames[index++] = frames[1][i];
		}
		
		index = 0;
		for(int i=0; i < 4; i++) {
			westFrames[index++] = frames[2][i];
		}
		
		TextureRegion north = northFrames[0];
		TextureRegion south = southFrames[0];
		TextureRegion east = eastFrames[0];
		TextureRegion west = westFrames[0];
		
		faceNorth = new Animation<TextureRegion>(0.2f, north);
		faceSouth = new Animation<TextureRegion>(0.2f, south);
		faceEast = new Animation<TextureRegion>(0.2f, east);
		faceWest = new Animation<TextureRegion>(0.2f, west);
		
		walkNorth = new Animation<TextureRegion>(0.2f, northFrames);
		walkSouth = new Animation<TextureRegion>(0.2f, southFrames);
		walkEast = new Animation<TextureRegion>(0.2f, eastFrames);
		walkWest = new Animation<TextureRegion>(0.2f, westFrames);
		
		currentSprite = faceSouth;
	}
	
	
	/**
	 * Play South moving animation.
	 */
	public void playSouth() {
		currentSprite = walkSouth;
	}
	
	/**
	 * Play North moving animation.
	 */
	public void playNorth() {
		currentSprite = walkNorth;
	}
	
	/**
	 * Play East moving animation.
	 */
	public void playEast() {
		currentSprite = walkEast;
	}
	
	/**
	 * Play West moving animation.
	 */
	public void playWest() {
		currentSprite = walkWest;
	}
	
	/**
	 * Play South facing animation.
	 */
	public void faceSouth() {
		currentSprite = faceSouth;
	}
	
	/**
	 * Play North facing animation.
	 */
	public void faceNorth() {
		currentSprite = faceNorth;
	}
	
	/**
	 * Play East facing animation.
	 */
	public void faceEast() {
		currentSprite = faceEast;
	}
	
	/**
	 * Play West facing animation.
	 */
	public void faceWest() {
		currentSprite = faceWest;
	}
	
	/**
	 * Render the main animation. There should only be one animation rendered here and the other methods should update that
	 * animation object with the direction they describe. This way, only one animation will be rendered and the other methods
	 * decide which one it will be.
	 */
	public void render(SpriteBatch batch, float x, float y) {
			
			stateTime += Gdx.graphics.getDeltaTime();
			TextureRegion currFrame = currentSprite.getKeyFrame(stateTime, true);
			batch.draw(currFrame, x, y);
	}
	
	
	public void dispose() {
		playerTexture.dispose();
		
	}
}
