package com.posbeu.littletown.terrain;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.posbeu.littletown.EntityFactory;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.terrain.vicinato.VicinatoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class PathManager {
    private List<Zolla> out = new ArrayList<>();
    private List<Zolla> best = new ArrayList<>();
    private Set<Zolla> visited = new HashSet<>();
    private int maxlen = 10000;

    public List<Zolla> build(Zolla zollaStart, Zolla zollaTarget) {
        buildIntern(zollaStart, zollaTarget, 0);
        System.out.println(zollaStart + "  " + zollaTarget);
        dumpOut(best);
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


        List<Zolla> vicini = VicinatoManager.getVicinato(zollaStart, zollaTarget);

        for (Zolla zz : vicini) {
            //dumpOut(out);

            if (zz.getElement() != null) continue;
            if (visited.contains(zz)) continue;

            //      System.out.println(out.size() + "      " + zz);

            Entity ent = EntityFactory.createCellCursor0(zz);
            Pool.getGameScreen().render(Gdx.graphics.getDeltaTime());


            if (out.size() < 20) {
                out.add(zz);

                if (out.size() < maxlen)
                    buildIntern(zz, zollaTarget, level++);
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
