package com.itstimetosnuff.jellyfishsurvival.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BubblePool {

	private Bubble[] objarray;
	private final int maxNumObjects = 20;
	
	
	public BubblePool(){
		objarray = new Bubble[maxNumObjects]; 
		for (int i=0; i<maxNumObjects; i++){
			objarray[i] = new Bubble();
		}
	}
	
	public void render(SpriteBatch batch){
		for (int i=0; i<maxNumObjects; i++){
			if(objarray[i].isInUse()){
				objarray[i].render(batch);
			}
		}
	}
	
	public void update(float deltaTime){
		for (int i=0; i<maxNumObjects; i++){
			if(objarray[i].isInUse()){
				objarray[i].update(deltaTime);
			}
		}
	}
	
	public void addBubble(Vector2 position, Vector2 size, Vector2 velocity){

		for (int i=0; i<maxNumObjects; i++){
			if (!objarray[i].isInUse()){
				objarray[i].setInUse(true);
				objarray[i].reconstruct(position, size, velocity);
				return;
			}
		}
	}
	
	public void clearAllObjects(){
		for (int i=0; i<maxNumObjects; i++){
			objarray[i].unuse();
		}
	}
}
