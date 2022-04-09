package com.testgame.demo.entities;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Simulates the damage animation for each entity in the game. It simulates a slight shaking from left to right and also changing of
 * the alpha value. 
 */
public class DamageAction extends SequenceAction {
	
	
	public DamageAction() {
		super(Actions.moveBy(1f, 0, 0.5f), Actions.moveBy(-1f, 0, 0.5f), Actions.moveBy(-1f, 0, 0.5f), Actions.moveBy(1f, 0, 0.5f));
	}
	
	
	public DamageAction(float shakeAmount) {
		super(Actions.moveBy(shakeAmount, 0, 0.5f), Actions.moveBy(-shakeAmount, 0, 0.5f), Actions.moveBy(-shakeAmount, 0, 0.5f), Actions.moveBy(shakeAmount, 0, 0.5f));
	}
	
	public DamageAction(float shakeAmount, float durPerAction) {
		super(Actions.moveBy(shakeAmount, 0, durPerAction), Actions.moveBy(-shakeAmount, 0, durPerAction), Actions.moveBy(-shakeAmount, 0, durPerAction), Actions.moveBy(shakeAmount, 0, durPerAction));
	}

}
