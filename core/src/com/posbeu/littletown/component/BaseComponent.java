package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class BaseComponent implements Component {
    private int i;

    private int j;

    public ModelInstance instance;

    protected Vector3 position;
    public Model model;

    @Override
    public String toString() {
        return model.toString();
    }

    public ModelInstance getInstance() {
        return instance;
    }

    public void setInstance(ModelInstance instance) {
        this.instance = instance;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void update(float delta){}
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }



    public void setJ(int j) {
        this.j = j;
    }
}