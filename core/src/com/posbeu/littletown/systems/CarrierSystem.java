package com.posbeu.littletown.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.Pool;
import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.abs.Ordine;
import com.posbeu.littletown.city.enums.TipoMerce;
import com.posbeu.littletown.city.infrastruct.CentraleOrdini;
import com.posbeu.littletown.city.infrastruct.RegistroProduzioni;
import com.posbeu.littletown.component.BaseComponent;
import com.posbeu.littletown.component.CarrierComponent;
import com.posbeu.littletown.component.EdificioComponent;
import com.posbeu.littletown.terrain.Zolla;

import java.util.List;

public class CarrierSystem extends EntitySystem {
    private final ModelBatch batch;
    private final Environment environment;

    private ImmutableArray<Entity> carrierEntities;
    private ImmutableArray<Entity> entities;

    private Engine engine;


    public CarrierSystem(ModelBatch batch, Environment environment) {
        this.environment = environment;
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        carrierEntities =
                engine.getEntitiesFor(
                        Family.all(
                                CarrierComponent.class
                        ).get());
        for (int i = 0; i < carrierEntities.size(); i++) {
            CarrierComponent mod =
                    carrierEntities.get(i).getComponent(CarrierComponent.class);
            batch.render(mod.instance, environment);
            updateMovement(delta, mod);
        }


    }

    private void updateMovement(float delta, CarrierComponent carrierComponent) {
        if (carrierComponent == null) return;
        System.out.println(carrierComponent.getCurrentStatus());
        Zolla target = null;

        switch (carrierComponent.getCurrentStatus()) {
            case IDLE:
                boolean foundOrdine = processOrdini(carrierComponent);
                if (!foundOrdine) return;
                carrierComponent.setCurrentStatus(CarrierComponent.Status.GET_CARICO);

                return;
            case GET_CARICO:
                target = carrierComponent.getFornitore();

                break;
            case PUT_CARICO:
                break;
        }


        Vector3 position = carrierComponent.instance.transform.getTranslation(new Vector3());
        Zolla currentZolla = Pool.getTerrain().getZolla(position.x, position.z);
        if (target.equals(currentZolla)) {

            processTarget(carrierComponent, currentZolla);
            return;
        }

        Zolla nextZolla = calculateNextZolla(currentZolla, target);
        if (nextZolla == null) return;
        PerspectiveCamera camera = Pool.getCamera();

        Vector3 pos = carrierComponent.getPosition();

        float dx = nextZolla.getNormalizedX() - currentZolla.getNormalizedX();
        float dz = nextZolla.getNormalizedZ() - currentZolla.getNormalizedZ();
        float dy = nextZolla.getY() - currentZolla.getY();
        float sum = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
        int fatt = 10;
        dx = fatt * dx / sum;
        dy = fatt * dy / sum;
        dz = fatt * dz / sum;


/*        if (target.equals(currentZolla))
            target = nextZolla;*/

        carrierComponent.instance.transform.translate(dx * delta, dz * delta, dy * delta);

        Vector3 translation = new Vector3();
        translation.x = 0.01F;
    }

    private void processTarget(CarrierComponent carrierComponent, Zolla currentZolla) {
        switch (carrierComponent.getCurrentStatus()) {
            case IDLE:

                return;
            case GET_CARICO:
                EdificioComponent comp = currentZolla.getEntity().getComponent(EdificioComponent.class);
                if (comp == null) return;

                EdificoProduzione edificio = (EdificoProduzione) comp.getEdificio();

             /*   BaseComponent comp = currentZolla.getEntity().getComponent(BaseComponent.class);
                if (comp == null) return;
                EdificioComponent ed = (EdificioComponent) comp;
                EdificoProduzione edificio = (EdificoProduzione) ed.getEdificio();*/
                System.out.println("CARICO " + edificio);
                break;
            case PUT_CARICO:
                break;
        }
    }


    private Zolla calculateNextZolla(Zolla currentZolla, Zolla target) {

        if (target == null) return null;
        Vector3 targ = target.getNormalized();

        List<Zolla> vics = currentZolla.getVicini();

        Zolla minZ = vics.get(0);
        float min = minZ.getNormalized().dst(targ);
        for (Zolla z : vics) {
            if (z.getElement() != null) continue;
            float dist = z.getNormalized().dst(targ);
            if (dist < min) {
                min = dist;
                minZ = z;
            }
        }
        return minZ;

    }

    private boolean processOrdini(CarrierComponent carrierComponent) {

        Ordine ordine = CentraleOrdini.getOrdine();
        if (ordine == null) return false;
        System.out.println("trovato ordine " + ordine);
        Zolla acquirente = ordine.getEdificio().getZolla();
        TipoMerce merceRichiesta = ordine.getMerce();
        EdificoProduzione edificioProduttore = RegistroProduzioni.getProduttore(merceRichiesta);
        if (edificioProduttore == null) {
            CentraleOrdini.restituisciOrdine(ordine);
            return false;
        }
        Zolla fornitore = edificioProduttore.getZolla();
        carrierComponent.setAcquirente(acquirente);
        carrierComponent.setFornitore(fornitore);
        return true;

    }
}