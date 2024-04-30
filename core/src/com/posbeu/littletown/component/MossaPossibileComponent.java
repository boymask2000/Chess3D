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
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.engine.mosse.Mossa;

public class MossaPossibileComponent extends BaseComponent implements Component {
   /* public MossaPossibileComponent(Vector3 pos) {
        this.position = pos;
        this.instance = new ModelInstance(getModel(), new
                Matrix4().setToTranslation(pos.x, pos.y, pos.z));
    }*/

    public MossaPossibileComponent(Mossa m) {
        Vector3 v = new Vector3(m.getTo().getFloatI(), 0, m.getTo().getFloatJ());
        this.position = v;
        setI(m.getTo().getI());
        setJ(m.getTo().getJ());
        this.instance = new ModelInstance(getModel(), new
                Matrix4().setToTranslation(v.x, v.y, v.z));
    }

    public Model getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new
                Material(ColorAttribute.createDiffuse(Color.GREEN),
                ColorAttribute.createSpecular(Color.GREEN),
                FloatAttribute.createShininess(16f));
        Model box = modelBuilder.createBox(Constants.CELL_SIZE, 1, Constants.CELL_SIZE, boxMaterial,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        this.model = box;
        return box;
    }
}
