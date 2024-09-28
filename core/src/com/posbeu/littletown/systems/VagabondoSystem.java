package com.posbeu.littletown.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.GameWorld;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.component.EdificioComponent;
import com.posbeu.littletown.component.TerrainComponent;
import com.posbeu.littletown.component.VagabondoComponent;
import com.posbeu.littletown.terrain.Zolla;

import java.util.List;

public class VagabondoSystem extends EntitySystem {
    private final ModelBatch batch;
    private final Environment environment;
    private ImmutableArray<Entity> edificioEntities;
    private ImmutableArray<Entity> vagabondoEntities;
    private ImmutableArray<Entity> entities;
    private Zolla currentZolla;
    private Engine engine;

    public VagabondoSystem(ModelBatch batch, Environment environment) {

        this.environment = environment;
        this.batch = batch;


    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        edificioEntities =
                engine.getEntitiesFor(
                        Family.all(
                                EdificioComponent.class
                        ).get());
        vagabondoEntities =
                engine.getEntitiesFor(
                        Family.all(
                                VagabondoComponent.class
                        ).get());
        for (int i = 0; i < vagabondoEntities.size(); i++) {
            VagabondoComponent mod =
                    vagabondoEntities.get(i).getComponent(VagabondoComponent.class);
            batch.render(mod.instance, environment);
            updateMovement(delta, mod);
        }


    }

    Zolla target = null;

    private void updateMovement(float delta, VagabondoComponent vagabondoComponent) {
        if (vagabondoComponent == null) return;
        if (target == null) target = getNextTarget();


        Vector3 position = vagabondoComponent.instance.transform.getTranslation(new Vector3());
        currentZolla = Pool.getTerrain().getZolla(position.x, position.z);
        if (target != null && target.equals(currentZolla)) target = null;

        Zolla nextZolla = calculateNextZolla(target);
        if (nextZolla == null) return;
        PerspectiveCamera camera = Pool.getCamera();

        Vector3 pos = vagabondoComponent.getPosition();

        float dx = nextZolla.getNormalizedX() - currentZolla.getNormalizedX();
        float dz = nextZolla.getNormalizedZ() - currentZolla.getNormalizedZ();
        float dy = nextZolla.getY() - currentZolla.getY();
        float sum = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
        int fatt = 10;
        dx = fatt * dx / sum;
        dy = fatt * dy / sum;
        dz = fatt * dz / sum;


        float dist = target.getPos().dst(position);


        if (target.equals(currentZolla))
            target = nextZolla;
     /*   camera.rotate(camera.up, deltaX);
        tmp.set(camera.direction).crs(camera.up).nor();
        camera.direction.rotate(tmp, deltaY);*/
//Move
        vagabondoComponent.instance.transform.translate(dx * delta, dz * delta, dy * delta);

        Vector3 translation = new Vector3();
        translation.x = 0.01F;

       /* vagabondoComponent.instance.transform.set(translation.x,
                translation.y, translation.z, camera.direction.x,
                camera.direction.y, camera.direction.z, 0);*/
       /* camera.position.set(translation.x, translation.y,
                translation.z);
        camera.update(true);*/
    }

    private void updateMovement0(float delta, VagabondoComponent vagabondoComponent) {
        if (vagabondoComponent == null) return;
        Zolla target = vagabondoComponent.getEndzolla();


        Vector3 position = vagabondoComponent.instance.transform.getTranslation(new Vector3());
        currentZolla = Pool.getTerrain().getZolla(position.x, position.z);
        if (target.equals(currentZolla)) return;

        Zolla nextZolla = calculateNextZolla(target);

        PerspectiveCamera camera = Pool.getCamera();

        Vector3 pos = vagabondoComponent.getPosition();

        float dx = nextZolla.getNormalizedX() - currentZolla.getNormalizedX();
        float dz = nextZolla.getNormalizedZ() - currentZolla.getNormalizedZ();
        float dy = nextZolla.getY() - currentZolla.getY();
        float sum = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
        int fatt = 10;
        dx = fatt * dx / sum;
        dy = fatt * dy / sum;
        dz = fatt * dz / sum;


        float dist = target.getPos().dst(position);

        if (target.equals(currentZolla))
            target = nextZolla;
     /*   camera.rotate(camera.up, deltaX);
        tmp.set(camera.direction).crs(camera.up).nor();
        camera.direction.rotate(tmp, deltaY);*/
//Move
        vagabondoComponent.instance.transform.translate(dx * delta, dz * delta, dy * delta);

        Vector3 translation = new Vector3();
        translation.x = 0.01F;

       /* vagabondoComponent.instance.transform.set(translation.x,
                translation.y, translation.z, camera.direction.x,
                camera.direction.y, camera.direction.z, 0);*/
       /* camera.position.set(translation.x, translation.y,
                translation.z);
        camera.update(true);*/
    }

    private Zolla calculateNextZolla(Zolla target) {
        if (target == null) return null;
        Vector3 targ = target.getNormalized();


        List<Zolla> vics = currentZolla.getVicini();
        Zolla minZ = vics.get(0);
        float min = minZ.getNormalized().dst(targ);
        for (Zolla z : vics) {
            if (z.getElement() != null) continue;
            float dist = z.getNormalized().dst(targ);
            if (dist < min) {
                min = dist;
                minZ = z;
            }
        }
        return minZ;

    }

    int k = -1;

    private Zolla getNextTarget() {
        if (edificioEntities.size() == 0) return null;
        k++;
        if (k >= edificioEntities.size()) k = 0;


        EdificioComponent comp = edificioEntities.get(k).getComponent(EdificioComponent.class);
        return comp.getZolla();


    }
}