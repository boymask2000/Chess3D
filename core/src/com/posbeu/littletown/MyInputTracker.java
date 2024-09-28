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
import com.posbeu.littletown.component.CellCursorComponent;
import com.posbeu.littletown.component.ModelComponent;

import com.posbeu.littletown.terrain.PathManager;
import com.posbeu.littletown.terrain.Zolla;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MyInputTracker implements InputProcessor {
    private PerspectiveCamera camera;
    private Engine engine;
    private ShapeRenderer shapeRenderer;
    private BaseComponent first = null;
    private CellCursorComponent cellCursorComponent;
    List<Zolla> path = new ArrayList<>();

    Zolla zollaStart;
    private boolean started = false;

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


        Vector3 point = Util.getPoint(screenX, screenY, camera);

        Zolla z = Pool.getTerrain().getZolla(point.x, point.z);

/*        System.out.println("ZOLLA :"+z);
        for( Zolla l: z.getVicini())
System.out.println(l);*/
        EntityFactory.createCellCursor(z);


/*        if (!started) {
            zollaStart = z;
            started = true;
            System.out.println("Punto partenza");
            path.clear();
            path.add(zollaStart);
            return true;
        }
        if (started) {

            started = false;

            EntityFactory.createSpLine(path);
        }*/

        return false;
    }

    private List<Zolla> calcolaPath(Zolla zollaStart, Zolla z) {
        PathManager pm = new PathManager();
        List<Zolla> path = pm.build(zollaStart, z);
        for (Zolla q : path)
            System.out.println(q);
        return path;
    }


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
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //  touchDragged(screenX, screenY, 0);
        Vector3 point = Util.getPoint(screenX, screenY, camera);
        Zolla z = Pool.getTerrain().getZolla(point.x, point.z);

        if (z.getElement() != null) return true;


        EntityFactory.createCellCursor(z);

        if (started && !path.contains(z)) {
            Zolla last = path.get(path.size() - 1);
            EntityFactory.createRoadElement(last, z);
            path.add(z);
        }

        return true;
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
}
