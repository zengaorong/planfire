package com.leo.leogame.planefire.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leo.leogame.planefire.assetmanage.Asset;

public class Ball extends AbstractGameObject {
    private Texture ball;

    public Ball() {
        init();
    }

    private void init() {
        ball = Asset.instance.ball.ball;
        dimension.set(0.25f, 0.5f);
        // Set bounding box for collision detection
        bounds.set(0, 0, dimension.x, dimension.y);
        origin.set(dimension.x / 2, dimension.y / 2);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        reg = new TextureRegion(ball);
        batch.draw(ball, position.x - origin.x, position.y
                        - origin.y, origin.x, origin.y, dimension.x, dimension.y,
                scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
