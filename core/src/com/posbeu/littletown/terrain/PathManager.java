package com.posbeu.littletown.terrain;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.posbeu.littletown.EntityFactory;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.terrain.vicinato.VicinatoManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathManager {
    private List<Zolla> out = new ArrayList<>();
    private List<Zolla> best = new ArrayList<>();
    private Set<Zolla> visited = new HashSet<>();
    private int maxlen = 10000;

    public List<Zolla> build(Zolla zollaStart, Zolla zollaTarget) {
        buildIntern(zollaStart, zollaTarget, 0);


        //  dumpOut(best);
        return best;
    }

    private List<Zolla> buildIntern(Zolla zollaStart, Zolla zollaTarget, int level) {
        if (level == 0) {
            visited.clear();
            maxlen = 10000;
        }
        if (zollaStart.equals(zollaTarget)) {
            System.out.println("FOUND");
            saveBest(out);
            dumpOut(out);
            maxlen = out.size();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return out;
        }

        visited.add(zollaStart);


        //    List<Zolla> vicini = VicinatoManager.getVicinato(zollaStart, zollaTarget);
        List<Zolla> vicini = zollaStart.getVicini();
        System.out.println(zollaStart + " --> " + vicini);
        for (Zolla zz : vicini) {
            System.out.println(zollaStart + " --> " + zz);

            if (zz.getElement() != null) continue;
            if (visited.contains(zz)) continue;

            Entity ent = EntityFactory.createCellCursor0(zz);
            Pool.getGameScreen().render(Gdx.graphics.getDeltaTime());

            if (out.size() < 40) {
                out.add(zz);

                if (out.size() < maxlen)
                    buildIntern(zz, zollaTarget, level + 1);
                out.remove(zz);
                EntityFactory.remove(ent);
                Pool.getGameScreen().render(Gdx.graphics.getDeltaTime());

            }


        }
        return out;

    }

    private void saveBest(List<Zolla> out) {
        best.clear();
        best.addAll(out);
    }

    private void dumpOut(List<Zolla> out) {
        for (Zolla z : out) System.out.print(z);
        System.out.println();
    }
}
