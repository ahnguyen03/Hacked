package com.hacked.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hacked.game.DeathRun;

/**
 * Created by Charles on 11/12/2016.
 */

public class MenuState extends State {

    private Texture bg;
    private Texture startButton;


    public MenuState(ManageStates ms){
        super(ms);
        bg = new Texture("background.png");
        startButton = new Texture("start.png");
    }

    @Override
    public void dispose() {

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            ms.set(new PlayState(ms));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, DeathRun.WIDTH, DeathRun.HEIGHT);
        sb.draw(startButton,(DeathRun.WIDTH/2) - (startButton.getWidth()/2), DeathRun.HEIGHT/2);
        sb.end();
    }


}
