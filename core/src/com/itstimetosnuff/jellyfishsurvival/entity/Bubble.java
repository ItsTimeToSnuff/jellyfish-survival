package com.itstimetosnuff.jellyfishsurvival.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.GameRenderer;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;


public class Bubble extends GameObject{
	
	public Bubble(){
		super();
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		checkLifetime();
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
	}
	
	public void reconstruct(Vector2 position, Vector2 size, Vector2 velocity) {
		setPosition(position);
		setSize(size);
		setTexture(Assets.bubble);
		setVelocity(velocity);
	}
	
	public void unuse(){
		setInUse(false);
	}
	
	private void checkLifetime(){
		if (getPosition().y > GameRenderer.height || getPosition().y < -200) unuse();
	}
}