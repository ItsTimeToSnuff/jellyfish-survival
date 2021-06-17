package com.itstimetosnuff.jellyfishsurvival;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itstimetosnuff.jellyfishsurvival.helper.Settings;

public class UpgradeScreen implements Screen {

    JellyfishSurvivalGame game;
    public static float height;
    public static float width = 1000;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    private BitmapFont font;

    public UpgradeScreen(JellyfishSurvivalGame game) {
        this.game = game;
        Settings.load();
        float ppu = Gdx.graphics.getWidth() / width;
        height = Gdx.graphics.getHeight() / ppu;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height, camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/fonts/neuropol200.fnt"));
    }

    public void dispose() {
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        batch.begin();
        batch.enableBlending();
        batch.end();
    }

    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}
