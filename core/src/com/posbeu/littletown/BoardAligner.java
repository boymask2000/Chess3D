package com.posbeu.littletown;

import com.posbeu.littletown.component.PezzoComponent;
import com.posbeu.littletown.engine.Board;

import java.util.List;

public class BoardAligner {
    public static void align(){

        Board board = Pool.getChessEngine().getBoard();
        List<PezzoComponent> pezziComps = Pool.getPezzi();

       /* for(PezzoComponent pc: pezziComps){
            if( !pezzoInBoard(pc))
        }*/
    }
}
