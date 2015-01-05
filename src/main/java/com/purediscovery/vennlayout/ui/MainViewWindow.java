package com.purediscovery.vennlayout.ui;

import com.purediscovery.vennlayout.ui.options.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 */


public class MainViewWindow extends JFrame {

    public MainViewWindow() throws HeadlessException {

        final RenderPanel renderPanel = new RenderPanel();

        this.getContentPane().add(renderPanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0,15));

        addButton(topPanel, renderPanel, "Initial", new OptionStartup());
        addButton(topPanel, renderPanel, "Sinusoidal", new SinColor());
        addButton(topPanel, renderPanel, "XYZ Dist T1", new DistXY(1, 8));
        addButton(topPanel, renderPanel, "XYZ Dist T2", new DistXY(1, 16));
        addButton(topPanel, renderPanel, "XYZ Dist T3", new DistXY(8, 1));
        addButton(topPanel, renderPanel, "XYZ Dist T4", new DistXY(4, 1));
        addButton(topPanel, renderPanel, "XYZ Dist T5", new DistXY(6, 1));
        addButton(topPanel, renderPanel, "XYZ Dist 1", new DistXY(1 / 4f, 1));
        addButton(topPanel, renderPanel, "XYZ Dist 1B", new DistXY(1 / 4f, 1, 0.75f));
        addButton(topPanel, renderPanel, "XYZ Dist 2", new DistXY(1 / 4f, 2));
        addButton(topPanel, renderPanel, "XYZ Dist 3", new DistXY(1 / 2f, 1));
        addButton(topPanel, renderPanel, "XYZ Dist 4", new DistXY(0.25, 0.5));
        addButton(topPanel, renderPanel, "XYZ Dist 5", new DistXY(16f, 1 / 32f));
        addButton(topPanel, renderPanel, "Black", new AllBlack(1, 0.5f, 500));

        addButton(topPanel, renderPanel, "Black 2", new RenderOption() {
            {
                depthOfCube = 250 / 2f;

                spacing = 2.5f;
                dotSize = 0.125f;
            }

            @Override
            public Color getColor(int xi, int yi, int depth) {
                return Color.black;
            }
        });
        addButton(topPanel, renderPanel, "Clear Black", new AllBlack(0.2f,0.75f, 750));


        addButton(topPanel, renderPanel, "Radial", new Radial());
        addButton(topPanel, renderPanel, "Radial Circles", new RadialCircles());
        addButton(topPanel, renderPanel, "Rectangular", new Rectangular());
        addButton(topPanel, renderPanel, "Rectangular 2", new Rectangular2());
        addButton(topPanel, renderPanel, "Depth", new Depth());


        addButton(topPanel, renderPanel, "Y", new RenderOption() {
            @Override
            public Color getColor(int xi, int yi, int depth) {
                float colorSaturation = -yi / 80.0f;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });


        addButton(topPanel, renderPanel, "Sparkles", new RenderOption() {
            final int depthSlices = (int) (depthOfCube / spacing);


            @Override
            public Color getColor(int xi, int yi, int depth) {
                float z = depth / (float) depthSlices;
                float colorSaturation = (xi + yi) / 80.0f / z;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });

        addButton(topPanel, renderPanel, "Sparkles Y", new RenderOption() {
            final int depthSlices = (int) (depthOfCube / spacing);


            @Override
            public Color getColor(int xi, int yi, int depth) {
                float z = depth / (float) depthSlices;
                float colorSaturation = (yi) / 160.0f / z;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });

        addButton(topPanel, renderPanel, "Halo", new RenderOption() {
            final int depthSlices = (int) (depthOfCube / spacing);


            @Override
            public Color getColor(int xi, int yi, int depth) {
                float z = depth / (float) depthSlices;
                float colorSaturation = (float) Math.sqrt(xi * (float) xi + yi * (float) yi) / 160.0f / z;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });
        addButton(topPanel, renderPanel, "Inv", new RenderOption() {
            final int depthSlices = (int) (depthOfCube / spacing);


            @Override
            public Color getColor(int xi, int yi, int depth) {
                float z = depth / (float) depthSlices;
                float colorSaturation = 1 / (1 - z + .1f) / 2;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });
        addButton(topPanel, renderPanel, "Ymod", new RenderOption() {
            @Override
            public Color getColor(int xi, int yi, int depth) {
                float colorSaturation = (yi % 2) / 2.0f;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });

        addButton(topPanel, renderPanel, "Ymod 2", new RenderOption() {
            {
                spacing = 25;
                depthOfCube = 1500;
                dotSize = 1;
            }

            @Override
            public Color getColor(int xi, int yi, int depth) {
                float colorSaturation = (depth % 2) / 2f;
                return Color.getHSBColor(colorSaturation, 1.0f, 1.0f);
            }
        });


        addButton(topPanel, renderPanel, "GCD", new GCD());


        JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new PrintUtil(renderPanel).print();
            }
        });
        topPanel.add(print);


        this.getContentPane().add(topPanel, BorderLayout.NORTH);


        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth()) - 50;
        int ySize = ((int) tk.getScreenSize().getHeight()) - 50;
        this.setSize(xSize, ySize);
        //this.pack();
        this.setLocationRelativeTo(getRootPane());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.getRootPane().setDoubleBuffered(false);
        //RepaintManager.currentManager(this.getRootPane()).setDoubleBufferingEnabled(false);

    }

    private void addButton(JPanel topPanel, final RenderPanel renderPanel, String name, final RenderOption option) {
        JButton optionButton = new JButton(name);
        optionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                renderPanel.setRenderOption(option);
            }
        });
        topPanel.add(optionButton);
    }


    public static void main(String args[]) {
        try {
            MainViewWindow c = new MainViewWindow();
            c.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
