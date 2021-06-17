package com.itstimetosnuff.jellyfishsurvival.entity;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;
import com.itstimetosnuff.jellyfishsurvival.helper.Settings;

public class LaserGenerator {

	LaserPool laserPool;

	float counter = 0;
	PlayerCharacter playerCharacter;
	RayHandler handler;

	public LaserGenerator(RayHandler handler, LaserPool laserPool, PlayerCharacter playerChar){
		this.laserPool = laserPool;
		this.handler = handler;
		this.playerCharacter = playerChar;
	}

	public void update(float delta){
		counter += delta;
		if (Settings.lasers>0 && counter > 5f/Settings.lasers){
			counter = 0;
			Vector2 position = new Vector2(playerCharacter.getPosition().x, playerCharacter.getPosition().y);
			Vector2 velocity = new Vector2( 0, 15);
			float sizeVal = 20;
			Vector2 size = new Vector2( sizeVal, sizeVal);
			Color color = Color.CYAN;
			Light light = new PointLight(handler, 100, color, 100, position.x, position.y);
			light.setSoft(false);
			laserPool.addLaser(position, size, velocity, light);
			if (Settings.soundOn) Assets.laser.play(.25f);
		}		
	}

}
