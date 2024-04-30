package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.component.PezzoComponent;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.BoardPosition;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.player.BoardGDX;

import java.util.List;

public class BoardAligner {
    public static void align() {

        pezziDaCancellare();
        pezziDaInserire();
        pezziDaSpostare();
    }

    private static void pezziDaSpostare() {
        Engine engine = Pool.getEngine();
        Board board = Pool.getChessEngine().getBoard();
        ImmutableArray<Entity> pezziEntities = engine.getEntitiesFor(
                Family.all(
                        PezzoComponent.class
                ).get());

        for (int i = 0; i < pezziEntities.size(); i++) {
            PezzoComponent pc =
                    pezziEntities.get(i).getComponent(PezzoComponent.class);
            int screenI = pc.getI();
            int   screenJ= pc.getJ();
            BoardPosition pos = pezzoInBoard(pc);

            if (pos.getI()!=screenI || pos.getJ()!=screenJ) {

                Vector3 dest = BoardGDX.convertToVectorPosition(pos.getI(), pos.getJ());
                pc.setDestination(dest, pos.getI(), pos.getJ());

            }

        }
    }

    private static void pezziDaInserire() {
    }

    private static void pezziDaCancellare() {
        Engine engine = Pool.getEngine();


        ImmutableArray<Entity> pezziEntities = engine.getEntitiesFor(
                Family.all(
                        PezzoComponent.class
                ).get());

        for (int i = 0; i < pezziEntities.size(); i++) {
            PezzoComponent pc =
                    pezziEntities.get(i).getComponent(PezzoComponent.class);
            if (pezzoInBoard(pc)==null)
                engine.removeEntity(pezziEntities.get(i));

        }
    }

    private static BoardPosition pezzoInBoard(PezzoComponent pc) {
        Board board = Pool.getChessEngine().getBoard();
        Pezzo pezzo = pc.getPezzo();

        Pezzo[][] pp = board.getBoard();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if(pp[i][j]==pezzo)return new BoardPosition(i,j);
            }
        return null;
    }
}
