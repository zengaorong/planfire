package com.leo.leogame.planefire.assetmanage;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.leo.leogame.planefire.staticdata.LeoData;
import org.w3c.dom.Text;

public class Asset implements Disposable{
    public static final Asset instance = new Asset();
    private AssetManager assetManager;
    public AssetEnemyPlane  assetEnemyPlane;
    public AssetBeginGameAnimation assetBeginGameAnimation;
    public AssetLevelDecoration levelDecoration;
    public AssetRock rock;

    public AssetBall ball;
    public AssetPlayerPlane PlayerPlane;
    public AssetBullet bullet;

    public void init(){
        assetManager = new AssetManager();

        assetManager.load(LeoData.ASSET_DIR,TextureAtlas.class);
        assetManager.load(LeoData.ASSET_begine_DIR,TextureAtlas.class);
        assetManager.load(LeoData.ASSET_bunny_DIR,TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas textureAtlas;
        textureAtlas =  assetManager.get(LeoData.ASSET_DIR,TextureAtlas.class);
        assetEnemyPlane = new AssetEnemyPlane(textureAtlas);
        PlayerPlane = new AssetPlayerPlane(textureAtlas);
        bullet =  new AssetBullet(textureAtlas);

        TextureAtlas atlas_begin_game;
        atlas_begin_game =  assetManager.get(LeoData.ASSET_begine_DIR,TextureAtlas.class);
        assetBeginGameAnimation = new AssetBeginGameAnimation(atlas_begin_game);

        TextureAtlas atlas_bunny_game;
        atlas_bunny_game =  assetManager.get(LeoData.ASSET_bunny_DIR,TextureAtlas.class);
        levelDecoration = new AssetLevelDecoration(atlas_bunny_game);
        rock = new AssetRock(atlas_bunny_game);

        ball = new AssetBall();



    }

    public static class AssetEnemyPlane{
        public AtlasRegion enemyplane;
        public AtlasRegion enemybomb;
        public AssetEnemyPlane(TextureAtlas atlas){
            enemyplane = atlas.findRegion("enemy3_n1");
            enemybomb = atlas.findRegion("enemy1_down3");
        }
    }

    public static class AssetPlayerPlane{
        public AtlasRegion PlayerPlane;
        public AssetPlayerPlane(TextureAtlas atlas){
            PlayerPlane = atlas.findRegion("hero1");
        }
    }

    public static class AssetBullet{
        public AtlasRegion bullet;
        public AssetBullet(TextureAtlas atlas){
            bullet = atlas.findRegion("bullet1");
        }
    }

    public static class AssetBeginGameAnimation{
        public Array<TextureRegion> array_plane_fly_into_screen = new Array<TextureRegion>();
        public Sprite sprint_title,sprint_background;
        public TextureRegion image_down,image_up;

        public  AssetBeginGameAnimation(TextureAtlas atlas){
            array_plane_fly_into_screen.add(atlas.findRegion("game_loading1"));
            array_plane_fly_into_screen.add(atlas.findRegion("game_loading2"));
            array_plane_fly_into_screen.add(atlas.findRegion("game_loading3"));
            array_plane_fly_into_screen.add(atlas.findRegion("game_loading4"));
            sprint_title = atlas.createSprite("shoot_copyright");
            sprint_background = atlas.createSprite("background");
            image_down = atlas.findRegion("btn_finish");
            image_up = atlas.findRegion("btn_finish");
        }
    }

    public class AssetLevelDecoration {
        public final AtlasRegion cloud01;
        public final AtlasRegion cloud02;
        public final AtlasRegion cloud03;
        public final AtlasRegion mountainLeft;
        public final AtlasRegion mountainRight;
        public final AtlasRegion waterOverlay;
        public final AtlasRegion carrot;
        public final AtlasRegion goal;

        public AssetLevelDecoration(TextureAtlas atlas) {
            cloud01 = atlas.findRegion("cloud01");
            cloud02 = atlas.findRegion("cloud02");
            cloud03 = atlas.findRegion("cloud03");
            mountainLeft = atlas.findRegion("mountain_left");
            mountainRight = atlas.findRegion("mountain_right");
            waterOverlay = atlas.findRegion("water_overlay");
            carrot = atlas.findRegion("carrot");
            goal = atlas.findRegion("goal");
        }
    }

    public class AssetRock {
        public final AtlasRegion edge;
        public final AtlasRegion middle;

        public AssetRock(TextureAtlas atlas) {
            edge = atlas.findRegion("rock_edge");
            middle = atlas.findRegion("rock_middle");
        }
    }

    public class AssetBall{
        public Texture ball;

        public AssetBall(){
            ball = new Texture("Ball.jpg");
        }
    }

    @Override
    public void dispose() {

    }
}
