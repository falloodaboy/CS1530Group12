package com.testgame.demo.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item extends Image {

	private ItemType type;
	
	public Item(ItemType type) {
		this.type = type;
		
		if(this.type == type.HEALTH) {
			System.out.println("This is the health type.");
		}
	}
}
