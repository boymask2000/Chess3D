package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
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
import com.posbeu.littletown.terrain.Zolla;

public class VagabondoComponent extends BaseComponent implements Component {
    private Zolla startZolla;
    private Zolla endzolla;


    private Zolla zolla;
    private final Vector3 tmp = new Vector3();

    public VagabondoComponent(Zolla startZolla, Zolla endZolla) {
        this.startZolla = startZolla;
        this.endzolla = endZolla;
        init(new Vector3(startZolla.getX() + Pool.DELTA / 2, startZolla.getZ() + Pool.DELTA / 2, startZolla.getY()), getModel());
    }

    public Model getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new
                Material(ColorAttribute.createDiffuse(Color.RED),
                ColorAttribute.createSpecular(Color.RED),
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

    public Zolla getStartZolla() {
        return startZolla;
    }

    public void setStartZolla(Zolla startZolla) {
        this.startZolla = startZolla;
    }

    public Zolla getEndzolla() {
        return endzolla;
    }

    public void setEndzolla(Zolla endzolla) {
        this.endzolla = endzolla;
    }
}
