package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import com.badlogic.gdx.utils.JsonReader;
import com.posbeu.littletown.component.*;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.models.AlberoModel;
import com.posbeu.littletown.terrain.Zolla;
import com.posbeu.littletown.terrain.ZollaElement;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {

    private static void createStaticEntity(Model model, float x, float y,
                                           float z, int i, int j) {
        final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y,
                z);

        modelComponent.setI(i);
        modelComponent.setJ(j);

        entity.add(modelComponent);

        //   Pool.addInstance(modelComponent);
        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
    }

    private static void createStaticEntity(Model model, float x, float y,
                                           float z) {
        final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y,
                z);

        entity.add(modelComponent);

        //   Pool.addInstance(modelComponent);
        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
    }


    public static void cleanCursor() {
        Engine engine = Pool.getEngine();
        ImmutableArray<Entity> cellCursorEntities = engine.getEntitiesFor(
                Family.all(
                        CellCursorComponent.class
                ).get());

        for (int i = 0; i < cellCursorEntities.size(); i++)
            engine.removeEntity(cellCursorEntities.get(i));
    }

    public static Entity createCellCursor(Zolla z) {
        cleanCursor();
        return createCellCursor0(z);

    }

    public static Entity createCellCursor0(Zolla z) {

        Vector3 pos = z.getPos();
        CellCursorComponent cellCursorComponent = new CellCursorComponent(z);
        cellCursorComponent.setPosition(pos);
        System.out.println("createCellCursor0" + pos);


        Entity entity = new Entity();
        entity.add(cellCursorComponent);
        cellCursorComponent.getInstance().transform.setTranslation(pos);

        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
        return entity;
    }

    public static Entity createCellCursor0old(Zolla z) {

        Vector3 pos = z.getPos();
        CellCursorComponent cellCursorComponent = new CellCursorComponent(z);
        cellCursorComponent.setPosition(pos);
        System.out.println("createCellCursor0" + pos);
        return createComponent(cellCursorComponent);
    }

    public static void remove(Entity c) {
        Engine engine = Pool.getEngine();
        engine.removeEntity(c);
    }

    private static Entity creatAlberoElement(Zolla zolla) {

        Vector3 pos = zolla.getPos();

        TerrainComponent comp = new TerrainComponent(zolla, ModelManager.getObjModel("treepack1"));
        comp.setPosition(pos);
        return createComponent(comp);
    }

    public static Entity createTerrainElement(Zolla zolla) {

        Vector3 pos = zolla.getPos();
        Engine engine = Pool.getEngine();
        Entity entity = new Entity();

        if (zolla.getElement() == ZollaElement.ALBERO) {
            creatAlberoElement(zolla);

        }

        engine.addEntity(entity);

        return entity;
    }

    private static Entity createComponent(BaseComponent comp) {

        Entity entity = new Entity();
        entity.add(comp);

        Engine engine = Pool.getEngine();
        engine.addEntity(entity);

        return entity;
    }

}
