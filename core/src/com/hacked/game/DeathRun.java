package com.hacked.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hacked.game.States.ManageStates;
import com.hacked.game.States.MenuState;

public class DeathRun extends Game {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 480;
    public static final String TITLE = "DeathRun";

    private SpriteBatch batch;
    private ManageStates ms;

    @Override
    public void create () {
        batch = new SpriteBatch();

        ms = new ManageStates();
        ms.push(new MenuState(ms));
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ms.update(Gdx.graphics.getDeltaTime());
        ms.render(batch);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
