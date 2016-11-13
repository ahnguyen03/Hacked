package com.hacked.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hacked.game.DeathRun;

import java.util.Random;


/**
 * Created by Charles on 11/12/2016.
 */

public class Wario  {
    private static int GRAVITY = -25;
    private static int FORWARD = 8;
    private static final int MOVEMENT = 125;
    private int jump_height;
    private Vector3 position;
    private Vector3 velocity;
    private Texture wario;
    private Texture ground;
    private Rectangle bound;
    private Animation WarioAnimation;

    public Wario(int x, int y){
        jump_height = 850;
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        wario = new Texture("Wario.png");
        WarioAnimation = new Animation(new TextureRegion(wario),4,0.45f);
        bound = new Rectangle(x,y,wario.getWidth()/4,wario.getHeight());
        ground = new Texture("ground.png");
    }

    public void update(float dt){
        velocity.add(0,GRAVITY,0);
        WarioAnimation.update(dt);
        velocity.scl(dt);

        position.add(FORWARD, velocity.y,0);
        if (ground.getHeight()/2 > position.y) {
            position.y = ground.getHeight() / 2;
        }
        velocity.scl(1/dt);
        bound.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return wario;
    }

    public TextureRegion getWario(){
        return WarioAnimation.getFrame();
    }

    public void jump(){
        if(this.getPosition().y <= ground.getHeight()/2){
            velocity.y = jump_height;
        }
    }
    public void raiseSpeed(){
        FORWARD = FORWARD + 1;
        jump_height -= 5;
        GRAVITY -= 2;
        if (GRAVITY < -37){
            GRAVITY = 37;
        }
        if (jump_height < 600) {
            jump_height = 600;
        }
    }
    public void raisejump(){
    }
    public void normalSpeed(){
        FORWARD = 4;
    }
    public void initialize(){
        FORWARD = 5;
        jump_height = 950;
        GRAVITY = -25;
    }
    public Rectangle getbound(){
        return bound;
    }
    public void dispose(){
        getTexture().dispose();
    }
    public  int  getForward(){
        return FORWARD;
    }
}
