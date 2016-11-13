package com.hacked.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hacked.game.DeathRun;

import java.util.Random;

/**
 * Created by alex on 2016-11-12.
 */

public class Crate {
    public static final int Crate_width = 50;
    private Texture Crate;
    private Vector2 posCrate;
    private Rectangle boundsCrate;
    private Texture ground;
    private Vector2 velocity;
    private int gravity;
    Random rand;
    private int fluctuation = 130;

    public Crate(float x){
        gravity = 15;
        velocity = new Vector2(0,0);
        ground = new Texture("ground.png");
        Crate = new Texture("Thwomp.png");
        posCrate = new Vector2(x,ground.getHeight()/2);
        boundsCrate = new Rectangle(posCrate.x,posCrate.y,Crate.getWidth()-6,Crate.getHeight()-6);
    }

    public Texture getCrate(){
        return Crate;
    }
    public Vector2 getPosCrate(){
        return posCrate;
    }
    public void reposition(float x){
        posCrate.set(x,ground.getHeight()/2);
        boundsCrate.setPosition(posCrate.x,posCrate.y);
    }

    public void update(float dt){

        velocity.add(0,gravity);
        velocity.scl(dt);

        posCrate.add(0, velocity.y);
        if (posCrate.y > DeathRun.HEIGHT - this.getCrate().getHeight()){
            posCrate.y = DeathRun.HEIGHT - this.getCrate().getHeight();
            gravity = -14;
        }
        if (ground.getHeight()/2 > posCrate.y) {
            posCrate.y = ground.getHeight()/2;
            gravity = 11;
        }
        velocity.scl(1/dt);
        boundsCrate.setPosition(posCrate.x,posCrate.y);
    }

    public boolean collide(Rectangle runner){
        return runner.overlaps(this.boundsCrate);
    }

}
