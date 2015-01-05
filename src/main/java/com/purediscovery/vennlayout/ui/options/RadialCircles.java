package com.purediscovery.vennlayout.ui.options;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:21 PM

 */


public class RadialCircles extends RenderOption {

    public RadialCircles() {
        //depthOfCube=10;
    }

    @Override
    public Color getColor(int xi, int yi, int depth) {
        float colorSaturation = ((float) Point2D.distance(0, 0, xi, yi)) * 1 % 1.0f;
        return Color.getHSBColor(colorSaturation/2+0.5f,  1.0f, 1.0f );
    }
}
