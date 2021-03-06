package com.mygdx.game.handlers;

import com.mygdx.game.Game;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Play;

import java.util.Stack;

/**
 * Created by buef on 11.04.2016.
 */
public class GameStateManager {

    private Game game;

    private Stack<GameState> gameStates;

    public static final int PLAY = 1;

    public GameStateManager(Game game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public Game game () {
        return this.game;
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();
    }

    public void touch(float x, float y) {
        gameStates.peek().touch(x,y);
    }

    private GameState getState(int state) {
        if(state == PLAY) return new Play(this);

        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }




}
