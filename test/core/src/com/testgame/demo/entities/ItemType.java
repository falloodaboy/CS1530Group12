package com.testgame.demo.entities;

public enum ItemType {
	HEALTH(10f), //increases the health of the user by 10
	POWER(20f), //increases the power of the user by 20
	SPEED(5f); // increases the speed of the user by 5
	

	private float val;
	
	private ItemType(float val) {
		this.val = val;
	}
}
