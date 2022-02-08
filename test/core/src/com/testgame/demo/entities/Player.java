package com.testgame.demo.entities;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.testgame.demo.world.GameMap;
import com.testgame.demo.world.Settings;
import com.testgame.demo.world.TiledGameMap;

public class Player extends Entity {

	private Texture image;
	private Texture spritesheet;
	private PlayerAnimation panim;
	
	public Player(float x, float y, TiledGameMap map) {
		super(x, y, EntitiesType.PLAYER, map);
		image = new Texture("player.png");
		panim = new PlayerAnimation(spritesheet);
		
	}

	@Override
	public void render(OrthographicCamera cam, SpriteBatch batch) {
		Vector3 playercoords = cam.project(new Vector3(this.getX(), this.getY(), 0));
		
		
		batch.draw(image, playercoords.x, playercoords.y, Settings.TILE_SIZE, Settings.TILE_SIZE);
		this.update(cam, Gdx.graphics.getDeltaTime());
		//cam.zoom = Settings.SCALE/2;
		Vector3 position = cam.position;
		position.x = getX() + (map.getPixelWidth()/2);
		position.y = getY() + (map.getPixelHeight()/2);
		//cam.position.lerp(position, 0.3f);
		cam.position.set(position);
		cam.update();

	}

	
	
	@Override
	public void dispose() {
		image.dispose();
	}

	@Override
	public void update(OrthographicCamera cam, float deltaTime) {
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.moveX(-EntitiesType.PLAYER.getSpeed());
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.moveX(EntitiesType.PLAYER.getSpeed());
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			this.moveY(EntitiesType.PLAYER.getSpeed());
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.moveY(-EntitiesType.PLAYER.getSpeed());
		}
		else {
			//reset the player anim to whichever direction is necessary
			//System.out.println("Player.java: update: Wrong input detected");
		}
	}

}



//System.out.println(cam.viewportWidth + " " + cam.viewportHeight);
//System.out.println(map.getPixelWidth() + " " + map.getHeight());

//System.out.println(map.getPixelWidth());
//System.out.println(worldScreenRatio);


//System.out.println("CameraX: " + cam.position.x + " CameraY: " + cam.position.y);
//System.out.println("PlayerX: " + getX() + " PlayerY: " + getY());



//Vector3 position = cam.position;
//Vector2 target = new Vector2(getX(), getY());
//
//position.x = cam.position.x + (target.x - cam.position.x) * 0.1f;
//position.y = cam.position.y + (target.y - cam.position.y) * 0.1f;
//cam.position.set(position);