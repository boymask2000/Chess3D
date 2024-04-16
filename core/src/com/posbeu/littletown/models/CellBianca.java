package com.posbeu.littletown.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.posbeu.littletown.Constants;

public class CellBianca {
    static final int SIZE= Constants.CELL_SIZE;
    public static Model getModel(){
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new
                Material(ColorAttribute.createDiffuse(Color.WHITE),
                ColorAttribute.createSpecular(com.badlogic.gdx.graphics.Color.RED),
                FloatAttribute.createShininess(16f));
        Model box = modelBuilder.createBox(SIZE, 1,SIZE,  boxMaterial,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return box;
    }
}
