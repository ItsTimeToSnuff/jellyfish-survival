package com.itstimetosnuff.jellyfishsurvival.entitytype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class Bass {

	public static TextureRegion[] sprites = {Assets.bass, Assets.bass2, Assets.bassflip, Assets.bass2flip};
	public static Vector2 scale = new Vector2(400, (float) 170);
	public static float speed = 2.3f;
}
