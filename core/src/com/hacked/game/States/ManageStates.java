package com.hacked.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Charles on 11/12/2016.
 */

public class ManageStates {
    private Stack<State> states;

    public ManageStates(){
        states = new Stack<State>();
    }

    public void pop(){
        states.pop();
    }

    public void push(State state){
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
