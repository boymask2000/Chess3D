package com.posbeu.littletown.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.component.CellCursorComponent;
import com.posbeu.littletown.component.RoadComponent;

public class PathBuilder extends EntitySystem {
    private Environment environment;
    private ModelBatch batch;
    private ImmutableArray<Entity> cellCursorEntities;
    private Vector3[] points = new Vector3[4];
    private CatmullRomSpline<Vector3> curve;
    private float t;
    private Engine engine;
    private ImmutableArray<Entity> roadEntities;

    public PathBuilder(ModelBatch batch, Environment environment) {
        this.batch = batch;
        this.environment = environment;

    }

    public void update(float delta) {
        roadEntities =
                engine.getEntitiesFor(
                        Family.all(
                                RoadComponent.class
                        ).get());

        for (int i = 0; i < roadEntities.size(); i++) {
            RoadComponent mod =
                    roadEntities.get(i).getComponent(RoadComponent.class);
            // batch.render(mod.instance, environment);
            mod.update(0.3F);
        }
    }

    public void build(Vector3 inizio, Vector3 fine) {
        t = 0;
        points[0] = inizio;
        points[3] = fine;
        Vector3 p1 = inizio.cpy();
        p1.x += 3;
        Vector3 p2 = fine.cpy();
        p2.x += 3;
        points[1] = p1;
        points[2] = p2;
        curve = new CatmullRomSpline<Vector3>(points, false);
    }

    public Vector3 getNextPoint() {
        if (t >= 1) return null;

        Vector3 point = new Vector3();
        curve.valueAt(point, t);
        t += 0.01;
        return point;
    }

    public void addedToEngine(Engine e) {
        this.engine = e;

    }
}
