package com.itstimetosnuff.jellyfishsurvival.entity;

import box2dLight.Light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.itstimetosnuff.jellyfishsurvival.GameWorld;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;
import com.itstimetosnuff.jellyfishsurvival.helper.Settings;

public class PlayerCharacter {
    private static float gravity = -.7f;
    private static final float maxFall = -8;
    private static final float dampingCoeff = .97f;
    private static float height;
    private static float width = 1000;
    private static float recoveryTime = 1;

    private TextureRegion[] sprites = {Assets.jelly1, Assets.jelly2, Assets.jelly3};
    private TextureRegion bubbleShield = Assets.bubbleShield;
    private Vector2 bubbleSize = new Vector2(120, 120);
    protected float animTime = 0;
    protected float totalDuration = .2f;

    private Vector2 imSize;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float angle = 0;
    private float angVelocity = 0;

    private World world;
    private TextureRegion texture;

    private Light light;

    private boolean jumped = false;
    private boolean started = false;

    private int bubbles = 0;

    private boolean shield = false;
    private boolean vulnerable = true;
    private float invincibility = 0;

    public PlayerCharacter(World w, Vector2 position) {
        world = w;
        this.position = position;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, gravity);

        imSize = new Vector2(100, 100);

        texture = sprites[0];

        float ppu = Gdx.graphics.getWidth() / width;
        height = Gdx.graphics.getHeight() / ppu;
    }

    public void update(float delta) {
        if (!started) hopInPlace();
        if (!started) velocity = velocity.add(new Vector2(0, -0.3f).scl(60 * delta));
        else velocity = velocity.add(new Vector2(acceleration.x, acceleration.y).scl(60 * delta));
        velocity.x = velocity.x * dampingCoeff;
        position = position.add(new Vector2(velocity.x, velocity.y).scl(60 * delta));
        position.y = position.y - GameWorld.height * 60 * delta;
        checkBounds();
        if (jumped) animate(delta);
        angle = angle * dampingCoeff + angVelocity;
        if (light != null) light.setPosition(position);
        invincibility = invincibility - delta;
        if (invincibility < 0) vulnerable = true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - imSize.x / 2, position.y - imSize.y / 2,
                imSize.x / 2, imSize.y / 2, imSize.x, imSize.y, 1, 1, angle);

        if (shield) {
            batch.draw(bubbleShield, position.x - bubbleSize.x / 2, position.y - bubbleSize.y / 2,
                    bubbleSize.x / 2, bubbleSize.y / 2, bubbleSize.x, bubbleSize.y, 1, 1, angle);
        }
    }

    private void checkBounds() {
        if (position.x < 0) {
            position.x = 0;
            velocity.x = 0;
        } else if (position.x >= width) {
            position.x = width;
            velocity.x = 0;
        } else if (position.y >= height * 2 / 3) {
            position.y = height * 2 / 3;
            GameWorld.height = velocity.y;
        }
        if (velocity.y < maxFall) velocity.y = maxFall;
    }

    private void animate(float delta) {
        if (sprites == null) return;
        animTime += delta;
        if (animTime < totalDuration * .1) {
            setTexture(sprites[0]);
        } else if (animTime < totalDuration * .3) {
            setTexture(sprites[1]);
        } else if (animTime < totalDuration * .7) {
            setTexture(sprites[2]);
        } else if (animTime < totalDuration * .9) {
            setTexture(sprites[1]);
        } else if (animTime < totalDuration) {
            setTexture(sprites[0]);
        } else {
            setTexture(sprites[0]);
            animTime = 0;
            jumped = false;
        }
    }

    public boolean collided() {
        if (shield) {
            shield = false;
            invincibility = recoveryTime;
            vulnerable = false;
            if (Settings.soundOn) Assets.crunch.play(.25f);
            return false;
        } else if (Settings.extralives > 0 && vulnerable) {
            invincibility = recoveryTime;
            vulnerable = false;
            Settings.loseLife();
            if (Settings.soundOn) Assets.crunch.play(.25f);
            return false;
        } else return vulnerable;
    }

    public void activateShield() {
        shield = true;
    }

    public void removeShield() {
        shield = false;
    }

    public void jumpLeft() {
        setVelocity(new Vector2(-5, 15));
        setAngle(30);
        setJumped(true);
        playJumpSound();
    }

    public void jumpRight() {
        setVelocity(new Vector2(5, 15));
        setAngle(-30);
        setJumped(true);
        playJumpSound();
    }

    private void playJumpSound() {
        if (Settings.soundOn) {
            long i = Assets.jump.play(1f);
            Assets.jump.setPitch(i, (float) 2);
        }
    }

    private void hopInPlace() {
        if (position.y < 400) {
            setVelocity(new Vector2(0, 10));
            jumped = true;
        }
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Vector2 getSize() {
        return imSize;
    }

    public void setAngVelocity(float angVelocity) {
        this.angVelocity = angVelocity;
    }

}
