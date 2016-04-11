package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.handlers.GameStateManager;

import java.util.List;

/**
 * Created by buef on 11.04.2016.
 */
public class Play extends  GameState{

    private World world;
    private Box2DDebugRenderer b2dr;
    private List<FixtureDef> map;

    public Play(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0,-9.81f), true);

        System.out.println("1");

        b2dr = new Box2DDebugRenderer();



        for(int y = 0; y<10; y++)
            for(int x = 0; x<10; x++) {
                // create platform
                BodyDef bdef = new BodyDef();
                bdef.position.set(x*10, y*10);
                bdef.type = BodyDef.BodyType.StaticBody;

                Body body = world.createBody(bdef);

                PolygonShape shape = new PolygonShape();

                shape.setAsBox(10,10);

                FixtureDef fdef = new FixtureDef();
                fdef.shape = shape;


                body.createFixture(fdef);
            }
    }

    @Override
    public void touch(float x, float y) {
        System.out.println(x);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
    }

    @Override
    public void render() {
        //clear
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, cam.combined);
    }

    @Override
    public void dispose() {

    }
}
