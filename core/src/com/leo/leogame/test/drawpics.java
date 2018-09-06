package com.leo.leogame.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leo.leogame.planefire.actor.EnemyPlane;
import com.leo.leogame.planefire.assetmanage.Asset;

import javax.microedition.khronos.opengles.GL;

public class drawpics extends ApplicationAdapter {
    public EnemyPlane enemyPlane;
    public TextureRegion bombregion;
    public SpriteBatch batch;
    @Override
    public void create() {
        Asset.instance.init();
        batch = new SpriteBatch();
        bombregion = Asset.instance.assetEnemyPlane.enemybomb;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,10,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bombregion,0,0);
        batch.end();
    }
}
