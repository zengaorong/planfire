package com.leo.leogame.planefire;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.leo.leogame.planefire.actor.*;
import com.leo.leogame.planefire.assetmanage.Asset;
import com.leo.leogame.planefire.screens.DirectedGame;
import com.leo.leogame.planefire.util.CameraHelper;
import com.badlogic.gdx.utils.Array;


public class WorldController {
    private static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;
    public World b2world;
    DirectedGame game;

    public EnemyPlane enemyPlane;
    public PlayerPlane playerPlane;
    public Bullet bullet;



    public WorldController(DirectedGame game) {
        this.game = game;
        init();
    }

    public void init(){
        enemyPlane = new EnemyPlane();
        playerPlane = new PlayerPlane();
        playerPlane.position.set(new Vector2(0,300f));
        bullet = new Bullet();
        bullet.position.set(new Vector2(0,200));
        bullet.velocity.set(new Vector2(0,-20f));
        cameraHelper = new CameraHelper();

    }

    private void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;
        //if (!cameraHelper.hasTarget(level.bunnyHead)) {
        //    // Camera Controls (move)
        //    float camMoveSpeed = 5 * deltaTime;
        //    float camMoveSpeedAccelerationFactor = 5;
        //    if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
        //        camMoveSpeed *= camMoveSpeedAccelerationFactor;
        //    if (Gdx.input.isKeyPressed(Keys.LEFT))
        //        moveCamera(-camMoveSpeed, 0);
        //    if (Gdx.input.isKeyPressed(Keys.RIGHT))
        //        moveCamera(camMoveSpeed, 0);
        //    if (Gdx.input.isKeyPressed(Keys.UP))
        //        moveCamera(0, camMoveSpeed);
        //    if (Gdx.input.isKeyPressed(Keys.DOWN))
        //        moveCamera(0, -camMoveSpeed);
        //    if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
        //        cameraHelper.setPosition(0, 0);
        //}

            // Camera Controls (move)
            float camMoveSpeed = 50 * deltaTime;
            float camMoveSpeedAccelerationFactor = 5;
            if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
                camMoveSpeed *= camMoveSpeedAccelerationFactor;
            if (Gdx.input.isKeyPressed(Keys.LEFT))
                moveCamera(-camMoveSpeed, 0);
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
                moveCamera(camMoveSpeed, 0);
            if (Gdx.input.isKeyPressed(Keys.UP))
                moveCamera(0, camMoveSpeed);
            if (Gdx.input.isKeyPressed(Keys.DOWN))
                moveCamera(0, -camMoveSpeed);
            if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
                cameraHelper.setPosition(0, 0);

        // Camera Controls (zoom)
        float camZoomSpeed = 1 * deltaTime;
        float camZoomSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            camZoomSpeed *= camZoomSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Keys.COMMA))
            cameraHelper.addZoom(camZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.PERIOD))
            cameraHelper.addZoom(-camZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.SLASH))
            cameraHelper.setZoom(1);
    }

    private void moveCamera(float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;
        cameraHelper.setPosition(x, y);
    }

    public void update(float deltaTime) {
        bullet.update(deltaTime);
        handleDebugInput(deltaTime);
        testCollisions();
    }

    public void testCollisions(){
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();

        r1.set(bullet.position.x - bullet.origin.x,bullet.position.y - bullet.origin.y,bullet.bounds.width,bullet.bounds.height);
        r2.set(enemyPlane.position.x-enemyPlane.origin.x,enemyPlane.position.y-enemyPlane.origin.y,enemyPlane.bounds.width,enemyPlane.bounds.height);

        if(r1.overlaps(r2)){
            System.out.println("overlaps");
        }
    }

}
