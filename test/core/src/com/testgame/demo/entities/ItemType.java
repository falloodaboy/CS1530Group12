package com.testgame.demo.entities;

public enum ItemType {
	HEALTH(10), //increases the health of the user by 10
	POWER(20), //increases the power of the user by 20
	SPEED(5); // increases the speed of the user by 5
	

	private float val;
	
	public float getVal() {
		return val;
	}
	
	private ItemType(float val) {
		this.val = val;
	}
}
