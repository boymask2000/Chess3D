package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.terrain.Zolla;

public class RoadComponent extends BaseComponent implements Component {
    private Zolla zolla1;
    private Zolla zolla2;
    private ImmediateModeRenderer20 lineRenderer = new ImmediateModeRenderer20(false, true, 0);
    private CatmullRomSpline<Vector2> spLine;
    private int k;
    private Vector2[] points;

    public RoadComponent(Zolla z1, Zolla z2) {
        this.zolla1 = z1;
        this.zolla2 = z2;

        //init(new Vector3(z.getX() + Pool.DELTA / 2, z.getZ() + Pool.DELTA / 2, z.getY()), getModel());
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        line(zolla1.getX(), zolla1.getZ(), zolla1.getY(), zolla2.getX(), zolla2.getZ(), zolla2.getY());


        if (points != null) {
            k = points.length;
            for (int i = 0; i < k - 1; ++i) {
//            shapeRenderer.line(spLine.valueAt(points[i], ((float) i) / ((float) k - 1)), spLine.valueAt(points[i + 1], ((float) (i + 1)) / ((float) k - 1)));

                line(points[i], points[i + 1]);

            }

        }
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

    private void line(float x1, float y1, float z1,
                      float x2, float y2, float z2) {
        // getInstance().transform.setTranslation(position);
        float r = 10;
        float g = 128;
        float b = 128;
        float a = 0;

        lineRenderer.begin(Pool.getCamera().combined, GL30.GL_LINES);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x1, y1, z1);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x2, y2, z2);
        lineRenderer.end();
    }

    private void line(Vector2 v1, Vector2 v2) {
        line(v1.x, 0, v1.y, v2.x, 0, v2.y);

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

    public void setSpLine(CatmullRomSpline<Vector2> myCatmull, Vector2[] points) {
        this.points = points;
        this.spLine = myCatmull;
    }
}
