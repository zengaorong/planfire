package com.leo.leogame.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.leo.leogame.planefire.actor.PlayerPlane;
import com.leo.leogame.planefire.assetmanage.Asset;
import com.badlogic.gdx.Input.Keys;
import com.leo.leogame.planefire.staticdata.LeoData;
import com.leo.leogame.planefire.util.CameraHelper;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class OverlapsEdit extends ApplicationAdapter {
    TextureRegion editpic;
    SpriteBatch batch;
    OrthographicCamera camera;
    ShapeRenderer editpicshape;
    PlayerPlane playerPlane;
    public CameraHelper cameraHelper;
    public Stage stage;
    public ImageButton exportbutton;
    public String picname;
    public Array<RectangleData> rectangleDataArray = new Array<RectangleData>();
    public TextButton addbutton;
    public Skin skin;

    RectangleActor newRectangle;
    public boolean iscreat = false;
    public Array<RectangleActor> rectangleActors = new Array<RectangleActor>();
    public RectangleActor selected;



    @Override
    public void create() {
        Asset.instance.init();
        batch = new SpriteBatch();
        editpicshape = new ShapeRenderer();
        camera = new OrthographicCamera(480,800);
        playerPlane = new PlayerPlane();
        cameraHelper = new CameraHelper();
        editpic = Asset.instance.assetEnemyPlane.enemyplane;
        picname = "enemyplane";
        stage = new Stage();



        Json json = new Json();
        json.setElementType(ArrayData.class, "rectangleDataArray", RectangleData.class);  // 指定Character中的item数据类型
        if(Gdx.files.internal("overlaps/" + picname + ".json")==null){
            rectangleDataArray = null;
        }else{
            ArrayData out = json.fromJson(ArrayData.class, Gdx.files.internal("overlaps/" + picname + ".json"));
            rectangleDataArray = out.rectangleDataArray;
        }

        skin = new Skin(Gdx.files.internal(LeoData.SKIN_LIBGDX_UI));
        addbutton = new TextButton("Button", skin);
        addbutton.setPosition(0, Gdx.graphics.getHeight()-addbutton.getHeight());
        addbutton.addListener(new ClickListener() {
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
        stage.addActor(addbutton);


        exportbutton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("button.png"))));
        exportbutton.setPosition(Gdx.graphics.getWidth()-exportbutton.getWidth(), Gdx.graphics.getHeight()-exportbutton.getHeight());
        exportbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileHandle file = Gdx.files.local("overlaps/" + picname + ".json");
                Json json = new Json();
                json.setOutputType(OutputType.json);
                //file.writeString(Base64Coder.encodeString(json.prettyPrint(save)), false);
                Save save = new Save();
                save.name = "Rectangles";
                save.size = 1;
                //save.testdata.put("data1","abc");
                //save.testdata.put("data2","你好");
                for(RectangleActor rectangleActor:rectangleActors){
                    RectangleData fff = new RectangleData();
                    fff.position_x = rectangleActor.getX() - Gdx.graphics.getWidth()/2;
                    fff.position_y = rectangleActor.getY() - Gdx.graphics.getHeight()/2;
                    fff.width = rectangleActor.getWidth();
                    fff.height = rectangleActor.getHeight();
                    save.rectangleDataArray.add(fff);
                }


                file.writeString(json.prettyPrint(save), false);
            }
        });
        stage.addActor(exportbutton);


        for(RectangleData data : rectangleDataArray){
            RectangleActor rectangleActor = new RectangleActor(data.position_x + Gdx.graphics.getWidth()/2,data.position_y + Gdx.graphics.getHeight()/2,data.width,data.height);
            rectangleActors.add(rectangleActor);
            rectangleActor.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("new class created");
                    RectangleActor temp = (RectangleActor) event.getListenerActor();
                    selected  = temp;
                }
            });
            stage.addActor(rectangleActor);
            //rectangleActors.add(rectangleActor);
        }
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
                0xff / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraHelper.applyTo(camera);
        handleDebugInput(Gdx.graphics.getDeltaTime());

        TextureRegion reg = null;
        reg = editpic;
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(reg.getTexture(), 0 - reg.getRegionWidth()/2 , 0 - reg.getRegionHeight()/2 , reg.getRegionWidth()/2, reg.getRegionHeight()/2, reg.getRegionWidth(), reg.getRegionHeight(),
                1, 1, 0, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, true);
        batch.end();


        stage.act();
        stage.draw();

        editpicshape.setProjectionMatrix(camera.combined);
        editpicshape.begin(ShapeRenderer.ShapeType.Line);
        editpicshape.rect(0 - reg.getRegionWidth()/2 , 0 - reg.getRegionHeight()/2,reg.getRegionWidth()/2, reg.getRegionHeight()/2, reg.getRegionWidth(), reg.getRegionHeight(),1, 1, 0);
        editpicshape.end();
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
            moveRentangel(0, camMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveRentangel(0, -camMoveSpeed);
        if(Gdx.input.isKeyPressed(Keys.X))
            changesize(0,0,scale);
        if(Gdx.input.isKeyPressed(Keys.Z))
            changesize(0,0,-scale);
        if(Gdx.input.isKeyPressed(Keys.W))
            changesize(0,sizeSpeend,0);
        if(Gdx.input.isKeyPressed(Keys.S))
            changesize(0,-sizeSpeend,0);
        if(Gdx.input.isKeyPressed(Keys.A))
            changesize(-sizeSpeend,0,0);
        if(Gdx.input.isKeyPressed(Keys.D))
            changesize(sizeSpeend,0,0);

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


    public static class ArrayData{
        public String name;
        public int size;

        public Array<RectangleData> rectangleDataArray = new Array<RectangleData>();

        @Override
        public String toString() {
            String string = new String();
            string += "Name: " + name + "\n";
            string += "size: " + size + "\n";
            string += "RectangleData: ";

            for (RectangleData rectangleData : rectangleDataArray) {
                string += rectangleData.toString() + " ";
            }

            return string;
        }
    }

    public static class RectangleData{
        public float position_x;
        public float position_y;
        public float width;
        public float height;
    }

    private static class Save {
        public String name;
        public int size;
        //public ObjectMap<String, Object> testdata = new ObjectMap<String, Object>();
        public Array<RectangleData> rectangleDataArray = new Array<RectangleData>();
    }
}
