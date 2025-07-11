package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Plane;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import static com.mygame.ScreenCommand.CREATE_DEPOSITO;
import static com.mygame.ScreenCommand.CREATE_FALEGNAME;
import static com.mygame.ScreenCommand.CREATE_MULINO;
import static com.mygame.ScreenCommand.CREATE_POZZO;
import com.mygame.buildings.FactoryBuildings;
import com.mygame.buildings.TipoEdificio;
import com.mygame.buildings.edifici.Edificio;
import com.mygame.catasto.InventarioEdifici;

/**
 *
 * @author giovanni
 */
public class InputHandler {

    private static final String MAPPING_ROTATE = "Rotate";
    private static final Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);

    private static final AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float intensity, float tpf) {
            setComandi(name, Pool.getCam(), tpf);

            Vector3f click3d = Pool.getIntersection();

            if (click3d != null) {
                CommandPanel commandPanel = Pool.getCommandPanel();
                ScreenCommand command = commandPanel.getCurrentCommand();
                if (command == null) {
                    return;
                }
                Spatial edificio = null;
                switch (command) {
                    case CREATE_FALEGNAME:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.FALEGNAME, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_POZZO:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.POZZO, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_TAGLIALEGNA:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.TAGLIALEGNA, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_FORESTALE:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.FORESTALE, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_DEPOSITO:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.DEPOSITO, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_ALLEVATORE_MAIALI:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.ALLEVATORE_MAIALI, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_FORNO:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.FORNO, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_MACELLAIO:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.MACELLAIO, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_MULINO:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.MULINO, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                    case CREATE_COLTIVATORE_GRANO:
                        edificio = FactoryBuildings.createBuilding(TipoEdificio.COLTIVATORE_GRANO, click3d);
                        setObject(click3d, ColorRGBA.Blue, edificio);
                        break;
                }

                commandPanel.reset();

            }
        }

    };

    private static void setComandi(String name, Camera cam, float tpf) {
        float speed = 0.001f; // ← Regola qui la quantità di spostamento

        if (name.equals("Forward")) {
            cam.setLocation(cam.getLocation().add(cam.getDirection().mult(speed * tpf)));
        }
        if (name.equals("Backward")) {
            cam.setLocation(cam.getLocation().subtract(cam.getDirection().mult(speed * tpf)));
        }
        if (name.equals("Left")) {
            cam.setLocation(cam.getLocation().add(cam.getLeft().mult(speed * tpf)));
        }
        if (name.equals("Right")) {
            cam.setLocation(cam.getLocation().subtract(cam.getLeft().mult(speed * tpf)));
        }

    }

    private static void setObject(Vector3f pos, ColorRGBA color, Spatial edificio) {

        float height = Pool.getTerrain().getHeight(new Vector2f(pos.x, pos.z));
        //    height = 9;

        edificio.setLocalTranslation(pos);
        //   Geometry pp = Pool.getFactory().myBox("ppppp", pos, color);

        // pp.setLocalTranslation(pos);
        //Pool.getRootNode().attachChild(pp);
    }
    private final static ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Click") && isPressed) {
                Vector2f click2d = Pool.getInputManager().getCursorPosition();
                Vector3f origin = Pool.getCam().getWorldCoordinates(click2d, 0f);
                Vector3f direction = Pool.getCam().getWorldCoordinates(click2d, 1f).subtract(origin).normalizeLocal();
                Ray ray = new Ray(origin, direction);

                Plane ground = new Plane(Vector3f.UNIT_Y, 0); // y = 0
                Vector3f intersection = new Vector3f();

                if (ray.intersectsWherePlane(ground, intersection)) {

                    // Sposta il marcatore
                    Pool.getMarker().setLocalTranslation(intersection);
                    Pool.getMarker().setCullHint(Spatial.CullHint.Inherit); // Rendi visibile
                }

                Pool.setIntersection(intersection);
            }
        }
    };
    private final static ActionListener popupActionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            //Qui elimino il menu se è mostrato. Da migliorare
            if (Pool.getGuiNode().getChildren().size() > 1) {
                Pool.getGuiNode().detachChildAt(1);
            }

            if (name.equals("ShowMenu") && !isPressed) {

                CollisionResults results = new CollisionResults();
                Vector2f click2d = Pool.getInputManager().getCursorPosition();
                Vector3f origin = Pool.getCam().getWorldCoordinates(click2d, 0f);
                Vector3f direction = Pool.getCam().getWorldCoordinates(click2d, 1f).subtract(origin).normalizeLocal();
                Ray ray = new Ray(origin, direction);

                Pool.getRootNode().collideWith(ray, results);
            
                if (results.size() > 0) {
                    Geometry target = results.getClosestCollision().getGeometry();

                    Edificio ed = InventarioEdifici.searchEdificioByGeometry(target);

                    if (ed != null) {
                        ed.showPopupMenu();
                    }
                    //  if (target == cube) {
//                        Vector3f worldPoint = results.getClosestCollision().getContactPoint();
//                        showPopupMenu(worldPoint);
                    // }
                }
            }
        }
    };

    public static void enable(SimpleApplication app) {

        app.getInputManager().addMapping(MAPPING_ROTATE, TRIGGER_ROTATE);
        app.getInputManager().addListener(analogListener, new String[]{MAPPING_ROTATE});
        app.getInputManager().addListener(actionListener, "Click");

        app.getInputManager().addMapping("ShowMenu", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        app.getInputManager().addListener(popupActionListener, "ShowMenu");

        app.getInputManager().addRawInputListener(new RawInputListener() {

            @Override
            public void onMouseMotionEvent(MouseMotionEvent evt) {
                int deltaWheel = evt.getDeltaWheel(); // Positivo o negativo

                if (deltaWheel != 0) {
                    // Muovi la camera lungo l'asse Y (su/giù)
                    Vector3f camLocation = Pool.getCam().getLocation();

                    Vector3f loc = camLocation.add(0, deltaWheel * 2.5f, deltaWheel * 0f);
                    //   System.out.println("X= "+loc.getX()+"  Y= "+loc.getY()+"  Z= "+loc.getZ());
                    if (loc.getY() < 10) {
                        return;
                    }
                    Pool.getCam().setLocation(loc);
                }
            }

            // Gli altri metodi vanno implementati ma possono restare vuoti
            @Override
            public void beginInput() {
            }

            @Override
            public void endInput() {
            }

            @Override
            public void onJoyAxisEvent(com.jme3.input.event.JoyAxisEvent evt) {
            }

            @Override
            public void onJoyButtonEvent(com.jme3.input.event.JoyButtonEvent evt) {
            }

            @Override
            public void onMouseButtonEvent(com.jme3.input.event.MouseButtonEvent evt) {
            }

            @Override
            public void onKeyEvent(com.jme3.input.event.KeyInputEvent evt) {
            }

            @Override
            public void onTouchEvent(com.jme3.input.event.TouchEvent evt) {
            }
        });

    }

}
