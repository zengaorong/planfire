package com.leo.leogame.planefire.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.leo.leogame.planefire.assetmanage.Asset;

public abstract class AbstractGameScreen implements Screen {
	protected DirectedGame game;
	
	
	public AbstractGameScreen(DirectedGame game) {
		super();
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		//Asset.instance.init();
	}

	@Override
	public void dispose() {
		Asset.instance.dispose();
	}

	public abstract InputProcessor getInputProcessor();;

}
