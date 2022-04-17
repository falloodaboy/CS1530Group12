package com.testgame.demo.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item extends Image {

	private ItemType type;
	
	public Item(ItemType type) {
		this.type = type;
		
		if(this.type == type.HEALTH) {
<<<<<<< HEAD
			System.out.println("This is the health type (it is a check)");
=======
			System.out.println("This is the health type");
>>>>>>> 63c77ec (added progress bar and item state)
		}
	}
}
