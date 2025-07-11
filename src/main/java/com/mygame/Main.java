package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
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
        cam.setLocation(new Vector3f(0, 5, 10));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
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

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);
    }

    private float fixedY = 10f;

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f camLoc = cam.getLocation();

        cam.setLocation(new Vector3f(camLoc.x, fixedY, camLoc.z));

        Pool.getWalkers().stream().forEach(p -> p.update(tpf));
        Pool.getEdifici().stream().forEach(p -> p.update(tpf));
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

}
