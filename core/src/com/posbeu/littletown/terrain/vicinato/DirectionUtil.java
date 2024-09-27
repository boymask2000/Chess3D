package com.posbeu.littletown.terrain.vicinato;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.terrain.Zolla;

public class DirectionUtil {
    public static Direction getDirection(Zolla source, Zolla target) {
        Vector3 srcNorm = source.getNormalized();
        Vector3 trgNorm = target.getNormalized();
        float xS = srcNorm.x;
        float yS = srcNorm.y;
        float xT = trgNorm.x;
        float yT = trgNorm.y;

        Direction dir = Direction.N;

        if (xS < xT && yS < yT) dir = Direction.NE;
        if (xS < xT && yS > yT) dir = Direction.SE;
        if (xS > xT && yS > yT) dir = Direction.SO;
        if (xS > xT && yS < yT) dir = Direction.NO;
        if (xS == xT && yS < yT) dir = Direction.N;
        if (xS == xT && yS > yT) dir = Direction.S;
        if (xS < xT && yS == yT) dir = Direction.E;
        if (xS > xT && yS == yT) dir = Direction.O;
        return dir;
    }
}
