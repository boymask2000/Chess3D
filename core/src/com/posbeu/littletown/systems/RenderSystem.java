package com.posbeu.littletown.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.component.*;


public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> pezziEntities;
    private ImmutableArray<Entity> modelEntities;
    private ImmutableArray<Entity> markersEntities;
    private ImmutableArray<Entity> entities4;
    private ImmutableArray<Entity> entities5;
    private ModelBatch batch;
    private Environment environment;
    private Engine engine;

    public RenderSystem(ModelBatch batch, Environment environment) {
        this.batch = batch;
        this.environment = environment;
    }

    public void addedToEngine(Engine e) {
        this.engine=e;
        pezziEntities =
                e.getEntitiesFor(
                        Family.all(
                                PezzoComponent.class

                        ).get());

    }

    public void update(float delta) {
        pezziEntities =
                engine.getEntitiesFor(
                        Family.all(
                                PezzoComponent.class
                        ).get());

        modelEntities =
                engine.getEntitiesFor(
                        Family.all(
                                ModelComponent.class
                        ).get());
        markersEntities =
                engine.getEntitiesFor(
                        Family.all(
                                MarkerComponent.class
                        ).get());

        for (int i = 0; i < pezziEntities.size(); i++) {
            PezzoComponent mod =
                    pezziEntities.get(i).getComponent(PezzoComponent.class);
            batch.render(mod.instance, environment);

        }
        for (int i = 0; i < modelEntities.size(); i++) {
            ModelComponent mod =
                    modelEntities.get(i).getComponent(ModelComponent.class);
            batch.render(mod.instance, environment);

        }
        for (int i = 0; i < markersEntities.size(); i++) {
            MarkerComponent mod =
                    markersEntities.get(i).getComponent(MarkerComponent.class);
            batch.render(mod.instance, environment);

        }

    }

    private void show(Vector3 textPosition, String s) {
        /* best to make these static, but whatever */
        // Vector3 textPosition = /* the location of the text */;
        PerspectiveCamera cam = Pool.getCamera();
        Matrix4 projection = new Matrix4();
        Matrix4 textTransform = new Matrix4();

        textTransform.setToTranslation(textPosition);
        textTransform.rotateTowardDirection(new Vector3().set(cam.direction).nor(), Vector3.Y);

        projection.set(cam.combined);
        SpriteBatch spriteBatch = new SpriteBatch();
        Matrix4 op = spriteBatch.getProjectionMatrix().cpy();
        Matrix4 ot = spriteBatch.getTransformMatrix().cpy();

        // push the matricies
        spriteBatch.setProjectionMatrix(projection);
        spriteBatch.setTransformMatrix(textTransform);
        BitmapFont myFont = Pool.getFont();

        spriteBatch.begin();
        myFont.draw(spriteBatch, s, 0, 0, 0, Align.center, false);
        spriteBatch.end();

        // pop the matricies
        spriteBatch.setProjectionMatrix(op);
        spriteBatch.setTransformMatrix(ot);
    }

}
