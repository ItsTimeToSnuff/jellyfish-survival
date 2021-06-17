package com.itstimetosnuff.jellyfishsurvival.entitytype;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class Carp {

	public static TextureRegion[] sprites = {Assets.carp, Assets.carp2, Assets.carpflip, Assets.carp2flip};
	public static Vector2 scale = new Vector2(400, 175);
	public static float speed = 3.5f;
}
