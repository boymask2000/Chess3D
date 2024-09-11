package com.posbeu.littletown;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Util {
    public static Vector3 getPoint(int screenX, int screenY, PerspectiveCamera camera) {
        // If you are only using a camera
        if(camera==null)return null;
        Ray pickRay = camera.getPickRay(screenX, screenY);
        // If your camera is managed by a viewport
        //   Ray pickRay = viewport.getPickRay(screenCoords.x, screenCoords.y);

        // we want to check a collision only on a certain plane, in this case the X/Z plane
        Plane plane = new Plane(new Vector3(0, 1, 0), Vector3.Zero);
        Vector3 intersection = new Vector3();
        if (Intersector.intersectRayPlane(pickRay, plane, intersection)) {
            // The ray has hit the plane, intersection is the point it hit
            return intersection;
        }
        return null;
    }
}
