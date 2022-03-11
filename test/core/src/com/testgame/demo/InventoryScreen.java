package com.testgame.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class InventoryScreen implements Screen {

	//trying out scene2d.ui to make this inventory screen.
	
	//should we create a new instance of this screen every time the player presses pause?
	//and delete this instance whenever the screen is hidden again?
	
	//could set some fields with the difference in height/width and resize the cells in the inventory accordingly whenever resize occurs
	
	
	private Stage stage;
	private float base = 0.07f;
	
	private Skin skin;
	//private Table root; //root table for the inventory screen.
	private Table inventory; //inventory display for character (using DragAndDrop class)
	private Window window;
	private DragAndDrop dnd; //used for Drag and Drop of items in list
	private Game game; //Game object which sets the screens
	private OrthographicCamera cam; //Camera looking into the world.
	private GameScreen gamescreen; //game screen to return to after player is done doing inventory stuff.
	private TextureAtlas tdsatlas;
	private Stack[][] cells;
	private Music inventorySound;
	

	public InventoryScreen(Game game, OrthographicCamera cam, GameScreen gamescreen) {
		this.game = game;
		this.cam = cam;
		this.gamescreen = gamescreen;
		cells =  new Stack[5][10];
		dnd = new DragAndDrop();
		try {
			inventorySound = Gdx.audio.newMusic(Gdx.files.internal("inventoryOpen.mp3"));
			inventorySound.setVolume(0.4f);
		} catch(Exception e) {
			System.out.print(e);
		}
	}
	
	
	@Override
	public void show() {
		inventorySound.play();
		
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("TDS.json"));
		tdsatlas = new TextureAtlas(Gdx.files.internal("TDS.atlas"));
		inventory = new Table(skin);
		window = new Window("", skin);
		inventory.setColor(skin.getColor("gray"));	
		window.setFillParent(true);
		
		for(int i=0; i < 5; i++) {
			for(int j=0; j < 10;j++) {
				Stack stack = new Stack();
				
				final Image imger = new Image(tdsatlas.findRegion("Inventory Cell"));
				final Image item = new Image(tdsatlas.findRegion("potion"));
				stack.add(imger);
				
				
				if(j == 1)
					stack.add(item);
				
				final Cell<Stack> cell = inventory.add(stack);
				cell.prefHeight(base*Gdx.graphics.getWidth());
				cell.prefWidth(base*Gdx.graphics.getWidth());
				cell.maxWidth(100);
				cell.maxHeight(100);
				cells[i][j] = cell.getActor(); //get reference to the inventory stack object for drag and drop later.
			}
			inventory.row();
		}
		
		
		for(int i=0; i < cells.length; i++) {
			for(int j=0; j < cells[i].length; j++) {
				final Stack stack = cells[i][j];
				dnd.addSource(new Source(stack) {
					
					final Payload payload = new Payload();
					@Override
					public Payload dragStart(InputEvent event, float x, float y, int pointer) {
						
						int top = stack.getChildren().size - 1;
						if(top > 0) {
							payload.setObject(stack.getChild(top));
							payload.setDragActor(stack.getChild(top));
							stack.getChild(top).remove();
						}
						return payload;
//						payload.setObject(stack.getChild(top));
//						payload.setDragActor(stack.getChild(top));
//						stack.getChild(top).remove();
//						return payload;
					}
					
					@Override
					public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
						if(target == null && payload.getObject() != null) {
							stack.add((Actor) payload.getObject());
						}
					}
				});
				
				dnd.addTarget(new Target(stack) {

					@Override
					public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
						return !(stack.getChildren().size >= 2);
					}

					@Override
					public void drop(Source source, Payload payload, float x, float y, int pointer) {
						if(stack.getChildren().size < 2 && payload.getObject() != null)
							stack.add((Actor) payload.getObject());
					}
					
				});
			}
		}

		
		Cell<Label> title = window.add(new Label("Inventory", skin)).left();
		window.row();
		Cell<Table> inv = window.add(inventory);
		//window.top();
		stage.addActor(window);
	
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
			inventorySound.play();
		}
	}

	@Override
	public void resize(int width, int height) {
		//stage.getViewport().setWorldSize(width, height);
		stage.getViewport().update(width, height, true);
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
		tdsatlas.dispose();
		inventorySound.dispose();
	}

}
