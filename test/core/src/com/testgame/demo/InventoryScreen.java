package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class InventoryScreen implements Screen {

	//trying out scene2d.ui to make this inventory screen.
	
	//should we create a new instance of this screen every time the player presses pause?
	//and delete this instance whenever the screen is hidden again?
	
	//
	
	private Stage stage;
	
	
	private Skin skin;
	private Table root; //root table for the inventory screen.
	private Table inventory; //inventory display for character (using DragAndDrop class)
	private Window window;
	private DragAndDrop dnd; //used for Drag and Drop of items in list
	private TextureAtlas atlas;
	private Game game; //Game object which sets the screens
	private OrthographicCamera cam; //Camera looking into the world.
	private GameScreen gamescreen; //game screen to return to after player is done doing inventory stuff.
	private TextureAtlas tdsatlas;
	
	public InventoryScreen(Game game, OrthographicCamera cam, GameScreen gamescreen) {
		this.game = game;
		this.cam = cam;
		this.gamescreen = gamescreen;
		
		
	}
	
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("craftacular-ui.json"));
		atlas = new TextureAtlas(Gdx.files.internal("craftacular-ui.atlas"));
		tdsatlas = new TextureAtlas(Gdx.files.internal("TDS.atlas"));
		inventory = new Table(skin);
		window = new Window("", skin);
		inventory.setWidth(100);
		inventory.setHeight(100);
		inventory.setColor(skin.getColor("gray"));	
		
		for(int i=0; i < 5; i++) {
			for(int j=0; j < 5;j++) {
				Cell<Image> cell = inventory.add(new Image(tdsatlas.findRegion("Inventory Cell")));
				cell.prefHeight(100);
				cell.prefWidth(100);
			}
			inventory.row();
		}
		
		
		root = new Table();
		root.setFillParent(true);
		root.top();
		
		Cell<Label> title = window.add(new Label("Inventory", skin));
		title.padRight(Value.percentWidth(0.5f, window));
		window.row();
		window.add(inventory);
		
		Cell<Window> wind = root.add(window);
		//wind.prefHeight(100);
		//wind.prefWidth(100);
		
		stage.addActor(root);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1, true);
		
		
		stage.draw();
		stage.act(delta);
		
		if(Gdx.input.isKeyJustPressed(Keys.I)) {
			Gdx.input.setInputProcessor(null);
			game.setScreen(gamescreen);
			this.dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		//stage.getViewport().setWorldSize(width, height);
		stage.getViewport().update(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();
		tdsatlas.dispose();
	}

}
