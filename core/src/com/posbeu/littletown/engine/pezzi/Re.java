package com.posbeu.littletown.engine.pezzi;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.mosse.Arrocco;
import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.Position;

public class Re extends Pezzo {
    public Re(Color colore) {
        super(PezziEnum.RE, colore);
    }

    @Override
    public void getMossePossibili(int x, int y, Board board, MossePossibili mossePossibili) {

        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i > 7) continue;
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j > 7) continue;
                if (i == x && j == y) continue;

                Pezzo p = board.getPezzo(i, j);
                if (p != null && p.getColore() == this.getColore()) continue;

                Mossa m = new Mossa(this, new Position(x, y), new Position(i, j));

                mossePossibili.addMossa(m);
            }
        }
        arrocco(x, y, board, mossePossibili);
    }

    private void arrocco(int x, int y, Board board, MossePossibili mossePossibili) {
        if (board.isCellaMinacciata(x, y, getColore())) return;
        arroccoSinistro(x, y, board, mossePossibili);
        arroccoDestro(x, y, board, mossePossibili);
    }

    private void arroccoSinistro(int x, int y, Board board, MossePossibili mossePossibili) {

        if (isAlreadyMoved()) return;

        Pezzo p = board.getPezzo(0, y);
        if (p == null) return;
        if (p.getPezzoEnum() != PezziEnum.TORRE || p.isAlreadyMoved()) return;
        for (int i = x - 1; i > 0; i++)
            if (!board.isEmpty(i, y) || board.isCellaMinacciata(i, y, getColore())) return;

        Arrocco m = new Arrocco(this, new Position(x,y), new Position(x-2,y));
        mossePossibili.addMossa(m);
    }

    private void arroccoDestro(int x, int y, Board board, MossePossibili mossePossibili) {
      //  System.out.println(x+" "+y);
        if (isAlreadyMoved()) return;

        Pezzo p = board.getPezzo(7, y);
        if (p == null) return;
        if (p.getPezzoEnum() != PezziEnum.TORRE || p.isAlreadyMoved()) return;
        for (int i = x + 1; i < 7; i++)
            if (!board.isEmpty(i, y) || board.isCellaMinacciata(i, y, getColore())) return;

        Arrocco m = new Arrocco(this, new Position(x,y), new Position(x+2,y));
       // System.out.println(m);
        mossePossibili.addMossa(m);
    }


    @Override
    public Model getModel() {
        return getModel("king");
    }
}
