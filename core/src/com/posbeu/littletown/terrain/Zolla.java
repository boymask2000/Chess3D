package com.posbeu.littletown.terrain;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.component.RoadComponent;
import com.posbeu.littletown.terrain.vicinato.Vicinato;
import com.posbeu.littletown.terrain.vicinato.Direction;
import com.posbeu.littletown.terrain.vicinato.Vicino;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Data;

@Data
public class Zolla {


    private String key;
    private List<Zolla> vicini = null;
    private final int size = Pool.DELTA;
    private Vector3 pos;


    private final Vector3 normalized;

    private float x;
    private float y;
    private float z;
    private ZollaElement element;
    private Terrain terrain = Pool.getTerrain();

    private Entity entity;
    private Vicinato vicinato = new Vicinato();

    public Zolla(float x, float y, float altezza) {
        this.x = x;
        this.y = y;
        this.z = altezza;
        normalized = normalize(x, y, z);

        //pos = new Vector3(x + size / 2, z, y + size / 2);
        pos = new Vector3(normalized.x, z, normalized.y);

    }

    public void addVicino(Vicino v) {
        vicinato.addVicino(v);

        if (!vicini.contains(v.getZolla()))
            vicini.add(v.getZolla());
    }

    public List<Zolla> getVicini() {
        if (vicini != null) return vicini;
        vicini = new CopyOnWriteArrayList<>();
        int x = (int) normalized.x;
        int y = (int) normalized.y;
        int d = Pool.DELTA;

        vicini.add(terrain.getStoredZolla(x + d, y - d));
        vicini.add(terrain.getStoredZolla(x + d, y));
        vicini.add(terrain.getStoredZolla(x + d, y + d));
        vicini.add(terrain.getStoredZolla(x - d, y - d));
        vicini.add(terrain.getStoredZolla(x - d, y));
        vicini.add(terrain.getStoredZolla(x - d, y + d));
        vicini.add(terrain.getStoredZolla(x, y - d));
        vicini.add(terrain.getStoredZolla(x, y + d));
        return vicini;
    }

    public static Vector3 normalize(float x, float y, float z) {
        final int norm = 5;//10
        int xx = (int) x;
        int yy = (int) y;
        xx = (int) (xx / norm) * norm;
        yy = (int) (yy / norm) * norm;
        return new Vector3(xx, yy, z);
    }


    @Override
    public String toString() {
        return "{" +
                key
                + '}';
    }

    public ZollaElement getElement() {
        return element;
    }

    public void setElement(ZollaElement element) {
        this.element = element;
    }

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zolla zolla = (Zolla) o;
        return key.equals(zolla.getKey());

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }


    public List<Zolla> getVicinato(Direction dir) {
        return vicinato.get(dir);
    }

    private final Map<Direction, RoadComponent> roads = new HashMap<>();

    public void addRoad(Direction dir, RoadComponent comp) {
        if (roads.get(dir) != null) return;
        roads.put(dir, comp);
    }

    public Vector3 getNormalized() {
        return normalized;
    }

    public float getNormalizedX() {
        return normalized.x;
    }

    public float getNormalizedZ() {
        return normalized.z;
    }

}
