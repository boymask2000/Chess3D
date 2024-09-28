package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.city.abs.Edificio;
import com.posbeu.littletown.terrain.Zolla;
import com.posbeu.littletown.terrain.ZollaElement;


public class EdificioComponent extends BaseComponent implements Component {


    private final Zolla zolla;


    private Edificio edificio;

    @Override
    public void update(float delta) {
        edificio.work();
    }

    public EdificioComponent(Zolla z, Model model) {
        this.zolla = z;
        init(new Vector3(z.getX() + Pool.DELTA / 2,
                z.getZ() + Pool.DELTA / 2, z.getY()), model);
    }

    private void init(Vector3 pos, Model model) {

        this.model = model;

        this.position = pos;
        this.instance = new ModelInstance(this.model, new
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

        //   this.instance.userData = pezzo;
    }

    public Zolla getZolla() {
        return zolla;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
        zolla.setElement(ZollaElement.EDIFICIO);
    }

    public Edificio getEdificio() {
        return edificio;
    }

}