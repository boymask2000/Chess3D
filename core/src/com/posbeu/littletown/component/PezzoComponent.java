package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.engine.pezzi.Pezzo;


public class PezzoComponent extends BaseComponent implements Component {
    private Pezzo pezzo;

    public PezzoComponent(Vector3 pos, Pezzo pezzo) {

        this.model = pezzo.getModel();
        this.position = pos;
        this.instance = new ModelInstance(model, new
                Matrix4().setToTranslation(pos.x, pos.y, pos.z));
        this.instance.userData = pezzo;
        this.pezzo = pezzo;


    }
}