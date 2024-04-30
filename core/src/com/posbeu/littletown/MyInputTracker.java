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
import com.posbeu.littletown.component.MarkerComponent;
import com.posbeu.littletown.component.ModelComponent;
import com.posbeu.littletown.component.MossaPossibileComponent;
import com.posbeu.littletown.component.PezzoComponent;
import com.posbeu.littletown.engine.BoardPosition;
import com.posbeu.littletown.engine.ChessEngine;
import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.player.BoardGDX;

import java.util.List;

public class MyInputTracker implements InputProcessor {
    private PerspectiveCamera camera;
    private Engine engine;
    private ShapeRenderer shapeRenderer;
    private BaseComponent first = null;
    private CellCursorComponent cellCursorComponent;


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

    private PezzoComponent pezzoComp;
    private List<Mossa> mossePossibili;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (first == null) {
            PezzoComponent selected = getPezzo(screenX, screenY);
            if (selected == null) {
                Pool.removeMarkers();
                first = null;

                return false;
            }


            pezzoComp = selected;
            Pezzo pezzo = pezzoComp.getPezzo();
            mossePossibili = Pool.getChessEngine().getMosse(pezzo, pezzoComp.getI(), pezzoComp.getJ());
            for (Mossa m : mossePossibili) {
                MossaPossibileComponent mk = new MossaPossibileComponent(m);
                Pool.addMossaPossibile(mk);
            }
            if (mossePossibili.size() == 0) {
                Pool.removeMarkers();
                first = null;

                return false;
            }
            first = selected;
        } else {
            ModelComponent selected = getCell(screenX, screenY);
            if (sameCell(first, selected)) {
                Pool.removeMarkers();
                first = null;

                return false;
            }
            if (!isMossaLegale(selected)) {
                Pool.removeMarkers();
                first = null;

                return false;
            }
            if (pezzoComp != null) {
                eseguiMossaAlloChessEngine(selected);
                pezzoComp.setDestination(selected);

                first = null;

                mossePossibili.clear();
             //   Pool.getChessEngine().dump();
            }
            Pool.removeMarkers();
        }
        return true;
    }

    private boolean sameCell(BaseComponent a, ModelComponent b) {
        return a.getI() == b.getI() && a.getJ() == b.getJ();
    }

    private void eseguiMossaAlloChessEngine(BaseComponent selected) {
        BoardPosition from = new BoardPosition(pezzoComp.getPezzo(), first.getI(), first.getJ());
        BoardPosition to = new BoardPosition(pezzoComp.getPezzo(), selected.getI(), selected.getJ());
        Mossa m = new Mossa(pezzoComp.getPezzo(), from, to);
        System.out.println(m);

        ChessEngine chessEngine = Pool.getChessEngine();


        Mossa response = chessEngine.play(m);

        BoardAligner.align();
        System.out.println("Risposta: " + response);

      /*  PezzoComponent comp = Pool.getPezzocomponentWithPezzo(response.getPezzo());
        System.out.println(comp);
        if (comp != null) {
            Vector3 dest = BoardGDX.convertToVectorPosition(response.getTo().getI(), response.getTo().getJ());
            comp.setDestination(dest, response.getTo().getI(), response.getTo().getJ());
        }*/
    }

    private boolean isMossaLegale(BaseComponent p) {
        int i = p.getI();
        int j = p.getJ();
        if (mossePossibili == null) return false;

        for (Mossa m : mossePossibili)
            if (m.getTo().getI() == i && m.getTo().getJ() == j) return true;
        return false;
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
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        ModelComponent cell = getCell(screenX, screenY);
        // System.out.println(cell.getI()+" "+cell.getJ());

        if (cellCursorComponent == null)
            cellCursorComponent = EntityFactory.createCellCursor(cell.getI(), cell.getJ());

        cellCursorComponent.setPos(cell.getI(), cell.getJ());

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

    private Vector3 getPoint(int screenX, int screenY) {
        // If you are only using a camera
        Ray pickRay = camera.getPickRay(screenX, screenY);
        // If your camera is managed by a viewport
        //   Ray pickRay = viewport.getPickRay(screenCoords.x, screenCoords.y);

        // we want to check a collision only on a certain plane, in this case the X/Z plane
        Plane plane = new Plane(new Vector3(0, Constants.CELL_SIZE, 0), Vector3.Zero);
        Vector3 intersection = new Vector3();
        if (Intersector.intersectRayPlane(pickRay, plane, intersection)) {
            // The ray has hit the plane, intersection is the point it hit

            return intersection;
        }
        return null;
    }

   /* public BaseComponent getObject(int screenX, int screenY) {
        Vector3 click = getPoint(screenX, screenY);

        BaseComponent result = null;
        float distance = 10000f;
        List<BaseComponent> instances = Pool.getInstances();
        for (BaseComponent comp : instances) {
            //   System.out.println("comp " + comp.getI() + " " + comp.getJ());
            Vector3 position = comp.getPosition();

            float dist2 = click.dst2(position);

            if (dist2 < distance) {
                result = comp;
                //   System.out.println("res "+result.getI()+" "+result.getJ()+ " "+dist2);
                distance = dist2;
            }

        }
        return result;
    }
*/
    public ModelComponent getCell(int screenX, int screenY) {
        Vector3 click = getPoint(screenX, screenY);

        ModelComponent result = null;
        float distance = 10000f;
        List<ModelComponent> celle = Pool.getCelle();
        for (ModelComponent comp : celle) {

            //   System.out.println("comp " + comp.getI() + " " + comp.getJ());
            Vector3 position = comp.getPosition();

            float dist2 = click.dst2(position);

            if (dist2 < distance) {
                result = (ModelComponent) comp;
                //   System.out.println("res "+result.getI()+" "+result.getJ()+ " "+dist2);
                distance = dist2;
            }

        }
        return result;
    }

    public PezzoComponent getPezzo(int screenX, int screenY) {
        Vector3 click = getPoint(screenX, screenY);

        PezzoComponent result = null;
        float distance = 10000f;
        List<PezzoComponent> pezzi = Pool.getPezzi();
        for (PezzoComponent comp : pezzi) {

            //   System.out.println("comp " + comp.getI() + " " + comp.getJ());
            Vector3 position = comp.getPosition();

            float dist2 = click.dst2(position);

            if (dist2 < distance) {
                result = (PezzoComponent) comp;
                //   System.out.println("res "+result.getI()+" "+result.getJ()+ " "+dist2);
                distance = dist2;
            }

        }
        return result;
    }
}
