package com.itstimetosnuff.jellyfishsurvival.entitytype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class Sardine{

	public static TextureRegion[] sprites = {Assets.sardine, Assets.sardine2, Assets.sardineflip, Assets.sardineflip};
	public static Vector2 scale = new Vector2(200, 80);
	public static float speed = 2.5f;
	
}