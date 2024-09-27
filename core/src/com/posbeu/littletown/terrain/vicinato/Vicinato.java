package com.posbeu.littletown.terrain.vicinato;

import com.posbeu.littletown.terrain.Zolla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Vicinato {
    private static final Map<Direction, Direction[]> dirs = new HashMap<>();

    static {
        dirs.put(Direction.N, new Direction[]{Direction.N, Direction.NE, Direction.NO});
        dirs.put(Direction.S, new Direction[]{Direction.S, Direction.SE, Direction.SO});
        dirs.put(Direction.SO, new Direction[]{Direction.SO, Direction.S, Direction.O});
        dirs.put(Direction.SE, new Direction[]{Direction.SE, Direction.S, Direction.E});
        dirs.put(Direction.NE, new Direction[]{Direction.NE, Direction.N, Direction.E});
        dirs.put(Direction.NO, new Direction[]{Direction.NO, Direction.N, Direction.O});
        dirs.put(Direction.E, new Direction[]{Direction.E, Direction.NE, Direction.SE});
        dirs.put(Direction.O, new Direction[]{Direction.O, Direction.NO, Direction.SO});
    }

    private Map<Direction, Vicino> vicini = new HashMap<>();

    public void addVicino(Vicino v) {
        vicini.put(v.getDir(), v);

    }

    public List<Zolla> get(Direction dir) {
        List<Zolla> out = new ArrayList<>();

        Direction[] ll = dirs.get(dir);
        for (Direction d : ll)
            if (vicini.get(d) != null)
                out.add(vicini.get(d).getZolla());
        return out;
    }
}
