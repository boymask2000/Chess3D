package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.component.BaseComponent;


public class RoadBuilder {
    private static final int DELTA = 2;

    public static void build(BaseComponent first, BaseComponent second) {
        Engine engine = Pool.getEngine();

        Vector3 pos = first.getPosition();
        float best = -1;
        boolean dir = true;
        boolean cont = true;
        while (cont) {
            dir = !dir;
            while (cont) {
                float dist = getNextPos(second, pos, dir);
                System.out.println(dist);
                if (dist < 5) cont = false;
                if (best > 0) {
                    if (dist < best) best = dist;
                    else break;
                }else best=dist;
                Entity entity1 = new Entity();

                engine.addEntity(entity1);
            }
        }
    }

    private static float getNextPos(BaseComponent second, Vector3 pos, boolean isx) {
        Vector3 target = second.getPosition();
        if (pos.dst(target) <= 4) return 0;
        Vector3 o = null;
        float best = 100000;
        if (isx) {
            Vector3 v1 = pos.cpy();
            v1.x = v1.x + DELTA;
            Vector3 v2 = pos.cpy();
            v2.x = v2.x - DELTA;
            float d1 = target.dst(v1);
            float d2 = target.dst(v2);
            o = v1;
            best = d1;
            if (d2 < best) {
                best = d2;
                o = v2;
            }
        } else {
            Vector3 v1 = pos.cpy();
            v1.z = v1.z + DELTA;
            Vector3 v2 = pos.cpy();
            v2.z = v2.z - DELTA;
            float d1 = target.dst(v1);
            float d2 = target.dst(v2);
            o = v1;
            best = d1;
            if (d2 < best) {
                best = d2;
                o = v2;
            }
        }

        pos.x = o.x;
        pos.y = o.y;
        pos.z = o.z;
        return best;
    }

    private static Vector3 getNextPos(BaseComponent second, Vector3 pos) {
        Vector3 target = second.getPosition();
        if (pos.dst(target) <= 4) return null;

        Vector3 v1 = pos.cpy();
        v1.x = v1.x + DELTA;
        Vector3 v2 = pos.cpy();
        v2.x = v2.x - DELTA;
        Vector3 v3 = pos.cpy();
        v3.y = v3.y + DELTA;
        Vector3 v4 = pos.cpy();
        v4.y = v4.y - DELTA;
        Vector3 v5 = pos.cpy();
        v5.z = v5.z + DELTA;
        Vector3 v6 = pos.cpy();
        v6.z = v6.z - DELTA;
        float d1 = target.dst(v1);
        Vector3 o = v1;
        float best = d1;
        float d2 = target.dst(v2);
        float d3 = target.dst(v3);
        float d4 = target.dst(v4);
        float d5 = target.dst(v5);
        float d6 = target.dst(v6);
        if (d2 < best) {
            best = d2;
            o = v2;
        }
        if (d3 < best) {
            best = d3;
            o = v3;
        }
        if (d4 < best) {
            best = d4;
            o = v4;
        }
        if (d5 < best) {
            best = d5;
            o = v5;
        }
        if (d6 < best) {
            best = d6;
            o = v6;
        }
        return o;

    }
}
