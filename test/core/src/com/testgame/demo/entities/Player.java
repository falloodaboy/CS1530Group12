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
		image = new Texture("");
	}

	@Override
	public void render(OrthographicCamera cam, SpriteBatch batch) {
		this.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(image, this.getX(), this.getY(), image.getWidth(), image.getHeight());
		batch.end();
		cam.translate(this.getX(), this.getY());
		cam.update();
	}

	
	
	@Override
	public void dispose() {
		image.dispose();
	}

	@Override
	public void update(float deltaTime) {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.moveX(-EntitiesType.PLAYER.getSpeed() * deltaTime);
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.moveX(EntitiesType.PLAYER.getSpeed()* deltaTime);
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			this.moveY(EntitiesType.PLAYER.getSpeed()* deltaTime);
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.moveY(-EntitiesType.PLAYER.getSpeed()* deltaTime);
		}
		else {
			throw new RuntimeException("Player.java: update: Wrong input detected");
		}

	}

}
