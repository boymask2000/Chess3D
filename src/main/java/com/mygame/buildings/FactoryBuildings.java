/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings;

import com.mygame.buildings.edifici.coltivatore.EdificioColtivatore;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.mygame.Pool;
import static com.mygame.buildings.TipoEdificio.DEPOSITO;
import static com.mygame.buildings.TipoEdificio.FALEGNAME;
import static com.mygame.buildings.TipoEdificio.POZZO;
import com.mygame.buildings.edifici.AllevatoreMaiali;
import com.mygame.buildings.edifici.Deposito;
import com.mygame.buildings.edifici.Edificio;
import com.mygame.buildings.edifici.Falegname;
import com.mygame.buildings.edifici.Forestale;
import com.mygame.buildings.edifici.Forno;
import com.mygame.buildings.edifici.Macellaio;
import com.mygame.buildings.edifici.Mulino;
import com.mygame.buildings.edifici.Pozzo;
import com.mygame.buildings.edifici.Taglialegna;
import com.mygame.catasto.InventarioEdifici;
import com.mygame.models.ModelManager;

/**
 *
 * @author giovanni
 */
public class FactoryBuildings {

    public static Spatial createBuilding(TipoEdificio tipoEdificio, Vector3f click3d) {
        Edificio ed = null;
      
        switch (tipoEdificio) {

            case MACELLAIO:
                ed = new Macellaio(click3d);
                break;
            case FORNO:
                ed = new Forno(click3d);
                break;
            case MULINO:
                ed = new Mulino(click3d);
                break;
            case ALLEVATORE_MAIALI:
                ed = new AllevatoreMaiali(click3d);
                break;
            case FALEGNAME:
                ed = new Falegname(click3d);
                break;
            case FORESTALE:
                ed = new Forestale(click3d);
                break;
            case POZZO:
                ed = new Pozzo(click3d);
                break;
            case TAGLIALEGNA:
                ed = new Taglialegna(click3d);
                break;
            case DEPOSITO:
                ed = new Deposito(click3d);
                break;
            case COLTIVATORE_GRANO:
                ed = new EdificioColtivatore(TipoEdificio.COLTIVATORE_GRANO,click3d);
                break;
        }
        if (ed != null) {
            return createSpatial(ed);
        }
        return null;
    }

    private static Spatial createSpatial( Edificio edificio) {
        Node n= ModelManager.getModelNodeForTipoEdificio(edificio.getTipoEdificio());
//        Spatial model = Pool.getAssetManager().loadModel(modelPath);
//        model.scale(0.10f); // opzionale
//        model.setLocalTranslation(0, 0, 0); // opzionale

        ControlEdificio control = new ControlEdificio(edificio);
        n.addControl(control);

// Se vuoi accedere a una Geometry specifica
//Geometry geom = findGeometry(model, "NomeGeometryDesiderata");
// oppure semplicemente attacca tutto alla scena
        Pool.getRootNode().attachChild(n);
        InventarioEdifici.add(edificio, n);
        edificio.setSpatial(n);
        return n;
    }

}
