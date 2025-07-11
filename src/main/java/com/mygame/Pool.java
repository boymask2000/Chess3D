/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.mygame.base.walkers.Walker0;
import com.mygame.buildings.edifici.Edificio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class Pool {

    private static final List<Walker0> walkers = new ArrayList<>();
    private static final List<Edificio> edifici = new ArrayList<>();

 

    private static Geometry marker;
    private static Vector3f intersection;

    private static Factory factory;
    private static Terrain terrain;
    private static AppSettings settings;
    private static Node guiNode;
    private static Camera cam;
    private static Node rootNode;
    private static InputManager inputManager;
    private static CommandPanel commandPanel;
    private static AssetManager assetManager;

    public static Vector3f getIntersection() {
        return intersection;
    }

    public static void setIntersection(Vector3f intersection) {
        Pool.intersection = intersection;
    }

    public static CommandPanel getCommandPanel() {
        return commandPanel;
    }

    public static void setCommandPanel(CommandPanel commandPanel) {
        Pool.commandPanel = commandPanel;
    }

    public static Geometry getMarker() {
        return marker;
    }

    public static void setMarker(Geometry marker) {
        Pool.marker = marker;
    }

    public static Terrain getTerrain() {
        return terrain;
    }

    public static void setTerrain(Terrain terrain) {
        Pool.terrain = terrain;
    }

    public static Factory getFactory() {
        return factory;
    }

    public static void setFactory(Factory factory) {
        Pool.factory = factory;
    }

    static void setSettings(AppSettings settings) {
        Pool.settings = settings;
    }

    public static AppSettings getSettings() {
        return settings;
    }

    static void setGuiNode(Node guiNode) {
        Pool.guiNode = guiNode;
    }

    public static Node getGuiNode() {
        return guiNode;
    }

    static void setCam(Camera cam) {
        Pool.cam = cam;
    }

    public static Camera getCam() {
        return cam;
    }

    static void setRootNode(Node rootNode) {
        Pool.rootNode = rootNode;
    }

    public static Node getRootNode() {
        return rootNode;
    }

    static void setInputManager(InputManager inputManager) {
        Pool.inputManager = inputManager;
    }

    public static InputManager getInputManager() {
        return inputManager;
    }

    public static void setAssetManager(AssetManager assetManager) {
        Pool.assetManager = assetManager;
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static List<Walker0> getWalkers() {
        return walkers;
    }

    public static void addWalker(Walker0 k) {

        walkers.add(k);
    }
    public static void addEdificio( Edificio ed ){
        edifici.add(ed);
    }
   public static List<Edificio> getEdifici() {
        return edifici;
    }
}
