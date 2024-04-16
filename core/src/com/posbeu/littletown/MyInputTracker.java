package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.posbeu.littletown.component.BaseComponent;

import java.util.List;

public class MyInputTracker implements InputProcessor {
    private Vector3 touchPos = new Vector3();
    private PerspectiveCamera camera;
    private Engine engine;
    private ShapeRenderer shapeRenderer;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos = getPoint(screenX, screenY);

        return false;
    }

    private BaseComponent first = null;
    private BaseComponent second = null;

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    int SIZE= Constants.CELL_SIZE;
    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        Vector3 pos = getPoint(screenX, screenY);
        System.out.println(pos.x+" " +pos.z);
System.out.println((int)((SIZE-pos.x)/SIZE)+" " +(int)(pos.z/SIZE));
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void setCamera(PerspectiveCamera perspectiveCamera) {
        this.camera = perspectiveCamera;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    private Vector3 getPoint(int screenX, int screenY) {
        // If you are only using a camera
        Ray pickRay = camera.getPickRay(screenX, screenY);
        // If your camera is managed by a viewport
        //   Ray pickRay = viewport.getPickRay(screenCoords.x, screenCoords.y);

        // we want to check a collision only on a certain plane, in this case the X/Z plane
        Plane plane = new Plane(new Vector3(0, SIZE, 0), Vector3.Zero);
        Vector3 intersection = new Vector3();
        if (Intersector.intersectRayPlane(pickRay, plane, intersection)) {
            // The ray has hit the plane, intersection is the point it hit
            return intersection;
        }
        return null;
    }



}
