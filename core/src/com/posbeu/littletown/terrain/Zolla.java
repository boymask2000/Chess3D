package com.posbeu.littletown.terrain;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Zolla {


    private String key;
    private final List<Zolla> vicini = new CopyOnWriteArrayList<>();
    private final int size;
    private final Vector3 pos;
    private final Vector2 normalized;
    private float x;
    private float y;
    private float z;
    private ZollaElement element;
    private Entity entity;

    public Zolla(float x, float y, float altezza) {
        this.x = x;
        this.y = y;
        this.z = altezza;
        normalized = normalize(x, y);
        this.size = Pool.DELTA;
        pos = new Vector3(x + size / 2, z, y + size / 2);
    }

    public void addVicino(Zolla z) {
        if (!vicini.contains(z))
            vicini.add(z);
    }

    public List<Zolla> getVicini() {
        return vicini;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public ZollaElement getElement() {
        return element;
    }

    public void setElement(ZollaElement element) {
        this.element = element;
    }

    public int getSize() {
        return size;
    }

    public Vector3 getPos() {
        return pos;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public static Vector2 normalize(float x, float y) {
        final int norm = 5;//10
        int xx = (int) x;
        int yy = (int) y;
        xx = (int) (xx / norm) * norm;
        yy = (int) (yy / norm) * norm;
        return new Vector2(xx, yy);
    }

    public Vector2 getNormalized() {
        return normalized;
    }

    @Override
    public String toString() {
        return "{" +
                key
                + '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
