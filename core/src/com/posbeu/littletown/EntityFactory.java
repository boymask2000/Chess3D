package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import com.posbeu.littletown.city.Pozzo;
import com.posbeu.littletown.city.abs.Edificio;
import com.posbeu.littletown.component.*;
import com.posbeu.littletown.terrain.Zolla;
import com.posbeu.littletown.terrain.ZollaElement;
import com.posbeu.littletown.terrain.vicinato.Direction;
import com.posbeu.littletown.terrain.vicinato.DirectionUtil;

import java.util.List;

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

    public static void createVagabondoEntity(Zolla startZolla, Zolla endZolla) {

        Vector3 pos = startZolla.getPos();
        VagabondoComponent vagabondoComponent = new VagabondoComponent(
                startZolla, endZolla);
        vagabondoComponent.setPosition(pos);

        Entity entity = new Entity();
        entity.add(vagabondoComponent);
        vagabondoComponent.getInstance().transform.setTranslation(pos);


        entity.add(vagabondoComponent);

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

        return createComponent(cellCursorComponent, pos);
    }

    public static void remove(Entity c) {
        Engine engine = Pool.getEngine();
        engine.removeEntity(c);
    }

    private static Entity tempSelectedObject = null;

    public static Entity creatHomeElement(Zolla zolla, boolean temporary, Edificio edificio) {
        Engine engine = Pool.getEngine();
        if (tempSelectedObject != null) {
            engine.removeEntity(tempSelectedObject);
            tempSelectedObject = null;
        }
        edificio.setZolla(zolla);

        Vector3 pos = zolla.getPos();

        EdificioComponent comp = new EdificioComponent(zolla, ModelManager.getModel(edificio));
        comp.setEdificio(edificio);

        Entity entity = createComponent(comp, pos);
        if (temporary) tempSelectedObject = entity;

        return entity;
    }

    private static Entity creatAlberoElement(Zolla zolla) {

        Vector3 pos = zolla.getPos();

        TerrainComponent comp = new TerrainComponent(zolla, ModelManager.getObjModel("treepack1"));

        return createComponent(comp, pos);
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

    private static Entity createComponent(BaseComponent comp, Vector3 position) {
        comp.getInstance().transform.setTranslation(position);
        Entity entity = new Entity();
        entity.add(comp);

        Engine engine = Pool.getEngine();
        engine.addEntity(entity);

        return entity;
    }

    public static Entity createRoadElement(Zolla z1, Zolla z2) {
        Direction direction2 = DirectionUtil.getDirection(z2, z1);
        Direction direction1 = DirectionUtil.getDirection(z1, z2);


        RoadComponent comp = new RoadComponent(z1, z2);
        z1.addRoad(direction2, comp);
        z2.addRoad(direction1, comp);


        Entity entity = new Entity();
        entity.add(comp);

        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
        return entity;
    }

    public static Entity createSpLine(List<Zolla> path) {


        int k = path.size();
        Vector2[] points = new Vector2[k];
        for (int i = 0; i < k; i++) {
            points[i] = new Vector2(path.get(i).getPos().x, path.get(i).getPos().z);

        }
        CatmullRomSpline<Vector2> myCatmull = new CatmullRomSpline<Vector2>(points, true);
        for (int i = 0; i < k; i++) {

            myCatmull.valueAt(points[i], ((float) i) / ((float) k - 1));
        }


        Zolla z1 = path.get(0);
        Zolla z2 = path.get(path.size() - 1);
        RoadComponent comp = new RoadComponent(z1, z2);


        comp.setSpLine(myCatmull, points);
        Entity entity = new Entity();
        entity.add(comp);

        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
        return entity;
    }
}
