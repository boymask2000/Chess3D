package com.posbeu.littletown.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.EntityFactory;
import com.posbeu.littletown.GameWorld;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.component.PezzoComponent;


import java.util.ArrayList;
import java.util.List;

public class WalkersSystem extends EntitySystem implements EntityListener {
    private final ModelBatch batch;
    private final Environment environment;
    private ImmutableArray<Entity> entities;
    private Entity player;
    private Quaternion quat = new Quaternion();
    private Engine engine;
    private GameWorld gameWorld;
    private ComponentMapper<PezzoComponent> cm =
            ComponentMapper.getFor(PezzoComponent.class);

    public WalkersSystem(ModelBatch batch, Environment environment) {
        this.batch = batch;
        this.environment = environment;
    }

    @Override
    public void addedToEngine(Engine e) {
        entities = e.getEntitiesFor(Family.all(PezzoComponent.class).get());

        this.engine = e;
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);

    }

    public void update(float delta) {

        for (Entity e : entities) {
            PezzoComponent mod = e.getComponent(PezzoComponent.class);

            mod.update(delta);

            batch.render(mod.instance, environment);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        player = entity;
    }

    @Override
    public void entityRemoved(Entity entity) {
    }

    private Entity walker = null;


}
