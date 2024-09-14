package com.posbeu.littletown.terrain;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.posbeu.littletown.EntityFactory;
import com.posbeu.littletown.Pool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class PathManager {
    private List<Zolla> out = new ArrayList<>();
    private Set<Zolla> visited = new HashSet<>();
    private int maxlen = 10000;
    ;

    public List<Zolla> build(Zolla zollaStart, Zolla z) {
        return buildIntern(zollaStart, z, 0);
    }

    private List<Zolla> buildIntern(Zolla zollaStart, Zolla z, int level) {
        if (level == 0) {
            visited.clear();
            maxlen = 10000;
        }
        visited.add(zollaStart);
        List<Zolla> vicini = zollaStart.getVicini();

        for (Zolla zz : vicini) {
            //dumpOut(out);
            Pool.getGameScreen().render(Gdx.graphics.getDeltaTime());


            if (zz.getElement() != null || out.contains(zz)) continue;
            if (visited.contains(zz)) continue;

            System.out.println(out.size() + "      " + zz);

            Entity ent = EntityFactory.creatAlberoElement(zz);
            Pool.getGameScreen().render(Gdx.graphics.getDeltaTime());

            if (zz.equals(z)) {
                System.out.println("FOUND");
                maxlen = out.size();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return out;
            }
            if (out.size() < 20) {
                out.add(zz);

                if (out.size() < maxlen)
                    buildIntern(zz, z, level++);
                out.remove(zz);
                EntityFactory.remove(ent);
                Pool.getGameScreen().render(Gdx.graphics.getDeltaTime());

            }


        }
        return out;

    }

    private void dumpOut(List<Zolla> out) {
        for (Zolla z : out) System.out.print(z);
        System.out.println();
    }
}
