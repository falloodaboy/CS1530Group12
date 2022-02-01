package com.testgame.demo.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgame.demo.world.GameMap;
import com.testgame.demo.world.TileType;

public class Player extends Entity {

	Texture image;
	
	public Player(float x, float y, GameMap map) {
		super(x, y, EntitiesType.PLAYER, map);
		image = new Texture("player.png");
	}

	@Override
	public void render(OrthographicCamera cam, SpriteBatch batch) {
		//cam.position.set(getX() + image.getWidth()/2, getY() + image.getHeight() / 2, 0);
		//batch.begin();
		batch.draw(image, this.getX(), this.getY(), 32, 32);
		//batch.end();
		this.update(cam, Gdx.graphics.getDeltaTime());
		
	}

	
	
	@Override
	public void dispose() {
		image.dispose();
	}

	@Override
	public void update(OrthographicCamera cam, float deltaTime) {
		//System.out.println(cam.position);
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			float xPrev = getX();
			float yPrev = getY();
			this.moveX(-EntitiesType.PLAYER.getSpeed());
			//System.out.println("X: " + pos.x  + " Y: " + pos.y);
			cam.translate((getX() - xPrev) - 3, (getY() - yPrev));

		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			float xPrev = getX();
			float yPrev = getY();
			this.moveX(EntitiesType.PLAYER.getSpeed());
			//System.out.println("X: " + pos.x  + " Y: " + pos.y);
			cam.translate((getX() - xPrev) + 3, (getY() - yPrev));
			
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			float xPrev = getX();
			float yPrev = getY();
			this.moveY(EntitiesType.PLAYER.getSpeed());
			//System.out.println("X: " + pos.x  + " Y: " + pos.y);
			cam.translate(getX() - xPrev, getY() - yPrev + 3);
			
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			float xPrev = getX();
			float yPrev = getY();
			this.moveY(-EntitiesType.PLAYER.getSpeed());
			//System.out.println("X: " + pos.x  + " Y: " + pos.y);
			cam.translate(getX() - xPrev, getY() - yPrev - 3);
		}
		else {
			//System.out.println("Player.java: update: Wrong input detected");
			//throw new RuntimeException("Player.java: update: Wrong input detected");
		}

	}

}
