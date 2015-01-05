package com.purediscovery.vennlayout.ui.options;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:21 PM

 */


public class AllBlack extends RenderOption {

    private final Color color;
    private final float depthOfCube;

    public AllBlack(float alpha, float dotSize, float depthOfCube) {
        this.depthOfCube = depthOfCube;
        color = new Color(0,0,0,alpha);
        this.dotSize = dotSize;
    }

    @Override
    public Color getColor(int xi, int yi, int depth) {
        return color;
    }
}
