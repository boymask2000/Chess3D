package com.posbeu.littletown.systems;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector3;

public class PathBuilder {
    private Vector3[] points = new Vector3[4];
    private CatmullRomSpline<Vector3> curve;
    private float t;

    public PathBuilder(Vector3 inizio, Vector3 fine) {
        t = 0;
        points[0] = inizio;
        points[3] = fine;
        Vector3 p1 = inizio.cpy();
        p1.x += 3;
        Vector3 p2 = fine.cpy();
        p2.x += 3;
        points[1] = p1;
        points[2] = p2;
        curve = new CatmullRomSpline<Vector3>(points, false);
    }

    public Vector3 getNextPoint() {
        if (t >= 1) return null;

        Vector3 point = new Vector3();
        curve.valueAt(point, t);
        t += 0.01;
        return point;
    }
}
