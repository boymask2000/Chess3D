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

    //  private ImmutableArray<Entity> vagabondoEntities;
    private ImmutableArray<Entity> edificioEntities;
    private ImmutableArray<Entity> cellCursorEntities;
    private ImmutableArray<Entity> modelEntities;
    private ImmutableArray<Entity> terrainEntities;

    private ImmutableArray<Entity> entities4;
    private ImmutableArray<Entity> entities5;
    private ModelBatch batch;
    private Environment environment;
    private Engine engine;

    public RenderSystem(ModelBatch batch, Environment environment) {
        this.batch = batch;
        this.environment = environment;

        Pool.setRenderSystem(this);
    }

    public void addedToEngine(Engine e) {
        this.engine = e;

    }

    public void update(float delta) {
        edificioEntities =
                engine.getEntitiesFor(
                        Family.all(
                                EdificioComponent.class
                        ).get());

       cellCursorEntities =
                engine.getEntitiesFor(
                        Family.all(
                                CellCursorComponent.class
                        ).get());


        modelEntities =
                engine.getEntitiesFor(
                        Family.all(
                                ModelComponent.class
                        ).get());

        terrainEntities =
                engine.getEntitiesFor(
                        Family.all(
                                TerrainComponent.class
                        ).get());

   /*     for (int i = 0; i < vagabondoEntities.size(); i++) {
            VagabondoComponent mod =
                    vagabondoEntities.get(i).getComponent(VagabondoComponent.class);
            batch.render(mod.instance, environment);
        }*/
        for (int i = 0; i < edificioEntities.size(); i++) {
            EdificioComponent mod =
                    edificioEntities.get(i).getComponent(EdificioComponent.class);
            batch.render(mod.instance, environment);
            mod.update(1);

        }
        for (int i = 0; i < cellCursorEntities.size(); i++) {
            CellCursorComponent mod =
                    cellCursorEntities.get(i).getComponent(CellCursorComponent.class);
            batch.render(mod.instance, environment);

        }
        for (int i = 0; i < terrainEntities.size(); i++) {
            TerrainComponent mod =
                    terrainEntities.get(i).getComponent(TerrainComponent.class);
            batch.render(mod.instance, environment);

        }

       for (int i = 0; i < modelEntities.size(); i++) {
            ModelComponent mod =
                    modelEntities.get(i).getComponent(ModelComponent.class);
            batch.render(mod.instance, environment);

        }
       //show(new Vector3(0,0,0), "ciao");
    }

    public void show(Vector3 textPosition, String s) {
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
