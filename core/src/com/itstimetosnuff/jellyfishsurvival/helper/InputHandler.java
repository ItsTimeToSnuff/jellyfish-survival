package com.itstimetosnuff.jellyfishsurvival.helper;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.itstimetosnuff.jellyfishsurvival.GameRenderer;
import com.itstimetosnuff.jellyfishsurvival.GameWorld;
import com.itstimetosnuff.jellyfishsurvival.entity.PlayerCharacter;

public class InputHandler implements InputProcessor {

    private static float height;
    private static float width = 1000;

    private OrthographicCamera camera;
    private RayHandler handler;
    private UIHelper uihandler;
    private GameWorld gameWorld;
    private World world;
    private GameRenderer gameRenderer;
    private PlayerCharacter gamechar;
    private Vector3 testPoint = new Vector3();

    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;

    public InputHandler(GameWorld gameWorld, GameRenderer gameRenderer, UIHelper uihandler) {
        camera = gameRenderer.getCamera();
        gamechar = gameWorld.getPlayerCharacter();
        this.gameRenderer = gameRenderer;
        float ppu = Gdx.graphics.getWidth() / width;
        height = Gdx.graphics.getHeight() / ppu;

        this.gameWorld = gameWorld;
        world = gameWorld.getWorld();
        handler = gameRenderer.getRayHandler();
        this.uihandler = uihandler;
    }

    public void update(float delta) {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (gameRenderer.state == GameRenderer.Running) {
            if (keycode == Input.Keys.RIGHT) {
                rightPressed = true;
                gamechar.jumpRight();
            }
            if (keycode == Input.Keys.LEFT) {
                leftPressed = true;
                gamechar.jumpLeft();
            }
            if (keycode == Input.Keys.UP) {
                gamechar.setVelocity(new Vector2(0, 50));
                gamechar.setAngle(0);
                upPressed = true;
            }
            if (keycode == Input.Keys.DOWN) {
                downPressed = true;
            }
            if (!gamechar.isStarted()) {
            	gamechar.setStarted(true);
			}
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            rightPressed = false;
        }
        if (keycode == Input.Keys.LEFT) {
            leftPressed = false;
        }
        if (keycode == Input.Keys.UP) {
            upPressed = false;
        }
        if (keycode == Input.Keys.DOWN) {
            downPressed = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        testPoint.set(screenX, screenY, 0);
        camera.unproject(testPoint);
        if (gameRenderer.state == GameRenderer.Menu) {
            if (testPoint.dst(new Vector3(gamechar.getPosition().x, gamechar.getPosition().y, 0)) < 200) {
                gameRenderer.setRunning();
                if (testPoint.x < width / 2) {
                    gamechar.jumpLeft();
                } else if (testPoint.x >= width / 2) {
                    gamechar.jumpRight();
                }
                gamechar.setStarted(true);
            }
        } else if (gameRenderer.state == GameRenderer.Running) {
            if (testPoint.x < width / 2) {
                gamechar.jumpLeft();
            } else if (testPoint.x >= width / 2) {
                gamechar.jumpRight();

            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        testPoint.set(screenX, screenY, 0);
        camera.unproject(testPoint);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
