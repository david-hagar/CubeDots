package com.purediscovery.vennlayout.ui.options;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:21 PM

 */


public class Radial extends RenderOption {
    @Override
    public Color getColor(int xi, int yi, int depth) {
        float colorSaturation = ((float) Point2D.distance(0, 0, xi, yi) / 10f);
        return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
    }
}
