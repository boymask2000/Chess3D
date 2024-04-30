package com.posbeu.littletown.engine.player;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.Constants;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.BoardPosition;

public class BoardGDX {

    private static Board board;
    private static int height;
    private static int width;

    static {
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
    }

    public static BoardPosition getPezzoActorInPosition(int screenX, int screenY) {
      //  System.out.println(screenX + " " + screenY);
        int y = height - screenY;

        int size = height / 8;
        int i = screenX / size;
        int j = y / size;
      //  System.out.println(i + " " + j);
        return new BoardPosition(board.getPezzo(i, j), i, j);

    }

    public static void setBoard(Board board) {
        BoardGDX.board = board;
    }

    public static float convertIPosToCoord(int i) {
        float consta = Constants.CELL_SIZE;// 6.5f;
        float posX = i * consta + Constants.CELL_SIZE;
        return posX;
     /*   int size = height / 8;
        return i * size;*/
    }
    public static float convertJPosToCoord(int j) {
        float consta = Constants.CELL_SIZE;// 6.5f;

        float posY = j * Constants.CELL_SIZE + Constants.CELL_SIZE;
        return posY;
    /*    int size = height / 8;
        return i * size;*/
    }

    public static Vector3 convertToVectorPosition(int i, int j){
        float posX = convertIPosToCoord(i);
        float posY = convertJPosToCoord(j);

        return new Vector3(posX,0,posY);
    }

    public static BoardPosition convertToFloatPos(int i, int j) {

        float posX = convertIPosToCoord(i);
        float posY = convertJPosToCoord(j);

/*        int size = height / 8;
        float posX = size * i;
        float posY = size * j;*/
        BoardPosition p = new BoardPosition();
        p.setJ(j);
        p.setI(i);
        p.setFloatI(posX);
        p.setFloatJ(posY);
        return p;
    }
    public static BoardPosition convertToFloatPos(BoardPosition p) {
        BoardPosition out= convertToFloatPos(p.getI(), p.getJ());
        out.setObj(p.getObj());
        return out;

    }
}
