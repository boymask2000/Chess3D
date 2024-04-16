package com.posbeu.littletown.engine.player;

import com.badlogic.gdx.Gdx;

import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.Position;

public class BoardGDX {

    private static Board board;
    private static int height;
    private static int width;

    static {
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
    }

    public static Position getPezzoActorInPosition(int screenX, int screenY) {
      //  System.out.println(screenX + " " + screenY);
        int y = height - screenY;

        int size = height / 8;
        int i = screenX / size;
        int j = y / size;
      //  System.out.println(i + " " + j);
        return new Position(board.getPezzo(i, j), i, j);

    }

    public static void setBoard(Board board) {
        BoardGDX.board = board;
    }

    public static float convertPosToCoord(int i) {
        int size = height / 8;
        return i * size;
    }

    public static Position convertToFloatPos(int i, int j) {

        int size = height / 8;
        float posX = size * i;
        float posY = size * j;
        Position p = new Position();
        p.setJ(j);
        p.setI(i);
        p.setFloatI(posX);
        p.setFloatJ(posY);
        return p;
    }
    public static Position convertToFloatPos(Position p) {
        Position out= convertToFloatPos(p.getI(), p.getJ());
        out.setObj(p.getObj());
        return out;

    }
}
