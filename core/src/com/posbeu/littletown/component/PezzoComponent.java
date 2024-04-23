package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.engine.pezzi.PezziEnum;
import com.posbeu.littletown.engine.pezzi.Pezzo;


public class PezzoComponent extends BaseComponent implements Component {
    private Pezzo pezzo;

    @Override
    public String toString() {
        return "PezzoComponent{" +
                "pezzo=" + pezzo +
                '}';
    }

    public PezzoComponent(Vector3 pos, Pezzo pezzo) {

        this.model = pezzo.getModel();

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

        this.instance.userData = pezzo;
        this.pezzo = pezzo;


    }
}