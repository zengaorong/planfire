package com.leo.leogame.test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RectangleActor extends Actor{
    private ShapeRenderer sr;

    public RectangleActor(){
        setPosition(0, 0);
        setSize(50, 50);

        sr = new ShapeRenderer();
    }

    public RectangleActor(float x, float y, float width,float height) {
        setPosition(x, y);
        setSize(width, height);
        sr = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        sr.setProjectionMatrix(batch.getProjectionMatrix());
        sr.setTransformMatrix(batch.getTransformMatrix());
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(this.getX(), this.getY(), this.getWidth(),this.getHeight());
        sr.end();
        batch.begin();
    }
}
