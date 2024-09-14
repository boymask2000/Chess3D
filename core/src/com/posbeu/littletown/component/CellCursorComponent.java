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
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.engine.player.BoardGDX;
import com.posbeu.littletown.terrain.Zolla;

public class CellCursorComponent extends BaseComponent implements Component {
    private Zolla zolla;

    public CellCursorComponent(Zolla z) {
        this.zolla = z;
        init(new Vector3(z.getX() + Pool.DELTA / 2, z.getZ() + Pool.DELTA / 2, z.getY()), getModel());
    }

    public Model getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new
                Material(ColorAttribute.createDiffuse(Color.RED),
                ColorAttribute.createSpecular(com.badlogic.gdx.graphics.Color.RED),
                FloatAttribute.createShininess(16f));
        Model box = modelBuilder.createBox(5, 5, 5, boxMaterial,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return box;
    }

    private void init(Vector3 pos, Model model) {

        this.model = model;

        this.position = pos;
        this.instance = new ModelInstance(model, new
                Matrix4().setToTranslation(pos.x, pos.y, pos.z));


        BoundingBox box = new BoundingBox();
        this.instance.calculateBoundingBox(box);
        Vector3 dim = new Vector3();
        box.getDimensions(dim);
        //System.out.println(dim.x);
        float fac = Constants.PEZZO_SIZE / dim.x;
        this.instance.transform.scale(fac, fac, fac);
        //   this.instance.calculateTransforms();
        BoundingBox box1 = new BoundingBox();
        this.instance.calculateBoundingBox(box1);
        dim = new Vector3();
        box1.getDimensions(dim);
        //  System.out.println(dim.x);
        this.instance.transform.translate(pos.x, pos.y, pos.z);

        //     this.instance.transform.translate(zolla.getX(), zolla.getZ(), zolla.getY());

        //   this.instance.userData = pezzo;
    }
}
