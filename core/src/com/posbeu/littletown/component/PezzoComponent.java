package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.engine.pezzi.PezziEnum;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.systems.PathBuilder;


public class PezzoComponent extends BaseComponent implements Component {

    private int i;
    private int j;

    private Pezzo pezzo;

    private final int STATO_IDLE = 0;
    private static final int STATO_GOING = 3;

    private int currentState = STATO_IDLE;
    private PathBuilder pathBuilder;


    private Vector3 destination;

    @Override
    public void update(float delta) {
        if (destination == null) return;
        if (currentState == STATO_IDLE) {


            pathBuilder = new PathBuilder(getPosition(), destination);

            currentState = STATO_GOING;
            return;
        }
        if (currentState == STATO_GOING) {

            Vector3 next = pathBuilder.getNextPoint();

            if (next == null) {
                destination = null;
                currentState = STATO_IDLE;
                return;
            }
            setPosition(next);
            instance.transform.setTranslation(next);

        }
    }

    @Override
    public String toString() {
        return "PezzoComponent{" +
                "pezzo=" + pezzo +
                '}';
    }

    public PezzoComponent(int i, int j, Pezzo pezzo) {
        this.i = i;
        this.j = j;
        float consta = Constants.CELL_SIZE;// 6.5f;
        float posX = i * consta + Constants.CELL_SIZE;
        float posY = j * Constants.CELL_SIZE + Constants.CELL_SIZE;

        init(new Vector3(posX, 0, posY), pezzo);
    }

    /*    public PezzoComponent(Vector3 pos, Pezzo pezzo) {
            init( pos,  pezzo);
        }*/
    private void init(Vector3 pos, Pezzo pezzo) {

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

    public Pezzo getPezzo() {
        return pezzo;
    }

    public int getI() {
        return i;
    }

    public void setDestination(Vector3 destination) {
        this.destination = destination;
    }

    public int getJ() {
        return j;
    }
}