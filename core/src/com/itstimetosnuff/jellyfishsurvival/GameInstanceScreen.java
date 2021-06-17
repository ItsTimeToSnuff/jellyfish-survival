package com.itstimetosnuff.jellyfishsurvival;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.itstimetosnuff.jellyfishsurvival.helper.InputHandler;
import com.itstimetosnuff.jellyfishsurvival.helper.Settings;
import com.itstimetosnuff.jellyfishsurvival.helper.UIHelper;

public class GameInstanceScreen implements Screen{

	JellyfishSurvivalGame game;
	InputHandler inputHandler;
	GameRenderer gameRenderer;
	GameWorld gameWorld;
	UIHelper uiHandler;
	
	public GameInstanceScreen(JellyfishSurvivalGame game) {
		Settings.load();
		this.game = game;
		uiHandler = new UIHelper(game);
		gameWorld = new GameWorld();
		gameRenderer = new GameRenderer(gameWorld, uiHandler);
		inputHandler = new InputHandler(gameWorld, gameRenderer, uiHandler);
		uiHandler.setRenderer(gameRenderer);
		uiHandler.setGameWorld(gameWorld);
		gameWorld.setHandler(gameRenderer.getRayHandler());
		gameWorld.postCreate();
	}

	public void dispose(){
		gameWorld.dispose();
	}

	@Override
	public void render(float delta){
		inputHandler.update(delta);
		gameWorld.update(delta);
		gameRenderer.render(delta);
	}
	
	public void resize(int width, int height){
		gameRenderer.getViewport().update(width, height);
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
	
	public InputProcessor getInputProcessor(){
		return inputHandler;
	}

	public Stage getStage(){
		return gameRenderer.getStage();
	}
	
	@Override
	public void resume() {
	}

}
