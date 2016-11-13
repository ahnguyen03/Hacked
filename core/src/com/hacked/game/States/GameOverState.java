package com.hacked.game.States;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.hacked.game.DeathRun;

import com.badlogic.gdx.graphics.Color;

import javax.swing.plaf.ColorChooserUI;

/**
 * Created by alex on 2016-11-12.
 */

public class GameOverState extends State {
    private Texture bg;
    private BitmapFont bmf;
    private BitmapFont small_text;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public int final_score;
    public static List<Integer> scores = new ArrayList<Integer>();




    public GameOverState(ManageStates gam, int score,List<Integer> array){
        super(gam);
        bg = new Texture("mario-curtains.jpg");
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/alterebro-pixel-font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        parameter.color = Color.WHITE;
        bmf = generator.generateFont(parameter);
        parameter.size = 52;
        final_score = score;
        small_text = generator.generateFont(parameter);
        scores = array;
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
        int max_index = scores.indexOf(Collections.max(scores));
        sb.setProjectionMatrix(ocam.combined);
        sb.begin();
        sb.draw(bg,0,0, DeathRun.WIDTH,DeathRun.HEIGHT);
        bmf.draw(sb,"Your Score is: " + final_score,DeathRun.WIDTH/2 - 150, DeathRun.HEIGHT/2 + 100);
        bmf.draw(sb,"GAME OVER",DeathRun.WIDTH/2 - 100,DeathRun.HEIGHT/2);
        small_text.draw(sb,"Tap Again To Play",DeathRun.WIDTH/2 - 120, DeathRun.HEIGHT/2 - 100);
        small_text.draw(sb,"Highest Score: " + scores.get(max_index),DeathRun.WIDTH/2 + 10, DeathRun.HEIGHT/2 + 380  );
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
