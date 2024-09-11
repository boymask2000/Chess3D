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


    public static void cleanCursor(){
        Engine engine = Pool.getEngine();
        ImmutableArray<Entity> cellCursorEntities = engine.getEntitiesFor(
                Family.all(
                        CellCursorComponent.class
                ).get());

        for (int i = 0; i < cellCursorEntities.size(); i++)
            engine.removeEntity(cellCursorEntities.get(i));
    }

    public static CellCursorComponent createCellCursor(Zolla z) {
        cleanCursor();
        Vector3 pos = z.getPos();
        CellCursorComponent cellCursorComponent = new CellCursorComponent(z);
        cellCursorComponent.setPosition(pos);
        Entity entity = new Entity();
        entity.add(cellCursorComponent);

        Engine engine = Pool.getEngine();
        engine.addEntity(entity);

        return cellCursorComponent;
    }

    public static Entity creatAlberoElement(Zolla zolla) {

        Vector3 pos = zolla.getPos();
        Engine engine = Pool.getEngine();

        TerrainComponent comp = new TerrainComponent(zolla, ModelManager.getObjModel("qqPineTree1"));
        comp.setPosition(pos);
        //comp.setPosition(new Vector3(i * Constants.CELL_SIZE, 0, j * Constants.CELL_SIZE));
        Entity entity = new Entity();
        entity.add(comp);
        engine.addEntity(entity);

        return entity;
    }


}
