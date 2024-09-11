package com.posbeu.littletown.terrain;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.ChessGame;
import com.posbeu.littletown.EntityFactory;
import com.posbeu.littletown.Util;
import com.posbeu.littletown.engine.pezzi.Cavallo;
import com.posbeu.littletown.engine.pezzi.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Terrain {
    private ImmediateModeRenderer20 lineRenderer = new ImmediateModeRenderer20(false, true, 0);
    private PerspectiveCamera camera;
    private Map<String, Zolla> map = new HashMap<>();

    private final int DELTA = 10;

    public void render(float delta) {
        Vector3 vec = Util.getPoint(0, 0, camera);
        Vector3 vecEnd = Util.getPoint((int) ChessGame.VIRTUAL_WIDTH, (int) ChessGame.VIRTUAL_HEIGHT, camera);
        if (vec == null) return;
        for (float x = vec.x; x < vec.x + 500; x += DELTA)
            for (float z = vec.z - 500; z < vec.z + 500; z += DELTA) {
                Zolla zolla = getZolla(x, z);

                lineZolla(zolla, getZolla(x + DELTA, z));
                lineZolla(zolla, getZolla(x - DELTA, z));

                lineZolla(zolla, getZolla(x, z + DELTA));
                lineZolla(zolla, getZolla(x, z - DELTA));


            }
    }

    private void lineZolla(Zolla z1, Zolla z2) {
        line(z1.getX(), z1.getZ(), z1.getY(), z2.getX(), z2.getZ(), z2.getY());
    }

    private void line(float x1, float y1, float z1,
                      float x2, float y2, float z2) {
        float r = 10;
        float g = 89;
        float b = 128;
        float a = 0;
        lineRenderer.begin(camera.combined, GL30.GL_LINES);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x1, y1, z1);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x2, y2, z2);
        lineRenderer.end();
    }

    public void setCamera(PerspectiveCamera camera) {

        this.camera = camera;
    }


    public Zolla getZolla(float x, float y) {
        Vector2 v = Zolla.normalize(x,y);
        int xx = (int) v.x;
        int yy = (int) v.y;

        String key = xx + "|" + yy;

        Zolla p = map.get(key);
        if (p == null) {
            Random rand = new Random();

            int z = rand.nextInt(5);

            float f = (float) z;

            Zolla zolla = new Zolla(x, y, f, DELTA);
            Random r = new Random();
            int cc = r.nextInt(100);

            map.put(key, zolla);
            p = zolla;

        //    EntityFactory.createZollaElement(p);
            if (cc > 90 && p.getEntity() == null) {

                Entity ent = EntityFactory.creatAlberoElement(p);
                p.setEntity(ent);
            }
        }

        return p;
    }

    public List<Zolla> getZolleAsList(){
        return new ArrayList<Zolla>(map.values());
    }
}
