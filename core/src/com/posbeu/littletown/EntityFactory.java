package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import com.posbeu.littletown.component.*;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.models.CellBianca;
import com.posbeu.littletown.models.CellNera;

public class EntityFactory {

    static Model playerModel;

    static {
        ModelBuilder modelBuilder = new ModelBuilder();
        Texture playerTexture = new
                Texture(Gdx.files.internal("badlogic.jpg"));
        Material material = new
                Material(TextureAttribute.createDiffuse(playerTexture),
                ColorAttribute.createSpecular(1, 1, 1, 1),
                FloatAttribute.createShininess(8f));
        playerModel = modelBuilder.createCapsule(2f, 6f, 16, material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
                        | VertexAttributes.Usage.TextureCoordinates);
    }

    private static void createStaticEntity(Model model, float x, float y,
                                           float z, int i, int j) {
        final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y,
                z);

        modelComponent.setI(i);
        modelComponent.setJ(j);

        entity.add(modelComponent);

        Pool.addInstance(modelComponent);
        Engine engine = Pool.getEngine();
        engine.addEntity(entity);
    }

    public static void createCell(int i, int j) {

        Model m = CellNera.getModel();
        if ((i + j) % 2 == 0) m = CellBianca.getModel();

        float consta = Constants.CELL_SIZE;//6.5f;
        float posX = i * consta + Constants.CELL_SIZE;
        float posY = j * Constants.CELL_SIZE + Constants.CELL_SIZE;

        createStaticEntity(m, posX, -1, posY, i, j);
    }

    public static CellCursorComponent createCellCursor(int i, int j) {
        Entity entity = new Entity();
        CellCursorComponent cellCursorComponent = new CellCursorComponent(i, j);

        entity.add(cellCursorComponent);

        Pool.addInstance(cellCursorComponent);
        Engine engine = Pool.getEngine();
        engine.addEntity(entity);

        return cellCursorComponent;
    }

    public static void createPezzo(int i, int j, Pezzo pezzo) {
        Pool.getChessEngine().setPezzo(pezzo, i, j);

        float consta = Constants.CELL_SIZE;// 6.5f;
        float posX = i * consta + Constants.CELL_SIZE;
        float posY = j * Constants.CELL_SIZE + Constants.CELL_SIZE;

        Vector3 pos = new Vector3(posX, 0, posY);
        Engine engine = Pool.getEngine();

        PezzoComponent comp = new PezzoComponent(i, j, pezzo);
        comp.setPosition(pos);
        //comp.setPosition(new Vector3(i * Constants.CELL_SIZE, 0, j * Constants.CELL_SIZE));
        Entity entity = new Entity();
        entity.add(comp);
        engine.addEntity(entity);

        Pool.addInstance(comp);
    }

}
