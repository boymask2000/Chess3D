package com.posbeu.littletown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.posbeu.littletown.terrain.Terrain;

public class GameScreen implements Screen {
    private ChessGame game;
    private GameWorld gameWorld;
    private ButtonScreen buttonScreen;
    private StartScreen startScreen;
    private final Terrain terrain = new Terrain();

    public GameScreen(ChessGame game) {
        this.game = game;
        Pool.setTerrain(terrain);

        gameWorld = new GameWorld();

        MyInputTracker inputProcessor = gameWorld.getInputProcessor();
    //    inputProcessor.setEngine(gameWorld.getEngine());
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void initScreen(Screen sceen) {

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        terrain.render(4);
        //   FaseGioco fase = Pool.getFaseGioco();

        gameWorld.render(delta);


    }

    @Override
    public void resize(int width, int height) {
        if (gameWorld != null)
            gameWorld.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }


// empty methods from Screen
}
