package com.posbeu.littletown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.utils.JsonReader;
import com.posbeu.littletown.city.abs.Edificio;

import java.util.HashMap;
import java.util.Map;

public class ModelManager {
    private static Map<String, Model> models = new HashMap<>();

    public static Model getModel(Edificio edificio) {
        return ModelManager.getg3djModel("falegname");
    }

    public static Model getg3djModel(String fName) {

        String fileName = fName;

        fileName += ".g3dj";

        Model m = models.get(fileName);
        if (m != null) return m;

        m = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("data/" + fileName));
        models.put(fileName, m);
        return m;
    }

    public static Model getObjModel(String fName) {

        String fileName = fName;

        fileName += ".obj";

        Model m = models.get(fileName);
        if (m != null) return m;

        ModelLoader loader = new ObjLoader();
        m = loader.loadModel(Gdx.files.internal("data/" + fileName));
        models.put(fileName, m);
        return m;

    }
}
