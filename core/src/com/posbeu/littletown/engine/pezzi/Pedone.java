package com.posbeu.littletown.engine.pezzi;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.posbeu.littletown.engine.Engine;
import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.Position;

public class Pedone extends Pezzo {


    public Pedone(Color colore) {
        super(PezziEnum.PEDONE, colore);
    }

    @Override
    public void getMossePossibili(int i, int j, Board board, MossePossibili mossePossibili) {

        Position from = new Position(i, j);
        int fact = 1;
        if (getColore() == Color.NERO) fact = -1;

        if (j + fact * 2 >= 0 && j + fact * 2 < 8)
            if (!isAlreadyMoved() && board.getPezzo(i, j + fact * 2) == null) {
                Mossa m = new Mossa(this, from, new Position(i, j + fact * 2));
                mossePossibili.addMossa(m);
            }
        if (j + fact >= 0 && j + fact < 8)
            if (board.getPezzo(i, j + fact) == null) {
                Mossa m = new Mossa(this, from, new Position(i, j + fact));
                mossePossibili.addMossa(m);
            }
        if (i > 0) {
            Pezzo pezzo = board.getPezzo(i - 1, j + fact);
            if (pezzo != null) {
                if (pezzo.getColore() == Engine.avversario(getColore())) {
                    Mossa m = new Mossa(this, from, new Position(i - 1, j + fact));
                    mossePossibili.addMossa(m);
                }
            }
        }
        if (i < 7) {
            Pezzo pezzo = board.getPezzo(i + 1, j + fact);
            if (pezzo != null) {
                if (pezzo.getColore() == Engine.avversario(getColore())) {
                    Mossa m = new Mossa(this, from, new Position(i + 1, j + fact));

                    mossePossibili.addMossa(m);
                }
            }
        }
    }

    @Override
    public Model getModel() {
        return getModel("pedone");
    }
}
