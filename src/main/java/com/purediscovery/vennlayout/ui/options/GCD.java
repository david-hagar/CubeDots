package com.purediscovery.vennlayout.ui.options;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 11/29/14
 * Time: 1:44 PM
 */


public class GCD extends RenderOption {

    private int depthOffset;

    public GCD() {
        if( viewToScreen % spacing != 0)
            throw new RuntimeException("viewToScreen % spacing != 0");

        depthOffset = Math.round(viewToScreen / spacing);

    }

    @Override
    public Color getColor(int xi, int yi, int depth) {


        int axi = Math.abs(xi);
        int ayi = Math.abs(yi);
        int d = depth + depthOffset;
        int max = Math.min(Math.min( axi, ayi),d);
        float colorSaturation =  gcd(axi, ayi, d) /(float) max *4;
        return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }


    private static long gcd(long a, long b, long c) {

        return gcd(gcd(a, b), c);
    }

}

