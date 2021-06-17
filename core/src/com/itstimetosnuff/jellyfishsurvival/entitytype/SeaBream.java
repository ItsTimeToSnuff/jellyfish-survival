package com.itstimetosnuff.jellyfishsurvival.entitytype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class SeaBream {

	public static TextureRegion[] sprites = {Assets.seabream, Assets.seabream2, Assets.seabreamflip, Assets.seabream2flip};
	public static Vector2 scale = new Vector2(500, 200);
	public static float speed = 2f;
}
