package com.leo.leogame.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class clicktest extends ApplicationAdapter {

    // 视口世界的宽高统使用 480 * 800, 并统一使用伸展视口（StretchViewport）
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    // 舞台
    private Stage stage;

    // 按钮 弹起 状态的纹理
    private Texture upTexture;

    // 按钮 按下 状态的纹理
    private Texture downTexture;

    // 按钮
    private Button button;

    @Override
    public void create() {
        // 设置日志输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // 使用伸展视口（StretchViewport）创建舞台
        stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));

        // 将输入处理设置到舞台（必须设置, 否则点击按钮没效果）
        Gdx.input.setInputProcessor(stage);

        /*
         * 第 1 步: 创建 弹起 和 按下 两种状态的纹理
         */
        upTexture = new Texture(Gdx.files.internal("Button.png"));
        downTexture = new Texture(Gdx.files.internal("Button2.png"));

        /*
         * 第 2 步: 创建 ButtonStyle
         */
        Button.ButtonStyle style = new Button.ButtonStyle();

        // 设置 style 的 弹起 和 按下 状态的纹理区域
        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));

        /*
         * 第 3 步: 创建 Button
         */
        button = new Button(style);

        // 设置按钮的位置
        button.setPosition(100, 200);

        // 给按钮添加点击监听器
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("is click");
            }
        });

        /*
         * 第 4 步: 添加 button 到舞台
         */
        stage.addActor(button);
    }

    @Override
    public void render() {
        // 黑色清屏
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 更新舞台逻辑
        stage.act();
        // 绘制舞台
        stage.draw();
    }

    @Override
    public void dispose() {
        // 应用退出时释放资源
        if (upTexture != null) {
            upTexture.dispose();
        }
        if (downTexture != null) {
            downTexture.dispose();
        }
        if (stage != null) {
            stage.dispose();
        }
    }
}
