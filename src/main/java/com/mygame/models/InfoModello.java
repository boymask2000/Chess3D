/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.models;

/**
 *
 * @author giovanni
 */
public class InfoModello {
    private String path;
    private float localScale;

    public InfoModello(String path, float localScale) {
        this.path = path;
        this.localScale = localScale;
    }
    
    
    public String getPath() {
        return path;
    }

    public float getLocalScale() {
        return localScale;
    }

    
}
