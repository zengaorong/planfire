package com.leo.leogame.planefire.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.leo.leogame.planefire.WorldController;
import com.leo.leogame.planefire.WorldRenderer;

import javax.microedition.khronos.opengles.GL10;

public class TestScreen extends AbstractGameScreen {

    WorldController worldController;
    WorldRenderer worldRenderer;

    public TestScreen(DirectedGame game){
        super(game);
    }

    @Override
    public void render(float deltaTime) {
        //super.render(delta);
        //worldController.update(deltaTime);

        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
                0xff / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldController.update(deltaTime);
        worldRenderer.render();
    }

    @Override
    public void show() {
        System.out.println("TestScreen work");
        worldController = new WorldController(game);
        worldRenderer = new WorldRenderer(worldController);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }
}
