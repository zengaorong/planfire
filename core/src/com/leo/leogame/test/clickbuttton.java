package com.leo.leogame.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class clickbuttton extends ApplicationAdapter{
    public ImageButton exportbutton;
    public Stage stage;

    @Override
    public void create() {
        stage = new Stage();

        exportbutton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("button.png"))));
        exportbutton.setPosition(Gdx.graphics.getWidth()-exportbutton.getWidth(), Gdx.graphics.getHeight()-exportbutton.getHeight());
        exportbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("sfsajfijio");
            }
        });
        stage.addActor(exportbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        stage.act();
        stage.draw();
    }
}
