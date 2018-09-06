package com.leo.leogame.planefire.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.leo.leogame.planefire.assetmanage.Asset;
import com.leo.leogame.test.OverlapsEdit;

public class EnemyPlane extends AbstractGameObject {
    public TextureRegion enemyplane;
    TextureRegion reg = null;
    public Array<OverlapsEdit.RectangleData> rectangleDataArray = new Array<OverlapsEdit.RectangleData>();
    public EnemyPlane(){
        init();
    }

    public void init(){
        enemyplane = Asset.instance.assetEnemyPlane.enemyplane;
        reg = enemyplane;
        dimension.set(reg.getRegionWidth(),reg.getRegionHeight());
        bounds.set(0, 0, dimension.x, dimension.y);
        origin.set(dimension.x / 2, dimension.y / 2);

        Json json = new Json();
        json.setElementType(OverlapsEdit.ArrayData.class, "rectangleDataArray", OverlapsEdit.RectangleData.class);  // 指定Character中的item数据类型
        OverlapsEdit.ArrayData out = json.fromJson(OverlapsEdit.ArrayData.class, Gdx.files.internal("overlaps/enemyplane.json"));
        rectangleDataArray = out.rectangleDataArray;
    }

    public void render(SpriteBatch batch){

        batch.draw(reg.getTexture(), position.x - origin.x, position.y
                        - origin.y, origin.x, origin.y, dimension.x, dimension.y,
                scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, true);
    }

    public void render_rect(Matrix4 matrix4){
        shaperect.setProjectionMatrix(matrix4);
        shaperect.begin(ShapeRenderer.ShapeType.Line);
        shaperect.rect(position.x - origin.x, position.y - origin.y,reg.getRegionWidth(), reg.getRegionHeight());

        for(OverlapsEdit.RectangleData rectangleData :rectangleDataArray){
            shaperect.rect(position.x  + rectangleData.position_x, position.y + rectangleData.position_y,rectangleData.width, rectangleData.height);
        }

        shaperect.end();
    }
}
