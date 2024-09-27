package com.posbeu.littletown.terrain.vicinato;

import com.badlogic.gdx.math.Vector2;
import com.posbeu.littletown.terrain.Zolla;

import java.util.List;

public class VicinatoManager {

    public static List<Zolla> getVicinato(Zolla source, Zolla target) {

        Direction dir = DirectionUtil.getDirection(source, target);

        return source.getVicinato(dir);
    }


    public static void addVicino(Zolla zolla, Zolla vicino) {
        Direction dir = DirectionUtil.getDirection(zolla, vicino);
        Vicino v = new Vicino(vicino, dir);
        zolla.addVicino(v);
    }
}
