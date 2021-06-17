package com.itstimetosnuff.jellyfishsurvival.helper;

import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.itstimetosnuff.jellyfishsurvival.GameWorld;

public class LightHelper {
	
	RayHandler handler;
	
	public LightHelper(World world, Camera camera){
		handler = new RayHandler(world);
		handler.setCombinedMatrix(camera.combined);
		
	}

	public void update(){
		if(!Settings.endlessMode){
			handler.setAmbientLight(.1f,.1f,.1f, -.5f + GameWorld.depth/800);
		}
	}
	
	public RayHandler getRayHandler(){
		return handler;
	}
}
