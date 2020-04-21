/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author linaksel
 */
public class State2D extends JPanel {

    private double[][] dotList;
    private double[][] points;
    private int dots;
    Color color;
    
    public State2D(Color color, int dots) {
        points = new double[3][3];
        this.dots = dots * 1000;
        points[0][0] = 100;
        points[0][1] = 100;
        points[0][2] = 100;
        points[1][0] = -100;
        points[1][1] = -100;
        points[1][2] = 100;
        points[2][0] = -100;
        points[2][1] = 100;
        points[2][2] = -100;
        builder();
        this.color = color;
    }
    
    private void builder() {
        dotList = new double[dots][3];
        Random random = new Random();
        double kx = random.nextInt(100);
        double ky = random.nextInt(100);
        double kz = random.nextInt(100);
        for (int i = 0; i < dots; i++) {
            int luku = random.nextInt(300000) % 3;
            kx = (points[luku][0] + kx) / 2;
            ky = (points[luku][1] + ky) / 2;
            kz = (points[luku][2] + kz) / 2;
            dotList[i][0] = kx;
            dotList[i][1] = ky;
            dotList[i][2] = kz;
        }
    }
    
    public void turnX() {
        double[][] newDots = new double[dots][3]; 
        double[][] newPoints = new double[3][3];
        for (int i = 0; i < dots; i++) {
            newDots[i][0] = dotList[i][0];
            newDots[i][1] = dotList[i][1] * cos(0.1) + dotList[i][2] * (-sin(0.1));
            newDots[i][2] = dotList[i][1] * sin(0.1) + dotList[i][2] * cos(0.1);
        }
        for (int j = 0; j < 3; j++) {
            newPoints[j][0] = points[j][0];
            newPoints[j][1] = points[j][1] * cos(0.1) + points[j][2] * (-sin(0.1));
            newPoints[j][2] = points[j][1] * sin(0.1) + points[j][2] * cos(0.1);
        }
        this.dotList = newDots;
        this.points = newPoints;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void turnY() {
        double[][] newDots = new double[dots][3]; 
        double[][] newPoints = new double[3][3];
        for (int i = 0; i < dots; i++) {
            newDots[i][0] = dotList[i][0] * cos(0.1) + dotList[i][2] * sin(0.1);
            newDots[i][1] = dotList[i][1];
            newDots[i][2] = dotList[i][0] * (-sin(0.1)) + dotList[i][2] * cos(0.1);
        }
        for (int j = 0; j < 3; j++) {
            newPoints[j][0] = points[j][0] * cos(0.1) + points[j][2] * sin(0.1);
            newPoints[j][1] = points[j][1];
            newPoints[j][2] = points[j][0] * (-sin(0.1)) + points[j][2] * cos(0.1);
        }
        this.dotList = newDots;
        this.points = newPoints;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void turnZ() {
        double[][] newDots = new double[dots][3]; 
        double[][] newPoints = new double[3][3];
        for (int i = 0; i < dots; i++) {
            newDots[i][0] = dotList[i][0] * cos(0.1) + dotList[i][1] * (-sin(0.1));
            newDots[i][1] = dotList[i][0] * sin(0.1) + dotList[i][1] * cos(0.1);
            newDots[i][2] = dotList[i][2];
        }
        for (int j = 0; j < 3; j++) {
            newPoints[j][0] = points[j][0] * cos(0.1) + points[j][1] * (-sin(0.1));
            newPoints[j][1] = points[j][0] * sin(0.1) + points[j][1] * cos(0.1);
            newPoints[j][2] = points[j][2];
        }
        this.dotList = newDots;
        this.points = newPoints;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void zoomIn() {
        this.zoomer(1.01);
    }
    
    public void zoomOut() {
        this.zoomer(0.99);
    }
    
    private void zoomer(double multi) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                this.points[j][i] = this.points[j][i] * multi;
            }
        }
        this.builder();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void chosenDots(int dots) {
        this.dots = dots * 1000;
        this.builder();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 1100, 1100);
        g.setColor(color);
        for (int i = 0; i < dots; i++) {
            g.fillRect((int) dotList[i][0] + 400, (int) dotList[i][1] + 400, 1, 1);
        }
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getDots() {
        return this.dots;
    }
    
    public double[][] getDotList() {
        return this.dotList;
    }
}
