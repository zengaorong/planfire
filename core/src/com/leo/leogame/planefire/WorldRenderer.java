package com.leo.leogame.planefire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.leo.leogame.planefire.actor.Bullet;
import com.leo.leogame.planefire.actor.Carrot;
import com.leo.leogame.planefire.actor.Rock;
import com.leo.leogame.planefire.assetmanage.Asset;
import com.leo.leogame.planefire.staticdata.LeoData;

public class WorldRenderer {
    private WorldController worldController;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private OrthographicCamera cameraGUI;
    private Box2DDebugRenderer b2debugRenderer;
    private static final boolean DEBUG_DRAW_BOX2D_WORLD = false;
    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(LeoData.VIEWPORT_WIDTH,
                LeoData.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();

        cameraGUI = new OrthographicCamera(LeoData.VIEWPORT_GUI_WIDTH,
                LeoData.VIEWPORT_GUI_HEIGHT);
        cameraGUI.position.set(0, 0, 0);
        cameraGUI.setToOrtho(true); // flip y-axis
        cameraGUI.update();

        b2debugRenderer = new Box2DDebugRenderer();

    }

    public void render() {
        // renderTestObjects();
        renderWorld(batch);
        //renderGui(batch);
    }

    private void renderWorld(SpriteBatch batch) {
        worldController.cameraHelper.applyTo(cameraGUI);
        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();
        worldController.enemyPlane.render(batch);
        worldController.playerPlane.render(batch);
        for(Bullet bullet:worldController.bullets){
            bullet.render(batch);
        }
        batch.end();
        worldController.playerPlane.render_rect(cameraGUI.combined);

        worldController.stage.act();
        worldController.stage.draw();
        //worldController.bullet.render_rect(cameraGUI.combined);
        worldController.enemyPlane.render_rect(cameraGUI.combined);
    }

    private void renderGui(SpriteBatch batch) {
        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();
        //renderGuiScore(batch);
        batch.end();
    }

    //private void renderGuiScore(SpriteBatch batch) {
    //    worldController.enemyPlane.render(batch);
    //}
}
