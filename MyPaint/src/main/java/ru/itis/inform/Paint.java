package ru.itis.inform;

import javax.swing.*;
import java.awt.*;

public class Paint {
    private JFrame frame;
    private Container content;
    private JToolBar toolBar;
    private Drawer drawer;

    public Paint() {
        frame = new JFrame("Paint");
        content = new Container();
        toolBar = new JToolBar();
        drawer = new Drawer();
    }

    public void run() {
        toolBar.add(buttonColorChoose());
        toolBar.add(buttonClean());
        toolBar.add(buttonFill());
        toolBar.add(pictureChooseButton());
        toolBar.add(pictureFillButton());
        toolBar.add(affineTransformButton());
        toolBar.add(affineTurnButton());
        toolBar.add(affineResetButton());
        toolBar.add(affineAnimation());
        toolBar.add(sliderDepthChoose());
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

        content.setLayout(new BorderLayout());
        content.add(drawer, BorderLayout.CENTER);
        content.add(toolBar, BorderLayout.NORTH);

        frame.setLocation(100, 100);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(content, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton buttonFill() {
        JButton fill = new JButton("Fill");
        fill.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Choose a Color", null);
            drawer.fillTheArea(color);
        });
        return fill;
    }

    private JButton buttonColorChoose() {
        JButton colorChoose = new JButton("Color");
        colorChoose.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Choose a Color", null);
            drawer.setColor(color);
        });
        return colorChoose;
    }

    private JButton buttonClean() {
        JButton clean = new JButton("Clean");
        clean.addActionListener(e -> drawer.clear());
        return clean;
    }

    private JSlider sliderDepthChoose() {
        JSlider depthChoose = new JSlider(0, 50);
        depthChoose.setValue(1);
        depthChoose.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            drawer.setSize(source.getValue());
        });
        return depthChoose;
    }

    private JButton pictureChooseButton() {
        JButton pictureChooseButton = new JButton("Picture choose");
        pictureChooseButton.addActionListener(e -> drawer.pictureChoose());
        return pictureChooseButton;
    }

    private JButton pictureFillButton() {
        JButton pictureFillButton = new JButton("Picture fill");
        pictureFillButton.addActionListener(e -> drawer.pictureFill());
        return pictureFillButton;
    }

    private JButton affineTransformButton() {
        JButton affineTransformButton = new JButton("Affine transform");
        affineTransformButton.addActionListener(e -> drawer.affineTransform());
        return affineTransformButton;
    }

    private JButton affineTurnButton() {
        JButton affineTurnButton = new JButton("Affine turn");
        affineTurnButton.addActionListener(e -> drawer.affineTurn());
        return affineTurnButton;
    }

    private JButton affineResetButton() {
        JButton affineResetButton = new JButton("Affine reset");
        affineResetButton.addActionListener(e -> drawer.resetAffine());
        return affineResetButton;
    }

    private JButton affineAnimation() {
        JButton affineAnimation = new JButton("Animation");
        affineAnimation.addActionListener(e -> {
            try {
                drawer.animate();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        return affineAnimation;
    }
}
