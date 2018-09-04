package com.leo.leogame.planefire.actor;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.leo.leogame.planefire.assetmanage.Asset;

public class BeginGame extends AbstractGameObject{
    Array<TextureRegion> array_plane_fly_into_screen = new Array<TextureRegion>();
    Animation animation_plane_fly_into_screen;
    float statetime = 0f;
    float flyMovex;
    float y;
    TextureRegion region;
    Sprite title,background;
    ImageButton back;
    Stage ui;
    public BeginGame(){
        init();
    }
    public void init(){
        array_plane_fly_into_screen = Asset.instance.assetBeginGameAnimation.array_plane_fly_into_screen;
        title = Asset.instance.assetBeginGameAnimation.sprint_title;
        background = Asset.instance.assetBeginGameAnimation.sprint_background;
        animation_plane_fly_into_screen = new Animation(0.3f,array_plane_fly_into_screen);
        region = array_plane_fly_into_screen.get(2);
        flyMovex = (Gdx.graphics.getWidth() - region.getRegionWidth()) / 2;
        y = (Gdx.graphics.getHeight() - (title.getHeight() + 150));
        ui = new Stage();

        ImageButtonStyle style = new ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(Asset.instance.assetBeginGameAnimation.image_up);
        style.imageDown = new TextureRegionDrawable(Asset.instance.assetBeginGameAnimation.image_up);
        back = new ImageButton(style);

        back.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("button click");

            }
        });
        back.setX(Gdx.graphics.getWidth() - back.getWidth() - 2);
        back.setY(10);
        ui.addActor(back);
        Gdx.input.setInputProcessor(ui);

        new Timer().scheduleTask(new Task() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("scheduleTesk is work");
            }
        }, 3);
    }
    public void render(SpriteBatch batch,float delta){
        batch.begin();
        background.draw(batch);
        title.draw(batch);
        statetime += delta;
        TextureRegion frame = animation_plane_fly_into_screen.getKeyFrame(statetime,true);
        batch.draw(frame, flyMovex, y - 40 - region.getRegionHeight());
        batch.end();
        ui.draw();
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
