package com.itstimetosnuff.jellyfishsurvival.entity;

import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.GameRenderer;

public class EnemyGenerator {

	EnemyPool enemypool;
	private double spawnChance = 0.03;
	private int numTypes = 1;
	private final int maxNumTypes = 7;
	private double enemySpeed = 2.5;
	private double minEnemySpeed = .5;
	
	public EnemyGenerator(EnemyPool enemypool){
		this.enemypool=enemypool;
	}
	
	public void update(){
		float x = (float) Math.random();
		if (x < spawnChance){
			float direction = (float) Math.random();
			float speed = (float) (enemySpeed*Math.random());
			float spawnPosition = (float) (4* GameRenderer.width*Math.random());
			int typeID = (int) Math.floor(numTypes*Math.random());
			speed = (float) ((speed < minEnemySpeed) ? minEnemySpeed:speed);
			float posX; float velX;
			if (direction < 0.5){
				posX = 0;
				velX = speed;
			} else{
				posX = GameRenderer.width;
				velX = -speed;
			}
			if (spawnPosition > GameRenderer.height) posX = (float) (Math.random() * GameRenderer.width);
			Vector2 position = new Vector2(posX, GameRenderer.height/3 + spawnPosition);
			Vector2 velocity = new Vector2(velX, 0);
			enemypool.addEnemy(typeID, position, velocity);
		}
	}

	public void setNumTypes(int numTypes) {
		if (numTypes <= maxNumTypes) this.numTypes = numTypes;
	}
	
	
}
