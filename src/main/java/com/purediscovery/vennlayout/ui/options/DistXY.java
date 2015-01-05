package com.purediscovery.vennlayout.ui.options;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:21 PM
 */


public class DistXY extends RenderOption {

    private double zScale;
    private double saturationScale;

    public DistXY(double zScale, double saturationScale) {
        this(zScale, saturationScale, 1);
    }


    public DistXY(double zScale, double saturationScale, float dotSizeAdjust) {
        this.zScale = zScale;
        this.saturationScale = saturationScale;


        dotSize /=dotSizeAdjust;

    }

    @Override
    public Color getColor(int xi, int yi, int depth) {

        //float colorSaturation = (float) Math.sin(Math.max(Math.abs(xi), Math.abs(yi)) / 4)*2;

        float colorSaturation = (float) (Math.sqrt(xi*xi +yi*yi +depth *depth*zScale) * saturationScale);
        return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
    }
}
