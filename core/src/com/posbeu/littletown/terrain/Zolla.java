package com.posbeu.littletown.terrain;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.terrain.vicinato.Vicinato;
import com.posbeu.littletown.terrain.vicinato.VicinatoDir;
import com.posbeu.littletown.terrain.vicinato.Vicino;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Zolla {


    private String key;
    private final List<Zolla> vicini = new CopyOnWriteArrayList<>();
    private final int size = Pool.DELTA;

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 pos) {
        this.pos = pos;
    }

    private Vector3 pos;
    private final Vector2 normalized;
    private float x;
    private float y;
    private float z;
    private ZollaElement element;
    private Entity entity;
    private Vicinato vicinato = new Vicinato();

    public Zolla(float x, float y, float altezza) {
        this.x = x;
        this.y = y;
        this.z = altezza;
        normalized = normalize(x, y);

        //pos = new Vector3(x + size / 2, z, y + size / 2);
        pos = new Vector3(normalized.x, z, normalized.y);

    }

    public void addVicino(Vicino v) {
        vicinato.addVicino(v);
    }

    public static Vector2 normalize(float x, float y) {
        final int norm = 5;//10
        int xx = (int) x;
        int yy = (int) y;
        xx = (int) (xx / norm) * norm;
        yy = (int) (yy / norm) * norm;
        return new Vector2(xx, yy);
    }


    @Override
    public String toString() {
        return "{" +
                key
                + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zolla zolla = (Zolla) o;
        return key.equals(zolla.getKey());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }


    public List<Zolla> getVicinato(VicinatoDir dir) {
        return vicinato.get(dir);
    }
}
