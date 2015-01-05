package com.purediscovery.vennlayout.ui.options;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:18 PM
 */
public abstract class RenderOption {

    public float viewToScreen = 50;
    public float viewToOrigin = 100;

    public float depthOfCube = 1000;     // cube starts at screen
    public float spacing = 10;
    public float dotSize = 0.5f;


    public abstract Color getColor(int xi, int yi, int depth);



}
