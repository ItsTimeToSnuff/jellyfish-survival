package com.itstimetosnuff.jellyfishsurvival.entitytype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class Swordfish {

	public static TextureRegion[] sprites = {Assets.swordfish, Assets.swordfish2, Assets.swordfishflip, Assets.swordfish2flip};
	public static Vector2 scale = new Vector2(800, 224);
	public static float speed = 6f;
}
