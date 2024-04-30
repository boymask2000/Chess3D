package com.posbeu.littletown.engine.pezzi;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.BoardPosition;
import com.posbeu.littletown.engine.mosse.Mossa;

public abstract class Pezzo {
    private final PezziEnum pezzoEnum;
    private final Color colore;
    private boolean alreadyMoved = false;

    public Pezzo(PezziEnum pEnum, Color colore) {
        this.pezzoEnum = pEnum;
        this.colore = colore;
    }

    public Color getColore() {
        return colore;
    }

    public int getValore() {
        return pezzoEnum.getValore();
    }

    public String getCode() {
        return pezzoEnum.getCode();
    }

    @Override
    public String toString() {
        return "Pezzo{" +
                pezzoEnum +
                "-" + colore +
                '}';
    }

    public PezziEnum getPezzoEnum() {
        return pezzoEnum;
    }

    public boolean isAlreadyMoved() {
        return alreadyMoved;
    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public abstract void getMossePossibili(int i, int j, Board board, MossePossibili mossePossibili);

    protected boolean buildMossa(BoardPosition from, BoardPosition to, Board b, MossePossibili mossePossibili) {
        int i = to.getI();
        int j = to.getJ();
        if (i < 0 || i > 7) return false;
        if (j < 0 || j > 7) return false;
        Pezzo p = b.getPezzo(i, j);
        if (p != null) {
            if (p.getColore() != this.getColore()) {
                Mossa m = new Mossa(this, from, to);
                mossePossibili.addMossa(m);
            }
            return false;
        }
        Mossa m = new Mossa(this, from, to);
        mossePossibili.addMossa(m);
        return true;
    }


    public Model getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new
                Material(ColorAttribute.createDiffuse(com.badlogic.gdx.graphics.Color.GREEN),
                ColorAttribute.createSpecular(com.badlogic.gdx.graphics.Color.RED),
                FloatAttribute.createShininess(16f));
        Model box = modelBuilder.createBox(5, 5, 5, boxMaterial,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return box;
    }

    ;

    protected Model getModel(String fName) {

        String fileName = fName;

        if (getColore() == Color.BIANCO)
            fileName += "_b.g3dj";
        else
            fileName += "_n.g3dj";

        return new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("data/" + fileName));
    }
}
