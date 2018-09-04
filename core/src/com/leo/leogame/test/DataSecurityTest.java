package com.leo.leogame.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DataSecurityTest extends ApplicationAdapter {
    SpriteBatch sb;
    BitmapFont bf;
    DataSaveSecurity dataSaveSecurity;

    @Override
    public void create() {
        sb = new SpriteBatch();
        bf = new BitmapFont();

        dataSaveSecurity = new DataSaveSecurity();
        //for (int i = 0; i < 3; i++) {
        //    dataSaveSecurity.saveDataValue("data"+i, 100+i);
        //}
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        bf.draw(sb, ""+dataSaveSecurity.loadDataValue("data0", Integer.class), 40, 40);
        bf.draw(sb, ""+dataSaveSecurity.loadDataValue("data1", Integer.class), 40, 80);
        bf.draw(sb, ""+dataSaveSecurity.loadDataValue("data2", Integer.class), 40, 120);
        sb.end();
    }

    @Override
    public void dispose() {
        sb.dispose();
        bf.dispose();
    }
}
