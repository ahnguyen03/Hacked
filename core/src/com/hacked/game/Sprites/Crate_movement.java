package com.hacked.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hacked.game.DeathRun;

/**
 * Created by alex on 2016-11-12.
 */

public class Crate_movement {
    public static final int Crate_width = 50;
    private int gravity;
    private Texture Crate;
    private Vector2 posCrate;
    private Rectangle boundsCrate;
    private Texture ground;
    private Vector2 velocity;


    public Crate_movement(float x){
        gravity = 15;
        velocity = new Vector2(0,0);
        ground = new Texture("ground.png");
        Crate = new Texture("Thwomp.png");
        posCrate = new Vector2(x,ground.getHeight()/2);
        boundsCrate = new Rectangle(posCrate.x,posCrate.y,Crate.getWidth()-6,Crate.getHeight()-6);
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
    public Texture getCrate(){
        return Crate;
    }
    public Vector2 getPosCrate(){
        return posCrate;
    }
    public void reposition(float x){
        posCrate.set(x,posCrate.y);
        boundsCrate.setPosition(posCrate.x,posCrate.y);
    }


    public boolean collide(Rectangle runner){
        return runner.overlaps(this.boundsCrate);
    }

}
