package com.itstimetosnuff.jellyfishsurvival.entity;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.GameRenderer;

public class MoteGenerator {

	MotePool motePool;
	private final double spawnChance = 0.02;
	private final double shieldChance = 0.02;

	RayHandler handler;

	public MoteGenerator(RayHandler handler, MotePool motePool){
		this.motePool = motePool;
		this.handler = handler;
	}

	public void update(){
		if (Math.random() < spawnChance){
			Vector2 position = new Vector2((float) (Math.random()*1000), (float) (GameRenderer.height + 100 + Math.random()*300));
			Vector2 velocity = new Vector2( 0, 0);
			float sizeVal = (float) (Math.random()*30+20);
			Vector2 size = new Vector2( sizeVal, sizeVal);
			if (Math.random() < shieldChance) size = new Vector2(120,120);
			Color color = selectRandomColor();
			motePool.addMote(position, size, velocity, new PointLight(handler, 100, color, 150, position.x, position.y));
		}		
	}

	private Color selectRandomColor(){

		int key = (int) Math.floor(10*Math.random());

		Color color = Color.BLUE;
		switch (key) {
		case 0:
			color = Color.CYAN;
			break;
		case 1:
			color = Color.GREEN;
			break;
		case 2:
			color = Color.MAGENTA;
			break;
		case 3:
			color = Color.MAROON;
			break;
		case 4:
			color = Color.ORANGE;
			break;
		case 5:
			color = Color.PINK;
			break;
		case 6:
			color = Color.PURPLE;
			break;
		case 7:
			color = Color.RED;
			break;
		case 8:
			color = Color.TEAL;
			break;
		case 9:
			color = Color.YELLOW;
			break;

		}
		return color;
	}
}
