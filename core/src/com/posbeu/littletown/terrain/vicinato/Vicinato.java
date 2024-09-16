package com.posbeu.littletown.terrain.vicinato;

import com.badlogic.gdx.math.Vector2;
import com.posbeu.littletown.terrain.Zolla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Vicinato {
    private static final Map<VicinatoDir, VicinatoDir[]> dirs = new HashMap<>();

    static {
        dirs.put(VicinatoDir.N, new VicinatoDir[]{VicinatoDir.N, VicinatoDir.NE, VicinatoDir.NO});
        dirs.put(VicinatoDir.S, new VicinatoDir[]{VicinatoDir.S, VicinatoDir.SE, VicinatoDir.SO});
        dirs.put(VicinatoDir.SO, new VicinatoDir[]{VicinatoDir.SO, VicinatoDir.S, VicinatoDir.O});
        dirs.put(VicinatoDir.SE, new VicinatoDir[]{VicinatoDir.SE, VicinatoDir.S, VicinatoDir.E});
        dirs.put(VicinatoDir.NE, new VicinatoDir[]{VicinatoDir.NE, VicinatoDir.N, VicinatoDir.E});
        dirs.put(VicinatoDir.NO, new VicinatoDir[]{VicinatoDir.NO, VicinatoDir.N, VicinatoDir.O});
        dirs.put(VicinatoDir.E, new VicinatoDir[]{VicinatoDir.E, VicinatoDir.NE, VicinatoDir.SE});
        dirs.put(VicinatoDir.O, new VicinatoDir[]{VicinatoDir.O, VicinatoDir.NO, VicinatoDir.SO});
    }

    private Map<VicinatoDir, Vicino> vicini = new HashMap<>();

    public void addVicino(Vicino v) {
        vicini.put(v.getDir(), v);

    }

    public List<Zolla> get(VicinatoDir dir) {
        List<Zolla> out = new ArrayList<>();

        VicinatoDir[] ll = dirs.get(dir);
        for (VicinatoDir d : ll)
            if (vicini.get(d) != null)
                out.add(vicini.get(d).getZolla());
        return out;
    }
}
