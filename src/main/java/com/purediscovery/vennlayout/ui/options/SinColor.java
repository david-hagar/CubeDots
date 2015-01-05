package com.purediscovery.vennlayout.ui.options;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:21 PM
 */


public class SinColor extends RenderOption {
    @Override
    public Color getColor(int xi, int yi, int depth) {

        //float colorSaturation = (float) Math.sin(Math.max(Math.abs(xi), Math.abs(yi)) / 4)*2;

        float colorSaturation = (float) Math.sin(Point2D.distance(0, 0, xi, yi) / 16);
        return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
    }
}
