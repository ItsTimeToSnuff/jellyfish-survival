package com.itstimetosnuff.jellyfishsurvival.helper;

import box2dLight.PointLight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.itstimetosnuff.jellyfishsurvival.GameRenderer;
import com.itstimetosnuff.jellyfishsurvival.GameWorld;
import com.itstimetosnuff.jellyfishsurvival.JellyfishSurvivalGame;

public class UIHelper {
	JellyfishSurvivalGame game;
	private float height;
	private float width = 1000;
	private float heightscale;
	private float widthscale;
	private GameRenderer renderer;
	private GameWorld gameWorld;
	private Stage stage;
	private Button button1;
	private Button button2;
	private Button menuButton; private boolean menuSet = false;
	private Button menuButton2; private boolean menuSet2 = false;
	private Button brightButton; private boolean brightSet = false;
	private Button livesButton; private boolean livesSet = false;
	private Button headstartButton; private boolean headstartSet = false;
	private Button laserButton; private boolean laserSet = false;

	public UIHelper(JellyfishSurvivalGame game){
		this.game = game;
		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;
		heightscale = Gdx.graphics.getHeight()/height;
		widthscale = Gdx.graphics.getWidth()/width;
		stage = new Stage();
		setupButtons();
	}

	private void setupButtons(){
		float b_width = 120*widthscale;
		float b_height = 120*heightscale;
		button1 = new Button(new TextureRegionDrawable(Assets.modButton));
		button1.setWidth(b_width);
		button1.setHeight(b_height);
		button1.setPosition(widthscale*(width/2 - 350 - 120/2f),heightscale*(170 - 120/2f));
		button1.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				renderer.setUpgrade();
			}
		});
		stage.addActor(button1);
		button2 = new Button(new TextureRegionDrawable(Assets.backButton));
		if (Settings.muted) button2.setChecked(true);
		button2.setWidth(b_width);
		button2.setHeight(b_height);
		button2.setPosition(widthscale*(width/2 + 350 - 120/2f),heightscale*(170 - 120/2f));
		button2.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
		stage.addActor(button2);
	}

	public void hideButtons(){
		button1.remove();
		button2.remove();
	}

	public void showButtons(){
		setupButtons();
	}

	public void showGameOverButton(){
		menuButton = new Button(new TextureRegionDrawable(Assets.menuButton));
		menuButton.setWidth(450*widthscale);
		menuButton.setHeight(100*heightscale);
		menuButton.setPosition(widthscale*(width/2 - 450/2f), heightscale*(height/4));
		menuButton.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				renderer.setMenu();
			}
		});
		stage.addActor(menuButton);
		menuSet = true;
	}

	public void removeGameOverButton(){
		if (menuSet) {
			menuButton.remove();
			menuSet = false;
		}
	}

	public void showUpgradeButtons(){
		menuButton2 = new Button(new TextureRegionDrawable(Assets.menuButton));
		menuButton2.setWidth(450*widthscale);
		menuButton2.setHeight(100*heightscale);
		menuButton2.setPosition(widthscale*(width/2 - 450/2f), heightscale*(100));
		menuButton2.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				renderer.setMenu();
				removeUpgradeButtons();
			}
		});
		stage.addActor(menuButton2);
		menuSet2 = true;
		brightButton = new Button(new TextureRegionDrawable(Assets.plusButton));
		brightButton.setWidth(70*widthscale);
		brightButton.setHeight(70*heightscale);
		brightButton.setPosition(widthscale*(width - 125), heightscale*(height*5/6 - 300 - 65));
		brightButton.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				Settings.buyBright();
				Color color2 = new Color(0f, .7f, 1f, .8f);
				gameWorld.getPlayerCharacter().getLight().setActive(false);
				gameWorld.getPlayerCharacter().setLight(new PointLight(gameWorld.getHandler(), 400, color2, Settings.brightness, 
						gameWorld.getPlayerCharacter().getPosition().x, gameWorld.getPlayerCharacter().getPosition().y));
				gameWorld.getPlayerCharacter().getLight().setSoft(false);
			}
		});
		if (Settings.bubbles >= Settings.brightCost){
			stage.addActor(brightButton);
			brightSet = true;
		}
		livesButton = new Button(new TextureRegionDrawable(Assets.plusButton));
		livesButton.setWidth(70*widthscale);
		livesButton.setHeight(70*heightscale);
		livesButton.setPosition(widthscale*(width - 125), heightscale*(height*5/6 - 400 - 65));
		livesButton.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				Settings.buyLife();
			}
		});
		if (Settings.bubbles >= Settings.livesCost){
			stage.addActor(livesButton);
			livesSet = true;
		}
		headstartButton = new Button(new TextureRegionDrawable(Assets.plusButton));
		headstartButton.setWidth(70*widthscale);
		headstartButton.setHeight(70*heightscale);
		headstartButton.setPosition(widthscale*(width - 125), heightscale*(height*5/6 - 500 - 65));
		headstartButton.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				Settings.buyHeadStart();

			}
		});
		if (Settings.bubbles >= Settings.headStartCost){
			stage.addActor(headstartButton);
			headstartSet = true;
		}
		laserButton = new Button(new TextureRegionDrawable(Assets.plusButton));
		laserButton.setWidth(70*widthscale);
		laserButton.setHeight(70*heightscale);
		laserButton.setPosition(widthscale*(width - 125), heightscale*(height*5/6 - 600 - 65));
		laserButton.addListener(new ClickListener(){
			@Override 
			public void clicked(InputEvent event, float x, float y){
				Settings.buyLasers();

			}
		});
		if (Settings.bubbles >= Settings.lasersCost){
			stage.addActor(laserButton);
			laserSet = true;
		} 
	}

	public void removeUpgradeButtons(){
		if (menuSet2){
			menuButton2.remove();
			menuSet = false;
		}
		if (brightSet){
			brightButton.remove();
			brightSet = false;
		}
		if (livesSet){
			livesButton.remove();
			livesSet = false;
		}
		if (headstartSet){
			headstartButton.remove();
			headstartSet = false;
		}
		if (laserSet){
			laserButton.remove();
			laserSet = false;
		}
	}

	public void removeBrightButton(){
		if (brightSet){
			brightButton.remove();
			brightSet = false;
		}
	}

	public void removeLivesButton(){
		if (livesSet){
			livesButton.remove();
			livesSet = false;
		}
	}

	public void removeHeadStartButton(){
		if (headstartSet){
			headstartButton.remove();
			headstartSet = false;
		}
	}

	public void removeLasersButton(){
		if (laserSet){
			laserButton.remove();
			laserSet = false;
		}
	}

	public Stage getStage(){
		return stage;
	}

	public void setRenderer(GameRenderer renderer){
		this.renderer = renderer;
	}

	public void setGameWorld(GameWorld world){
		this.gameWorld = world;
	}

}
