package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.mygame.pezzi.Alfiere_B;
import com.mygame.pezzi.Alfiere_N;
import com.mygame.pezzi.Casella;
import com.mygame.pezzi.Cavallo_B;
import com.mygame.pezzi.Cavallo_N;
import com.mygame.pezzi.King_B;
import com.mygame.pezzi.King_N;
import com.mygame.pezzi.Pedone_B;
import com.mygame.pezzi.Pedone_N;
import com.mygame.pezzi.Regina_B;
import com.mygame.pezzi.Regina_N;
import com.mygame.pezzi.Torre_B;
import com.mygame.pezzi.Torre_N;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {

        Main app = new Main();

        app.start();
    }

    @Override
    public void simpleInitApp() {
        Pool.setCam(cam);
        initPanel();

        makeMarker();

        flyCam.setEnabled(true);
        cam.setLocation(new Vector3f(50, 80, -50));
        cam.lookAt(new Vector3f(50, 0, 50), Vector3f.UNIT_Y);
        flyCam.setDragToRotate(true);
        inputManager.setCursorVisible(true);
        //  flyCam.setEnabled(false);

        //  ChaseCamera chaseCam = new ChaseCamera(cam, target, inputManager);
        Pool.setFactory(new Factory(this));
        Pool.setSettings(this.settings);
        Pool.setGuiNode(guiNode);
        Pool.setAssetManager(assetManager);
        Pool.setCommandPanel(new CommandPanel(guiNode));

        Pool.setRootNode(rootNode);
        Pool.setInputManager(inputManager);
        InputHandler.enable(this);

        DefaultAppState state = new DefaultAppState(this);
        getStateManager().attach(state);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1f, -1f, -1f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

// Aggiunta di una seconda luce soft dall’altro lato
        DirectionalLight fill = new DirectionalLight();
        fill.setDirection(new Vector3f(1f, -0.5f, 1f).normalizeLocal());
        fill.setColor(ColorRGBA.White.mult(0.2f));
        rootNode.addLight(fill);

        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White.mult(0.3f)); // Più basso per evitare effetto "piatto"
        rootNode.addLight(ambient);

          createCaselle();
        createPezzi();
    }

    private float fixedY = 10f;

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f camLoc = cam.getLocation();

        //      cam.setLocation(new Vector3f(camLoc.x, fixedY, camLoc.z));
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void makeMarker() {
        Geometry marker;
        Sphere sphere = new Sphere(16, 16, 0.1f);
        marker = new Geometry("Marker", sphere);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Black.Red);
        marker.setMaterial(mat);
        rootNode.attachChild(marker);

        // All'inizio nascondiamo il marker
        marker.setCullHint(Spatial.CullHint.Always);

        inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        Pool.setMarker(marker);
    }

    private void initPanel() {
        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

    }

    private void createPezzi() {

        Pool.addPezzo(new Torre_B("A", "1"));
        Pool.addPezzo(new Cavallo_B("B", "1"));
        Pool.addPezzo(new Alfiere_B("C", "1"));
        Pool.addPezzo(new King_B("D", "1"));
        Pool.addPezzo(new Regina_B("E", "1"));
        Pool.addPezzo(new Alfiere_B("F", "1"));
        Pool.addPezzo(new Cavallo_B("G", "1"));
        Pool.addPezzo(new Torre_B("H", "1"));

        Pool.addPezzo(new Pedone_B("A", "2"));
        Pool.addPezzo(new Pedone_B("B", "2"));
        Pool.addPezzo(new Pedone_B("C", "2"));
        Pool.addPezzo(new Pedone_B("D", "2"));
        Pool.addPezzo(new Pedone_B("E", "2"));
        Pool.addPezzo(new Pedone_B("F", "2"));
        Pool.addPezzo(new Pedone_B("G", "2"));
        Pool.addPezzo(new Pedone_B("H", "2"));

        Pool.addPezzo(new Regina_N("E", "8"));
        Pool.addPezzo(new King_N("D", "8"));
        Pool.addPezzo(new Alfiere_N("F", "8"));
        Pool.addPezzo(new Alfiere_N("C", "8"));
        Pool.addPezzo(new Cavallo_N("B", "8"));
        Pool.addPezzo(new Cavallo_N("G", "8"));
        Pool.addPezzo(new Torre_N("A", "8"));
        Pool.addPezzo(new Torre_N("H", "8"));

        Pool.addPezzo(new Pedone_N("A", "7"));
        Pool.addPezzo(new Pedone_N("B", "7"));
        Pool.addPezzo(new Pedone_N("C", "7"));
        Pool.addPezzo(new Pedone_N("D", "7"));
        Pool.addPezzo(new Pedone_N("E", "7"));
        Pool.addPezzo(new Pedone_N("F", "7"));
        Pool.addPezzo(new Pedone_N("G", "7"));
        Pool.addPezzo(new Pedone_N("H", "7"));

    }

    private void createCaselle() {
          String colonne = "ABCDEFGH";
         
          for(int i=1; i<=8; i++)
              for(int j=1; j<=8; j++){
                   new Casella(i,j);
              }
        
    }

}
