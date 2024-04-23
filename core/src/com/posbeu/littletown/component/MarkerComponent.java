package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.engine.pezzi.Pezzo;


public class MarkerComponent extends BaseComponent implements Component {


    public MarkerComponent(Vector3 pos) {



        this.position = pos;
        this.instance = new ModelInstance(getModel(), new
                Matrix4().setToTranslation(pos.x, pos.y, pos.z));


    }

    public Model getModel(){
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new
                Material(ColorAttribute.createDiffuse(Color.RED),
                ColorAttribute.createSpecular(com.badlogic.gdx.graphics.Color.RED),
                FloatAttribute.createShininess(16f));
        Model box = modelBuilder.createBox(Constants.CELL_SIZE, 1,Constants.CELL_SIZE,  boxMaterial,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return box;
    }

    @Override
    public String toString() {
        return "MarkerComponent{}";
    }
}