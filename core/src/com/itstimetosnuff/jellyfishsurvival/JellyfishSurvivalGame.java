package com.itstimetosnuff.jellyfishsurvival;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;

public class JellyfishSurvivalGame extends Game {

    @Override
    public void create() {
        Assets.load();
        loadGameScreen();
    }

    public void dispose() {
        Assets.dispose();
    }

    public void loadGameScreen() {
        GameInstanceScreen currentScreen = new GameInstanceScreen(this);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(currentScreen.getStage());
        multiplexer.addProcessor(currentScreen.getInputProcessor());
        Gdx.input.setInputProcessor(multiplexer);
        setScreen(currentScreen);
        Gdx.app.log("Screen", "changed to main screen");
    }

    public void loadUpgradeScreen() {
        UpgradeScreen ugScreen = new UpgradeScreen(this);
        setScreen(ugScreen);
        Gdx.app.log("Screen", "changed to upgrade screen");
    }
}
