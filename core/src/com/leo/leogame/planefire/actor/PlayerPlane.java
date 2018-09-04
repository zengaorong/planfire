package com.leo.leogame.planefire.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.leo.leogame.planefire.assetmanage.Asset;

public class PlayerPlane extends AbstractGameObject {
    public TextureRegion enemyplane;
    TextureRegion reg = null;
    public PlayerPlane(){
        init();
    }

    public void init(){
        enemyplane = Asset.instance.PlayerPlane.PlayerPlane;
        reg = enemyplane;
        dimension.set(reg.getRegionWidth(),reg.getRegionHeight());
        bounds.set(0, 0, dimension.x, dimension.y);
        origin.set(dimension.x / 2, dimension.y / 2);
    }

    public void render(SpriteBatch batch){
        TextureRegion reg = null;
        reg = enemyplane;

        batch.draw(reg.getTexture(), position.x - origin.x, position.y
                        - origin.y, origin.x, origin.y, dimension.x, dimension.y,
                scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, true);


    }

    public void render_rect(Matrix4 matrix4){
        shaperect.setProjectionMatrix(matrix4);
        shaperect.begin(ShapeRenderer.ShapeType.Line);
        shaperect.rect(position.x - origin.x, position.y - origin.y,reg.getRegionWidth(), reg.getRegionHeight());
        shaperect.end();
    }
}
