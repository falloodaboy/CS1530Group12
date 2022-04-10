package com.testgame.demo.entities;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Simulates the damage animation for each entity in the game. It simulates a slight shaking from left to right and also changing of
 * the alpha value. 
 * 
 * Idea: have a Sequence of Actions and each action is a Parallel Action with 
 */
public class DamageAction extends ParallelAction {
	
	
	public DamageAction(float time, float alpha, float dist) {
			this.addAction(new AlphaComponent(time, alpha));
			this.addAction(new MoveComponent(time, dist));
	}


	private class AlphaComponent extends SequenceAction {
		
		
		public AlphaComponent(float time, float val) {
			this.addAction(Actions.alpha(val, time));
			this.addAction(Actions.alpha(1, time));
			this.addAction(Actions.alpha(val, time));
			this.addAction(Actions.alpha(1, time));
			
		}
		
	}
	
	private class MoveComponent extends SequenceAction {
		
		
		public MoveComponent(float time, float dist) {
			this.addAction(Actions.moveBy(dist, 0, time/2));
			this.addAction(Actions.moveBy(-dist, 0, time/2));
			this.addAction(Actions.moveBy(-dist, 0, time/2));
			this.addAction(Actions.moveBy(dist, 0, time/2));
		}
	}

}
