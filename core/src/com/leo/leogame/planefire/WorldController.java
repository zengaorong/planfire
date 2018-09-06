package com.leo.leogame.planefire;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Pool;
import com.leo.leogame.planefire.actor.*;
import com.leo.leogame.planefire.screens.DirectedGame;
import com.leo.leogame.planefire.util.CameraHelper;
import com.leo.leogame.test.OverlapsEdit;
import com.leo.leogame.test.RectangleActor;

import javax.xml.transform.Source;
import java.text.BreakIterator;


public class WorldController {
    private static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;
    public World b2world;
    DirectedGame game;
    public ImageButton firebutton;
    public Stage stage;

    public EnemyPlane enemyPlane;
    public PlayerPlane playerPlane;

    public ImageButton exportbutton;
    //public Bullet bullet;

    // 创建玩家子弹池 敌人子弹池
    public Pool<Bullet> bulletPool = new Pool<Bullet>() {
        @Override
        protected Bullet newObject() {
            return new Bullet();
        }
    };
    // 子弹池的使用者 活跃的子弹放到该数组中 碰撞检测也在这里
    public Array<Bullet> bullets = new Array<Bullet>();



    public WorldController(DirectedGame game) {
        this.game = game;
        init();
    }

    public void init(){
        enemyPlane = new EnemyPlane();
        playerPlane = new PlayerPlane();
        playerPlane.position.set(new Vector2(0,300f));
        //bullet = new Bullet();
        //bullet.position.set(new Vector2(0,200));
        //bullet.velocity.set(new Vector2(0,-20f));
        cameraHelper = new CameraHelper();
        firebutton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("button.png"))));
        stage = new Stage();

        firebutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("fire fire");
                Bullet bullet =  bulletPool.obtain();
                Vector2 vector2 = playerPlane.getbulletposition();
                bullet.position = vector2;
                bullet.velocity.set(new Vector2(0,-20f));
                bullets.add(bullet);
                //playerPlane.fire();
            }
        });
        //firebutton.setPosition(Gdx.graphics.getWidth()-firebutton.getWidth(), Gdx.graphics.getHeight()-firebutton.getHeight());
        firebutton.setPosition(0, 0);

        stage.addActor(firebutton);
        Gdx.input.setInputProcessor(stage);

    }


    public void update(float deltaTime) {
        if(bullets.size!=0){
            for(Bullet bullet:bullets){
                bullet.update(deltaTime);
            }
        }

        handleDebugInput(deltaTime);
        //if(playerPlane.bullet != null){
        //    playerPlane.bullet.update(deltaTime);
        //}
        testCollisions();
    }

    private void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;

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


        float camMoveSpeed = 50 * deltaTime;
        float scale = 0.5f * deltaTime;
        float sizeSpeend = 20*deltaTime;
        float camMoveSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveRentangel(-camMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveRentangel(camMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Keys.UP))
            moveRentangel(0, -camMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveRentangel(0, camMoveSpeed);
        //if(Gdx.input.isKeyPressed(Keys.X))
        //    changesize(0,0,scale);
        //if(Gdx.input.isKeyPressed(Keys.Z))
        //    changesize(0,0,-scale);
        //if(Gdx.input.isKeyPressed(Keys.W))
        //    changesize(0,sizeSpeend,0);
        //if(Gdx.input.isKeyPressed(Keys.S))
        //    changesize(0,-sizeSpeend,0);
        //if(Gdx.input.isKeyPressed(Keys.A))
        //    changesize(-sizeSpeend,0,0);
        //if(Gdx.input.isKeyPressed(Keys.D))
        //    changesize(sizeSpeend,0,0);
    }

    public void moveRentangel(float x,float y){
        playerPlane.position.x = playerPlane.position.x + x;
        playerPlane.position.y = playerPlane.position.y + y;

        //selected.setPosition(selected.getX()+x,selected.getY()+y);
    }

    //public void changesize(float x,float y,float scale){
    //    selected.setScale(selected.getScaleX() + scale);
    //    selected.setSize((selected.getWidth()+x)*selected.getScaleX(),(selected.getHeight()+y)*selected.getScaleX());
    //    selected.setScale(1);
    //}


    public void testCollisions(){
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        if(bullets.size==0)
            return;
        //

        //r2.set(enemyPlane.position.x-enemyPlane.origin.x,enemyPlane.position.y-enemyPlane.origin.y,enemyPlane.bounds.width,enemyPlane.bounds.height);

        for(Bullet bullet:bullets){
            r1.set(bullet.position.x - bullet.origin.x,bullet.position.y - bullet.origin.y,bullet.bounds.width,bullet.bounds.height);
            for(OverlapsEdit.RectangleData rectangleData:enemyPlane.rectangleDataArray){
                r2.set(rectangleData.position_x,rectangleData.position_y,rectangleData.width,rectangleData.height);
                if(r1.overlaps(r2)){
                    System.out.println("overlaps");
                    break;
                }
            }
        }







    }

}
