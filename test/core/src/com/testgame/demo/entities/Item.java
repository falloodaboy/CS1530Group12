package com.testgame.demo.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item extends Image {

	private ItemType type;
	
	public Item(ItemType type) {
		this.type = type;
	}
}
