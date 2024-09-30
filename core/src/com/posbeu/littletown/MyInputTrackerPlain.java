package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.city.Boscaiolo;
import com.posbeu.littletown.city.Pozzo;
import com.posbeu.littletown.city.Segheria;
import com.posbeu.littletown.terrain.PathManager;
import com.posbeu.littletown.terrain.Zolla;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MyInputTrackerPlain implements InputProcessor {
    private PerspectiveCamera camera;
    private Engine engine;
    private ShapeRenderer shapeRenderer;

    private List<Zolla> path = new ArrayList<>();

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

    boolean vagCreated = false;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 point = Util.getPoint(screenX, screenY, camera);

        Zolla z = Pool.getTerrain().getZolla(point.x, point.z);

        createObj(z, false);
        Pool.setSelectedButton(null);


        if (!vagCreated) {
            vagCreated = true;
            EntityFactory.createCarrierEntity(z);
        }
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
        if (point == null) return true;
        Zolla z = Pool.getTerrain().getZolla(point.x, point.z);

        if (z.getElement() != null) return true;

        createObj(z, true);


        // EntityFactory.createCellCursor(z);


        return true;
    }

    private boolean createObj(Zolla z, boolean temp) {
        TipoOggetto obj = Pool.getSelectedButton();
        if (obj == null) return true;

        switch (obj) {
            case FALEGNAME:
                EntityFactory.creatHomeElement(z, temp, new Pozzo());
                break;
            case BOSCAIOLO:
                EntityFactory.creatHomeElement(z, temp, new Boscaiolo());
                break;
            case POZZO:
                EntityFactory.creatHomeElement(z, temp, new Pozzo());
                break;
            case SEGHERIA:
                EntityFactory.creatHomeElement(z, temp, new Segheria());
                break;
        }
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
}
