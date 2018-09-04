package com.leo.leogame.planefire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MenuScreen extends AbstractGameScreen{
    public Texture texture;
    public SpriteBatch batch;

    public MenuScreen(DirectedGame game) {
        super(game);
        init();
    }

    public void init(){
        texture = new Texture("badlogic.jpg");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture,0f,0f);
        batch.end();


    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }
}
