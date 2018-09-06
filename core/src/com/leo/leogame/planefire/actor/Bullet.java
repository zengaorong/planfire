package com.leo.leogame.planefire.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.leo.leogame.planefire.assetmanage.Asset;

public class Bullet extends AbstractGameObject implements Poolable {

    public TextureRegion bullet;
    TextureRegion reg = null;
    public boolean alive;
    public Bullet(){
        init();
    }

    public void init(){
        bullet = Asset.instance.bullet.bullet;
        reg = bullet;
        dimension.set(reg.getRegionWidth(),reg.getRegionHeight());
        bounds.set(0, 0, dimension.x, dimension.y);
        origin.set(dimension.x / 2, dimension.y / 2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(reg.getTexture(), position.x - origin.x, position.y
                        - origin.y, origin.x, origin.y, dimension.x, dimension.y,
                scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }

    public void render_rect(Matrix4 matrix4){
        shaperect.setProjectionMatrix(matrix4);
        shaperect.begin(ShapeRenderer.ShapeType.Line);
        shaperect.rect(position.x - origin.x, position.y - origin.y,reg.getRegionWidth(), reg.getRegionHeight());
        shaperect.end();
    }

    @Override
    public void reset() {
        position.set(0,0);
        alive = false;
    }
}
