package com.hacked.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.particles.values.WeightMeshSpawnShapeValue;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.hacked.game.DeathRun;

import com.hacked.game.Sprites.Crate;
import com.hacked.game.Sprites.Crate_movement;
import com.hacked.game.Sprites.Wario;
import java.util.*;

/**
 * Created by Charles on 11/12/2016.
 */


public class PlayState extends State {
    private int Amount_Crate = 4;
    private static  int Crate_Spacing = 420;

    private Texture bg;
    private Texture ground;
    private Texture hole;
    private Vector2 bgPos1;
    private Vector2 bgPos2;
    private Vector2 groundPos1;
    private Vector2 groundPos2;

    private Wario wario;
    private Array<Crate> crates;
    private int score;
    private String scoreLabel;
    public static String MaxscoreLabel;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter_small;
    private BitmapFont bmf;
    private BitmapFont small_text;
    private int crateCount;
    private Random rand;
    private static int FLUCTUATION = 130;
    public static int Random;

    public static List<Integer> scores = new ArrayList<Integer>();

    public PlayState(ManageStates ms) {

        super(ms);
        score = 0;
        if(scores.isEmpty() == false){
            int max_index = scores.indexOf(Collections.max(scores));
            MaxscoreLabel = Integer.toString(scores.get(max_index));
        }
        scoreLabel = Integer.toString(score);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/alterebro-pixel-font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        parameter.color = Color.WHITE;
        bmf = generator.generateFont(parameter);
        parameter_small = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter_small.size = 42;
        small_text = generator.generateFont(parameter_small);
        bg = new Texture("background.png");
        bgPos1 = new Vector2(ocam.position.x - ocam.viewportWidth/2, 0);
        bgPos2 = new Vector2(ocam.position.x - ocam.viewportWidth/2 + DeathRun.WIDTH,0);
        ground = new Texture("ground.png");

        groundPos1 = new Vector2(ocam.position.x - ocam.viewportWidth/2, 0);
        groundPos2 = new Vector2(ocam.position.x - ocam.viewportWidth/2 + DeathRun.WIDTH,0);
        wario = new Wario(DeathRun.WIDTH/6, ground.getHeight()/2);
        wario.initialize();
        ocam.setToOrtho(false, DeathRun.WIDTH, DeathRun.HEIGHT);
        crates = new Array<Crate>();
        crateCount = new Integer(0);

        for (int i = 1; i <= Amount_Crate; i++){
            crates.add(new Crate(i* (Crate_Spacing + Crate.Crate_width)));

        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            wario.jump();

        }


    }

    @Override
    public void dispose() {
        bg.dispose();
        wario.dispose();
        ground.dispose();


    }

    @Override
    public void update(float dt) {

        handleInput();

        ocam.position.x = wario.getPosition().x + 120;
        groundUpdate();
        bgUpdate();
        ocam.update();


        for(int i = 0; i < crates.size; i++){
            Crate crate = crates.get(i);

            if(ocam.position.x - (ocam.viewportWidth/2) > crate.getPosCrate().x + crate.getCrate().getWidth()) {
                if(score > 5) {
                    System.out.println("LOL");
                    int Spacing = 550;
                    crate.reposition(crate.getPosCrate().x + ((Crate.Crate_width + Spacing) * Amount_Crate));
                }
                else{
                    crate.reposition(crate.getPosCrate().x + ((Crate.Crate_width + Crate_Spacing) * Amount_Crate));
                }

                if (i ==3){
                    wario.raiseSpeed();
                }
            }

            if(score > 5){
                crate.update(dt);
            }
            if (crate.collide(wario.getbound())){
                if(!scores.contains(score)) {
                    scores.add(score);
                }
                if(scores.size()>= 3){
                    int minIndex = scores.indexOf(Collections.min(scores));
                    scores.remove(minIndex);
                }

                ms.set(new GameOverState(ms,score,scores));
            }
        }
        wario.update(dt);
        updateScore();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(ocam.combined);
        sb.begin();
        sb.draw(bg,bgPos1.x,bgPos1.y, DeathRun.WIDTH,DeathRun.HEIGHT);
        sb.draw(bg,bgPos2.x,bgPos2.y, DeathRun.WIDTH,DeathRun.HEIGHT);
        sb.draw(ground,groundPos1.x,groundPos1.y,DeathRun.WIDTH,ground.getHeight());
        sb.draw(ground,groundPos2.x,groundPos2.y,DeathRun.WIDTH,ground.getHeight());
        for (Crate crate: crates){
            if(score < 5) {
                sb.draw(crate.getCrate(), crate.getPosCrate().x, ground.getHeight() / 2 - 5);
            }
            else{
                sb.draw(crate.getCrate(),crate.getPosCrate().x,crate.getPosCrate().y);
            }

        }
        sb.draw(wario.getWario(), wario.getPosition().x, wario.getPosition().y);
        bmf.draw(sb, scoreLabel, wario.getPosition().x + 110, 700);
        if(scores.isEmpty() == false){
            small_text.draw(sb, "Highest Score: " + MaxscoreLabel, wario.getPosition().x + 130, 800);
        }
        sb.end();
    }

    public void groundUpdate(){
        if (ocam.position.x - (ocam.viewportWidth/2)> groundPos1.x + DeathRun.WIDTH){
            groundPos1.add(DeathRun.WIDTH*2,0);
        }
        if (ocam.position.x - (ocam.viewportWidth/2)> groundPos2.x + DeathRun.WIDTH){
            groundPos2.add(DeathRun.WIDTH*2,0);
        }
    }
    public void bgUpdate(){
        if (ocam.position.x - (ocam.viewportWidth/2) > bgPos1.x + DeathRun.WIDTH){
            bgPos1.add(DeathRun.WIDTH*2,0);
        }
        if (ocam.position.x - (ocam.viewportWidth/2) > bgPos2.x + DeathRun.WIDTH){
            bgPos2.add(DeathRun.WIDTH*2,0);
        }
    }

    public void updateScore(){
        if ((wario.getPosition().x - wario.getbound().getWidth()/2) > crates.get(crateCount).getPosCrate().x){
            crateCount +=1;
            score +=1;
            scoreLabel = Integer.toString(score);

            if(scores.isEmpty() == false) {
                int max_index = scores.indexOf(Collections.max(scores));
                if (score > scores.get(max_index)) {
                    MaxscoreLabel = Integer.toString(score);
                }
            }
            if (crateCount == 4){
                crateCount = 0;
            }
        }
    }

}
