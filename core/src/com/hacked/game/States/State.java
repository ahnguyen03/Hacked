package com.hacked.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Charles on 11/12/2016.
 */

public abstract class State {

    protected ManageStates ms;
    protected OrthographicCamera ocam;

    protected State(ManageStates ms){
        this.ms = ms;
        ocam = new OrthographicCamera();
        ocam.setToOrtho(false);
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
