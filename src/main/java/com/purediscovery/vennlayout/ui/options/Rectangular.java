package com.purediscovery.vennlayout.ui.options;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:30 PM
 */

public class Rectangular extends RenderOption{


    @Override
    public Color getColor(int xi, int yi, int depth) {
        float colorSaturation = (Math.max(Math.abs(xi),Math.abs(yi)))/64.0f ;
        return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
    }


}
