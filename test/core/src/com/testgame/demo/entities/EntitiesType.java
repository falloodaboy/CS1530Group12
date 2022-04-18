package com.testgame.demo.entities;


/** 
 * @author zohai
 * EntitiesType where all entities in the game can be defined here. Each entity has 3 stats which are
 * strength, speed, and stamina. Each stat has a range of 0-100 which determines how tough a fight is.
 * Entities are defined as Strength, Stamina, Speed in their constructor.
 */
public enum EntitiesType {
	
	ENEMYA("EnemyA", 5, 5, 2),   //balanced enemy
	ENEMYB("EnemyB", 9, 4, 2),  //strong enemy
	ENEMYC("EnemyC", 2, 4, 5), //agile enemy
	PLAYER("Player", 10, 10, 2);
	
	
	private String name;
	private float strength;
	private float stamina;
	private float speed;
	 
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getStrength() {
		return strength;
	}


	public void setStrength(float strength) {
		this.strength = strength;
	}


	public float getStamina() {
		return stamina;
	}


	public void setStamina(float stamina) {
		this.stamina = stamina;
	}


	public float getSpeed() {
		return speed;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}


	private EntitiesType(String name, float strength, float stamina, float speed) {
		this.name= name;
		this.strength = strength;
		this.stamina = stamina;
		this.speed = speed;
	}
}
