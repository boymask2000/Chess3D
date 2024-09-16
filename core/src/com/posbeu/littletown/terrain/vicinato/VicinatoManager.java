package com.posbeu.littletown.terrain.vicinato;

import com.badlogic.gdx.math.Vector2;
import com.posbeu.littletown.terrain.Zolla;

import java.util.List;

public class VicinatoManager {
    public static List<Zolla> getVicinato(Zolla source, Zolla target) {
        VicinatoDir dir = getDir(source, target);

        return source.getVicinato(dir);


/*        switch (dir){
            case NE: return source.getViciniNE();
            case SE: return source.getViciniSE();
            case SO: return source.getViciniSO();
            case NO: return source.getViciniNO();
            case N: return source.getViciniN();
            case S: return source.getViciniS();
            case E: return source.getViciniE();
            case W: return source.getViciniW();
        }
        return null;*/
    }


    public static void addVicino(Zolla zolla, Zolla vicino) {
        VicinatoDir dir = getDir(zolla, vicino);
        Vicino v = new Vicino(vicino, dir);
        zolla.addVicino(v);
    }

    private static VicinatoDir getDir(Zolla source, Zolla target) {
        Vector2 srcNorm = source.getNormalized();
        Vector2 trgNorm = target.getNormalized();
        float xS = srcNorm.x;
        float yS = srcNorm.y;
        float xT = trgNorm.x;
        float yT = trgNorm.y;

        VicinatoDir dir = VicinatoDir.N;

        if (xS < xT && yS < yT) dir = VicinatoDir.NE;
        if (xS < xT && yS > yT) dir = VicinatoDir.SE;
        if (xS > xT && yS > yT) dir = VicinatoDir.SO;
        if (xS > xT && yS < yT) dir = VicinatoDir.NO;
        if (xS == xT && yS < yT) dir = VicinatoDir.N;
        if (xS == xT && yS > yT) dir = VicinatoDir.S;
        if (xS < xT && yS == yT) dir = VicinatoDir.E;
        if (xS > xT && yS == yT) dir = VicinatoDir.O;
        return dir;
    }

}
