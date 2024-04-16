package com.posbeu.littletown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class GameScreen implements Screen {
    LittleTown game;
    GameWorld gameWorld;
    ButtonScreen buttonScreen;
    public GameScreen(LittleTown game) {
        this.game = game;
        gameWorld = new GameWorld();
        buttonScreen = new ButtonScreen(game);
        Pool.setButtonScreen(buttonScreen);

        Stage stage = buttonScreen.getStage();
        Gdx.input.setInputProcessor(stage);
        MyInputTracker inputProcessor = gameWorld.getInputProcessor();
        inputProcessor.setEngine(gameWorld.getEngine());

        Gdx.input.setInputProcessor(new InputMultiplexer(stage,
                inputProcessor));


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameWorld.render(delta);
        buttonScreen.render(delta);
    }
    @Override
    public void resize(int width, int height) {
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
        gameWorld.dispose();
    }


// empty methods from Screen
}
