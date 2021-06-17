package com.itstimetosnuff.jellyfishsurvival;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itstimetosnuff.jellyfishsurvival.helper.Assets;
import com.itstimetosnuff.jellyfishsurvival.helper.LightHelper;
import com.itstimetosnuff.jellyfishsurvival.helper.Settings;
import com.itstimetosnuff.jellyfishsurvival.helper.UIHelper;

public class GameRenderer {

	public static final int Menu = 0;
	public static final int Running = 1;
	public static final int GameOver = 2;
	public static final int Paused = 3;
	public static final int Upgrade = 4;
	public int state = Menu;
	public boolean lights_enabled = true;
	public static float height;
	public static float width = 1000;
	OrthographicCamera camera;
	TextureRegion background = Assets.background;
	Vector2 bg_size = new Vector2(2000,4000);
	Viewport viewport;
	SpriteBatch batch;
	RayHandler handler;
	LightHelper lightHelper;
	UIHelper uiHelper;
	Stage stage;
	ShapeRenderer shapeRenderer;
	GameWorld gameInstance;
	World world;
	private GlyphLayout glyphLayout;
	private BitmapFont font;

	public GameRenderer(GameWorld gameInstance, UIHelper uihandler){
		this.gameInstance = gameInstance;
		world = gameInstance.getWorld();
		uiHelper = uihandler;
		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		viewport = new FitViewport(width, height, camera);
		stage = uihandler.getStage();
		camera.update();
		batch = new SpriteBatch();
		lightHelper = new LightHelper(world, camera);
		handler = lightHelper.getRayHandler();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		font = new BitmapFont(Gdx.files.internal("data/fonts/neuropol200.fnt"));
		glyphLayout = new GlyphLayout();
	}

	public void render(float delta){
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		batch.begin();
		batch.enableBlending();
		Color color = batch.getColor();
		float oldAlpha = color.a; 
		float scale = .3f;
		color.a = oldAlpha*scale; 
		batch.setColor(color); 
		if (GameWorld.depth*2 < 1000) batch.draw(background, 0, -GameWorld.depth*2, bg_size.x/2, bg_size.y, bg_size.x, bg_size.y, 1, 1, 0);
		else batch.draw(background, 0, -1000, bg_size.x/2, bg_size.y, bg_size.x, bg_size.y, 1, 1, 0);
		color.a = oldAlpha;
		batch.setColor(color);
		gameInstance.render(batch);
		batch.end();
		batch.setProjectionMatrix(camera.combined);
		if (lights_enabled){
			handler.setCombinedMatrix(camera.combined, camera.position.x,
					camera.position.y, camera.viewportWidth * camera.zoom,
					camera.viewportHeight * camera.zoom);
			handler.updateAndRender();
		}
		lightHelper.update();
		if (gameInstance.isGameOver() && state != GameOver) setGameOver();

		switch (state) {
		case Menu:
			renderMenu();
			break;
		case Running:
			renderRunning();
			break;
		case GameOver:
			renderGameOver();
			break;
		case Paused:
			renderPaused();
			break;
		case Upgrade:
			renderUpgrade();
		}

	}

	private void renderPaused() {

	}

	private void renderGameOver() {
		batch.begin();
		font.setColor(Color.BLACK);
		if (gameInstance.isGameWin()){
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			font.getData().setScale(.6f);
			String winString = "The Surface";
			glyphLayout.setText(font, winString);
			font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height*3/4);

			font.getData().setScale(.3f);
			String temp = "To be continued...";
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height*3/4 - 150);

			font.getData().setScale(.3f);
			temp = "Endless Mode Unlocked";
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height/4 + 220);
			
			temp = "+" + (gameInstance.getBubbles()+(int)(GameWorld.depth- Settings.headStart)/10);
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2, height/4 + 300 );
			
			Vector2 imSize = new Vector2(50, 50); 
			batch.draw(Assets.bubble, width/2 - 80, height/4 + 280 - glyphLayout.height/2,
					imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);


		}else {
			font.setColor(Color.WHITE);
			font.getData().setScale(.6f);
			String gameOverString = "Game Over";
			glyphLayout.setText(font, gameOverString);
			font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height*2/3);

			font.getData().setScale(.3f);
			String temp = "Try Some Upgrades?";
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height/4 + 220);
			
			temp = "+" + (gameInstance.getBubbles()+(int)(GameWorld.depth-Settings.headStart)/10);
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2, height/4 + 300 );
			
			Vector2 imSize = new Vector2(50, 50); 
			batch.draw(Assets.bubble, width/2 - 80, height/4 + 280 - glyphLayout.height/2,
					imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);
		}

		font.getData().setScale(.25f);
		String scoreStr = "You reached " + (int) GameWorld.depth + "m";
		glyphLayout.setText(font, scoreStr);
		font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height*2/3 - 200);

		batch.end();
		stage.act(Gdx.graphics.getDeltaTime()); 
		stage.draw();
	}

	private void renderRunning() {
		batch.begin();
		font.setColor(Color.WHITE);
		font.getData().setScale(.6f);
		int depth;
		String depthString;
		if (Settings.endlessMode){
			depth = (int) (GameWorld.depth);
			depthString = depth + "m";
		} else{
			depth = (int) (1000 - GameWorld.depth);
			depthString = "-" + depth + "m";
		}

		glyphLayout.setText(font, depthString);
		font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height - 50);

		font.getData().setScale(.25f);
		String bubbleStr = "" + gameInstance.getBubbles();
		glyphLayout.setText(font, bubbleStr);
		font.draw(batch, glyphLayout, width - 200 - glyphLayout.width/2, height - 200);

		Vector2 imSize = new Vector2(50, 50); 
		batch.draw(Assets.bubble, width - 50 - imSize.x, height-240, 
				imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);

		String livesStr = "x" + Settings.extralives;
		font.draw(batch, livesStr, 100, height - 200);

		batch.end();
	}

	private void renderMenu() {
		batch.begin();
		font.setColor(Color.WHITE);

		font.getData().setScale(.25f);
		String tapString = "TAP TO JUMP";
		glyphLayout.setText(font, tapString);
		font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, 200);

		if (Settings.upgradesAvailable()){
			font.getData().setScale(.2f);
			font.setColor(new Color(0f, .7f, 1f, .8f));
			String temp = "Upgrades";
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2 - 350 - glyphLayout.width/2,  350);
			temp = "Available";
			glyphLayout.setText(font, temp);
			font.draw(batch, glyphLayout, width/2 -350 - glyphLayout.width/2, 300);
		}

		batch.end();
		stage.act(Gdx.graphics.getDeltaTime()); 
		stage.draw();
	}

	private void renderUpgrade(){
		batch.begin();
		font.setColor(Color.WHITE);
		font.getData().setScale(.6f);
		String titleString = "Upgrades";
		glyphLayout.setText(font, titleString);
		font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height*5/6);

		font.getData().setScale(.25f);
		String bubbleStr = "" + Settings.bubbles;
		glyphLayout.setText(font, bubbleStr);
		font.draw(batch, glyphLayout, width/2 - glyphLayout.width/2, height*5/6 - 200);

		Vector2 imSize = new Vector2(50, 50);
		batch.draw(Assets.bubble, width/2 - glyphLayout.width/2 - 100 - imSize.x/2, height*5/6-245,
				imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);

		font.getData().setScale(.25f);
		String temp = "Glow: " + Settings.brightness;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, 50, height*5/6 - 300 - glyphLayout.height/2);

		temp = "Extra Lives: " + Settings.extralives;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, 50, height*5/6 - 400 - glyphLayout.height/2);
		
		temp = "Head Start: " + Settings.headStart + "m";
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, 50, height*5/6 - 500 - glyphLayout.height/2);
		
		temp = "Lasers: " + Settings.lasers;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, 50, height*5/6 - 600 - glyphLayout.height/2);


		font.setColor(Color.GRAY);
		temp = "" + Settings.brightCost;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, width - 200 - glyphLayout.width, height*5/6 - 300 - glyphLayout.height/2);
		if (Settings.bubbles < Settings.brightCost) uiHelper.removeBrightButton();

		if (Settings.extralives < 3){
		temp = "" + Settings.livesCost;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, width - 200 - glyphLayout.width, height*5/6 - 400 - glyphLayout.height/2);
		}
		if (Settings.bubbles < Settings.livesCost || Settings.extralives >= 3) uiHelper.removeLivesButton();
		
		if (Settings.headStart < 500){
		temp = "" + Settings.headStartCost;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, width - 200 - glyphLayout.width, height*5/6 - 500 - glyphLayout.height/2);
		}
		if (Settings.bubbles < Settings.headStartCost || Settings.headStart >= 500) uiHelper.removeHeadStartButton();
		
		if (Settings.lasers < 2){
		temp = "" + Settings.lasersCost;
		glyphLayout.setText(font, temp);
		font.draw(batch, glyphLayout, width - 200 - glyphLayout.width, height*5/6 - 600 - glyphLayout.height/2);
		}
		if (Settings.bubbles < Settings.lasersCost || Settings.lasers >= 2) uiHelper.removeLasersButton();
		
		batch.end();

		stage.act(Gdx.graphics.getDeltaTime()); 
		stage.draw();
	}

	public RayHandler getRayHandler(){
		return handler;
	}

	public OrthographicCamera getCamera(){
		return camera;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public Stage getStage(){
		return stage;
	}

	public Viewport getViewport(){
		return viewport;
	}

	public void setRunning(){
		state = Running;
		lights_enabled = true;
		uiHelper.hideButtons();
	}

	public void setMenu(){
		state = Menu;
		lights_enabled = true;
		uiHelper.showButtons();
		uiHelper.removeGameOverButton();
		gameInstance.reset();
	}

	public void setGameOver(){
		state = GameOver;
		uiHelper.showGameOverButton();
	}

	public void setUpgrade(){
		state = Upgrade;
		uiHelper.hideButtons();
		uiHelper.showUpgradeButtons();
	}

}
