package com.purediscovery.vennlayout.ui.options;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Depth extends RenderOption {

    private final int depthSlices;

    public Depth() {
        depthSlices = (int) (depthOfCube / spacing);

    }

    @Override
    public Color getColor(int xi, int yi, int depth) {

        //float colorSaturation = (float) Math.sqrt(depth / (float) depthSlices *2);
        //float colorSaturation = (float) Math.sin(depth / (float) depthSlices * Math.PI *2);
        //float colorSaturation = (float) Math.log(1+ depth / (float) depthSlices * 16);
        float colorSaturation = depth / (float) depthSlices;
        //colorSaturation *= colorSaturation * colorSaturation;
        return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);


    }


}
