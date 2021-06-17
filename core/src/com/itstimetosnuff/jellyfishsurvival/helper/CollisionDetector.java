package com.itstimetosnuff.jellyfishsurvival.helper;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.entity.Enemy;
import com.itstimetosnuff.jellyfishsurvival.entity.EnemyPool;
import com.itstimetosnuff.jellyfishsurvival.entity.Laser;
import com.itstimetosnuff.jellyfishsurvival.entity.LaserPool;
import com.itstimetosnuff.jellyfishsurvival.entity.Mote;
import com.itstimetosnuff.jellyfishsurvival.entity.MotePool;
import com.itstimetosnuff.jellyfishsurvival.entity.PlayerCharacter;

public class CollisionDetector {
	
	public static float scale = .75f;
	
	public static Mote checkMoteCollision(PlayerCharacter c, MotePool motePool){
		for (Mote mote : motePool.getObjarray()){
			if (mote.isInUse()){
				if (c.getPosition().dst(mote.getPosition()) < (c.getSize().x/4+mote.getSize().x)){
					return mote;
				}
			}
		}
		return null;
	}

	public static void checkLaserCollision(LaserPool l, EnemyPool enemyPool, MotePool motePool, RayHandler handler){
		for (Laser laser : l.getObjarray()){
			for (Enemy enemy : enemyPool.getObjarray()){
				if (laser.isInUse() && enemy.isInUse() && overlap(laser, enemy)){
					laser.unuse();
					enemy.unuse();
					Color color = Color.YELLOW;
					Light light = new PointLight(handler, 100, color, 150, enemy.getPosition().x, enemy.getPosition().y);
					light.setSoft(false);
					motePool.addSpecialMote(new Vector2(enemy.getPosition().x, enemy.getPosition().y), 
							new Vector2(35,35), new Vector2(0,0), light);
				}
			}
		}
	}

	public static Enemy checkCollision(PlayerCharacter c, EnemyPool enemypool){
		for (Enemy enemy : enemypool.getObjarray()){
			if (enemy.isInUse() && overlap(c, enemy)){
					return enemy;
			}
		}
		return null;
	}
	
	private static boolean overlap(PlayerCharacter c, Enemy e){
		Vector2 size1 = new Vector2(c.getSize().x*scale, c.getSize().y*scale);
		int x1 = (int) (c.getPosition().x - size1.x/2);
		int y1 =(int) (c.getPosition().y - size1.y/2);
		int width1 = (int) size1.x;
		int height1 = (int) size1.y;
		Vector2 size2 = new Vector2(e.getSize().x*scale, e.getSize().y*scale);
		int x2 = (int) (e.getPosition().x - size2.x/2);
		int y2 =(int) (e.getPosition().y - size2.y/2);
		int width2 = (int) size2.x;
		int height2 = (int) size2.y;
		Rectangle r1 = new Rectangle(x1,y1,width1,height1);
		Rectangle r2 = new Rectangle(x2,y2,width2,height2);
		return r1.overlaps(r2);
	}
	
	private static boolean overlap(Laser c, Enemy e){
		Vector2 size1 = new Vector2(10,10);
		int x1 = (int) (c.getPosition().x - size1.x/2);
		int y1 =(int) (c.getPosition().y - size1.y/2);
		int width1 = (int) size1.x;
		int height1 = (int) size1.y;
		Vector2 size2 = new Vector2(e.getSize().x*scale, e.getSize().y*scale);
		int x2 = (int) (e.getPosition().x - size2.x/2);
		int y2 =(int) (e.getPosition().y - size2.y/2);
		int width2 = (int) size2.x;
		int height2 = (int) size2.y;
		Rectangle r1 = new Rectangle(x1,y1,width1,height1);
		Rectangle r2 = new Rectangle(x2,y2,width2,height2);
		return r1.overlaps(r2);  
	}

}
