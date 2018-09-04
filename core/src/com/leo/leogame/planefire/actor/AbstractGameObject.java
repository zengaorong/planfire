package com.leo.leogame.planefire.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by leo on 2017/9/29.
 */

public abstract class AbstractGameObject{
    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    public Vector2 velocity; // 现在的速度
    public Vector2 terminalVelocity;// 最大速度
    public Vector2 friction;// 摩擦力
    public Vector2 acceleration;// 加速度
    public Rectangle bounds;// 刚体

    public Body body;

    public float stateTime;
    public Animation animation;
    public ShapeRenderer shaperect;
    public boolean debugEnabled = true;

    public AbstractGameObject() {
        position = new Vector2();
        dimension = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;

        velocity = new Vector2();
        terminalVelocity = new Vector2(30, 30);
        friction = new Vector2();
        acceleration = new Vector2();
        bounds = new Rectangle();
        shaperect = new ShapeRenderer();

    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        stateTime = 0;
    }

        public void update(float deltaTime) {
            stateTime += deltaTime;
        if (body == null) {
            updateMotionX(deltaTime);
            updateMotionY(deltaTime);
            // Move to new position
            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;
        } else {
            position.set(body.getPosition());
            rotation = body.getAngle() * MathUtils.radiansToDegrees;
        }
    }

    public abstract void render(SpriteBatch batch);

    protected void updateMotionX(float deltaTime) {
        if (velocity.x != 0) {
            // Apply friction
            if (velocity.x > 0) {
                velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
            } else {
                velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
            }
        }
        // Apply acceleration
        velocity.x += acceleration.x * deltaTime;
        // Make sure the object's velocity does not exceed the
        // positive or negative terminal velocity
        velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x,
                terminalVelocity.x);
    }

    protected void updateMotionY(float deltaTime) {
        if (velocity.y != 0) {
            // Apply friction
            if (velocity.y > 0) {
                velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
            } else {
                velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
            }
        }
        // Apply acceleration
        velocity.y += acceleration.y * deltaTime;
        // Make sure the object's velocity does not exceed the
        // positive or negative terminal velocity
        velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y,
                terminalVelocity.y);
    }
}
