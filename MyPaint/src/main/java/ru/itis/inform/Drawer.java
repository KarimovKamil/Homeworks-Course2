package ru.itis.inform;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by Kamil Karimov on 17.11.2016.
 */
public class Drawer extends JComponent {
    private Image image;
    private Image img;
    private Graphics2D graphics2D;
    private int x0, y0, x1, y1;
    private Color currentColor;
    private int width, height;
    private boolean animationActive;
    private JFileChooser jFileChooser;

    public Drawer() {
        jFileChooser = new JFileChooser();
        currentColor = Color.BLACK;
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = sSize.height;
        width = sSize.width;

        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                if (graphics2D != null) {
                    graphics2D.drawLine(x0, y0, x1, y1);
                    repaint();
                    x0 = x1;
                    y0 = y1;
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(width, height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setPaint(currentColor);
        repaint();
    }

    void setColor(Color color) {
        currentColor = color;
        graphics2D.setPaint(color);
    }

    void setSize(int size) {
        graphics2D.setStroke(new BasicStroke(size));
    }

    void fillTheArea(Color color) {
        graphics2D.setPaint(color);
        graphics2D.fill(new Rectangle2D.Double(0, 0, getSize().width, getSize().height));
        repaint();
        graphics2D.setPaint(currentColor);
    }

    void pictureChoose() {
        jFileChooser.showOpenDialog(this);
        File file = jFileChooser.getSelectedFile();
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void pictureFill() {
        if (img != null) {
            graphics2D.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            repaint();
        }
    }

    void affineTransform() {
        clear();
        AffineTransform affineTransform = new AffineTransform(1, 0, 1 / Math.sqrt(3), 1, 0, 0);
        graphics2D.transform(affineTransform);
        addImageForAffine();
        repaint();
    }

    void affineTurn() {
        clear();
        AffineTransform affineTransform = AffineTransform.getRotateInstance(Math.PI / 4, 180, 180);
        graphics2D.transform(affineTransform);
        addImageForAffine();
        repaint();
    }

    void resetAffine() {
        clear();
        AffineTransform affineTransform = new AffineTransform();
        graphics2D.setTransform(affineTransform);
        addImageForAffine();
        repaint();
    }

    void animate() {
        Thread animation = new Thread(() -> {
            animationActive = !animationActive;
            while (animationActive) {
                affineTurn();
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        });
        animation.start();
    }

    private void addImageForAffine() {
        graphics2D.drawImage(img, 180, 180, 120, 110, this);
        repaint();
    }
}
