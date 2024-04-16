package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.posbeu.littletown.engine.pezzi.Alfiere;
import com.posbeu.littletown.engine.pezzi.Cavallo;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.Pedone;
import com.posbeu.littletown.engine.pezzi.Re;
import com.posbeu.littletown.engine.pezzi.Regina;
import com.posbeu.littletown.engine.pezzi.Torre;
import com.posbeu.littletown.systems.RenderSystem;

public class GameWorld {
    private static final double SIN = Math.sin(Math.PI / 4);
    private static final float FOV = 67F;
    private ModelBatch modelBatch;
    private Environment environment;

    private Terrain terrain = new Terrain();

    public Engine getEngine() {
        return engine;
    }

    private Engine engine;

    public ModelBuilder modelBuilder = new ModelBuilder();
    private PerspectiveCamera perspectiveCamera;

    private ShapeRenderer shapeRenderer;

    public MyInputTracker getInputProcessor() {
        return inputProcessor;
    }

    private MyInputTracker inputProcessor;

    public GameWorld() {

        initEnvironment();
        initModelBatch();
        initPersCamera();
        addSystems();
        addEntities();

        initPezzi();
    }

    private void initPezzi() {
        EntityFactory.createPezzo(0, 0, new Torre(Color.BIANCO));
        EntityFactory.createPezzo(1, 0, new Cavallo(Color.BIANCO));
        EntityFactory.createPezzo(2, 0, new Alfiere(Color.BIANCO));
        EntityFactory.createPezzo(3, 0, new Regina(Color.BIANCO));
        EntityFactory.createPezzo(4, 0, new Re(Color.BIANCO));
        EntityFactory.createPezzo(5, 0, new Alfiere(Color.BIANCO));
        EntityFactory.createPezzo(6, 0, new Cavallo(Color.BIANCO));
        EntityFactory.createPezzo(7, 0, new Torre(Color.BIANCO));

        for (int i = 0; i < 8; i++)
            EntityFactory.createPezzo(i, 1, new Pedone(Color.BIANCO));

        EntityFactory.createPezzo(0, 7, new Torre(Color.NERO));
        EntityFactory.createPezzo(1, 7, new Cavallo(Color.NERO));
        EntityFactory.createPezzo(2, 7, new Alfiere(Color.NERO));
        EntityFactory.createPezzo(3, 7, new Regina(Color.NERO));
        EntityFactory.createPezzo(4, 7, new Re(Color.NERO));
        EntityFactory.createPezzo(5, 7, new Alfiere(Color.NERO));
        EntityFactory.createPezzo(6, 7, new Cavallo(Color.NERO));
        EntityFactory.createPezzo(7, 7, new Torre(Color.NERO));
        for (int i = 0; i < 8; i++)
            EntityFactory.createPezzo(i, 6, new Pedone(Color.NERO));
    }

    private void addEntities() {
        createGround();
    }

    private void createGround() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                EntityFactory.createCell(i, j);
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);

    }

    private void initPersCamera() {
        perspectiveCamera = new PerspectiveCamera(FOV,
                LittleTown.VIRTUAL_WIDTH, LittleTown.VIRTUAL_HEIGHT);
        perspectiveCamera.position.set(25f, 50f, 0);
        //  perspectiveCamera.position.set(500f, 500f, 500f);
        perspectiveCamera.lookAt(25f, 0f, 30f);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(perspectiveCamera.combined);

        inputProcessor = new MyInputTracker();
        //    Gdx.input.setInputProcessor(inputProcessor);
        inputProcessor.setCamera(perspectiveCamera);

        inputProcessor.setShapeRenderer(shapeRenderer);
        terrain.setCamera(perspectiveCamera);

    }

    private void addSystems() {
        engine = new Engine();
        engine.addSystem(new RenderSystem(modelBatch, environment));


        Pool.setEngine(engine);

    }

    public void render(float delta) {

        movement();
        renderWorld(delta);
    }

    float angle = 0;
    private void movement() {
        Vector3 position = perspectiveCamera.position;

     //   position = new Vector3(4*Constants.CELL_SIZE,0, 4*Constants.CELL_SIZE);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angle += Math.PI/10;

            perspectiveCamera.rotate(position, -(float) (Math.PI/10));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angle -= Math.PI/10;

            perspectiveCamera.rotate(position, (float) (Math.PI/10));
        }

        perspectiveCamera.update();
    }

    private void movement0() {

        int delta = 50;
        Vector3 position = perspectiveCamera.position;
        //    perspectiveCamera.transform.getTranslation(position);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //    position.z+=delta*Gdx.graphics.getDeltaTime();
            position.x -= delta * SIN * Gdx.graphics.getDeltaTime();
            position.z += delta * SIN * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.x -= delta * SIN * Gdx.graphics.getDeltaTime();
            position.z -= delta * SIN * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.x += delta * SIN * Gdx.graphics.getDeltaTime();
            position.z += delta * SIN * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //   position.z-=delta*Gdx.graphics.getDeltaTime();
            position.x += delta * SIN * Gdx.graphics.getDeltaTime();
            position.z -= delta * SIN * Gdx.graphics.getDeltaTime();
        }
        perspectiveCamera.position.lerp(position, 0.1f);
        perspectiveCamera.update();
    }

    protected void renderWorld(float delta) {
        modelBatch.begin(perspectiveCamera);
        engine.update(delta);
        modelBatch.end();
    }

    public void dispose() {


        modelBatch.dispose();
        modelBatch = null;
    }

    private void initEnvironment() {


        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }

    private void initModelBatch() {
        modelBatch = new ModelBatch();
    }
    /*The ModelBatch is one of the objects, which require disposing, hence we
    add it to the dispose function. */

    /*With the camera set we can now fill in the resize function as well*/
    public void resize(int width, int height) {
        perspectiveCamera.viewportHeight = height;
        perspectiveCamera.viewportWidth = width;
    }
    //and set up the render function with the modelbatch

}
