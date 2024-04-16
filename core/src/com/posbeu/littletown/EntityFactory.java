package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import com.posbeu.littletown.component.*;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.models.CellBianca;
import com.posbeu.littletown.models.CellNera;


import java.util.List;

public class EntityFactory {
    static final int SIZE=Constants.CELL_SIZE;
    static Model playerModel;

    static {
        ModelBuilder modelBuilder = new ModelBuilder();
        Texture playerTexture = new
                Texture(Gdx.files.internal("badlogic.jpg"));
        Material material = new
                Material(TextureAttribute.createDiffuse(playerTexture),
                ColorAttribute.createSpecular(1, 1, 1, 1),
                FloatAttribute.createShininess(8f));
        playerModel = modelBuilder.createCapsule(2f, 6f, 16, material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
                        | VertexAttributes.Usage.TextureCoordinates);
    }

    public static void createStaticEntity(Model model, float x, float y,
                                            float z) {
        final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y,
                z);
        entity.add(modelComponent);

        Pool.addInstance(modelComponent);
        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
    //    return entity;
    }

    public static void createCell(int i, int j){
        Vector3 pos = new Vector3(i*SIZE, 0,j*SIZE);
        Model m = CellNera.getModel();
        if( (i+j) % 2 ==0) m= CellBianca.getModel();
        createStaticEntity(m, i*SIZE, -1,j*SIZE);

    }



    public static void createPezzo(int i, int j,  Pezzo pezzo){
        Vector3 pos = new Vector3(i*SIZE, 0,j*SIZE);
        Engine engine = Pool.getEngine();
        Entity entity = new Entity();
        PezzoComponent comp = new PezzoComponent(pos, pezzo);
        entity.add(comp);
        engine.addEntity(entity);

        Pool.addInstance(comp);
    }


 public static void createPezzo(Vector3 pos, Engine engine, Pezzo pezzo) {
     Entity entity = new Entity();
     PezzoComponent comp = new PezzoComponent(pos, pezzo);
     entity.add(comp);
     engine.addEntity(entity);

     Pool.addInstance(comp);
 }
}
