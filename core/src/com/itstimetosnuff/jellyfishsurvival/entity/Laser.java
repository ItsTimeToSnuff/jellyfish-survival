package com.itstimetosnuff.jellyfishsurvival.entity;

import box2dLight.Light;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.GameRenderer;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class Laser extends GameObject{

	Light light;
	
	public Laser(){
		super();
	}

	public void update(float deltaTime){
		super.update(deltaTime);
		if (light != null) light.setPosition(getPosition());
		checkLifetime();
	}

	public void render(SpriteBatch batch){
		super.render(batch);
	}

	public void reconstruct(Vector2 position, Vector2 size, Vector2 velocity, Light light) {
		setPosition(position);
		setSize(size);
		setTexture(Assets.bubble);
		setVelocity(velocity);
		setLight(light);
	}

	public void unuse(){
		setInUse(false);
		if (light != null) light.setActive(false);
	}

	private void checkLifetime(){
		if (getPosition().y < 0 || getPosition().y > GameRenderer.height) unuse();
	}
	
	public void setLight(Light light){
		this.light = light;
	}

}