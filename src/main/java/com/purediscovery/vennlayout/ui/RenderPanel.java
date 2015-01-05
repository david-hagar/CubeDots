package com.purediscovery.vennlayout.ui;


import com.purediscovery.vennlayout.ui.options.OptionStartup;
import com.purediscovery.vennlayout.ui.options.RenderOption;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 */
public class RenderPanel extends JPanel {
    /*
     float viewToScreen = 50;
     float viewToOrigin = 100;

     float depthOfCube = 1000;     // cube starts at screen
     float spacing = 20;
     float dotSize = 0.5f;
      */
    Point2D.Float tmpPoint = new Point2D.Float();
    Rectangle2D.Float tmpRect = new Rectangle2D.Float();
    Ellipse2D.Float tmpEllipse = new Ellipse2D.Float();
    private RenderOption renderOption;

    public RenderPanel() {
        //this.setDoubleBuffered(false);
        //RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);

        renderOption = new OptionStartup();
    }


    private void project(float x, float y, float z, Point2D.Float screenPoint) {

        float k = renderOption.viewToScreen / (renderOption.viewToOrigin + z);
        screenPoint.x = x * k;
        screenPoint.y = y * k;
    }

    private void drawPoint(Graphics2D g, float x, float y, float z, float pointWidth) {
        project(x, y, z, tmpPoint);

        tmpRect.x = tmpPoint.x - pointWidth;
        tmpRect.y = tmpPoint.y - pointWidth;
        tmpRect.width = pointWidth * 2;
        tmpRect.height = pointWidth * 2;

        g.fill(tmpRect);
    }

    private void drawCircle(Graphics2D g, float x, float y, float z, float pointWidth) {
        project(x, y, z, tmpPoint);

        tmpEllipse.x = tmpPoint.x - pointWidth;
        tmpEllipse.y = tmpPoint.y - pointWidth;
        tmpEllipse.width = pointWidth * 2;
        tmpEllipse.height = pointWidth * 2;

        g.fill(tmpEllipse);
    }

    private float calcPointWidth(float z) {
        Point2D.Float p = new Point2D.Float();
        project(renderOption.dotSize, 0, z, p);
        return p.x;
    }

    private Point2D.Double findViewEdge(float depth, double screenX, double screenY) {
        float k = (renderOption.viewToScreen + depth) / renderOption.viewToScreen;
        return new Point2D.Double(screenX / 2 * k, screenY / 2 * k);
    }


    private void draw(Graphics2D g, double screenX, double screenY) {
        int depthSlices = (int) (renderOption.depthOfCube / renderOption.spacing);
        for (int i = depthSlices; i >= 0; i--) {
            float depth = i * renderOption.spacing;
            float z = -renderOption.viewToOrigin + renderOption.viewToScreen + depth;
            float pointWidth = calcPointWidth(z);

            Point2D.Double edge = findViewEdge(depth, screenX, screenY);
            int xCount = (int) (edge.x / renderOption.spacing);
            int yCount = (int) (edge.y / renderOption.spacing);

            for (int xi = -xCount; xi <= xCount; xi++) {
                float x = xi * renderOption.spacing;
                for (int yi = -yCount; yi <= yCount; yi++) {
                    float y = yi * renderOption.spacing;

                    g.setColor(renderOption.getColor(xi, yi, i));
                    drawCircle(g, x, y, z, pointWidth);
                }
            }
        }
    }


    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;


        Dimension d = this.getSize();


        drawAll(g, 0, 0, d.getWidth(), d.getHeight());
    }

    public void setRenderOption(RenderOption renderOption) {
        this.renderOption = renderOption;
        this.repaint();
    }


    public void drawAll(Graphics2D g, double x, double y, double width, double height) {

        RenderingHints renderHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(renderHints);

        g.setColor(Color.WHITE);
        g.fill(new Rectangle2D.Double(0, 0, width, height));

        //g.setColor(Color.BLACK);
        //g.drawLine(0,0,d.width,d.height);

        float virtualScreenHeight = 100;
        g.translate(x+width / 2, y + height / 2);
        double k = height / virtualScreenHeight;
        g.scale(k * 0.95, k * 0.95);

        draw(g, width / k, height / k);
    }
}
