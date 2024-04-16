package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class ModelComponent extends BaseComponent implements Component {


    public ModelComponent(Model model, float x, float y, float z) {
        this.model = model;
        this.instance = new ModelInstance(model, new
                Matrix4().setToTranslation(x, y, z));
    }

    public ModelComponent(Model model, Vector3 pos) {
        this.model = model;
        this.instance = new ModelInstance(model, new
                Matrix4().setToTranslation(pos.x, pos.y, pos.z));
    }
}