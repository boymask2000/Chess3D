package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.posbeu.littletown.component.BaseComponent;
import com.posbeu.littletown.component.MarkerComponent;
import com.posbeu.littletown.component.ModelComponent;
import com.posbeu.littletown.component.MossaPossibileComponent;
import com.posbeu.littletown.component.PezzoComponent;
import com.posbeu.littletown.engine.ChessEngine;
import com.posbeu.littletown.engine.pezzi.Pezzo;


import java.util.ArrayList;
import java.util.List;

public class Pool {

    private static FreeTypeFontGenerator generator = null;
    private static BitmapFont genericfont;
    private static Engine engine;
    private static List<Entity> entities = new ArrayList<>();
    private static ButtonScreen buttonScreen;
    private static PerspectiveCamera camera;
    private static ChessEngine chessEngine;

    public static ButtonScreen getButtonScreen() {
        return buttonScreen;
    }

    public static void setButtonScreen(ButtonScreen buttonScreen) {
        Pool.buttonScreen = buttonScreen;
    }


    public static void addEntity(Entity e) {
        entities.add(e);
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static Engine getEngine() {
        return engine;
    }

    public static void setEngine(Engine engine) {
        Pool.engine = engine;
    }

    private static List<Entity> markers = new ArrayList<>();

    public static void addMarker(MarkerComponent m) {

        //   addInstance(m);
        Entity entity = new Entity();
        entity.add(m);
        markers.add(entity);
        engine.addEntity(entity);
    }

    public static void addMossaPossibile(MossaPossibileComponent m) {


        Entity entity = new Entity();
        entity.add(m);
        //    addEntity(entity);
        markers.add(entity);//  <----------- attenzionw
        engine.addEntity(entity);
    }

    public static PerspectiveCamera getCamera() {
        return camera;
    }

    public static BitmapFont getFont() {
        if (generator == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter par = new FreeTypeFontGenerator.FreeTypeFontParameter();
            par.size = 4;
            FileHandle fontFile = Gdx.files.internal("fonts/OpenSans-Italic.ttf");
            generator = new FreeTypeFontGenerator(fontFile);
            genericfont = generator.generateFont(par); // px
            genericfont.setColor(Color.WHITE);
            genericfont.getData().setScale(0.7f);
            genericfont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return genericfont;
    }

    public static void removeMarkers() {
        for (Entity m : markers) {
            engine.removeEntity(m);

        }
        markers.clear();
    }

    public static void setChessEngine(ChessEngine chessEngine) {
        Pool.chessEngine = chessEngine;
    }

    public static ChessEngine getChessEngine() {
        return chessEngine;
    }

    public static PezzoComponent getPezzoAtPosition(int i, int j) {
        ImmutableArray<Entity> pezziEntities = engine.getEntitiesFor(
                Family.all(
                        PezzoComponent.class
                ).get());
        for (int x = 0; x < pezziEntities.size(); x++) {
            PezzoComponent c =
                    pezziEntities.get(x).getComponent(PezzoComponent.class);
            if (c.getI() == i && c.getJ() == j) return c;

        }
        return null;
    }

    public static List<PezzoComponent> getPezzi() {
        ImmutableArray<Entity> pezziEntities = engine.getEntitiesFor(
                Family.all(
                        PezzoComponent.class
                ).get());

        List<PezzoComponent> out = new ArrayList<>();

        for (int x = 0; x < pezziEntities.size(); x++) {
            PezzoComponent c =
                    pezziEntities.get(x).getComponent(PezzoComponent.class);
            out.add( c);

        }

        return out;
    }
    public static List<ModelComponent> getCelle() {
        ImmutableArray<Entity> celleEntities = engine.getEntitiesFor(
                Family.all(
                        ModelComponent.class
                ).get());

        List<ModelComponent> out = new ArrayList<>();

        for (int x = 0; x < celleEntities.size(); x++) {
            ModelComponent c =
                    celleEntities.get(x).getComponent(ModelComponent.class);
            out.add( c);

        }

        return out;
    }

    public static PezzoComponent getPezzocomponentWithPezzo(Pezzo p) {
        ImmutableArray<Entity> pezziEntities = engine.getEntitiesFor(
                Family.all(
                        PezzoComponent.class
                ).get());

        for (int x = 0; x < pezziEntities.size(); x++) {
            PezzoComponent pc =
                    pezziEntities.get(x).getComponent(PezzoComponent.class);

            if (pc.getPezzo() == p) return pc;
        }

        return null;
    }

}
