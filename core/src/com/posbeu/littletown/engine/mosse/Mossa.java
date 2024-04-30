package com.posbeu.littletown.engine.mosse;



import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.BoardPosition;

import java.util.Objects;

public class Mossa {
    private Pezzo pezzo;
    private BoardPosition from;
    private BoardPosition to;
    private Pezzo pezzoEliminato;

    private boolean prevAlreadyMoved;





    public Pezzo getPezzo() {
        return pezzo;
    }

    public void setPezzo(Pezzo pezzo) {
        this.pezzo = pezzo;
    }

    public BoardPosition getFrom() {
        return from;
    }

    public void setFrom(BoardPosition from) {
        this.from = from;
    }

    public BoardPosition getTo() {
        return to;
    }

    public void setTo(BoardPosition to) {
        this.to = to;
    }

    public Mossa(Pezzo p, BoardPosition from, BoardPosition to) {
        this.pezzo = p;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
   //     if (o == null || getClass() != o.getClass()) return false;
        Mossa mossa = (Mossa) o;
        return getPezzo().getCode().equals(mossa.getPezzo().getCode())
                && getPezzo().getColore() == mossa.getPezzo().getColore()
                && getFrom().equals(mossa.getFrom()) && getTo().equals(mossa.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPezzo(), getFrom(), getTo());
    }

    @Override
    public String toString() {
        return "Mossa{" +
                "pezzo=" + pezzo +
                ", from=" + from +
                ", to=" + to +
                '}';
    }


    public void setPezzoEliminato(Pezzo pezzo) {
        this.pezzoEliminato = pezzo;
    }
    public Pezzo getPezzoEliminato() {
        return pezzoEliminato;
    }

    public void revert( Board board ){

        board.setPezzo(getTo(), getPezzoEliminato());
        board.setPezzo(getFrom(), getPezzo());
        getPezzo().setAlreadyMoved(isPrevAlreadyMoved());


    }
    public void esegui(Board board, boolean simulate) {
/*
        if( !simulate)
            Anagrafica.spostaPezzoActor(this)*/;

        setPrevAlreadyMoved(getPezzo().isAlreadyMoved());
        getPezzo().setAlreadyMoved(true);
        setPezzoEliminato(board.getPezzo(getTo()));
        board.setPezzo(getTo(), getPezzo());
        board.setPezzo(getFrom(), null);
    }

    public void setPrevAlreadyMoved(boolean alreadyMoved) {
        this.prevAlreadyMoved=alreadyMoved;
    }
    public boolean isPrevAlreadyMoved() {
        return prevAlreadyMoved;
    }


}
