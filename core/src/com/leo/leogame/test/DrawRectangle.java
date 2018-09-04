package com.leo.leogame.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class DrawRectangle extends ApplicationAdapter{
    public ShapeRenderer shapeRenderer;
    Stage stage;
    ImageButton button;
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;
    private Texture upTexture;
    RectangleActor newRectangle;
    RectangleActor selected=null;
    public boolean iscreat = false;
    public Array<RectangleActor> rectangleActors;


    // 按钮 按下 状态的纹理
    private Texture downTexture;
    @Override
    public void create() {
        stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));

        // 将输入处理设置到舞台（必须设置, 否则点击按钮没效果）
        Gdx.input.setInputProcessor(stage);

        button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("button.png"))));
        button.setPosition(Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-100);
        stage.addActor(button);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("hahahahha");
                newRectangle = new RectangleActor();
                newRectangle.setPosition(80,80);
                newRectangle.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("new class created");
                        RectangleActor temp = (RectangleActor) event.getListenerActor();
                        selected  = temp;
                        rectangleActors.add(temp);
                    }
                });
                stage.addActor(newRectangle);
                iscreat = true;
            }
        });

        RectangleActor ca = new RectangleActor(100, 100, 200, 200);
        //RectangleActor ca = new RectangleActor();
        ca.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("is click");
                String kkk = this.getClass().getName();
                this.getClass();
                System.out.println(event.getListenerActor().getWidth());
            }


        }
        );
        stage.addActor(ca);
        Gdx.input.setInputProcessor(stage);
    }

    public void updata(float deltaTime){
        if (Gdx.app.getType() != Application.ApplicationType.Desktop || selected==null)
            return;
        float camMoveSpeed = 50 * deltaTime;
        float scale = 0.5f * deltaTime;
        float sizeSpeend = 20*deltaTime;
        float camMoveSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveRentangel(-camMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveRentangel(camMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Keys.UP))
            moveRentangel(0, camMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveRentangel(0, -camMoveSpeed);
        if(Gdx.input.isKeyPressed(Keys.X))
            changesize(0,0,scale);
        if(Gdx.input.isKeyPressed(Keys.W))
            changesize(0,sizeSpeend,0);

        if(Gdx.input.isKeyPressed(Keys.P)){
            System.out.println("position is x:" + selected.getX() + "y:" + selected.getY());
            System.out.println("size is width:" + selected.getWidth() + "height:" + selected.getHeight());
        }
    }

    public void moveRentangel(float x,float y){
        selected.setPosition(selected.getX()+x,selected.getY()+y);
    }

    public void changesize(float x,float y,float scale){
        selected.setScale(selected.getScaleX() + scale);
        selected.setSize((selected.getWidth()+x)*selected.getScaleX(),(selected.getHeight()+y)*selected.getScaleX());
        selected.setScale(1);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
                0xff / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updata(Gdx.graphics.getDeltaTime());
        if(iscreat){
            RectangleActor jijij = newRectangle;
            iscreat = false;
        }

        stage.act();
        stage.draw();

    }
}
